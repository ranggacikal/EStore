package sidiq.com.estore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.adapter.DataStockAdapter;
import sidiq.com.estore.adapter.PesanBarangAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataStockItem;
import sidiq.com.estore.model.DataTokoItem;
import sidiq.com.estore.model.ResponseDataStock;
import sidiq.com.estore.model.ResponseDataToko;

public class PesanBarangActivity extends AppCompatActivity {

    RecyclerView rvPesanBarang;

    public static final String EXTRA_ID_TOKO = "extra_id_toko";
    ProgressDialog loading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_barang);
        rvPesanBarang = findViewById(R.id.recycler_pesan_barang);



        LoadRecyclerPesanBarang();
    }

    private void LoadRecyclerPesanBarang() {

        String id_toko = getIntent().getStringExtra(EXTRA_ID_TOKO);
        ConfigRetrofit.service.DataStock(id_toko).enqueue(new Callback<ResponseDataStock>() {
            @Override
            public void onResponse(Call<ResponseDataStock> call, Response<ResponseDataStock> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataStockItem> dataBarangList = response.body().getDataStock();
                    PesanBarangAdapter adapter = new PesanBarangAdapter(PesanBarangActivity.this, dataBarangList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(PesanBarangActivity.this, 2, LinearLayoutManager.VERTICAL, false);
                    rvPesanBarang.setLayoutManager(gridLayoutManager);
                    rvPesanBarang.setAdapter(adapter);
                }else{
                    Toast.makeText(PesanBarangActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataStock> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pesanan, menu);
        MenuItem menuItem = menu.findItem(R.id.pesanan_action);
        menuItem.setIcon(R.drawable.ic_pesanan);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.pesanan_action:
                startActivity(new Intent(this, PesananActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
