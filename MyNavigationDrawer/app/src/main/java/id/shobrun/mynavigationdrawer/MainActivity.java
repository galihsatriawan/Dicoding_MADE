package id.shobrun.mynavigationdrawer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CircleImageView profileCircleImageView;
    String profileImageUrl = "https://media.licdn.com/dms/image/C5103AQG2S9vQSMNIOg/profile-displayphoto-shrink_200_200/0?e=1568851200&v=beta&t=DZkY7Vtkh9Q1I0FKZg5nC1a0Fyv3eEuq99QcRLPPh1Q";

    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Home");
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Halo ini Action dari snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        profileCircleImageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
        Glide.with(MainActivity.this)
                .load(profileImageUrl)
                .into(profileCircleImageView);
        // First Run
        if(savedInstanceState == null){
            Fragment currentFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
                    mFragmentTransaction.add(R.id.content_main,currentFragment)
                                        .commit();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        drawer.removeDrawerListener(toggle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Fragment fragment = null;
        String title = "";
        switch (id){
            case R.id.nav_home:
                title = "Home";
                fragment = new HomeFragment();
                break;
            case R.id.nav_camera :
                title = "Camera";
                fragment = new PageFragment();
                bundle.putString(PageFragment.EXTRAS,"Camera");
                fragment.setArguments(bundle);
                break;
            case R.id.nav_gallery :
                title = "Gallery";
                fragment = new PageFragment();
                bundle.putString(PageFragment.EXTRAS,"Gallery");
                fragment.setArguments(bundle);
                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_tools:
                break;
            case R.id.nav_cart :
                break;

            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            case R.id.nav_map:
                break;
            case R.id.nav_hotel:
                break;
            case R.id.nav_subway:
                break;

        }
        if(fragment!= null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main,fragment)
                    .commit();
        }
        if(getSupportActionBar()!= null)
            getSupportActionBar().setTitle(title);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
