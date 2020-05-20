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

import java.util.List;

import sidiq.com.estore.PesanBarangActivity;
import sidiq.com.estore.R;
import sidiq.com.estore.SharedPreference.SharedPreferencedConfig;
import sidiq.com.estore.model.DataTokoItem;

public class DaftarTokoAdapter extends RecyclerView.Adapter<DaftarTokoAdapter.DaftarTokoViewHolder> {

    private Context mContext;
    private List<DataTokoItem> tokoItems;

    private SharedPreferencedConfig preferencedConfig;



    public DaftarTokoAdapter(Context mContext, List<DataTokoItem> tokoItems) {
        this.mContext = mContext;
        this.tokoItems = tokoItems;
    }

    @NonNull
    @Override
    public DaftarTokoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daftar_toko, parent, false);
        return new DaftarTokoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarTokoViewHolder holder, final int position) {
        final String urlImg = tokoItems.get(position).getImageUser();
        Glide.with(mContext)
                .load(urlImg)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgToko);

        holder.txtNamaToko.setText(tokoItems.get(position).getNamaLengkap());
        holder.txtAlamatToko.setText(tokoItems.get(position).getAlamat());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencedConfig = new SharedPreferencedConfig(mContext);
                Intent pindahPesan = new Intent(mContext, PesanBarangActivity.class);
                pindahPesan.putExtra(PesanBarangActivity.EXTRA_ID_TOKO, tokoItems.get(position).getIdUser());

                String nama_toko = tokoItems.get(position).getNamaLengkap();
                preferencedConfig.savePrefString(SharedPreferencedConfig.PREFERENCE_NAMA_TOKO_STATUS, nama_toko);
                mContext.startActivity(pindahPesan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tokoItems.size();
    }

    public class DaftarTokoViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaToko, txtAlamatToko;
        ImageView imgToko;
        public DaftarTokoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaToko = itemView.findViewById(R.id.text_nama_daftar_toko);
            txtAlamatToko = itemView.findViewById(R.id.text_alamat_daftar_toko);
            imgToko = itemView.findViewById(R.id.img_daftar_toko);
        }
    }
}
