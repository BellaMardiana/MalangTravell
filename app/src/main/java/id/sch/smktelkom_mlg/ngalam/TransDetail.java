package id.sch.smktelkom_mlg.ngalam;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.smktelkom_mlg.ngalam.baru.Trans;

import static id.sch.smktelkom_mlg.ngalam.R.id.tips;

public class TransDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Trans trans = (Trans) getIntent().getSerializableExtra(transActivity.TRANS);
        setTitle(trans.judul);

        ImageView ivFoto=(ImageView) findViewById(R.id.imageViewPic);
        ivFoto.setImageURI(Uri.parse(trans.pict));

        TextView tvJudul=(TextView) findViewById(tips);
        tvJudul.setText(trans.judul);

        TextView tvDesk=(TextView) findViewById(R.id.tips_detail);
        tvDesk.setText(trans.deskripsi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
                                             {
                                                 @Override
                                                 public void onClick(View v)
                                                 {
                                                     onBackPressed();
                                                 }
                                             }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
