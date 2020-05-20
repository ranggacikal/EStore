package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseDaftarMember;

public class MemberPelangganActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_NAMA_LENGKAP = "extra_nama_lengkap";
    public static final String EXTRA_EMAIL = "extra_email";
    public static final String EXTRA_ID = "extra_id";

    TextView txtNamaLengkap, txtEmail;
    EditText edtNoTelepon;
    Button btnDaftarMember;
    ImageView imgDaftar;

    String id_user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_pelanggan);
        setTitle("Daftar Member");

        txtNamaLengkap = findViewById(R.id.text_nama_daftar_member);
        txtEmail = findViewById(R.id.text_email_daftar_member);
        edtNoTelepon = findViewById(R.id.edt_no_telpon);
        btnDaftarMember = findViewById(R.id.button_daftar_member);
        imgDaftar = findViewById(R.id.img_daftar_member);

        id_user = getIntent().getStringExtra(EXTRA_ID);
        final String nama_lengkap = getIntent().getStringExtra(EXTRA_NAMA_LENGKAP);
        final String email = getIntent().getStringExtra(EXTRA_EMAIL);
        final String image = getIntent().getStringExtra(EXTRA_IMAGE);

        Glide.with(MemberPelangganActivity.this)
                .load(image)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(imgDaftar);

        txtNamaLengkap.setText(nama_lengkap);
        txtEmail.setText(email);

        btnDaftarMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no_telpon = edtNoTelepon.getText().toString();
                String status_member = "tidak";
                ConfigRetrofit.service.DaftarMember(id_user, no_telpon, status_member).enqueue(new Callback<ResponseDaftarMember>() {
                    @Override
                    public void onResponse(Call<ResponseDaftarMember> call, Response<ResponseDaftarMember> response) {
                        int status = response.body().getSukses();
                        String pesan = response.body().getPesan();

                        if (status == 1){

                            final Dialog dialogBerhasil = new Dialog(MemberPelangganActivity.this);
                            dialogBerhasil.setContentView(R.layout.dialog_berhasil_daftar);
                            dialogBerhasil.setTitle("Berhasil");
                            TextView tutup = dialogBerhasil.findViewById(R.id.text_berhasil_tutup);
                            dialogBerhasil.show();

                            tutup.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogBerhasil.dismiss();
                                }
                            });

                        }else{
                            Toast.makeText(MemberPelangganActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDaftarMember> call, Throwable t) {
                        Toast.makeText(MemberPelangganActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
