package com.bam.mysqltest.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bam.mysqltest.API.APIRequestData;
import com.bam.mysqltest.API.RetroServer;
import com.bam.mysqltest.Activity.MainActivity;
import com.bam.mysqltest.Model.DataModel;
import com.bam.mysqltest.Model.ResponseModel;
import com.bam.mysqltest.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;
    private int id;

    public AdapterData(Context ctx, List<DataModel> listData){
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvid.setText(String.valueOf(dm.getId()));
        holder.tvname.setText(dm.getName());
        holder.tvalamat.setText(dm.getAlamat());
        holder.tvtelepon.setText(dm.getTelepon());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvid, tvname, tvalamat, tvtelepon;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tv_id);
            tvname = itemView.findViewById(R.id.tv_name);
            tvalamat = itemView.findViewById(R.id.tv_alamat);
            tvtelepon = itemView.findViewById(R.id.tv_telepon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                    dialog.setMessage("aa");
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setCancelable(true);

                    id = Integer.parseInt(tvid.getText().toString());
                    dialog.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            delete();
                            dialogInterface.dismiss();
                            ((MainActivity) ctx).retrieveData();
                        }
                    });

                    dialog.setNegativeButton("ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    dialog.show();

                    return;
                }
            });
        }
        private void delete(){
            APIRequestData ardData = RetroServer.konRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hap = ardData.ardDelect(id);

            hap.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx,kode+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
