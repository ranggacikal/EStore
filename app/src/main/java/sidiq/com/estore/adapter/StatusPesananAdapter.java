package sidiq.com.estore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sidiq.com.estore.R;
import sidiq.com.estore.StatusPesananActivity;
import sidiq.com.estore.UploadBuktiTransferActivity;
import sidiq.com.estore.model.DataStatusPesananItem;

public class StatusPesananAdapter extends RecyclerView.Adapter<StatusPesananAdapter.StatusPesananViewHolder> {

    Context mContext;
    List<DataStatusPesananItem> statusPesananItem;

    public StatusPesananAdapter(Context mContext, List<DataStatusPesananItem> statusPesananItem) {
        this.mContext = mContext;
        this.statusPesananItem = statusPesananItem;
    }

    @NonNull
    @Override
    public StatusPesananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_pesanan, parent, false);
        return new StatusPesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StatusPesananViewHolder holder, final int position) {

        holder.txtNamaPelanggan.setText(statusPesananItem.get(position).getNamaPelanggan());
        holder.txtIdPesanan.setText(statusPesananItem.get(position).getIdTransaksi());
        holder.txtListPesanan.setText(statusPesananItem.get(position).getListPesanan());

        int totalPesan = Integer.parseInt(statusPesananItem.get(position).getTotalHarga());
        Locale localID = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localID);

        holder.txtTotalHarga.setText(formatRp.format(totalPesan));
        holder.txtStatusPesanan.setText(statusPesananItem.get(position).getStatusPesanan());
        holder.txtMetodeBayar.setText(statusPesananItem.get(position).getMetodePembayaran());
        holder.txtNamaLengkapToko.setText(statusPesananItem.get(position).getNamaLengkapToko());

        String statusPembayaran = statusPesananItem.get(position).getMetodePembayaran();

        if (statusPembayaran.equals("Transfer")){
            holder.btnUploadTf.setVisibility(View.VISIBLE);
            holder.btnUploadTf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String buktiTf = statusPesananItem.get(position).getBuktiTransfer();

                    if (buktiTf.equals("")) {
                        Intent pindahTf = new Intent(mContext, UploadBuktiTransferActivity.class);
                        pindahTf.putExtra(UploadBuktiTransferActivity.EXTRA_ID_TRANSAKSI, statusPesananItem.get(position).getIdTransaksi());
                        mContext.startActivity(pindahTf);
                        ((StatusPesananActivity) mContext).finish();
                    }else if (!buktiTf.equals("")){
                        Toast.makeText(mContext, "Anda Sudah Melakukan Upload Bukti Transfer", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else if(statusPembayaran.equals("Cash")){
            holder.btnUploadTf.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return statusPesananItem.size();
    }

    public class StatusPesananViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdPesanan, txtListPesanan, txtTotalHarga, txtStatusPesanan, txtNamaPelanggan, txtMetodeBayar, txtNamaLengkapToko;
        Button btnUploadTf;
        public StatusPesananViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaPelanggan = itemView.findViewById(R.id.text_pelanggan_status);
            txtIdPesanan = itemView.findViewById(R.id.text_id_status);
            txtListPesanan = itemView.findViewById(R.id.text_list_status);
            txtTotalHarga = itemView.findViewById(R.id.text_total_status);
            txtStatusPesanan = itemView.findViewById(R.id.text_status_pesanan);
            btnUploadTf = itemView.findViewById(R.id.btn_upload_tf);
            txtMetodeBayar = itemView.findViewById(R.id.text_metode_bayar_status);
            txtNamaLengkapToko = itemView.findViewById(R.id.text_toko_status);
        }
    }
}
