package id.shobrun.moviecatalogue.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.views.fragment.MyPreferenceFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.setting_holder,new MyPreferenceFragment())
                .commit();
    }
}
