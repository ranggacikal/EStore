package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.adapter.DataTransaksiAdapter;
import sidiq.com.estore.adapter.TransaksiPelangganAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataTransaksiPelangganItem;
import sidiq.com.estore.model.DataTransaksiTokoItem;
import sidiq.com.estore.model.ResponseTransaksiPelanggan;

public class TransaksiPelangganActivity extends AppCompatActivity {

    RecyclerView rvTransaksiPelanggan;

    EditText edtSearchPelanggan;

    List<DataTransaksiPelangganItem> pelangganItemList;
    TransaksiPelangganAdapter adapter;
    private SharedPreferencedConfig preferencedConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_pelanggan);

        preferencedConfig = new SharedPreferencedConfig(this);
        rvTransaksiPelanggan= findViewById(R.id.recycler_data_transaksi_pelanggan);
        edtSearchPelanggan = findViewById(R.id.edt_search_pelanggan);
        LoadTransaksiPelanggan();

        edtSearchPelanggan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<DataTransaksiPelangganItem> filteredList = new ArrayList<>();

        for (DataTransaksiPelangganItem item : pelangganItemList){
            if (item.getTanggalTransaksi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void LoadTransaksiPelanggan() {

        String id_pelanggan = preferencedConfig.getPreferenceIdUser();

        ConfigRetrofit.service.TransaksiPelanggan(id_pelanggan).enqueue(new Callback<ResponseTransaksiPelanggan>() {
            @Override
            public void onResponse(Call<ResponseTransaksiPelanggan> call, Response<ResponseTransaksiPelanggan> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataTransaksiPelangganItem> transaksiPelangganList = response.body().getDataTransaksiPelanggan();
                    TransaksiPelangganAdapter adapter = new TransaksiPelangganAdapter(TransaksiPelangganActivity.this, transaksiPelangganList);
                    rvTransaksiPelanggan.setAdapter(adapter);
                    rvTransaksiPelanggan.setLayoutManager(new LinearLayoutManager(TransaksiPelangganActivity.this));
                }else{
                    Toast.makeText(TransaksiPelangganActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksiPelanggan> call, Throwable t) {
                Toast.makeText(TransaksiPelangganActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TransaksiPelangganActivity.this, MainActivity.class));
        finish();
    }
}
