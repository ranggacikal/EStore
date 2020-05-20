package sidiq.com.estore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import sidiq.com.estore.LihatBuktiTransferActivity;
import sidiq.com.estore.PesananTokoActivity;
import sidiq.com.estore.R;
import sidiq.com.estore.StatusPesananTransaksiActivity;
import sidiq.com.estore.model.DataPesananTokoItem;

public class PesananTokoAdapter extends RecyclerView.Adapter<PesananTokoAdapter.PesananTokoViewHolder> {
    Context mContext;
    List<DataPesananTokoItem> pesananTokoItems;

    public PesananTokoAdapter(Context mContext, List<DataPesananTokoItem> pesananTokoItems) {
        this.mContext = mContext;
        this.pesananTokoItems = pesananTokoItems;
    }

    @NonNull
    @Override
    public PesananTokoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan_toko, parent, false);
        return new PesananTokoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesananTokoViewHolder holder, final int position) {

        holder.txtNama.setText(pesananTokoItems.get(position).getNamaPelanggan());
        holder.txtIdPesanan.setText(pesananTokoItems.get(position).getIdTransaksi());
        holder.txtListPesanan.setText(pesananTokoItems.get(position).getListPesanan());
        holder.txtStatusPesanan.setText(pesananTokoItems.get(position).getStatusPesanan());
        holder.txtMetodePengambilan.setText(pesananTokoItems.get(position).getMetodePengambilan());
        holder.txtAlamat.setText(pesananTokoItems.get(position).getAlamat());
        holder.txtTanggal.setText(pesananTokoItems.get(position).getTanggalTransaksi());
        holder.txtMetodePembayaran.setText(pesananTokoItems.get(position).getMetodePembayaran());

        int total = Integer.parseInt(pesananTokoItems.get(position).getTotalHarga());

        Locale localId = new Locale("in", "ID");
        NumberFormat formatRp = NumberFormat.getCurrencyInstance(localId);

        holder.txtTotalPesanan.setText(formatRp.format(total));

        String metodeBayar = pesananTokoItems.get(position).getMetodePembayaran();

        if (metodeBayar.equals("Cash")){
            holder.btnCheckTf.setVisibility(View.GONE);
        }else if (metodeBayar.equals("Transfer")){
            holder.btnCheckTf.setVisibility(View.VISIBLE);
            holder.btnCheckTf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLihat = new Intent(mContext, LihatBuktiTransferActivity.class);
                    intentLihat.putExtra(LihatBuktiTransferActivity.EXTRA_ID_TRANSAKSI, pesananTokoItems.get(position).getIdTransaksi());
                    intentLihat.putExtra(LihatBuktiTransferActivity.EXTRA_BUKTI_TF_TRANSAKSI, pesananTokoItems.get(position).getBuktiTransfer());
                    intentLihat.putExtra(LihatBuktiTransferActivity.EXTRA_STATUS_PESANAN, pesananTokoItems.get(position).getStatusPesanan());
                    mContext.startActivity(intentLihat);
                    ((PesananTokoActivity)mContext).finish();
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StatusPesananTransaksiActivity.class);
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_ID_TRANSAKSI, pesananTokoItems.get(position).getIdTransaksi());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_NAMA_PELANGGAN_TRANSAKSI, pesananTokoItems.get(position).getNamaPelanggan());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_LIST_PESANAN_TRANSAKSI, pesananTokoItems.get(position).getListPesanan());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_TOTAL_HARGA_TRANSAKSI, pesananTokoItems.get(position).getTotalHarga());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_METODE_PEMBAYARAN_TRANSAKSI, pesananTokoItems.get(position).getMetodePembayaran());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_METODE_PENGAMBILAN_TRANSAKSI, pesananTokoItems.get(position).getMetodePengambilan());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_ALAMAT_TRANSAKSI, pesananTokoItems.get(position).getAlamat());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_STATUS_PESANAN_TRANSAKSI, pesananTokoItems.get(position).getStatusPesanan());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_STATUS_TRANSAKSI, pesananTokoItems.get(position).getStatusTransaksi());
                intent.putExtra(StatusPesananTransaksiActivity.EXTRA_TANGGAL_TRANSAKSI, pesananTokoItems.get(position).getTanggalTransaksi());
                mContext.startActivity(intent);
                ((PesananTokoActivity)mContext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return pesananTokoItems.size();
    }

    public class PesananTokoViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdPesanan, txtListPesanan, txtTotalPesanan, txtStatusPesanan, txtMetodePengambilan, txtAlamat, txtTanggal,
        txtMetodePembayaran, txtNama;

        Button btnCheckTf;
        public PesananTokoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.text_nama_pesanan_toko);
            txtIdPesanan = itemView.findViewById(R.id.text_id_pesanan_toko);
            txtListPesanan = itemView.findViewById(R.id.text_list_pesanan_toko);
            txtTotalPesanan = itemView.findViewById(R.id.text_total_pesanan_toko);
            txtStatusPesanan = itemView.findViewById(R.id.text_status_pesanan_toko);
            txtMetodePengambilan = itemView.findViewById(R.id.text_metode_pengambilan_toko);
            txtAlamat = itemView.findViewById(R.id.text_alamat_pesanan_toko);
            txtTanggal = itemView.findViewById(R.id.text_tanggal_pesanan_toko);
            txtMetodePembayaran = itemView.findViewById(R.id.text_metode_bayar_pesanan_toko);
            btnCheckTf = itemView.findViewById(R.id.btn_check_tf);
        }
    }
}
