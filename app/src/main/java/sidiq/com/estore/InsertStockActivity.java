package sidiq.com.estore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseInsertStock;

public class InsertStockActivity extends AppCompatActivity {

    EditText edtNamaBarang, edtHargaBarang, edtUkuranBarang;
    Button btnPilihGambar, btnInsertStock;
    ImageView imgPilihGambar;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_stock);

        preferencedConfig = new SharedPreferencedConfig(this);

        edtNamaBarang = findViewById(R.id.edt_nama_barang_stock);
        edtHargaBarang = findViewById(R.id.edt_harga_barang_stock);
        edtUkuranBarang = findViewById(R.id.edt_ukuran_barang_stock);
        imgPilihGambar = findViewById(R.id.img_insert_stock);
        btnInsertStock = findViewById(R.id.btn_insert_stock);
        btnPilihGambar = findViewById(R.id.btn_pilih_gambar_stock);

        btnInsertStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDataStock();
            }
        });

        btnPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPilihGambar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void InsertDataStock() {

        String nama_stock = edtNamaBarang.getText().toString();
        String harga_stock = edtHargaBarang.getText().toString();
        String ukuran_stock = edtUkuranBarang.getText().toString();
        String nama_toko = preferencedConfig.getPreferenceIdUser();
        String image_barang = imageToString();

        if (nama_stock.isEmpty()){
            edtNamaBarang.setError("Nama Barang Tidak Boleh Kosong");
            edtNamaBarang.requestFocus();
            return;
        }

        if (harga_stock.isEmpty()){
            edtHargaBarang.setError("Harga Barang Tidak Boleh Kosong");
            edtHargaBarang.requestFocus();
            return;
        }

        if (ukuran_stock.isEmpty()){
            edtUkuranBarang.setError("Ukuran Barang Tidak Boleh Kosong");
            edtUkuranBarang.requestFocus();
            return;
        }

        ConfigRetrofit.service.InsertStock(nama_stock, harga_stock, ukuran_stock, image_barang, nama_toko).enqueue(new Callback<ResponseInsertStock>() {
            @Override
            public void onResponse(Call<ResponseInsertStock> call, Response<ResponseInsertStock> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(InsertStockActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(InsertStockActivity.this, DataStockActivity.class));
                    edtNamaBarang.setText("");
                    edtHargaBarang.setText("");
                    edtUkuranBarang.setText("");
                    imgPilihGambar.setImageResource(R.drawable.logoestore);
                    edtNamaBarang.requestFocus();
                    finish();
                }else{
                    Toast.makeText(InsertStockActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseInsertStock> call, Throwable t) {
                Toast.makeText(InsertStockActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, DataStockActivity.class));
    }
}
