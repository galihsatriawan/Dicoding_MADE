package id.shobrun.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Locale;

import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.presenter.DetailMoviePresenter;
import id.shobrun.moviecatalogue.view.DetailMovieView;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {
    public static final String EXTRA_MOVIE = "extra_movie";
    private Movie mMovie;
    ImageView imgPoster,imgBanner;
    TextView tvRating,tvContent,tvProduction,tvTitle,tvDuration,tvRelease;
    AppCompatRatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initContent();

        if(getIntent() != null){
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);

            DetailMoviePresenter presenter = new DetailMoviePresenter(getApplicationContext(),this);
            presenter.loadDetailMovie(mMovie.getId());


        }

    }
    public void initContent(){
        tvTitle = findViewById(R.id.text_title_movie);
        tvRating = findViewById(R.id.text_rating);
        tvProduction = findViewById(R.id.text_production);
        tvContent = findViewById(R.id.text_content);
        tvDuration = findViewById(R.id.text_duration);
        tvRelease = findViewById(R.id.text_release_date);

        imgPoster = findViewById(R.id.image_poster);
        imgBanner = findViewById(R.id.img_banner_poster);

        ratingBar = findViewById(R.id.rb_rating);

        showActionBar();
    }
    @Override
    public void showDetailMovie(MovieModel movieModel, int position) {
        Movie movie = movieModel.getMovie(position);
        tvTitle.setText(movie.getName());
        tvRating.setText(movie.getRating()+"");
        tvProduction.setText(movie.getProductionCompany());
        SimpleDateFormat dtf = new SimpleDateFormat("EEEE MMM dd, yyy", Locale.getDefault());
        tvRelease.setText(dtf.format(movie.getReleaseDate()));
        tvContent.setText(movie.getDescription());
        tvDuration.setText(getDuration(movie.getDuration()));

        ratingBar.setRating((float)movie.getRating());
        Glide.with(getApplicationContext()).load(movie.getPoster()).into(imgPoster);
        Glide.with(getApplicationContext()).load(movie.getPoster()).into(imgBanner);
    }
    public String getDuration(int duration){
        String result = "";
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
