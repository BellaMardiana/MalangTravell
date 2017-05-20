package id.sch.smktelkom_mlg.ngalam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.smktelkom_mlg.ngalam.model.Kuliner;

public class KDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Kuliner kuliner=(Kuliner) getIntent().getSerializableExtra(KMainActivity.KULINER);
        setTitle(kuliner.judul);
        ImageView ivFoto=(ImageView) findViewById(R.id.imageFoto);
        ivFoto.setImageURI(Uri.parse(kuliner.foto));

        TextView tvDeskripsi=(TextView) findViewById(R.id.kplace_detail);
        tvDeskripsi.setText(kuliner.detail+"\n\n"+kuliner.deskripsi);

        TextView tvLokasi=(TextView) findViewById(R.id.place_location);
        tvLokasi.setText(kuliner.lokasi);

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
                goBook("https://www.tripadvisor.co.id/RestaurantsNear-g297710-d8555945-Malang_City_Square-Malang_East_Java_Java.html");
            }
        });


    }


    private void goBook(String book) {
        Uri maps = Uri.parse(book);
        Intent intent = new Intent(Intent.ACTION_VIEW, maps);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }
}

