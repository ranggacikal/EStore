package sidiq.com.estore;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseRegister;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNamaLengkap, edtUsername, edtEmail, edtPassword, edtRePassword, edtTanggalLahir, edtNoKtp, edtTempatLahir, edtNoTelp;
    Spinner spinnerJenisKelamin;
    CircleImageView imgRegister;
    Button btnRegister, btnPilihGambar;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtNamaLengkap = findViewById(R.id.edt_nama_lengkap);
        edtUsername = findViewById(R.id.edt_username);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtRePassword = findViewById(R.id.edt_repassword);
        btnRegister = findViewById(R.id.button_register);
        imgRegister = findViewById(R.id.img_register);
        btnPilihGambar = findViewById(R.id.button_pilih_gambar);
        edtTanggalLahir = findViewById(R.id.edt_tanggal_lahir);
        edtNoKtp = findViewById(R.id.edt_no_ktp);
        edtTempatLahir = findViewById(R.id.edt_tempat_lahir);
        spinnerJenisKelamin = findViewById(R.id.spinner_jenis_kelamin);
        edtNoTelp = findViewById(R.id.edt_no_telpon);

        dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        edtTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        btnPilihGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PilihGambar();
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
                imgRegister.setImageBitmap(bitmap);
                btnPilihGambar.setVisibility(View.GONE);
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

    private void Register() {

        final String nama_lengkap = edtNamaLengkap.getText().toString();
        final String username = edtUsername.getText().toString();
        final String email = edtEmail.getText().toString();
        final String password = edtPassword.getText().toString();
        final String repassword = edtRePassword.getText().toString();
        final String level = "Pelanggan";
        final String image = imageToString();
        final String no_ktp = edtNoKtp.getText().toString();
        final String no_telpon = edtNoTelp.getText().toString();
        final String tempat_lahir = edtTempatLahir.getText().toString();
        final String tanggal_lahir = edtTanggalLahir.getText().toString();
        final String jenis_kelamin = spinnerJenisKelamin.getSelectedItem().toString();
        final String status_member = "tidak";

        if (nama_lengkap.isEmpty()){
            edtNamaLengkap.setError("Nama Lengkap Tidak Boleh Kosong");
            edtNamaLengkap.requestFocus();
            return;
        }

        if (username.isEmpty()){
            edtUsername.setError("Username Tidak Boeh Kosong");
            edtUsername.requestFocus();
            return;
        }

        if (email.isEmpty()){
            edtEmail.setError("Email Tidak Boleh Kosong");
            edtEmail.requestFocus();
            return;
        }

        if (no_ktp.isEmpty()){
            edtNoKtp.setError("No. KTP Tidak Boleh Kosong");
            edtNoKtp.requestFocus();
            return;
        }

        if (no_telpon.isEmpty()){
            edtNoTelp.setError("No. Telpon Tidak Boleh Kosong");
            edtNoTelp.requestFocus();
            return;
        }

        if (tempat_lahir.isEmpty()){
            edtTempatLahir.setError("Tempat Lahir Tidak Boleh Kosong");
            edtTempatLahir.requestFocus();
            return;
        }

        if (tanggal_lahir.isEmpty()){
            edtTanggalLahir.setError("Tanggal Lahir Tidak Boleh Kosong");
            edtTanggalLahir.requestFocus();
            return;
        }

        if (repassword.isEmpty()){
            edtRePassword.setError("Tidak Boleh Kosong");
            edtRePassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Harap Masukan Email Yang Valid");
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPassword.setError("Password Tidak Boleh Kosong");
            edtPassword.requestFocus();
            return;
        }

        if (!repassword.equals(password)){
            edtRePassword.setError("Password Tidak Sama");
            edtRePassword.requestFocus();
            return;
        }

        final Dialog dialogRegister = new Dialog(RegisterActivity.this);
        dialogRegister.setContentView(R.layout.dialog_konfirmasi_register);
        dialogRegister.setTitle("Checkout");

        final TextView txtRegister = dialogRegister.findViewById(R.id.text_confirm_register);
        final TextView txtCekKembali = dialogRegister.findViewById(R.id.text_cek_kembali);
        dialogRegister.show();

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialogRegist = ProgressDialog.show(RegisterActivity.this, "", "Loading..", false);
                ConfigRetrofit.service.Register(nama_lengkap, username, email, password, level, image, status_member, no_ktp, no_telpon, jenis_kelamin, tempat_lahir, tanggal_lahir).enqueue(new Callback<ResponseRegister>() {
                    @Override
                    public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                        dialogRegist.dismiss();
                        int status = response.body().getStatus();
                        String pesan = response.body().getPesan();

                        if (status == 1){
                            Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            edtNamaLengkap.setText("");
                            edtUsername.setText("");
                            edtEmail.setText("");
                            edtPassword.setText("");
                            edtRePassword.setText("");
                            edtNoKtp.setText("");
                            edtTanggalLahir.setText("");
                            edtTempatLahir.setText("");
                            spinnerJenisKelamin.setSelection(0);
                            imgRegister.setImageResource(R.drawable.logoestore);
                            edtNamaLengkap.requestFocus();
                            dialogRegister.dismiss();
                        }else{
                            Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseRegister> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "CHECK NETWORK...", Toast.LENGTH_SHORT).show();
                        dialogRegist.dismiss();
                    }
                });
            }
        });


    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edtTanggalLahir.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
