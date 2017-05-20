package id.sch.smktelkom_mlg.ngalam.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.ngalam.R;
import id.sch.smktelkom_mlg.ngalam.baru.Trans;


/**
 * Created by Mokleters on 5/8/2017.
 */

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.ViewHolder> {

    ArrayList<Trans> transList;
    ITransAdapter mITransAdapter;


    public TransAdapter(Context context, ArrayList<Trans> transList) {
        this.transList = transList;
        mITransAdapter=(ITransAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transss, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trans trans = transList.get(position);
        holder.tvJudul.setText(trans.judul);
        holder.tvDeskripsi.setText(trans.deskripsi);
        holder.ivPict.setImageURI(Uri.parse(trans.pict));
    }

    @Override
    public int getItemCount() {

        if (transList != null)
            return transList.size();
        return 0;
    }

    public  interface ITransAdapter
    {
        void doClick(int pos);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        TextView tvDeskripsi;
        ImageView ivPict;

        public ViewHolder(View transssView) {
            super(transssView);
            tvJudul = (TextView) transssView.findViewById(R.id.title);
            tvDeskripsi = (TextView) transssView.findViewById(R.id.describe);
            ivPict=(ImageView) transssView.findViewById(R.id.pictView);
            itemView.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                mITransAdapter.doClick(getAdapterPosition());
                                            }
                                        }
            );
        }
    }

}

