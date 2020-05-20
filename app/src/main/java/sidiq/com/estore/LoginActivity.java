package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseLogin;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView register;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferencedConfig = new SharedPreferencedConfig(this);

        edtUsername = findViewById(R.id.edt_username_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.button_login);
        register = findViewById(R.id.text_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginHandler();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        if (preferencedConfig.getPreferenceIsLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    private void LoginHandler() {

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

        final ProgressDialog progDialog = ProgressDialog.show(LoginActivity.this, "", "Loading..", false);

        ConfigRetrofit.service.Login(username, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                progDialog.dismiss();
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1 ){

                    String levelDb = response.body().getDataLogin().getLevel();
                    if(levelDb.equals("Pelanggan")){
                        Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent intentPelanggan = new Intent(LoginActivity.this, MainActivity.class);
                        intentPelanggan.putExtra(MainActivity.EXTRA_ID_USER, response.body().getDataLogin().getIdUser());
                        intentPelanggan.putExtra(MainActivity.EXTRA_IMAGE_USER, response.body().getDataLogin().getImageUser());
                        intentPelanggan.putExtra(MainActivity.EXTRA_EMAIL_USER, response.body().getDataLogin().getEmail());
                        intentPelanggan.putExtra(MainActivity.EXTRA_NAMA_USER, response.body().getDataLogin().getNamaLengkap());
                        intentPelanggan.putExtra(MainActivity.EXTRA_STATUS_MEMBER, response.body().getDataLogin().getStatusMember());

                        String idUser = response.body().getDataLogin().getIdUser();
                        String username = response.body().getDataLogin().getUsername();
                        String email = response.body().getDataLogin().getEmail();
                        String imageUser = response.body().getDataLogin().getImageUser();
                        String levelUser = response.body().getDataLogin().getLevel();
                        String status_member = response.body().getDataLogin().getStatusMember();
                        String nama_lengkap = response.body().getDataLogin().getNamaLengkap();

                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_ID_USER, idUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_USERNAME, username);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_LENGKAP, nama_lengkap);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_EMAIL, email);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_IMAGE, imageUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_LEVEL_USER, levelUser);
                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_STATUS_MEMBER, status_member);
                        preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, true);
                        startActivity(intentPelanggan);
                        finish();
                        edtUsername.setText("");
                        edtPassword.setText("");
                        edtUsername.requestFocus();
                    }else if(levelDb.equals("Toko")){
                        Toast.makeText(LoginActivity.this, "Ini Adalah Halaman Login Pelanggan, Silahkan Login di halaman TOKO", Toast.LENGTH_SHORT).show();
                        edtUsername.setText("");
                        edtPassword.setText("");
                        edtUsername.requestFocus();
                    }else if (levelDb.equals("Admin")){
                        Toast.makeText(LoginActivity.this, "Admin Tidak Dapat Login Disini...", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                progDialog.dismiss();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, LoginAsActivity.class));
        finish();
    }
}
