package sidiq.com.estore.adapter;

import android.content.Context;
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
import sidiq.com.estore.model.DataUserItem;

public class DataUserAdapter extends RecyclerView.Adapter<DataUserAdapter.DataUserViewHolder> {

    private Context mContext;
    private List<DataUserItem> dataUserItems;

    public DataUserAdapter(Context mContext, List<DataUserItem> dataUserItems) {
        this.mContext = mContext;
        this.dataUserItems = dataUserItems;
    }

    @NonNull
    @Override
    public DataUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_user, parent, false);
        return new DataUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataUserViewHolder holder, int position) {

        final String linkImage = dataUserItems.get(position).getImageUser();

        Glide.with(mContext)
                .load(linkImage)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgUser);

        holder.txtIdUser.setText(dataUserItems.get(position).getIdUser());
        holder.txtNamaUser.setText(dataUserItems.get(position).getNamaLengkap());
        holder.txtEmailUser.setText(dataUserItems.get(position).getEmail());
        holder.txtLevelUser.setText(dataUserItems.get(position).getLevel());
    }

    @Override
    public int getItemCount() {
        return dataUserItems.size();
    }

    public class DataUserViewHolder extends RecyclerView.ViewHolder {

        TextView txtIdUser, txtNamaUser, txtEmailUser, txtLevelUser;
        ImageView imgUser;
        public DataUserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIdUser = itemView.findViewById(R.id.text_id_data_user);
            txtNamaUser = itemView.findViewById(R.id.text_nama_data_user);
            txtEmailUser = itemView.findViewById(R.id.text_email_data_user);
            txtLevelUser = itemView.findViewById(R.id.text_level_data_user);
            imgUser = itemView.findViewById(R.id.img_data_user);
        }
    }
}
