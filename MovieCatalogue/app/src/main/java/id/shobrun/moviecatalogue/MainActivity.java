package id.shobrun.moviecatalogue;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import id.shobrun.moviecatalogue.component.SectionsPagerAdapter;
import id.shobrun.moviecatalogue.presenter.MainPresenter;
import id.shobrun.moviecatalogue.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter mPresenter;
    private ViewPager viewPager;
    private TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        initPresenter();
        mPresenter.onLoad();
    }
    private void initComponent(){
        viewPager = findViewById(R.id.view_pager);
        tabs = findViewById(R.id.tabs);
    }
    private void initPresenter(){
        mPresenter = new MainPresenter(getApplicationContext(),this);
    }
    @Override
    public void showActionBar() {

    }

    @Override
    public void showTabLayout() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }
}
