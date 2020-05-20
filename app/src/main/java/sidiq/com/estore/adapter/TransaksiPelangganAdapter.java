package sidiq.com.estore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sidiq.com.estore.DetailTransaksiPelangganActivity;
import sidiq.com.estore.R;
import sidiq.com.estore.TransaksiPelangganActivity;
import sidiq.com.estore.model.DataTransaksiPelangganItem;

public class TransaksiPelangganAdapter extends RecyclerView.Adapter<TransaksiPelangganAdapter.TransaksiPelangganViewHolder> {
    Context mContext;
    List<DataTransaksiPelangganItem> transaksiPelangganItems;

    public TransaksiPelangganAdapter(Context mContext, List<DataTransaksiPelangganItem> transaksiPelangganItems) {
        this.mContext = mContext;
        this.transaksiPelangganItems = transaksiPelangganItems;
    }

    @NonNull
    @Override
    public TransaksiPelangganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi_pelanggan, parent, false);
        return new TransaksiPelangganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiPelangganViewHolder holder, int position) {

        holder.txtId.setText(transaksiPelangganItems.get(position).getIdTransaksi());
        holder.txtListPesanan.setText(transaksiPelangganItems.get(position).getListPesanan());

        int Total = Integer.parseInt(transaksiPelangganItems.get(position).getTotalHarga());

        Locale localID = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localID);

        holder.txtTotal.setText(formatRp.format(Total));
        holder.txtStatusTransaksi.setText(transaksiPelangganItems.get(position).getStatusTransaksi());
        holder.txtTanggal.setText(transaksiPelangganItems.get(position).getTanggalTransaksi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailTransaksiPelangganActivity.class);
                intent.putExtra(DetailTransaksiPelangganActivity.EXTRA_ID_TRANSAKSI_PELANGGAN, transaksiPelangganItems.get(position).getIdTransaksi());
                intent.putExtra(DetailTransaksiPelangganActivity.EXTRA_NAMA_PELANGGAN_TRANSAKSI_PELANGGAN, transaksiPelangganItems.get(position).getNamaPelanggan());
                intent.putExtra(DetailTransaksiPelangganActivity.EXTRA_LIST_TRANSAKSI_PELANGGAN, transaksiPelangganItems.get(position).getListPesanan());
                intent.putExtra(DetailTransaksiPelangganActivity.EXTRA_TOTAL_TRANSAKSI_PELANGGAN, transaksiPelangganItems.get(position).getTotalHarga());
                intent.putExtra(DetailTransaksiPelangganActivity.EXTRA_TANGGAL_TRANSAKSI_PELANGGAN, transaksiPelangganItems.get(position).getTanggalTransaksi());
                mContext.startActivity(intent);
                ((TransaksiPelangganActivity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return transaksiPelangganItems.size();
    }

    public class TransaksiPelangganViewHolder extends RecyclerView.ViewHolder {

        TextView txtId, txtListPesanan, txtTotal, txtStatusTransaksi, txtTanggal;
        public TransaksiPelangganViewHolder(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.text_id_transaksi_pelanggan);
            txtListPesanan = itemView.findViewById(R.id.text_list_transaksi_pelanggan);
            txtTotal = itemView.findViewById(R.id.text_total_transaksi_pelanggan);
            txtStatusTransaksi = itemView.findViewById(R.id.text_status_transaksi_pelanggan);
            txtTanggal = itemView.findViewById(R.id.text_tanggal_transaksi_pelanggan);
        }
    }

    public void filterList(ArrayList<DataTransaksiPelangganItem> filteredList){
        transaksiPelangganItems = filteredList;
        notifyDataSetChanged();
    }
}
