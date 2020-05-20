package sidiq.com.estore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseInsertPengaduan;

public class PengaduanActivity extends AppCompatActivity {

    @BindView(R.id.text_nama_pengadu)
    TextView textNamaPengadu;
    @BindView(R.id.edt_aduan)
    EditText edtAduan;
    @BindView(R.id.button_pengaduan)
    Button buttonPengaduan;
    @BindView(R.id.button_lihat_response)
    Button buttonLihatResponse;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        textNamaPengadu.setText(preferencedConfig.getPreferenceNamaLengkap());

        buttonPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_pengadu = preferencedConfig.getPreferenceNamaLengkap();
                String pengaduan = edtAduan.getText().toString();
                String id_pelanggan = preferencedConfig.getPreferenceIdUser();

                ConfigRetrofit.service.InsertPengaduan(nama_pengadu, pengaduan, id_pelanggan).enqueue(new Callback<ResponseInsertPengaduan>() {
                    @Override
                    public void onResponse(Call<ResponseInsertPengaduan> call, Response<ResponseInsertPengaduan> response) {
                        int status = response.body().getStatus();
                        String pesan = response.body().getPesan();

                        if (status == 1){
                            Toast.makeText(PengaduanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            edtAduan.setText("");
                        }else{
                            Toast.makeText(PengaduanActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseInsertPengaduan> call, Throwable t) {
                        Toast.makeText(PengaduanActivity.this, "ERROR : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonLihatResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PengaduanActivity.this, ResponsePengaduanActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PengaduanActivity.this, MainActivity.class));
        finish();
    }
}
