package id.shobrun.consumer.views;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.shobrun.consumer.R;

import id.shobrun.consumer.views.adapter.FavoriteSectionsPagerAdapter;
import id.shobrun.consumer.views.iview.IMainFavoriteView;

public class MainFavoriteActivity extends AppCompatActivity implements IMainFavoriteView {
    private ViewPager viewPager;
    private TabLayout tabs;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_favorite);
        initComponent();
        showTabLayout();
    }
    private void initComponent(){
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        showActionBar();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        getMenuInflater().inflate(R.menu.main_menu,menu);


        MenuItem item_list= menu.findItem(R.id.action_list_favorite);
        item_list.setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_change_settings:
                Intent setting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(setting);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTabLayout() {
        FavoriteSectionsPagerAdapter sectionsPagerAdapter = new FavoriteSectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void showActionBar() {
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }


}
