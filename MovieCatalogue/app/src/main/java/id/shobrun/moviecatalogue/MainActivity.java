package id.shobrun.moviecatalogue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import id.shobrun.moviecatalogue.component.MovieAdapter;
import id.shobrun.moviecatalogue.model.MainModel;
import id.shobrun.moviecatalogue.presenter.DetailMoviePresenter;
import id.shobrun.moviecatalogue.presenter.MainPresenter;
import id.shobrun.moviecatalogue.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {
    private ListView lvMovie;
    MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMovie = findViewById(R.id.list_movie);
        movieAdapter = new MovieAdapter(getApplicationContext());
        lvMovie.setAdapter(movieAdapter);
        final MainPresenter presenter = new MainPresenter(getApplicationContext(),this);
        presenter.loadMovie();
    }

    @Override
    public void showListMovieCatalogue(final MainModel model) {
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailMovie = new Intent(MainActivity.this, DetailMovieActivity.class);
                detailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE,model.getMovies().get(position));
                startActivity(detailMovie);
            }
        });
        movieAdapter.setMovies(model.getMovies());

    }

    @Override
    public void showActionBar() {

    }
}
