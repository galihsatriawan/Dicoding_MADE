package id.shobrun.moviecatalogue.views;

import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.shobrun.moviecatalogue.R;

import id.shobrun.moviecatalogue.views.adapter.FavoriteSectionsPagerAdapter;
import id.shobrun.moviecatalogue.views.iview.IMainFavoriteView;

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

        MenuItem item_search = menu.findItem(R.id.search);
        item_search.setVisible(false);

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
            case R.id.action_settings:
                Intent settings = new Intent(this,SettingActivity.class);
                startActivity(settings);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("List Favorite");
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
