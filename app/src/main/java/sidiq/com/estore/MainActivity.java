package sidiq.com.estore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STATUS_MEMBER = "extra_status_member";
    public static final String EXTRA_IMAGE_USER = "extra_image_user";
    public static final String EXTRA_EMAIL_USER = "extra_email_user";
    public static final String EXTRA_NAMA_USER = "extra_nama_user";
    public static final String EXTRA_ID_USER = "extra_id_user";

    CardView cardPesanBarang, cardPengaduan, cardTransaksi, cardStatusPesanan;

    private SharedPreferencedConfig preferencedConfig;

    String member = "";
    String imageUser = "";
    String emailUser = "";
    String namaUser = "";
    String idUser = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencedConfig = new SharedPreferencedConfig(this);

        cardPesanBarang = findViewById(R.id.card_daftar_toko);
        cardStatusPesanan = findViewById(R.id.card_status_pesanan);
        cardTransaksi = findViewById(R.id.card_transaksi_pelanggan);
        cardPengaduan = findViewById(R.id.card_pengaduan_user);

        cardStatusPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatusPesananActivity.class));
            }
        });

        cardPesanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerPesanBarang();
            }
        });

        cardTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TransaksiPelangganActivity.class));
                finish();
            }
        });

        cardPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PengaduanActivity.class));
                finish();
            }
        });
    }

    private void HandlerPesanBarang() {
        member = preferencedConfig.getPreferenceStatusMember();
        if (member.equals("tidak")||member.equals("")){
            final Dialog dialogNotMember = new Dialog(MainActivity.this);
            dialogNotMember.setContentView(R.layout.dialog_not_member);
            dialogNotMember.setTitle("PERINGATAN");
            dialogNotMember.show();

            final TextView closeDialog = dialogNotMember.findViewById(R.id.text_dialog_tutup);
            closeDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogNotMember.dismiss();
                }
            });
        }else if(member.equals("iya")){
            Toast.makeText(MainActivity.this, "Anda Terdaftar Member, Silahkan Berbelanja", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, DaftarTokoActivity.class));
        }
    }

    private void HandleMember() {
        namaUser = preferencedConfig.getPreferenceUsername();
        imageUser = preferencedConfig.getPreferenceImage();
        emailUser = preferencedConfig.getPreferenceEmail();
        idUser = preferencedConfig.getPreferenceIdUser();
        member = preferencedConfig.getPreferenceStatusMember();

        if(member.equals("tidak") || member.equals("")){
            Intent pindahMember = new Intent(MainActivity.this, MemberPelangganActivity.class);
            pindahMember.putExtra(MemberPelangganActivity.EXTRA_NAMA_LENGKAP, namaUser);
            pindahMember.putExtra(MemberPelangganActivity.EXTRA_EMAIL, emailUser);
            pindahMember.putExtra(MemberPelangganActivity.EXTRA_IMAGE, imageUser);
            pindahMember.putExtra(MemberPelangganActivity.EXTRA_ID, idUser);
            startActivity(pindahMember);
        }else if(member.equals("iya")){
            final Dialog dialogIsMember = new Dialog(MainActivity.this);
            dialogIsMember.setContentView(R.layout.dialog_is_member);
            dialogIsMember.setTitle("PERINGATAN");
            dialogIsMember.show();

            final TextView closeDialog = dialogIsMember.findViewById(R.id.text_isMember_tutup);
            closeDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogIsMember.dismiss();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        MenuItem menuItem = menu.findItem(R.id.profile_action);
        menuItem.setIcon(R.drawable.ic_profile);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.profile_action:
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
