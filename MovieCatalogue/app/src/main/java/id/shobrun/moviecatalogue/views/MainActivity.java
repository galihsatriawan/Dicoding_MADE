package id.shobrun.moviecatalogue.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.views.adapter.SectionsPagerAdapter;
import id.shobrun.moviecatalogue.views.fragment.MovieCatalogueViewFragment;
import id.shobrun.moviecatalogue.views.fragment.TvShowFragment;
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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager!=null){
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    searchInFragment(s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });

        }

        return super.onCreateOptionsMenu(menu);
    }
    private void searchInFragment(String s){
        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:"+ R.id.view_pager+":"+viewPager.getCurrentItem());
        if(viewPager.getCurrentItem() == 0 && page != null){
            ((MovieCatalogueViewFragment)page).updateSearch(s);
        }else{
            ((TvShowFragment)page).updateSearch(s);
        }
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
