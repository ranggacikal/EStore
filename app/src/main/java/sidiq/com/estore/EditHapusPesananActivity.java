package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseDeletePesanan;
import sidiq.com.estore.model.ResponseEditPesanan;

public class EditHapusPesananActivity extends AppCompatActivity {

    public static final  String EXTRA_ID_PESANAN = "extraIdPesanan";
    public static final String EXTRA_QTY_PESANAN = "extraQtyPesanan";
    public static final String EXTRA_IMAGE_PESANAN = "extraImagePesanan";
    public static final String EXTRA_HARGA_SATUAN_PESANAN = "extraHargaSatuanPesanan";

    EditText edtQtyEdit;
    ImageView imgPesanan;
    Button btnEdit, btnHapus;

    String id_pesanan = "";
    String qty_pesanan = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hapus_pesanan);

        edtQtyEdit = findViewById(R.id.edt_qty_edit);
        imgPesanan = findViewById(R.id.img_pesanan_edit);
        btnEdit = findViewById(R.id.btn_edit_pesanan);
        btnHapus = findViewById(R.id.btn_hapus_pesanan);

        id_pesanan = getIntent().getStringExtra(EXTRA_ID_PESANAN);
        qty_pesanan = getIntent().getStringExtra(EXTRA_QTY_PESANAN);
        final String image_pesanan = getIntent().getStringExtra(EXTRA_IMAGE_PESANAN);

        edtQtyEdit.setText(qty_pesanan);

        Glide.with(EditHapusPesananActivity.this)
                .load(image_pesanan)
                .placeholder(R.drawable.logoestore)
                .into(imgPesanan);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPesanan();
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HapusPesanan();
            }
        });
    }

    private void HapusPesanan() {

        ConfigRetrofit.service.DeletePesanan(id_pesanan).enqueue(new Callback<ResponseDeletePesanan>() {
            @Override
            public void onResponse(Call<ResponseDeletePesanan> call, Response<ResponseDeletePesanan> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    startActivity(new Intent(EditHapusPesananActivity.this, PesananActivity.class));
                    Toast.makeText(EditHapusPesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditHapusPesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDeletePesanan> call, Throwable t) {

            }
        });
    }

    private void EditPesanan() {

        String qty = edtQtyEdit.getText().toString();
        int qtyEdit = Integer.parseInt(qty);
        int hargaSatuan = Integer.parseInt(getIntent().getStringExtra(EXTRA_HARGA_SATUAN_PESANAN));
        int total_harga = qtyEdit * hargaSatuan;

        ConfigRetrofit.service.EditPesanan(id_pesanan, qty, total_harga).enqueue(new Callback<ResponseEditPesanan>() {
            @Override
            public void onResponse(Call<ResponseEditPesanan> call, Response<ResponseEditPesanan> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status==1){
                    startActivity(new Intent(EditHapusPesananActivity.this, PesananActivity.class));
                    Toast.makeText(EditHapusPesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditHapusPesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEditPesanan> call, Throwable t) {
                Toast.makeText(EditHapusPesananActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditHapusPesananActivity.this, PesananActivity.class));
        finish();
    }
}
