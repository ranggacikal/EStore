package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseLogin;

public class LoginTokoActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView register;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_toko);

        edtUsername = findViewById(R.id.edt_username_login_toko);
        edtPassword = findViewById(R.id.edt_password_login_toko);
        btnLogin = findViewById(R.id.button_login_toko);
        register = findViewById(R.id.text_register_toko);

        preferencedConfig = new SharedPreferencedConfig(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerLoginToko();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginTokoActivity.this, RegisterTokoActivity.class));
                finish();
            }
        });

        if (preferencedConfig.getPreferenceIsLogin()){
            startActivity(new Intent(LoginTokoActivity.this, TokoActivity.class));
            finish();
        }
    }

    private void HandlerLoginToko() {

        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();



        if (username.isEmpty()){
            edtUsername.setError("username tidak boleh kosong");
            edtUsername.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPassword.setError("password tidak boleh kosong");
            edtPassword.requestFocus();
            return;
        }

        final ProgressDialog progDialog = ProgressDialog.show(LoginTokoActivity.this, "", "Loading..", false);

        ConfigRetrofit.service.Login(username, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progDialog.dismiss();
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1 ){

                    String levelDb = response.body().getDataLogin().getLevel();
                    if(levelDb.equals("Toko")){
                        Toast.makeText(LoginTokoActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent intentToko = new Intent(LoginTokoActivity.this, TokoActivity.class);

                        String idUser = response.body().getDataLogin().getIdUser();
                        String username = response.body().getDataLogin().getUsername();
                        String email = response.body().getDataLogin().getEmail();
                        String imageUser = response.body().getDataLogin().getImageUser();
                        String levelUser = response.body().getDataLogin().getLevel();

                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_USER, idUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USERNAME, username);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, email);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE, imageUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_LEVEL_USER, levelUser);
                        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                        startActivity(intentToko);
                        finish();
                        edtUsername.setText("");
                        edtPassword.setText("");
                        edtUsername.requestFocus();
                    }else if(levelDb.equals("Pelanggan")){
                        Toast.makeText(LoginTokoActivity.this, "Ini Adalah Halaman Login Toko, Silahkan Login di halaman Pelanggan", Toast.LENGTH_LONG).show();
                        edtUsername.setText("");
                        edtPassword.setText("");
                        edtUsername.requestFocus();
                    }else if (levelDb.equals("Admin")){
                        Toast.makeText(LoginTokoActivity.this, "Admin Tidak Dapat Login Disini...", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginTokoActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginTokoActivity.this, "Check Network..", Toast.LENGTH_SHORT).show();
                progDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginTokoActivity.this, LoginAsActivity.class));
        finish();
    }
}
