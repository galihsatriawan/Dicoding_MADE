package id.shobrun.moviecatalogue.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.viewmodels.DetailTvShowViewModel;
import id.shobrun.moviecatalogue.views.iview.IDetailTvShowView;

public class DetailTvActivity extends AppCompatActivity implements IDetailTvShowView {
    public static final String EXTRA_TV = "extra_tv";
    private ImageView imgPoster;
    private ImageView imgBanner;
    private TextView tvRating;
    private TextView tvContent;
    private TextView tvProduction;
    private TextView tvTitle;
    private TextView tvDuration;
    private TextView tvRelease;
    private ImageView iconFavorite;
    private AppCompatRatingBar ratingBar;
    private DetailTvShowViewModel viewModel;
    private TvShow mTvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);
        initUI();
        initViewModel();

        if(getIntent() != null){
            showActionBar();
            mTvShow = getIntent().getParcelableExtra(EXTRA_TV);
            viewModel.setTvShow(mTvShow);
            showDetailTvShow(mTvShow);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_change_settings:
                Intent setting = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(setting);
                break;
            case R.id.action_favorite:
                Log.d(this.getClass().getSimpleName(), "onOptionsItemSelected: ");
                isFavorite();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final  MenuItem favoriteMenuItem = menu.findItem(R.id.action_favorite);
        FrameLayout rootView = (FrameLayout) favoriteMenuItem.getActionView();

        iconFavorite = rootView.findViewById(R.id.ic_favorite);
        viewModel.checkTvShowById(mTvShow.getId());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(favoriteMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    private void isFavorite(){
        viewModel.updateTvShowAfterAction(mTvShow);
    }

    public void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel.class);
        viewModel.setAppView(getApplicationContext(),this);
        viewModel.getTvShow().observe(this, new Observer<TvShow>() {
            @Override
            public void onChanged(@Nullable TvShow tvShow) {
                showDetailTvShow(tvShow);
            }
        });

    }
    @Override
    public void initUI(){
        tvTitle = findViewById(R.id.text_title_movie);
        tvRating = findViewById(R.id.text_rating);
        tvProduction = findViewById(R.id.text_production);
        tvContent = findViewById(R.id.text_content);
        tvDuration = findViewById(R.id.text_duration);
        tvRelease = findViewById(R.id.text_release_date);
        imgPoster = findViewById(R.id.image_poster);
        imgBanner = findViewById(R.id.img_banner_poster);
        ratingBar = findViewById(R.id.rb_rating);

    }

    @Override
    public void setIconFavorite(int res) {
        iconFavorite.setImageResource(res);
    }

    @Override
    public void showDetailTvShow(TvShow tvShow) {
        tvTitle.setText(tvShow.getName());
        tvRating.setText(String.valueOf(tvShow.getVote_average()));
        tvProduction.setText(tvShow.getProductionCompany());
        SimpleDateFormat dtf = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());

        try {
            Date release_date = dtf.parse((tvShow.getFirst_air_date()));
            tvRelease.setText(dtf.format(release_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvContent.setText(tvShow.getOverview());
        tvDuration.setText(getDuration(tvShow.getDuration()));
        ratingBar.setRating(tvShow.getVote_average().floatValue());
        Glide.with(getApplicationContext()).load(Constants.IMAGE_BASE_URL+tvShow.getPoster_path()).into(imgPoster);
        Glide.with(getApplicationContext()).load(Constants.BACKDROP_BASE_URL+tvShow.getBackdrop_path()).into(imgBanner);
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private String getDuration(int duration){
        String result;
        int hours = duration/60;
        int minutes = duration%60;
        result = hours +" Hours " +minutes +" Minutes";
        return result;
    }
    @Override
    public void showActionBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail TV Show");
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
