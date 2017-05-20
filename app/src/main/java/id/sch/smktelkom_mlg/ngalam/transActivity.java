package id.sch.smktelkom_mlg.ngalam;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.ngalam.adapter.TransAdapter;
import id.sch.smktelkom_mlg.ngalam.baru.Trans;

/**
 * Created by Mokleters on 5/7/2017.
 */

public class transActivity extends AppCompatActivity implements TransAdapter.ITransAdapter {

    public static final String TRANS = "trans";
    ArrayList<Trans> mList = new ArrayList<>();
    TransAdapter mAdapter;
    ArrayList<Trans> mListAll=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new TransAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);

        fillData();

        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
                               {
                                   @Override
                                   public void onClick(View view)
                                   {
                                       goPic("https://www.instagram.com");
                                   }
                               }
        );

    }

    private void goPic(String instagram) {
        Uri ig=Uri.parse(instagram);
        Intent intent=new Intent(Intent.ACTION_VIEW, ig);
        if (intent.resolveActivity(getPackageManager())!=null)
            startActivity(intent);
    }

    private void fillData() {
        Resources resources = getResources();
        String[] arJudul = resources.getStringArray(R.array.trans);
        String[] arDeskripsi = resources.getStringArray(R.array.trans_details);
        TypedArray za=resources.obtainTypedArray(R.array.pictures);
        String[] arPict=new String[za.length()];
        for (int a=0;a<arPict.length;a++)
        {
            int id=za.getResourceId(a,0);
            arPict[a]= ContentResolver.SCHEME_ANDROID_RESOURCE+"://"
                    +resources.getResourcePackageName(id)+'/'
                    +resources.getResourceTypeName(id)+'/'
                    +resources.getResourceEntryName(id);
        }
        za.recycle();

        for (int a= 0; a < arJudul.length; a++) {
            mList.add(new Trans(arJudul[a], arDeskripsi[a], arPict[a]));
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
        Intent intent=new Intent(transActivity.this, TransDetail.class);
        intent.putExtra(TRANS, mList.get(pos));
        startActivity(intent);
    }
}



