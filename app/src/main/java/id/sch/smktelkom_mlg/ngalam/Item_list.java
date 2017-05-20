package id.sch.smktelkom_mlg.ngalam;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Mokleters on 04/30/2017.
 */


public class Item_list extends DetailActivity {
    TextView tvrating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initiate rating bar and a button
        final RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingbar1);
        final TextView tvrating = (TextView) findViewById(R.id.textview3);

    }
}