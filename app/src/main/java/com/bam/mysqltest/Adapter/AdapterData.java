package com.bam.mysqltest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bam.mysqltest.Model.DataModel;
import com.bam.mysqltest.R;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;

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
        }
    }
}
