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
import sidiq.com.estore.adapter.DataMemberAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataMemberItem;
import sidiq.com.estore.model.ResponseDataMember;

public class DataMemberActivity extends AppCompatActivity {

    RecyclerView rvDataMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_member);
        setTitle("Data Member");

        rvDataMember = findViewById(R.id.recycler_data_member);

        LoadRecyclerDataMember();
    }

    private void LoadRecyclerDataMember() {

        ConfigRetrofit.service.DataMember().enqueue(new Callback<ResponseDataMember>() {
            @Override
            public void onResponse(Call<ResponseDataMember> call, Response<ResponseDataMember> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataMemberItem> dataMemberList = response.body().getDataMember();
                    DataMemberAdapter adapter = new DataMemberAdapter(DataMemberActivity.this, dataMemberList);
                    rvDataMember.setAdapter(adapter);
                    rvDataMember.setLayoutManager(new LinearLayoutManager(DataMemberActivity.this));
                }else{
                    Toast.makeText(DataMemberActivity.this, "DATA TIDAK ADA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataMember> call, Throwable t) {
                Toast.makeText(DataMemberActivity.this, "Check Network", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
