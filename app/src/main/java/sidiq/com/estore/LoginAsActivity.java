package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;

public class LoginAsActivity extends AppCompatActivity {

    Button btnPelanggan, btnToko;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);

        btnPelanggan = findViewById(R.id.button_pelanggan);
        btnToko = findViewById(R.id.button_toko);

        preferencedConfig = new SharedPreferencedConfig(this);

        btnPelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAsActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAsActivity.this, LoginTokoActivity.class));
                finish();
            }
        });

        if (preferencedConfig.getPreferenceIsLogin()){
            String level = preferencedConfig.getPreferenceLevelUser();

            if (level.equals("Pelanggan")){
                startActivity(new Intent(LoginAsActivity.this, MainActivity.class));
                finish();
            }else if (level.equals("Toko")){
                startActivity(new Intent(LoginAsActivity.this, TokoActivity.class));
                finish();
            }
        }
    }
}
