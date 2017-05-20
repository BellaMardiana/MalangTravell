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

import id.sch.smktelkom_mlg.ngalam.model.Hotel;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Hotel hotel=(Hotel) getIntent().getSerializableExtra(MainActivity.HOTEL);
        setTitle(hotel.judul);
        ImageView ivFoto=(ImageView) findViewById(R.id.imageFoto);
        ivFoto.setImageURI(Uri.parse(hotel.foto));

        TextView tvDeskripsi=(TextView) findViewById(R.id.place_detail);
        tvDeskripsi.setText(hotel.detail+"\n\n"+hotel.deskripsi);

        TextView tvLokasi=(TextView) findViewById(R.id.place_location);
        tvLokasi.setText(hotel.lokasi);

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
                goBook("https://www.agoda.com");
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
