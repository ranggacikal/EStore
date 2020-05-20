package sidiq.com.estore;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseRegisterToko;

public class RegisterTokoActivity extends AppCompatActivity {


    @BindView(R.id.edt_nama_toko)
    EditText edtNamaToko;
    @BindView(R.id.edt_username_toko)
    EditText edtUsernameToko;
    @BindView(R.id.edt_email_toko)
    EditText edtEmailToko;
    @BindView(R.id.edt_password_toko)
    EditText edtPasswordToko;
    @BindView(R.id.edt_repassword_toko)
    EditText edtRepasswordToko;
    @BindView(R.id.edt_alamat_toko)
    EditText edtAlamatToko;
    @BindView(R.id.img_register_toko)
    ImageView imgRegisterToko;
    @BindView(R.id.button_pilih_gambar_toko)
    Button btnPilihGambarToko;
    @BindView(R.id.button_register_toko)
    Button btnRegisterToko;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_toko);
        ButterKnife.bind(this);

        btnPilihGambarToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
            }
        });

        btnRegisterToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterToko();
            }
        });
    }

    private void RegisterToko() {
        final String nama_toko = edtNamaToko.getText().toString();
        final String username = edtUsernameToko.getText().toString();
        final String email = edtEmailToko.getText().toString();
        final String password = edtPasswordToko.getText().toString();
        final String repassword = edtRepasswordToko.getText().toString();
        final String alamat_toko = edtAlamatToko.getText().toString();
        final String level = "Toko";
        final String image = imageToString();

        if (nama_toko.isEmpty()){
            edtNamaToko.setError("Nama Toko Tidak Boleh Kosong");
            edtNamaToko.requestFocus();
            return;
        }

        if (username.isEmpty()){
            edtUsernameToko.setError("Username Tidak Boeh Kosong");
            edtUsernameToko.requestFocus();
            return;
        }

        if (email.isEmpty()){
            edtEmailToko.setError("Email Tidak Boleh Kosong");
            edtEmailToko.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmailToko.setError("Harap Masukan Email Yang Valid");
            edtEmailToko.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPasswordToko.setError("Password Tidak Boleh Kosong");
            edtPasswordToko.requestFocus();
            return;
        }

        if (!repassword.equals(password)){
            edtRepasswordToko.setError("Password Tidak Sama");
            edtRepasswordToko.requestFocus();
            return;
        }

        if (alamat_toko.isEmpty()){
            edtAlamatToko.setError("Alamat Toko Tidak Boleh Kosong");
            edtAlamatToko.requestFocus();
            return;
        }

        final Dialog dialogRegisterToko = new Dialog(RegisterTokoActivity.this);
        dialogRegisterToko.setContentView(R.layout.dialog_confirm_register_toko);
        dialogRegisterToko.setTitle("Checkout");

        final TextView txtRegisterToko = dialogRegisterToko.findViewById(R.id.text_confirm_register_toko);
        final TextView txtCekKembaliToko = dialogRegisterToko.findViewById(R.id.text_cek_kembali_toko);
        dialogRegisterToko.show();

        txtRegisterToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progDialog = ProgressDialog.show(RegisterTokoActivity.this, "", "Loading..", false);
                ConfigRetrofit.service.RegisterToko(nama_toko, username, email, password, level, image, alamat_toko).enqueue(new Callback<ResponseRegisterToko>() {
                    @Override
                    public void onResponse(Call<ResponseRegisterToko> call, Response<ResponseRegisterToko> response) {
                        progDialog.dismiss();
                        int status = response.body().getStatus();
                        String pesan = response.body().getPesan();

                        if (status == 1){
                            Toast.makeText(RegisterTokoActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            edtNamaToko.setText("");
                            edtUsernameToko.setText("");
                            edtEmailToko.setText("");
                            edtPasswordToko.setText("");
                            edtRepasswordToko.setText("");
                            edtAlamatToko.setText("");
                            imgRegisterToko.setImageResource(R.drawable.logoestore);
                            progDialog.dismiss();
                            dialogRegisterToko.dismiss();
                        }else{
                            Toast.makeText(RegisterTokoActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            progDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegisterToko> call, Throwable t) {
                        Toast.makeText(RegisterTokoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        progDialog.dismiss();
                    }
                });
            }
        });

        txtCekKembaliToko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogRegisterToko.dismiss();
            }
        });
    }

    private void PilihGambar() {

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
                imgRegisterToko.setImageBitmap(bitmap);
                btnPilihGambarToko.setVisibility(View.GONE);
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
        startActivity(new Intent(RegisterTokoActivity.this, LoginTokoActivity.class));
        finish();
    }
}
