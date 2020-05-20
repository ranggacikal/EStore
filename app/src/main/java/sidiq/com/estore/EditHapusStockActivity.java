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
import sidiq.com.estore.model.ResponseHapusStock;
import sidiq.com.estore.model.ResponseUpdateStock;

public class EditHapusStockActivity extends AppCompatActivity {

    public static final String EXTRA_ID_STOCK = "extraIdStock";
    public static final String EXTRA_NAMA_STOCK = "extraNamaStock";
    public static final String EXTRA_HARGA_STOCK = "extraHargaStock";
    public static final String EXTRA_UKURAN_STOCK = "extraUkuranStock";
    public static final String EXTRA_IMAGE_STOCK = "extraImageStock";
    public static final String EXTRA_NAMA_TOKO_STOCK = "extraNamaTokoStock";

    EditText edtNamaBarang, edtHargaBarang, edtUkuranBarang;
    ImageView imgStock;
    Button btnEditStock, btnHapusStock;

    String id_stock = "";
    String  nama_toko = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hapus_stock);

        edtNamaBarang = findViewById(R.id.edt_nama_barang_edit);
        edtHargaBarang = findViewById(R.id.edt_harga_barang_edit);
        edtUkuranBarang = findViewById(R.id.edt_ukuran_barang_edit);
        btnEditStock = findViewById(R.id.btn_edit_stock);
        btnHapusStock = findViewById(R.id.btn_hapus_stock);
        imgStock = findViewById(R.id.img_barang_edit);

        String nama_intent = getIntent().getStringExtra(EXTRA_NAMA_STOCK);
        String harga_intent = getIntent().getStringExtra(EXTRA_HARGA_STOCK);
        String ukuran_intent = getIntent().getStringExtra(EXTRA_UKURAN_STOCK);
        final String img_intent = getIntent().getStringExtra(EXTRA_IMAGE_STOCK);

        edtNamaBarang.setText(nama_intent);
        edtHargaBarang.setText(harga_intent);
        edtUkuranBarang.setText(ukuran_intent);

        Glide.with(EditHapusStockActivity.this)
                .load(img_intent)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(imgStock);

        btnEditStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditStockHandler();
            }
        });

        btnHapusStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HapusStockHandler();
            }
        });
    }

    private void HapusStockHandler() {
        id_stock = getIntent().getStringExtra(EXTRA_ID_STOCK);

        ConfigRetrofit.service.HapusStock(id_stock).enqueue(new Callback<ResponseHapusStock>() {
            @Override
            public void onResponse(Call<ResponseHapusStock> call, Response<ResponseHapusStock> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(EditHapusStockActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditHapusStockActivity.this, DataStockActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseHapusStock> call, Throwable t) {

            }
        });
    }

    private void EditStockHandler() {
        String nama_barang = edtNamaBarang.getText().toString();
        String harga_barang = edtHargaBarang.getText().toString();
        String ukuran_barang = edtUkuranBarang.getText().toString();
        String image_barang = getIntent().getStringExtra(EXTRA_IMAGE_STOCK);
        id_stock = getIntent().getStringExtra(EXTRA_ID_STOCK);
        nama_toko = getIntent().getStringExtra(EXTRA_NAMA_TOKO_STOCK);

        ConfigRetrofit.service.UpdateStock(id_stock, nama_barang, harga_barang, ukuran_barang, image_barang, nama_toko).enqueue(new Callback<ResponseUpdateStock>() {
            @Override
            public void onResponse(Call<ResponseUpdateStock> call, Response<ResponseUpdateStock> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(EditHapusStockActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditHapusStockActivity.this, DataStockActivity.class));
                    finish();
                }else{
                    Toast.makeText(EditHapusStockActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateStock> call, Throwable t) {
                Toast.makeText(EditHapusStockActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, DataStockActivity.class));
    }
}
