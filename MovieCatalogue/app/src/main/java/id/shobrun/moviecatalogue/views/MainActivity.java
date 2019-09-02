package id.shobrun.moviecatalogue.views;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.views.adapter.SectionsPagerAdapter;
import id.shobrun.moviecatalogue.views.iview.IMainView;


public class MainActivity extends AppCompatActivity implements IMainView {
    private ViewPager viewPager;
    private TabLayout tabs;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        showTabLayout();
    }
    private void initUI(){
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }
    @Override
    public void showActionBar() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_change_settings:
                Intent setting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(setting);
                break;
            case R.id.action_list_favorite:
                Intent list_favorite = new Intent(this, MainFavoriteActivity.class);
                startActivity(list_favorite);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTabLayout() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }


}
