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
import sidiq.com.estore.adapter.StatusPesananAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataStatusPesananItem;
import sidiq.com.estore.model.ResponseStatusPesanan;

public class StatusPesananActivity extends AppCompatActivity {

    RecyclerView rvStatusPesanan;

    private SharedPreferencedConfig preferencedConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pesanan);

        preferencedConfig = new SharedPreferencedConfig(this);

        rvStatusPesanan = findViewById(R.id.recycler_status_pesanan);

        LoadRecyclerStatusPesanan();
    }

    private void LoadRecyclerStatusPesanan() {

        String id_pelanggan = preferencedConfig.getPreferenceIdUser();
        ConfigRetrofit.service.getStatusPesanan(id_pelanggan).enqueue(new Callback<ResponseStatusPesanan>() {
            @Override
            public void onResponse(Call<ResponseStatusPesanan> call, Response<ResponseStatusPesanan> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataStatusPesananItem> dataStatusPesananList = response.body().getDataStatusPesanan();
                    StatusPesananAdapter adapter = new StatusPesananAdapter(StatusPesananActivity.this, dataStatusPesananList);
                    rvStatusPesanan.setAdapter(adapter);
                    rvStatusPesanan.setLayoutManager(new LinearLayoutManager(StatusPesananActivity.this));
                }else{
                    Toast.makeText(StatusPesananActivity.this, "Data Tidak Ada...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusPesanan> call, Throwable t) {
                Toast.makeText(StatusPesananActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
