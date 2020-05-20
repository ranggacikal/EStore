package sidiq.com.estore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TokoActivity extends AppCompatActivity {


    CardView cardDataStock, cardDataPesanan, cardTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko);

        cardDataStock = findViewById(R.id.card_data_barang);
        cardDataPesanan = findViewById(R.id.card_data_pesanan);
        cardTransaksi = findViewById(R.id.card_transaksi);

        cardDataStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TokoActivity.this, DataStockActivity.class));
            }
        });

        cardDataPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TokoActivity.this, PesananTokoActivity.class));
            }
        });

        cardTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TokoActivity.this, TransaksiActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        MenuItem menuItem = menu.findItem(R.id.profile_action);
        menuItem.setIcon(R.drawable.ic_profile);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.profile_action:
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
