package sidiq.com.estore.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.R;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataStockItem;
import sidiq.com.estore.model.ResponseInsertPesanan;

public class PesanBarangAdapter extends RecyclerView.Adapter<PesanBarangAdapter.PesanBarangViewHolder> {

    Context mContext;
    List<DataStockItem> dataStockItems;

    private SharedPreferencedConfig preferencedConfig;

    public PesanBarangAdapter(Context mContext, List<DataStockItem> dataStockItems) {
        this.mContext = mContext;
        this.dataStockItems = dataStockItems;
    }

    @NonNull
    @Override
    public PesanBarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesan_barang, parent, false);
        return new PesanBarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesanBarangViewHolder holder, final int position) {

        final String linkImg = dataStockItems.get(position).getImageBarang();

        Glide.with(mContext)
                .load(linkImg)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.drawable.logoestore)
                .into(holder.imgBarang);

        holder.txtNamaBarang.setText(dataStockItems.get(position).getNamaBarang());

        int hargaBarang = Integer.parseInt(dataStockItems.get(position).getHargaBarang());

        Locale localeId = new Locale("in", "ID");

        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localeId);

        holder.txtHargaBarang.setText(formatRp.format(hargaBarang));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog_item_qty);
                dialog.setTitle("Quantity");

                final ImageView cartKurang = dialog.findViewById(R.id.cart_kurang);
                final ImageView cartTambah = dialog.findViewById(R.id.cart_tambah);
                final TextView addTocart = dialog.findViewById(R.id.tambah_cart_dialog);
                final TextView quantity = dialog.findViewById(R.id.cart_quantity);
                preferencedConfig = new SharedPreferencedConfig(mContext);
                quantity.setText(String.valueOf(0));
                final int[] cartCounter = {0};
                cartKurang.setEnabled(true);
                cartKurang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cartCounter[0] == 1){
                            Toast.makeText(mContext, "cant add less than 0", Toast.LENGTH_SHORT).show();
                        }else{
                            cartCounter[0] -= 1;
                            quantity.setText(String.valueOf(cartCounter[0]));
                        }
                    }
                });
                cartTambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cartTambah.setEnabled(true);
                        cartCounter[0] += 1;
                        quantity.setText(String.valueOf(cartCounter[0]));
                    }
                });

                dialog.show();
                addTocart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int qty = cartCounter[0];

                        final String nama_barang = dataStockItems.get(position).getNamaBarang();
                        int harga = Integer.parseInt(dataStockItems.get(position).getHargaBarang());
                        int total_harga = qty * harga;
                        String image_barang = dataStockItems.get(position).getImageBarang();
                        String ukuran_barang = dataStockItems.get(position).getUkuranBarang();
                        String nama_pelanggan = preferencedConfig.getPreferenceIdUser();
                        final String nama_toko = dataStockItems.get(position).getNamaToko();


                        if (qty == 0){
                            Toast.makeText(mContext, "Tidak boleh 0", Toast.LENGTH_SHORT).show();
                        }else {
                            ConfigRetrofit.service.InsertPesanan(image_barang, nama_barang, ukuran_barang, qty, harga, total_harga, nama_pelanggan, nama_toko).enqueue(new Callback<ResponseInsertPesanan>() {
                                @Override
                                public void onResponse(Call<ResponseInsertPesanan> call, Response<ResponseInsertPesanan> response) {
                                    int status = response.body().getStatus();
                                    String pesan = response.body().getPesan();
                                    if (status == 1) {
                                        Toast.makeText(mContext, "Order : " + nama_barang + " x " + qty, Toast.LENGTH_SHORT).show();
                                        preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_TOKO, nama_toko);
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(mContext, pesan, Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseInsertPesanan> call, Throwable t) {
                                    Toast.makeText(mContext, "ERROR NOHHHH !", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataStockItems.size();
    }

    public class PesanBarangViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaBarang, txtHargaBarang;
        ImageView imgBarang;
        public PesanBarangViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaBarang = itemView.findViewById(R.id.text_nama_barang);
            txtHargaBarang = itemView.findViewById(R.id.text_harga_barang);
            imgBarang = itemView.findViewById(R.id.img_pesan_barang);
        }
    }
}
