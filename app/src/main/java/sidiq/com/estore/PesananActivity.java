package sidiq.com.estore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.adapter.OrderAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.CheckoutOrderItem;
import sidiq.com.estore.model.DataOrderItem;
import sidiq.com.estore.model.ResponseCheckoutOrder;
import sidiq.com.estore.model.ResponseDataOrder;
import sidiq.com.estore.model.ResponseDeleteOrder;
import sidiq.com.estore.model.ResponseInsertCheckout;
import sidiq.com.estore.model.ResponseTotalOrder;

public class PesananActivity extends AppCompatActivity {

    RecyclerView rvPesanan;
    TextView txtTotal;
    Button btnCheckout;

    String dataBarang;
    String totalHarga;

    private SharedPreferencedConfig preferencedConfig;

    public static final String EXTRA_NAMA_PELANGGAN = "extraNamaPelanggan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        preferencedConfig = new SharedPreferencedConfig(this);

        rvPesanan = findViewById(R.id.recycler_pesanan);
        txtTotal = findViewById(R.id.text_total_order);
        btnCheckout = findViewById(R.id.button_checkout);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandlerButtonCheckout();
            }
        });

        GetDataOrder();

        LoadRecyclerPesanan();

        SetTotalOrder();
    }

    private void GetDataOrder() {

        String nama_pelanggan = preferencedConfig.getPreferenceIdUser();

        ConfigRetrofit.service.CheckoutOrder(nama_pelanggan).enqueue(new Callback<ResponseCheckoutOrder>() {
            @Override
            public void onResponse(Call<ResponseCheckoutOrder> call, Response<ResponseCheckoutOrder> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<CheckoutOrderItem> checkoutOrderItems = response.body().getCheckoutOrder();
                    dataBarang = StringUtils.join(checkoutOrderItems, "\n");

                }else{
                    Toast.makeText(PesananActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCheckoutOrder> call, Throwable t) {

            }
        });
    }

    private void HandlerButtonCheckout() {

        final Dialog dialogCheckout = new Dialog(PesananActivity.this);
        dialogCheckout.setContentView(R.layout.dialog_checkout);
        dialogCheckout.setTitle("Checkout");

        final Spinner spinnerPembayaran = dialogCheckout.findViewById(R.id.spinner_metode_pembayaran);
        final Spinner spinnerPengambilan = dialogCheckout.findViewById(R.id.spinner_metode_pengambilan);
        final EditText edtAlamat = dialogCheckout.findViewById(R.id.edt_alamat_checkout);
        final TextView checkout = dialogCheckout.findViewById(R.id.checkout_cart_dialog);
        dialogCheckout.show();

        Calendar calendar = Calendar.getInstance();
        final String tanggal_transaksi = DateFormat.getDateInstance().format(calendar.getTime());
        final String status_transaksi = "Belum Selesai";
        final int total_harga = Integer.parseInt(totalHarga);
        final String status_pesanan = "Menunggu Toko";

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namaPelanggan = preferencedConfig.getPreferenceNamaLengkap();
                final String metodePembayaran = spinnerPembayaran.getSelectedItem().toString();
                final String metodePengambilan = spinnerPengambilan.getSelectedItem().toString();
                final String alamat = edtAlamat.getText().toString();

                String id_pelanggan = preferencedConfig.getPreferenceIdUser();
                String nama_toko = preferencedConfig.getPreferenceNamaToko();
                String nama_lengkap_toko = preferencedConfig.getPreferenceNamaTokoStatus();

                ConfigRetrofit.service.InsertCheckout(namaPelanggan, dataBarang, total_harga,
                        metodePembayaran, metodePengambilan, alamat, status_pesanan, status_transaksi, tanggal_transaksi,id_pelanggan,nama_toko, nama_lengkap_toko)
                        .enqueue(new Callback<ResponseInsertCheckout>() {
                            @Override
                            public void onResponse(Call<ResponseInsertCheckout> call, Response<ResponseInsertCheckout> response) {
                                int status = response.body().getStatus();
                                String pesan = response.body().getPesan();

                                if (status == 1){
                                    Toast.makeText(PesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                    dialogCheckout.dismiss();
                                    DeleteOrder();
                                    finish();
                                }else{
                                    Toast.makeText(PesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseInsertCheckout> call, Throwable t) {
                                Toast.makeText(PesananActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void DeleteOrder() {

        String nama_pelanggan = preferencedConfig.getPreferenceIdUser();
        ConfigRetrofit.service.DeleteOrder(nama_pelanggan).enqueue(new Callback<ResponseDeleteOrder>() {
            @Override
            public void onResponse(Call<ResponseDeleteOrder> call, Response<ResponseDeleteOrder> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    startActivity(new Intent(PesananActivity.this, MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteOrder> call, Throwable t) {

            }
        });
    }

    private void LoadRecyclerPesanan() {

        String nama_pelanggan = preferencedConfig.getPreferenceIdUser();
        ConfigRetrofit.service.DataOrder(nama_pelanggan).enqueue(new Callback<ResponseDataOrder>() {
            @Override
            public void onResponse(Call<ResponseDataOrder> call, Response<ResponseDataOrder> response) {
                int status = response.body().getStatus();
                String pesan = response.body().getPesan();

                if (status == 1){
                    List<DataOrderItem> dataOrderList = response.body().getDataOrder();
                    OrderAdapter adapter = new OrderAdapter(PesananActivity.this, dataOrderList);
                    rvPesanan.setAdapter(adapter);
                    rvPesanan.setLayoutManager(new LinearLayoutManager(PesananActivity.this));
                }else{
                    Toast.makeText(PesananActivity.this, pesan, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseDataOrder> call, Throwable t) {
                Toast.makeText(PesananActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SetTotalOrder() {

        String nama_pelanggan = preferencedConfig.getPreferenceIdUser();
        ConfigRetrofit.service.TotalOrder(nama_pelanggan).enqueue(new Callback<ResponseTotalOrder>() {
            @Override
            public void onResponse(Call<ResponseTotalOrder> call, Response<ResponseTotalOrder> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    String total = response.body().getTotalSemua().getTotalHarga();

                    if (total != null) {
                        int total2 = Integer.parseInt(total);

                        Locale localID = new Locale("in", "ID");
                        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localID);
                        txtTotal.setText(formatRp.format(total2));

                        totalHarga = response.body().getTotalSemua().getTotalHarga();
                    }else{
                        Toast.makeText(PesananActivity.this, "Belum Ada Pesanan", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PesananActivity.this, "ERROR..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTotalOrder> call, Throwable t) {

            }
        });
    }

}
