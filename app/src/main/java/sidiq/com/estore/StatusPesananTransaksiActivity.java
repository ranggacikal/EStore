package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.ResponseEditStatusPesanan;
import sidiq.com.estore.model.ResponseEditStatusTransaksi;

public class StatusPesananTransaksiActivity extends AppCompatActivity {

    public static final String EXTRA_ID_TRANSAKSI = "extraIdTransaksi";
    public static final String EXTRA_NAMA_PELANGGAN_TRANSAKSI = "extraNamaPelangganTransaksi";
    public static final String EXTRA_LIST_PESANAN_TRANSAKSI = "extraListPesananTransaksi";
    public static final String EXTRA_TOTAL_HARGA_TRANSAKSI = "extraTotalHargaTransaksi";
    public static final String EXTRA_METODE_PEMBAYARAN_TRANSAKSI = "extraMetodePembayaranTransaksi";
    public static final String EXTRA_METODE_PENGAMBILAN_TRANSAKSI = "extraMetodePengambilanTransaksi";
    public static final String EXTRA_ALAMAT_TRANSAKSI = "extraAlamatTransaksi";
    public static final String EXTRA_STATUS_PESANAN_TRANSAKSI = "extraStatusPesananTransaksi";
    public static final String EXTRA_STATUS_TRANSAKSI = "extraStatusTransaksi";
    public static final String EXTRA_TANGGAL_TRANSAKSI = "extraTanggalTransaksi";

    TextView txtIdTransaksi, txtNamaPelanggan, txtListPesanan, txtTotalHarga, txtMetodeBayar, txtMetodeAmbil, txtAlamat,
    txtStatusPesanan, txtStatusTransaksi, txtTanggalTransaksi;

    Spinner spinnerStatusPesanan;
    Button btnEditPesanan, btnSelesaiTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pesanan_transaksi);

        txtIdTransaksi = findViewById(R.id.edit_id_transaksi);
        txtNamaPelanggan = findViewById(R.id.edit_nama_transaksi);
        txtListPesanan = findViewById(R.id.edit_list_transaksi);
        txtTotalHarga = findViewById(R.id.edit_total_transaksi);
        txtMetodeBayar = findViewById(R.id.edit_metode_bayar);
        txtMetodeAmbil = findViewById(R.id.edit_metode_ambil);
        txtAlamat = findViewById(R.id.edit_alamat_transaksi);
        txtStatusPesanan = findViewById(R.id.edit_status_pesanan_transaksi);
        txtStatusTransaksi = findViewById(R.id.edit_status_transaksi);
        txtTanggalTransaksi = findViewById(R.id.edit_tanggal_transaksi);
        spinnerStatusPesanan = findViewById(R.id.spinner_status_pesanan);
        btnEditPesanan = findViewById(R.id.btn_edit_status_pesanan);
        btnSelesaiTransaksi = findViewById(R.id.btn_selesai_transaksi);

        txtIdTransaksi.setText(getIntent().getStringExtra(EXTRA_ID_TRANSAKSI));
        txtNamaPelanggan.setText(getIntent().getStringExtra(EXTRA_NAMA_PELANGGAN_TRANSAKSI));
        txtListPesanan.setText(getIntent().getStringExtra(EXTRA_LIST_PESANAN_TRANSAKSI));

        Locale localId = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localId);
        int total = Integer.parseInt(getIntent().getStringExtra(EXTRA_TOTAL_HARGA_TRANSAKSI));
        txtTotalHarga.setText(formatRp.format(total));

        txtMetodeBayar.setText(getIntent().getStringExtra(EXTRA_METODE_PEMBAYARAN_TRANSAKSI));
        txtMetodeAmbil.setText(getIntent().getStringExtra(EXTRA_METODE_PENGAMBILAN_TRANSAKSI));
        txtAlamat.setText(getIntent().getStringExtra(EXTRA_ALAMAT_TRANSAKSI));
        txtStatusPesanan.setText(getIntent().getStringExtra(EXTRA_STATUS_PESANAN_TRANSAKSI));
        txtStatusTransaksi.setText(getIntent().getStringExtra(EXTRA_STATUS_TRANSAKSI));
        txtTanggalTransaksi.setText(getIntent().getStringExtra(EXTRA_TANGGAL_TRANSAKSI));

        btnEditPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditStatusPesanan();
            }
        });

        btnSelesaiTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditStatusTransaksi();
            }
        });
    }

    private void EditStatusTransaksi() {
        String id_transaksi = getIntent().getStringExtra(EXTRA_ID_TRANSAKSI);
        String status_transaksi = "Selesai";

        final Dialog dialogTransaksi = new Dialog(StatusPesananTransaksiActivity.this);
        dialogTransaksi.setContentView(R.layout.dialog_confirm_transaksi);
        dialogTransaksi.setTitle("Checkout");

        final TextView txtTransaksi = dialogTransaksi.findViewById(R.id.text_confirm_transaksi);
        final TextView txtCekKembali = dialogTransaksi.findViewById(R.id.text_batal_transaksi);
        dialogTransaksi.show();

        txtTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progDialog = ProgressDialog.show(StatusPesananTransaksiActivity.this, "", "Loading..", false);
                ConfigRetrofit.service.EditStatusTransaksi(id_transaksi, status_transaksi).enqueue(new Callback<ResponseEditStatusTransaksi>() {
                    @Override
                    public void onResponse(Call<ResponseEditStatusTransaksi> call, Response<ResponseEditStatusTransaksi> response) {
                        int status = response.body().getStatus();
                        String pesan = response.body().getPesan();
                        progDialog.dismiss();
                        if(status == 1){
                            Toast.makeText(StatusPesananTransaksiActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            dialogTransaksi.dismiss();
                        }else{
                            Toast.makeText(StatusPesananTransaksiActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            dialogTransaksi.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEditStatusTransaksi> call, Throwable t) {
                        Toast.makeText(StatusPesananTransaksiActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        progDialog.dismiss();
                    }
                });
            }
        });

        txtCekKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTransaksi.dismiss();
            }
        });
    }

    private void EditStatusPesanan() {
        final String status_pesanan = spinnerStatusPesanan.getSelectedItem().toString();
        String id_transaksi = getIntent().getStringExtra(EXTRA_ID_TRANSAKSI);

        ConfigRetrofit.service.EditStatusPesanan(id_transaksi, status_pesanan).enqueue(new Callback<ResponseEditStatusPesanan>() {
            @Override
            public void onResponse(Call<ResponseEditStatusPesanan> call, Response<ResponseEditStatusPesanan> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();
                
                if (status == 1){
                    Toast.makeText(StatusPesananTransaksiActivity.this, pesan+" "+status_pesanan, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StatusPesananTransaksiActivity.this, PesananTokoActivity.class));
                    finish();
                }else{
                    Toast.makeText(StatusPesananTransaksiActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseEditStatusPesanan> call, Throwable t) {
                Toast.makeText(StatusPesananTransaksiActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StatusPesananTransaksiActivity.this, PesananTokoActivity.class));
        finish();
    }
}
