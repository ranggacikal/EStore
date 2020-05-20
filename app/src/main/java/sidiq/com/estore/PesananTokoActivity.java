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
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.adapter.PesananTokoAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataPesananTokoItem;
import sidiq.com.estore.model.ResponsePesananToko;

public class PesananTokoActivity extends AppCompatActivity {

    RecyclerView rvPesananToko;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_toko);

        preferencedConfig = new SharedPreferencedConfig(this);

        rvPesananToko = findViewById(R.id.recycler_pesanan_toko);

        LoadRecyclerPesananToko();
    }

    private void LoadRecyclerPesananToko() {

        String id_toko = preferencedConfig.getPreferenceIdUser();
        ConfigRetrofit.service.PesananToko(id_toko).enqueue(new Callback<ResponsePesananToko>() {
            @Override
            public void onResponse(Call<ResponsePesananToko> call, Response<ResponsePesananToko> response) {
                int status = response.body().getStatus();

                if (status == 1) {

                    List<DataPesananTokoItem> pesananTokoList = response.body().getDataPesananToko();
                    PesananTokoAdapter adapter = new PesananTokoAdapter(PesananTokoActivity.this, pesananTokoList);
                    rvPesananToko.setAdapter(adapter);
                    rvPesananToko.setLayoutManager(new LinearLayoutManager(PesananTokoActivity.this));
                }else{
                    Toast.makeText(PesananTokoActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePesananToko> call, Throwable t) {
                Toast.makeText(PesananTokoActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
