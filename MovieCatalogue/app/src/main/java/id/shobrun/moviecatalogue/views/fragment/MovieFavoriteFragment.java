package id.shobrun.moviecatalogue.views.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.viewmodels.MovieFavoriteViewModel;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;
import id.shobrun.moviecatalogue.views.adapter.MovieFavoriteAdapter;
import id.shobrun.moviecatalogue.views.iview.IMovieFavoriteView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment implements IMovieFavoriteView {
    private final String TAG = getClass().getSimpleName();
    static private MovieFavoriteFragment INSTANCE;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MovieFavoriteAdapter movieAdapter;
    private MovieFavoriteViewModel viewModel;
    private LinearLayout content;
    private RelativeLayout containerMessage;
    private TextView tvMessage;
    public static MovieFavoriteFragment getInstance() {
        if(INSTANCE==null){
            synchronized (MovieFavoriteFragment.class){
                if(INSTANCE==null){
                    INSTANCE = new MovieFavoriteFragment();
                }
            }
        }
        return INSTANCE;
    }

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initRecyclerView();
        initViewModel();
    }

    @Override
    public void initUI() {
        progressBar = this.getView().findViewById(R.id.progressBar);
        recyclerView = this.getView().findViewById(R.id.recycler_movie_wishlist);
        content = this.getView().findViewById(R.id.container_recycler);
        containerMessage = this.getView().findViewById(R.id.container_message);
        tvMessage = this.getView().findViewById(R.id.text_message);
    }
    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);
        viewModel.setAppView(getContext(),this);
        viewModel.getMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {
                Log.e(TAG, "onChanged: "+movies.size());
                if (movies != null) {
                    hideProgress();
                    showListMovie(movies);
                }
            }
        });
        viewModel.loadFavoriteMovie();
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
    public void showListMovie(final ArrayList<Movie> movieList) {
        if(movieAdapter == null) {
            movieAdapter = new MovieFavoriteAdapter();

        }
        movieAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent detailMovie = new Intent(v.getContext(), DetailMovieActivity.class);
                detailMovie.putExtra(DetailMovieActivity.EXTRA_MOVIE,movieList.get(position));
                v.getContext().startActivity(detailMovie);
            }
        });
        movieAdapter.setMovies(movieList);
        recyclerView.setAdapter(movieAdapter);

    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadFavoriteMovie();
    }
}
