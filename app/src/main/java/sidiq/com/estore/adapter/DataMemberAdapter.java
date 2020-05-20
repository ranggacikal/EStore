package sidiq.com.estore.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import sidiq.com.estore.R;
import sidiq.com.estore.model.DataMemberItem;

public class DataMemberAdapter extends RecyclerView.Adapter<DataMemberAdapter.DataMemberViewHolder> {

    private Context mContext;
    private List<DataMemberItem> dataMemberItems;

    public DataMemberAdapter(Context mContext, List<DataMemberItem> dataMemberItems) {
        this.mContext = mContext;
        this.dataMemberItems = dataMemberItems;
    }

    @NonNull
    @Override
    public DataMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_member, parent, false);
        return new DataMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataMemberViewHolder holder, int position) {
        final String linkImageData = dataMemberItems.get(position).getImageUser();

        Glide.with(mContext)
                .load(linkImageData)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgDataMember);

        holder.txtId.setText(dataMemberItems.get(position).getIdUser());
        holder.txtNama.setText(dataMemberItems.get(position).getNamaLengkap());
        holder.txtEmail.setText(dataMemberItems.get(position).getEmail());
        holder.txtNoTelepon.setText(dataMemberItems.get(position).getNoTelpon());
    }

    @Override
    public int getItemCount() {
        return dataMemberItems.size();
    }

    public class DataMemberViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDataMember;
        TextView txtId, txtNama, txtEmail, txtNoTelepon;
        public DataMemberViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDataMember = itemView.findViewById(R.id.img_data_member);
            txtId = itemView.findViewById(R.id.text_id_data_member);
            txtNama = itemView.findViewById(R.id.text_nama_data_member);
            txtEmail = itemView.findViewById(R.id.text_email_data_member);
            txtNoTelepon = itemView.findViewById(R.id.text_notelpon_data_member);
        }
    }
}
