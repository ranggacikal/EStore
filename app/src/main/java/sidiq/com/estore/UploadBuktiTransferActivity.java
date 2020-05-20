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
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseUpdateBuktiTransfer;

public class UploadBuktiTransferActivity extends AppCompatActivity {

    public static final String EXTRA_ID_TRANSAKSI = "extraIdTransaksi";

    ImageView imgUploadTf;
    Button btnPilih, btnUploadTf;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti_transfer);

        imgUploadTf = findViewById(R.id.img_upload_tf);
        btnPilih = findViewById(R.id.btn_pilih_tf);
        btnUploadTf = findViewById(R.id.btn_upload_gambar_tf);

        btnPilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        btnUploadTf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadBuktiTransfer();
            }
        });
    }

    private void UploadBuktiTransfer() {

        String id_transaksi = getIntent().getStringExtra(EXTRA_ID_TRANSAKSI);
        String bukti_transfer = imageToString();

        ConfigRetrofit.service.UpdateBuktiTransfer(id_transaksi, bukti_transfer).enqueue(new Callback<ResponseUpdateBuktiTransfer>() {
            @Override
            public void onResponse(Call<ResponseUpdateBuktiTransfer> call, Response<ResponseUpdateBuktiTransfer> response) {
                int status = response.body().getSukses();
                String pesan = response.body().getPesan();

                if (status == 1){
                    Toast.makeText(UploadBuktiTransferActivity.this, pesan, Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(UploadBuktiTransferActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateBuktiTransfer> call, Throwable t) {
                Toast.makeText(UploadBuktiTransferActivity.this, "Check Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SelectImage() {

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
                imgUploadTf.setImageBitmap(bitmap);
                imgUploadTf.setVisibility(View.VISIBLE);
                btnPilih.setVisibility(View.GONE);
                btnUploadTf.setVisibility(View.VISIBLE);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UploadBuktiTransferActivity.this, StatusPesananActivity.class));
        finish();
    }
}
