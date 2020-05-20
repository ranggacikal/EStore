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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseKonfirmasiBuktiTransfer;

public class LihatBuktiTransferActivity extends AppCompatActivity {

    public static final String EXTRA_ID_TRANSAKSI = "extraIdTransaksi";
    public static final String EXTRA_BUKTI_TF_TRANSAKSI = "extraBuktiTf";
    public static final String EXTRA_STATUS_PESANAN = "extraStatusPesanan";

    TextView txtId;
    ImageView imgBuktiTf;
    Button btnKonfirmasi, btnBatalkanPesanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_bukti_transfer);

        txtId = findViewById(R.id.text_id_bukti);
        imgBuktiTf = findViewById(R.id.img_lihat_bukti);
        btnKonfirmasi = findViewById(R.id.button_konfirmasi_bukti);
        btnBatalkanPesanan = findViewById(R.id.button_batalkan_bukti);

        txtId.setText(getIntent().getStringExtra(EXTRA_ID_TRANSAKSI));

        final String urlImg = getIntent().getStringExtra(EXTRA_BUKTI_TF_TRANSAKSI);
        String statusPesanan = getIntent().getStringExtra(EXTRA_STATUS_PESANAN);

        if (!statusPesanan.equals("Menunggu Toko")){
            btnKonfirmasi.setVisibility(View.GONE);
            btnBatalkanPesanan.setVisibility(View.GONE);
        }

        Glide.with(LihatBuktiTransferActivity.this)
                .load(urlImg)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(imgBuktiTf);

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra(EXTRA_ID_TRANSAKSI);
                String status_pesanan = "Dalam Proses";

                ConfigRetrofit.service.KonfirmasiBukti(id, status_pesanan).enqueue(new Callback<ResponseKonfirmasiBuktiTransfer>() {
                    @Override
                    public void onResponse(Call<ResponseKonfirmasiBuktiTransfer> call, Response<ResponseKonfirmasiBuktiTransfer> response) {
                        int status = response.body().getStatus();
                        String pesan = response.body().getPesan();

                        if (status==1){
                            Toast.makeText(LihatBuktiTransferActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            btnKonfirmasi.setVisibility(View.GONE);
                            btnBatalkanPesanan.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(LihatBuktiTransferActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKonfirmasiBuktiTransfer> call, Throwable t) {
                        Toast.makeText(LihatBuktiTransferActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LihatBuktiTransferActivity.this, PesananTokoActivity.class));
        finish();
    }
}
