package id.sch.smktelkom_mlg.ngalam;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.ngalam.adapter.KulinerAdapter;
import id.sch.smktelkom_mlg.ngalam.model.Kuliner;

public class KMainActivity extends AppCompatActivity implements KulinerAdapter.IKulinerAdapter{

    public static final String KULINER = "kuliner";
    ArrayList<Kuliner> mList=new ArrayList<>();
    KulinerAdapter mAdapter;
    ArrayList<Kuliner> mListAll= new ArrayList<>();
    boolean isFilter;
    ArrayList<Integer> mListMapFilter=new ArrayList<>();
    String mQuery;
    private int[] arFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setContentView(R.layout.activity_maink);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter=new KulinerAdapter(this,mList);
        recyclerView.setAdapter(mAdapter);

        fillData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMaps("https://www.google.co.id/maps/@-7.9786453,112.631783,12z?hl=en");
            }
        });
    }

    private void goMaps(String url) {
        Uri maps=Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW, maps);
        if (intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
    }

    private void fillData() {
        Resources resources=getResources();
        String [] arJudul=resources.getStringArray(R.array.kplaces);
        String [] arDeskripsi=resources.getStringArray(R.array.kplace_desc);
        String [] arDetail=resources.getStringArray(R.array.kplace_details);
        String [] arLokasi=resources.getStringArray(R.array.kplace_locations);
        TypedArray a=resources.obtainTypedArray(R.array.kplaces_picture);
        String[] arFoto=new String[a.length()];
        for (int i=0;i<arFoto.length;i++){
            int id=a.getResourceId(i,0);
            arFoto[i]=ContentResolver.SCHEME_ANDROID_RESOURCE+"://"
                    +resources.getResourcePackageName(id)+'/'
                    +resources.getResourceTypeName(id)+'/'
                    +resources.getResourceEntryName(id);
        }
        a.recycle();

        for (int i=0;i<arFoto.length;i++){
            mList.add(new Kuliner(arJudul[i],arDeskripsi[i],arFoto[i],arDetail[i],arLokasi[i]));
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maink, menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
                                          {
                                              @Override
                                              public boolean onQueryTextSubmit(String query)
                                              {
                                                  return false;
                                              }

                                              @Override
                                              public boolean onQueryTextChange(String newText)
                                              {
                                                  mQuery=newText.toLowerCase();
                                                  doFilter(mQuery);
                                                  return true;
                                              }
                                          }
        );

        return true;
    }

    private void doFilter(String query) {
        if(!isFilter)
        {
            mListAll.clear();
            mListAll.addAll(mList);
            isFilter=true;
        }
        mList.clear();
        if (query==null||query.isEmpty())
        {
            mList.addAll(mListAll);
            isFilter=false;
        }
        else
        {
            mListMapFilter.clear();
            for (int i=0;i<mListAll.size();i++)
            {
                Kuliner kuliner=mListAll.get(i);
                if (kuliner.judul.toLowerCase().contains(query)||
                        kuliner.deskripsi.toLowerCase().contains(query)||
                        kuliner.lokasi.toLowerCase().contains(query))
                {
                    mList.add(kuliner);
                    mListMapFilter.add(i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doClick(int pos) {
        Intent intent=new Intent(this, KDetailActivity.class);
        intent.putExtra(KULINER,mList.get(pos));
        startActivity(intent);
    }

    @Override
    public void doFav(int pos) {

    }
}
