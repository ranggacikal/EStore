package sidiq.com.estore.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sidiq.com.estore.R;
import sidiq.com.estore.RequestMemberActivity;
import sidiq.com.estore.api.ConfigRetrofit;
import sidiq.com.estore.model.DataPermintaanMemberItem;
import sidiq.com.estore.model.ResponseConfirmReqMember;

public class RequestMemberAdapter extends RecyclerView.Adapter<RequestMemberAdapter.RequestMemberViewHolder> {

    private Context mContext;
    private List<DataPermintaanMemberItem> dataPermintaanMemberItems;

    public RequestMemberAdapter(Context mContext, List<DataPermintaanMemberItem> dataPermintaanMemberItems) {
        this.mContext = mContext;
        this.dataPermintaanMemberItems = dataPermintaanMemberItems;
    }

    @NonNull
    @Override
    public RequestMemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request_member, parent, false);
        return new RequestMemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestMemberViewHolder holder, final int position) {
        final String linkImage = dataPermintaanMemberItems.get(position).getImageUser();

        Glide.with(mContext)
                .load(linkImage)
                .placeholder(R.drawable.logoestore)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgReqMember);

        holder.txtNama.setText(dataPermintaanMemberItems.get(position).getNamaLengkap());
        holder.txtEMail.setText(dataPermintaanMemberItems.get(position).getEmail());
        holder.txtNoTelpon.setText(dataPermintaanMemberItems.get(position).getNoTelpon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogConfirm = new Dialog(mContext);
                dialogConfirm.setContentView(R.layout.dialog_confirm_member);
                dialogConfirm.setTitle("Konfirmasi Member");
                final TextView konfirmasi = dialogConfirm.findViewById(R.id.text_dialog_konfirmasi_member);
                final TextView tolak = dialogConfirm.findViewById(R.id.text_dialog_tolak_member);
                final TextView abaikan = dialogConfirm.findViewById(R.id.text_abaikan_member);

                dialogConfirm.show();
                konfirmasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String id_user = dataPermintaanMemberItems.get(position).getIdUser();
                        String status_member = "iya";

                        ConfigRetrofit.service.KonfirmasiMember(id_user, status_member).enqueue(new Callback<ResponseConfirmReqMember>() {
                            @Override
                            public void onResponse(Call<ResponseConfirmReqMember> call, Response<ResponseConfirmReqMember> response) {
                                int status = response.body().getSukses();
                                String pesan = response.body().getPesan();
                                if (status == 1){
                                    Toast.makeText(mContext, pesan, Toast.LENGTH_SHORT).show();
                                    dialogConfirm.dismiss();
                                    RequestMemberActivity.req.LoadRecyclerReqMember();
                                }else{
                                    Toast.makeText(mContext, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseConfirmReqMember> call, Throwable t) {

                            }
                        });
                    }
                });

                tolak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String id_user = dataPermintaanMemberItems.get(position).getIdUser();
                        String status_member = "tolak";

                        ConfigRetrofit.service.KonfirmasiMember(id_user, status_member).enqueue(new Callback<ResponseConfirmReqMember>() {
                            @Override
                            public void onResponse(Call<ResponseConfirmReqMember> call, Response<ResponseConfirmReqMember> response) {
                                int status = response.body().getSukses();
                                String pesan = response.body().getPesan();
                                if (status == 1){
                                    Toast.makeText(mContext, pesan, Toast.LENGTH_SHORT).show();
                                    dialogConfirm.dismiss();
                                    RequestMemberActivity.req.LoadRecyclerReqMember();
                                }else{
                                    Toast.makeText(mContext, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseConfirmReqMember> call, Throwable t) {

                            }
                        });
                    }
                });

                abaikan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogConfirm.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPermintaanMemberItems.size();
    }

    public class RequestMemberViewHolder extends RecyclerView.ViewHolder {
        ImageView imgReqMember;
        TextView txtNama, txtEMail, txtNoTelpon;
        public RequestMemberViewHolder(@NonNull View itemView) {
            super(itemView);

            imgReqMember = itemView.findViewById(R.id.img_req_member);
            txtNama = itemView.findViewById(R.id.text_nama_req_member);
            txtEMail = itemView.findViewById(R.id.text_email_req_member);
            txtNoTelpon = itemView.findViewById(R.id.text_notelpon_req_member);
        }
    }
}
