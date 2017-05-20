package id.sch.smktelkom_mlg.ngalam.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.ngalam.R;
import id.sch.smktelkom_mlg.ngalam.model.Kuliner;

/**
 * Created by Mokleters on 04/30/2017.
 */

public class KulinerAdapter extends RecyclerView.Adapter<KulinerAdapter.ViewHolder> {

    IKulinerAdapter mIKulinerAdapter;
    ArrayList<Kuliner> kulinerList;

    public KulinerAdapter(Context context, ArrayList<Kuliner> kulinerList) {
        this.kulinerList = kulinerList;
        mIKulinerAdapter = (IKulinerAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kuliner kuliner = kulinerList.get(position);
        holder.tvJudul.setText(kuliner.judul);
        holder.tvDeskripsi.setText(kuliner.deskripsi);
        holder.ivFoto.setImageURI(Uri.parse(kuliner.foto));
    }

    @Override
    public int getItemCount() {
        if (kulinerList != null)
            return kulinerList.size();
        return 0;
    }

    public interface IKulinerAdapter {
        void doClick(int pos);

        void doFav(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;
        Button bEdit;
        Button bDelete;
        ImageButton ibShare;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvJudul = (TextView) itemView.findViewById(R.id.judul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.deskripsi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIKulinerAdapter.doClick(getAdapterPosition());
                }
            });

        }
    }
}

