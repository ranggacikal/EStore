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

import sidiq.com.estore.EditHapusPesananActivity;
import sidiq.com.estore.PesananActivity;
import sidiq.com.estore.R;
import sidiq.com.estore.model.DataOrderItem;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context mContext;
    List<DataOrderItem> dataOrderItems;

    public OrderAdapter(Context mContext, List<DataOrderItem> dataOrderItems) {
        this.mContext = mContext;
        this.dataOrderItems = dataOrderItems;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        final String linkImg = dataOrderItems.get(position).getImageBarang();

        Glide.with(mContext)
                .load(linkImg)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.drawable.logoestore)
                .into(holder.imgPesanan);

        holder.txtNamaPesanan.setText(dataOrderItems.get(position).getNamaBarang());
        holder.txtUkuranPesanan.setText(dataOrderItems.get(position).getUkuranBarang());
        holder.txtQtyPesanan.setText(dataOrderItems.get(position).getQuantityBarang());

        int total = Integer.parseInt(dataOrderItems.get(position).getTotalHarga());

        Locale localId = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localId);

        holder.txtTotalHarga.setText(formatRp.format(total));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditHapusPesananActivity.class);
                intent.putExtra(EditHapusPesananActivity.EXTRA_ID_PESANAN, dataOrderItems.get(position).getIdOrder());
                intent.putExtra(EditHapusPesananActivity.EXTRA_QTY_PESANAN, dataOrderItems.get(position).getQuantityBarang());
                intent.putExtra(EditHapusPesananActivity.EXTRA_IMAGE_PESANAN, dataOrderItems.get(position).getImageBarang());
                intent.putExtra(EditHapusPesananActivity.EXTRA_HARGA_SATUAN_PESANAN, dataOrderItems.get(position).getHargaSatuan());
                mContext.startActivity(intent);
                ((PesananActivity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataOrderItems.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaPesanan, txtUkuranPesanan, txtQtyPesanan, txtTotalHarga;
        ImageView imgPesanan;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaPesanan = itemView.findViewById(R.id.text_nama_barang_pesanan);
            txtUkuranPesanan = itemView.findViewById(R.id.text_ukuran_pesanan);
            txtQtyPesanan = itemView.findViewById(R.id.text_qty_pesanan);
            txtTotalHarga = itemView.findViewById(R.id.text_total_pesanan);
            imgPesanan = itemView.findViewById(R.id.img_pesanan);
        }
    }
}
