package id.shobrun.learnespresso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_data";
    TextView tvPrevActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Activity Second");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tvPrevActivity = findViewById(R.id.text_prev_activity);

        if(getIntent() != null){

            String data = getIntent().getStringExtra(EXTRA_DATA);
            Log.e(getLocalClassName(), "onCreate: SecondActivity "+ data);
            tvPrevActivity.setText(data);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
