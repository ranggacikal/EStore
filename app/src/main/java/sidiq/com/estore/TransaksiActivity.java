package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.adapter.DataTransaksiAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataTokoItem;
import sidiq.com.estore.model.DataTransaksiTokoItem;
import sidiq.com.estore.model.ResponseTransaksiToko;

public class TransaksiActivity extends AppCompatActivity {

    RecyclerView rvTransaksi;

    private SharedPreferencedConfig preferencedConfig;

    EditText edtSearch;

    List<DataTransaksiTokoItem> tokoItemList;
    DataTransaksiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        preferencedConfig = new SharedPreferencedConfig(this);

        rvTransaksi = findViewById(R.id.recycler_data_transaksi);
        edtSearch = findViewById(R.id.edt_search);

        edtSearch.addTextChangedListener(new TextWatcher() {
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

        LoadDataTransaksi();



    }

    private void filter(String text) {
        ArrayList<DataTransaksiTokoItem> filteredList = new ArrayList<>();

        for (DataTransaksiTokoItem item : tokoItemList){
            if (item.getTanggalTransaksi().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    public void LoadDataTransaksi() {
        String nama_toko = preferencedConfig.getPreferenceIdUser();

        ConfigRetrofit.service.TransaksiToko(nama_toko).enqueue(new Callback<ResponseTransaksiToko>() {
            @Override
            public void onResponse(Call<ResponseTransaksiToko> call, Response<ResponseTransaksiToko> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    tokoItemList = response.body().getDataTransaksiToko();
                    adapter = new DataTransaksiAdapter(TransaksiActivity.this, tokoItemList);
                    rvTransaksi.setAdapter(adapter);
                    rvTransaksi.setLayoutManager(new LinearLayoutManager(TransaksiActivity.this));
                }else{
                    Toast.makeText(TransaksiActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksiToko> call, Throwable t) {
                Toast.makeText(TransaksiActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
