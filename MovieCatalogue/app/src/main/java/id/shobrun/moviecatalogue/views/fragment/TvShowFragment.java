package id.shobrun.moviecatalogue.views.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.data.TvShow;
import id.shobrun.moviecatalogue.contracts.TvShowContract;
import id.shobrun.moviecatalogue.presenters.TVShowRecyclerPresenter;
import id.shobrun.moviecatalogue.presenters.TvShowPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowContract.View {
    public static final String EXTRA_POPULAR = "movie_popular";
    public static final String EXTRA_TRENDING = "movie_trending";
    private RecyclerView mRecyclerMoviePopular,mRecyclerMovieTrending;
    private HashMap<String ,RecyclerView> mRecyclerViews = new HashMap<>();
    private TvShowPresenter mPresenter;
    private TVShowRecyclerPresenter mRecyclerPresenter;
    private static TvShowFragment instance ;
    public static TvShowFragment getTvShowInstance(){
        if(instance == null){
            instance = new TvShowFragment();
        }
        return instance;
    }
    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        mPresenter.loadTvShowPopular();
        mPresenter.loadTvShowTrending();
    }
    @Override
    public void initUI(){
        mRecyclerMoviePopular = this.getView().findViewById(R.id.recycler_tv_show_popular);
        mRecyclerMovieTrending = this.getView().findViewById(R.id.recycler_tv_show_trending_now);
        mRecyclerViews.put(EXTRA_POPULAR,mRecyclerMoviePopular);
        mRecyclerViews.put(EXTRA_TRENDING,mRecyclerMovieTrending);
    }
    private void initPresenter(){
        mPresenter = new TvShowPresenter(this,getContext());
        mRecyclerPresenter = new TVShowRecyclerPresenter(mRecyclerViews,getContext());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void showListTvShowPopular(ArrayList<TvShow> tvShows) {
        mRecyclerMoviePopular.setHasFixedSize(true);
        mRecyclerMoviePopular.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));

        mRecyclerPresenter.loadRecyclerView(EXTRA_POPULAR,tvShows);
    }

    @Override
    public void showListTvShowTrending(ArrayList<TvShow> tvShows) {
        mRecyclerMovieTrending.setHasFixedSize(true);
        mRecyclerMovieTrending.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));

        mRecyclerPresenter.loadRecyclerView(EXTRA_TRENDING,tvShows);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
