package id.shobrun.moviecatalogue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import id.shobrun.moviecatalogue.component.MovieAdapter;
import id.shobrun.moviecatalogue.model.MainModel;
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
    public void showListMovieCatalogue(MainModel model) {
        movieAdapter.setMovies(model.getMovies());
        movieAdapter.notifyDataSetChanged();
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void showActionBar() {

    }
}
