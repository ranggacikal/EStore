package sidiq.com.estore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.R;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.adapter.DataStockAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataStockItem;
import sidiq.com.estore.model.ResponseDataStock;

public class DataStockActivity extends AppCompatActivity {

    RecyclerView rvDataStock;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_stock);
        setTitle("Data Stock");

        preferencedConfig = new SharedPreferencedConfig(this);

        rvDataStock = findViewById(R.id.recycler_data_stock);

        LoadRecyclerDataStock();
    }

    private void LoadRecyclerDataStock() {


        String id_toko = preferencedConfig.getPreferenceIdUser();
        ConfigRetrofit.service.DataStock(id_toko).enqueue(new Callback<ResponseDataStock>() {
            @Override
            public void onResponse(Call<ResponseDataStock> call, Response<ResponseDataStock> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataStockItem> dataStockList = response.body().getDataStock();
                    DataStockAdapter adapter = new DataStockAdapter(DataStockActivity.this, dataStockList);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(DataStockActivity.this, 2, LinearLayoutManager.VERTICAL, false);
                    rvDataStock.setLayoutManager(gridLayoutManager);
                    rvDataStock.setAdapter(adapter);
                }else{
                    Toast.makeText(DataStockActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataStock> call, Throwable t) {
                Toast.makeText(DataStockActivity.this, "Check Network", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_stock, menu);
        MenuItem menuItem = menu.findItem(R.id.add_action);
        menuItem.setIcon(R.drawable.ic_add_stock);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.add_action:
                startActivity(new Intent(DataStockActivity.this, InsertStockActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        LoadRecyclerDataStock();
    }
}
