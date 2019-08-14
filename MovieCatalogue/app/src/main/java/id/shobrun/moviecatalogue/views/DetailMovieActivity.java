package id.shobrun.moviecatalogue.views;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.data.Movie;
import id.shobrun.moviecatalogue.contracts.DetailMovieContract;
import id.shobrun.moviecatalogue.presenters.DetailMoviePresenter;


public class DetailMovieActivity extends AppCompatActivity implements DetailMovieContract.View{
    public static final String EXTRA_MOVIE = "extra_movie";
    private ImageView imgPoster;
    private ImageView imgBanner;
    private TextView tvRating;
    private TextView tvContent;
    private TextView tvProduction;
    private TextView tvTitle;
    private TextView tvDuration;
    private TextView tvRelease;
    private AppCompatRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        DetailMoviePresenter presenter = new DetailMoviePresenter(getApplicationContext(),this);

        if(getIntent() != null){
            Movie mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            presenter.loadDetailMovie(mMovie);
        }
    }
    @Override
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
        }
        return super.onOptionsItemSelected(item);
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
    public void showDetailMovie(Movie movie) {
        tvTitle.setText(movie.getName());
        tvRating.setText(String.valueOf(movie.getRating()));
        tvProduction.setText(movie.getProductionCompany());
        SimpleDateFormat dtf = new SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.getDefault());
        tvRelease.setText(dtf.format(movie.getReleaseDate()));
        tvContent.setText(movie.getDescription());
        tvDuration.setText(getDuration(movie.getDuration()));
        ratingBar.setRating((float)movie.getRating());
        Glide.with(getApplicationContext()).load(movie.getPoster()).into(imgPoster);
        Glide.with(getApplicationContext()).load(movie.getPoster()).into(imgBanner);
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
