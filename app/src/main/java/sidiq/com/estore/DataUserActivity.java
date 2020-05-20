package sidiq.com.estore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.adapter.DataUserAdapter;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataUserItem;
import sidiq.com.estore.model.ResponseDataUser;

public class DataUserActivity extends AppCompatActivity {

    RecyclerView rvDataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);
        setTitle("Data User");

        rvDataUser = findViewById(R.id.recycler_data_user);

        LoadRecyclerDataUser();
    }

    private void LoadRecyclerDataUser() {

        ConfigRetrofit.service.DataUser().enqueue(new Callback<ResponseDataUser>() {
            @Override
            public void onResponse(Call<ResponseDataUser> call, Response<ResponseDataUser> response) {
                int status = response.body().getStatus();

                if (status == 1){
                    List<DataUserItem> dataUserList = response.body().getDataUser();
                    DataUserAdapter adapter = new DataUserAdapter(DataUserActivity.this, dataUserList);
                    rvDataUser.setAdapter(adapter);
                    rvDataUser.setLayoutManager(new LinearLayoutManager(DataUserActivity.this));
                }else{
                    Toast.makeText(DataUserActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataUser> call, Throwable t) {
                Toast.makeText(DataUserActivity.this, "Check Network...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
