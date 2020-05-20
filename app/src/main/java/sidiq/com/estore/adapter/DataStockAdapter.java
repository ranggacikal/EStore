package sidiq.com.estore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sidiq.com.estore.DataStockActivity;
import sidiq.com.estore.EditHapusStockActivity;
import sidiq.com.estore.R;
import sidiq.com.estore.model.DataStockItem;

public class DataStockAdapter extends RecyclerView.Adapter<DataStockAdapter.DataStockViewHolder> {
    private Context mContext;
    private List<DataStockItem> dataStockItems;

    public DataStockAdapter(Context mContext, List<DataStockItem> dataStockItems) {
        this.mContext = mContext;
        this.dataStockItems = dataStockItems;
    }

    @NonNull
    @Override
    public DataStockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_stock, parent, false);
        return new DataStockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataStockViewHolder holder, final int position) {

        String namaToko = dataStockItems.get(position).getNamaToko();



        final String linkImg = dataStockItems.get(position).getImageBarang();

        Glide.with(mContext)
                .load(linkImg)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgStock);

        holder.txtNamaStock.setText(dataStockItems.get(position).getNamaBarang());
        int harga = Integer.parseInt(dataStockItems.get(position).getHargaBarang());

        Locale localID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localID);
        holder.txtHarga.setText(formatRupiah.format(harga));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEdit = new Intent(mContext, EditHapusStockActivity.class);
                intentEdit.putExtra(EditHapusStockActivity.EXTRA_ID_STOCK, dataStockItems.get(position).getIdStock());
                intentEdit.putExtra(EditHapusStockActivity.EXTRA_NAMA_STOCK, dataStockItems.get(position).getNamaBarang());
                intentEdit.putExtra(EditHapusStockActivity.EXTRA_HARGA_STOCK, dataStockItems.get(position).getHargaBarang());
                intentEdit.putExtra(EditHapusStockActivity.EXTRA_UKURAN_STOCK, dataStockItems.get(position).getUkuranBarang());
                intentEdit.putExtra(EditHapusStockActivity.EXTRA_IMAGE_STOCK, dataStockItems.get(position).getImageBarang());
                intentEdit.putExtra(EditHapusStockActivity.EXTRA_NAMA_TOKO_STOCK, dataStockItems.get(position).getNamaToko());
                mContext.startActivity(intentEdit);
                ((DataStockActivity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataStockItems.size();
    }

    public class DataStockViewHolder extends RecyclerView.ViewHolder {

        ImageView imgStock;
        TextView txtNamaStock, txtHarga;
        public DataStockViewHolder(@NonNull View itemView) {
            super(itemView);

            imgStock = itemView.findViewById(R.id.img_data_stock);
            txtNamaStock = itemView.findViewById(R.id.text_nama_stock);
            txtHarga = itemView.findViewById(R.id.text_harga_stock);
        }
    }
}
