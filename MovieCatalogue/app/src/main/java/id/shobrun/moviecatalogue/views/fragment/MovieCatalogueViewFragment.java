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
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.viewmodels.MovieCatalogueViewModel;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;
import id.shobrun.moviecatalogue.views.adapter.MovieAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueViewFragment extends Fragment implements IMovieCatalogueView {
    private final String TAG = this.getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private MovieAdapter movieAdapter;
    ProgressBar progressBar;

    MovieCatalogueViewModel viewModel;
    private static MovieCatalogueViewFragment instance;
    public static MovieCatalogueViewFragment getMovieCatalogueInstance(){
        if(instance == null){
            instance = new MovieCatalogueViewFragment();
        }
        return instance;
    }
    public MovieCatalogueViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie_catalogue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        initRecyclerView();
        initViewModel();
    }
    public void initRecyclerView(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.e(TAG, "initPresenter: " );
    }
    public void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MovieCatalogueViewModel.class);
        viewModel.getMovies().observe(this,getMovies);
        viewModel.loadAllMovie();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void initUI() {
        mRecyclerView = this.getView().findViewById(R.id.recycler_movie_catalogue);
        progressBar = this.getView().findViewById(R.id.progressBar);
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListMovieCatalogue(final ArrayList<Movie> movies) {
        if(movieAdapter == null){
            movieAdapter = new MovieAdapter();
            mRecyclerView.setAdapter(movieAdapter);
        }
        movieAdapter.setMovies(movies);
        movieAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent detail = new Intent(v.getContext(), DetailMovieActivity.class);
                detail.putExtra(DetailMovieActivity.EXTRA_MOVIE,movies.get(position));
                v.getContext().startActivity(detail);
            }
        });
    }

    private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if(movies != null){
                hideProgress();
                showListMovieCatalogue(movies);
            }

        }
    };
}
