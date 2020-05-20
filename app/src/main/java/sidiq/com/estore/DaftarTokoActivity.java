package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.adapter.DaftarTokoAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataTokoItem;
import sidiq.com.estore.model.ResponseDataToko;

public class DaftarTokoActivity extends AppCompatActivity {

    RecyclerView rvDaftarToko;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_toko);

        rvDaftarToko = findViewById(R.id.recycler_daftar_toko);

        LoadRecyclerDaftarToko();
    }

    private void LoadRecyclerDaftarToko() {

        ConfigRetrofit.service.getDataToko().enqueue(new Callback<ResponseDataToko>() {
            @Override
            public void onResponse(Call<ResponseDataToko> call, Response<ResponseDataToko> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataTokoItem> tokoList = response.body().getDataToko();
                    DaftarTokoAdapter adapter = new DaftarTokoAdapter(DaftarTokoActivity.this, tokoList);
                    rvDaftarToko.setAdapter(adapter);
                    rvDaftarToko.setLayoutManager(new LinearLayoutManager(DaftarTokoActivity.this));
                }else{
                    Toast.makeText(DaftarTokoActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataToko> call, Throwable t) {
                Toast.makeText(DaftarTokoActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
