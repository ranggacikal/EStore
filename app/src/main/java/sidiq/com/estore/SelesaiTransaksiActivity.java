package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SelesaiTransaksiActivity extends AppCompatActivity {

    public static final String EXTRA_ID_TRANSAKSI = "extraIdTransaksi";
    public static final String EXTRA_NAMA_PELANGGAN_TRANSAKSI = "extraNamaPelangganTransaksi";
    public static final String EXTRA_LIST_PESANAN_TRANSAKSI = "extraListPesananTransaksi";
    public static final String EXTRA_TOTAL_HARGA_TRANSAKSI = "extraTotalHargaTransaksi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_transaksi);
    }
}
