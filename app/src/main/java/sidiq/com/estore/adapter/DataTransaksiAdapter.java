package sidiq.com.estore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sidiq.com.estore.DetailTransaksiPelangganActivity;
import sidiq.com.estore.DetailTransaksiTokoActivity;
import sidiq.com.estore.R;
import sidiq.com.estore.TransaksiActivity;
import sidiq.com.estore.TransaksiPelangganActivity;
import sidiq.com.estore.model.DataTransaksiTokoItem;

public class DataTransaksiAdapter extends RecyclerView.Adapter<DataTransaksiAdapter.DataTransaksiViewHolder>{
    Context mContext;
    List<DataTransaksiTokoItem> transaksiTokoItems;
    List<DataTransaksiTokoItem> transaksiTokoListFull;

    public DataTransaksiAdapter(Context mContext, List<DataTransaksiTokoItem> transaksiTokoItems) {
        this.mContext = mContext;
        this.transaksiTokoItems = transaksiTokoItems;
        transaksiTokoListFull = transaksiTokoItems;
    }

    @NonNull
    @Override
    public DataTransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi_toko, parent, false);
        return new DataTransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataTransaksiViewHolder holder, int position) {

        holder.txtId.setText(transaksiTokoItems.get(position).getIdTransaksi());
        holder.txtListPesanan.setText(transaksiTokoItems.get(position).getListPesanan());

        int total = Integer.parseInt(transaksiTokoItems.get(position).getTotalHarga());

        Locale localId = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localId);

        holder.txtTotal.setText(formatRp.format(total));
        holder.txtStatusTransaksi.setText(transaksiTokoItems.get(position).getStatusTransaksi());
        holder.txtTanggal.setText(transaksiTokoItems.get(position).getTanggalTransaksi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailTransaksiTokoActivity.class);
                intent.putExtra(DetailTransaksiTokoActivity.EXTRA_ID_TRANSAKSI_PELANGGAN, transaksiTokoItems.get(position).getIdTransaksi());
                intent.putExtra(DetailTransaksiTokoActivity.EXTRA_NAMA_PELANGGAN_TRANSAKSI, transaksiTokoItems.get(position).getNamaPelanggan());
                intent.putExtra(DetailTransaksiTokoActivity.EXTRA_LIST_TRANSAKSI_PELANGGAN, transaksiTokoItems.get(position).getListPesanan());
                intent.putExtra(DetailTransaksiTokoActivity.EXTRA_TOTAL_TRANSAKSI_PELANGGAN, transaksiTokoItems.get(position).getTotalHarga());
                intent.putExtra(DetailTransaksiTokoActivity.EXTRA_TANGGAL_TRANSAKSI_PELANGGAN, transaksiTokoItems.get(position).getTanggalTransaksi());
                mContext.startActivity(intent);
                ((TransaksiActivity)mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transaksiTokoItems.size();
    }

    public class DataTransaksiViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtListPesanan, txtTotal, txtStatusTransaksi, txtTanggal;
        public DataTransaksiViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.text_id_transaksi);
            txtListPesanan = itemView.findViewById(R.id.text_list_transaksi);
            txtTotal = itemView.findViewById(R.id.text_total_transaksi);
            txtStatusTransaksi = itemView.findViewById(R.id.text_status_transaksi);
            txtTanggal = itemView.findViewById(R.id.text_tanggal_transaksi);
        }
    }

    public void filterList(ArrayList<DataTransaksiTokoItem> filteredList){
        transaksiTokoItems = filteredList;
        notifyDataSetChanged();
    }
}
