package id.shobrun.moviecatalogue.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.views.fragment.MyPreferenceFragment;
import id.shobrun.moviecatalogue.views.iview.IRootView;

public class SettingActivity extends AppCompatActivity implements IRootView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        showActionBar();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.setting_holder,new MyPreferenceFragment())
                .commit();
    }

    @Override
    public void showActionBar() {
        Toolbar toolbar = findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Settings");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
