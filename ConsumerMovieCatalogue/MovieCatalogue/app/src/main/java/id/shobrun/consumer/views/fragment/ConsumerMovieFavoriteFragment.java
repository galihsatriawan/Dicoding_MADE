package id.shobrun.consumer.views.fragment;

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

import java.util.ArrayList;

import id.shobrun.consumer.R;
import id.shobrun.consumer.models.data.Movie;
import id.shobrun.consumer.viewmodels.ConsumerMovieFavoriteViewModel;
import id.shobrun.consumer.views.ConsumerDetailMovieActivity;
import id.shobrun.consumer.views.adapter.MovieFavoriteAdapter;
import id.shobrun.consumer.views.iview.IConsumerMovieFavoriteView;

public class ConsumerMovieFavoriteFragment extends Fragment implements IConsumerMovieFavoriteView {
    private final String TAG = getClass().getSimpleName();
    static private ConsumerMovieFavoriteFragment INSTANCE;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private MovieFavoriteAdapter movieAdapter;
    private ConsumerMovieFavoriteViewModel viewModel;
    private LinearLayout content;
    private RelativeLayout containerMessage;
    private TextView tvMessage;
    public static ConsumerMovieFavoriteFragment getInstance() {
        if(INSTANCE==null){
            synchronized (ConsumerMovieFavoriteFragment.class){
                if(INSTANCE==null){
                    INSTANCE = new ConsumerMovieFavoriteFragment();
                }
            }
        }
        return INSTANCE;
    }

    public ConsumerMovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consumer_movie_favorite, container, false);
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
    @Override
    public void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ConsumerMovieFavoriteViewModel.class);
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
        movieAdapter.setOnItemClickListener((v, position) -> {
            Intent detailMovie = new Intent(v.getContext(), ConsumerDetailMovieActivity.class);
            detailMovie.putExtra(ConsumerDetailMovieActivity.EXTRA_MOVIE,movieList.get(position));
            v.getContext().startActivity(detailMovie);
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
