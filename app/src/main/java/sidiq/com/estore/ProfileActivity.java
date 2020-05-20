package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;

public class ProfileActivity extends AppCompatActivity {

    private SharedPreferencedConfig preferencedConfig;

    TextView txtNamaLengkap, txtEmail;
    CircleImageView imgProfile;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        preferencedConfig = new SharedPreferencedConfig(this);

        txtNamaLengkap = findViewById(R.id.text_nama_profile);
        txtEmail = findViewById(R.id.text_email_profile);
        btnLogout = findViewById(R.id.btn_logout);
        imgProfile = findViewById(R.id.img_profile);

        txtNamaLengkap.setText(preferencedConfig.getPreferenceUsername());
        txtEmail.setText(preferencedConfig.getPreferenceEmail());

        final String urlImg = preferencedConfig.getPreferenceImage();

        Glide.with(this)
                .load(urlImg)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(imgProfile);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Logged Out..", Toast.LENGTH_SHORT).show();
                preferencedConfig.savePrefBoolean(SharedPreferencedConfig.PREFERENCE_IS_LOGIN, false);
                startActivity(new Intent(ProfileActivity.this, LoginAsActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String level = preferencedConfig.getPreferenceLevelUser();

        if (level.equals("Pelanggan")){
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finish();
        }else if(level.equals("Toko")){
            startActivity(new Intent(ProfileActivity.this, TokoActivity.class));
            finish();
        }
    }
}
