package id.shobrun.moviecatalogue.views;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.views.fragment.ConsumerMovieFavoriteFragment;
import id.shobrun.moviecatalogue.views.iview.IConsumerMainView;

public class ConsumerMainActivity extends AppCompatActivity implements IConsumerMainView {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_main);
        initUI();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_consumer,ConsumerMovieFavoriteFragment.getInstance())
                .commit();
    }
    private void initUI(){
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Content Provider");
    }
    @Override
    public void showActionBar() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem itemFavorite  = menu.findItem(R.id.action_list_favorite);
        MenuItem itemContentProvider = menu.findItem(R.id.action_content_provider);
        MenuItem itemSearch  = menu.findItem(R.id.action_search);
        itemFavorite.setVisible(false);
        itemContentProvider.setVisible(false);
        itemSearch.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
