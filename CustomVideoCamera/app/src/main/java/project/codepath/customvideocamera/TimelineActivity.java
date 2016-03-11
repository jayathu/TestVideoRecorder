package project.codepath.customvideocamera;

import android.app.Activity;
import android.os.Bundle;

public class TimelineActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, TimelineFragment.newInstance())
                    .commit();
        }


    }


}
