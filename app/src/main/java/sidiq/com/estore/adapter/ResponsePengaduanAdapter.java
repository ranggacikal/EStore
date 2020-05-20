package sidiq.com.estore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sidiq.com.estore.R;
import sidiq.com.estore.model.DataResponsePengaduanItem;
import sidiq.com.estore.model.ResponseBalasanPengaduan;

public class ResponsePengaduanAdapter extends RecyclerView.Adapter<ResponsePengaduanAdapter.ResponsePengaduanViewHolder> {

    Context mContext;
    List<DataResponsePengaduanItem> balasanPengaduanItems;

    public ResponsePengaduanAdapter(Context mContext, List<DataResponsePengaduanItem> balasanPengaduanItems) {
        this.mContext = mContext;
        this.balasanPengaduanItems = balasanPengaduanItems;
    }

    @NonNull
    @Override
    public ResponsePengaduanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_response_pengaduan, parent, false);
        return new ResponsePengaduanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResponsePengaduanViewHolder holder, int position) {
        holder.txtNamaPengadu.setText(balasanPengaduanItems.get(position).getNamaPengadu());
        holder.txtIsiAduan.setText(balasanPengaduanItems.get(position).getPengaduan());
        holder.txtBalasanAduan.setText(balasanPengaduanItems.get(position).getBalasanPengaduan());
    }

    @Override
    public int getItemCount() {
        return balasanPengaduanItems.size();
    }

    public class ResponsePengaduanViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaPengadu, txtIsiAduan, txtBalasanAduan;
        public ResponsePengaduanViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaPengadu = itemView.findViewById(R.id.text_balas_nama_pengadu);
            txtBalasanAduan = itemView.findViewById(R.id.text_isi_balasan);
            txtIsiAduan = itemView.findViewById(R.id.text_isi_aduan);
        }
    }
}
