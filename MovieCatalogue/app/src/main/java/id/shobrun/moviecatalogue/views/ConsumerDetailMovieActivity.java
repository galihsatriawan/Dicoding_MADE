package id.shobrun.moviecatalogue.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.viewmodels.ConsumerDetailMovieViewModel;
import id.shobrun.moviecatalogue.views.iview.IConsumerDetailMovieView;

public class ConsumerDetailMovieActivity extends AppCompatActivity implements IConsumerDetailMovieView {

    private final String TAG = getClass().getSimpleName();
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_ID_MOVIE = "extra_id_movie";
    public static final String ACTION_VIEW = "android.intent.action.VIEW";
    private FrameLayout menuFavorite;
    private ImageView iconFavorite;
    private ImageView imgPoster;
    private ImageView imgBanner;
    private TextView tvRating;
    private TextView tvContent;
    private TextView tvProduction;
    private TextView tvTitle;
    private TextView tvDuration;
    private TextView tvRelease;
    private AppCompatRatingBar ratingBar;
    private ConsumerDetailMovieViewModel viewModel;
    private Movie mMovie;

    private ProgressBar progressBar;
    private NestedScrollView content;
    private RelativeLayout containerMessage;
    private TextView tvMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_detail_movie);
        initUI();
        initViewModel();
        showActionBar();
        this.invalidateOptionsMenu();
        showProgress();
        if(getIntent() != null){
            int id = getIntent().getIntExtra(EXTRA_ID_MOVIE,0);
            if(id !=0){
                viewModel.getMovieById(id);

            }else{
                mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
                viewModel.setMovie(mMovie);
                hideProgress();
                showDetailMovie(mMovie);
            }

        }
    }
    @Override
    public void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ConsumerDetailMovieViewModel.class);
        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(@Nullable Movie movie) {
                showDetailMovie(movie);
            }
        });
        viewModel.setAppView(getApplicationContext(),this);
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
            case R.id.action_settings:
                Intent settings = new Intent(this,SettingActivity.class);
                startActivity(settings);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final  MenuItem favoriteMenuItem = menu.findItem(R.id.action_favorite);
        FrameLayout rootView = (FrameLayout) favoriteMenuItem.getActionView();

        iconFavorite = rootView.findViewById(R.id.ic_favorite);
        if(mMovie!=null){
            viewModel.checkMovieById(mMovie.getId());
        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(favoriteMenuItem);
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }
    private void isFavorite(){
        Log.d(TAG, "isFavorite: "+mMovie);
        viewModel.updateMovieAfterAction(mMovie);
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
        content  = findViewById(R.id.container_content);
        tvMessage = findViewById(R.id.text_message);
        progressBar = findViewById(R.id.progressBar);
        containerMessage = findViewById(R.id.container_message);
    }

    @Override
    public void showDetailMovie(Movie movie) {
        mMovie = movie;
        Log.d(TAG, "showDetailMovie: "+movie.getTitle());
        tvTitle.setText(movie.getTitle());
        tvRating.setText(String.valueOf(movie.getVote_average()));
        tvProduction.setText(movie.getProductionCompany());
        SimpleDateFormat pars = new SimpleDateFormat("yyyy-dd-MM",Locale.getDefault());
        SimpleDateFormat dtf = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());

        try {
            Date release_date = pars.parse((movie.getRelease_date()));
            tvRelease.setText(dtf.format(release_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvContent.setText(movie.getOverview());
        tvDuration.setText(getDuration(movie.getDuration()));
        ratingBar.setRating(movie.getVote_average().floatValue());
        Glide.with(getApplicationContext()).load(Constants.IMAGE_BASE_URL+movie.getPoster_path()).into(imgPoster);
        Glide.with(getApplicationContext()).load(Constants.BACKDROP_BASE_URL+movie.getBackdrop_path()).into(imgBanner);
        Log.d(TAG, "showDetailMovie: "+movie.getTags());

        if(movie.getTags()!=null && iconFavorite!=null){
            if(movie.getTags().contains(Constants.TAGS_FAVORITE)) {
                setIconFavorite(R.drawable.ic_favorite_black_24dp);
            }

        }
    }

    @Override
    public void setIconFavorite(int res) {
        iconFavorite.setImageResource(res);
    }

    @Override
    public void showProgress() {
        content.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        containerMessage.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        containerMessage.setVisibility(View.GONE);
    }
    @Override
    public void showMessage(String message) {
        content.setVisibility(View.GONE);
        containerMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(message);
    }

    @Override
    public void showMessageToast(String str) {
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
        Toolbar toolbar = findViewById(R.id.appbarlayout_tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Movie");
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
