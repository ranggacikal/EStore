package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.adapter.RequestMemberAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataPermintaanMemberItem;
import sidiq.com.estore.model.ResponsePermintaanMember;

public class RequestMemberActivity extends AppCompatActivity {

    RecyclerView rvReqMember;
    public static RequestMemberActivity req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_member);
        setTitle("Request Member");

        rvReqMember = findViewById(R.id.recycler_request_member);

        req = this;
        LoadRecyclerReqMember();
    }

    public void LoadRecyclerReqMember() {

        ConfigRetrofit.service.RequestMember().enqueue(new Callback<ResponsePermintaanMember>() {
            @Override
            public void onResponse(Call<ResponsePermintaanMember> call, Response<ResponsePermintaanMember> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataPermintaanMemberItem> dataPermintaanMemberList = response.body().getDataPermintaanMember();
                    RequestMemberAdapter adapter = new RequestMemberAdapter(RequestMemberActivity.this, dataPermintaanMemberList);
                    Log.d("Retrofit Get", "Jumlah DataReq Member: "+String.valueOf(dataPermintaanMemberList.size()));
                    rvReqMember.setLayoutManager(new LinearLayoutManager(RequestMemberActivity.this));
                    rvReqMember.setAdapter(adapter);
                }else{
                    Toast.makeText(RequestMemberActivity.this, "Tidak ADA DATA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePermintaanMember> call, Throwable t) {
                Toast.makeText(RequestMemberActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
