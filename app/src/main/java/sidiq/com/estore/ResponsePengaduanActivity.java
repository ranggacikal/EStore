package sidiq.com.estore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.adapter.ResponsePengaduanAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataResponsePengaduanItem;
import sidiq.com.estore.model.ResponseBalasanPengaduan;

public class ResponsePengaduanActivity extends AppCompatActivity {

    @BindView(R.id.rv_response_pengaduan)
    RecyclerView rvResponsePengaduan;

    private SharedPreferencedConfig preferencedConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_pengaduan);
        ButterKnife.bind(this);

        preferencedConfig = new SharedPreferencedConfig(this);

        LoadBalasanPengaduan();
    }

    private void LoadBalasanPengaduan() {

        String id_pelanggan = preferencedConfig.getPreferenceIdUser();

        ConfigRetrofit.service.BalasanPengaduan(id_pelanggan).enqueue(new Callback<ResponseBalasanPengaduan>() {
            @Override
            public void onResponse(Call<ResponseBalasanPengaduan> call, Response<ResponseBalasanPengaduan> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataResponsePengaduanItem> pengaduanItemList = response.body().getDataResponsePengaduan();
                    ResponsePengaduanAdapter adapter = new ResponsePengaduanAdapter(ResponsePengaduanActivity.this, pengaduanItemList);
                    rvResponsePengaduan.setAdapter(adapter);
                    rvResponsePengaduan.setLayoutManager(new LinearLayoutManager(ResponsePengaduanActivity.this));
                }else{
                    Toast.makeText(ResponsePengaduanActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBalasanPengaduan> call, Throwable t) {
                Toast.makeText(ResponsePengaduanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResponsePengaduanActivity.this, PengaduanActivity.class));
        finish();
    }
}
