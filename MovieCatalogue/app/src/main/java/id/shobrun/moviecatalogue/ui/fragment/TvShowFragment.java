package id.shobrun.moviecatalogue.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.presenter.MovieRecyclerPresenter;
import id.shobrun.moviecatalogue.presenter.TVShowRecyclerPresenter;
import id.shobrun.moviecatalogue.presenter.TvShowPresenter;
import id.shobrun.moviecatalogue.view.TvShowView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements TvShowView {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponent(view);
        initPresenter();
        mPresenter.loadTVShow();
    }
    private void initComponent(View view){
        mRecyclerMoviePopular = view.findViewById(R.id.recycler_tv_show_popular);
        mRecyclerMovieTrending = view.findViewById(R.id.recycler_tv_show_trending_now);
        mRecyclerViews.put(EXTRA_POPULAR,mRecyclerMoviePopular);
        mRecyclerViews.put(EXTRA_TRENDING,mRecyclerMovieTrending);
    }
    private void initPresenter(){
        mPresenter = new TvShowPresenter(this,getContext());
        mRecyclerPresenter = new TVShowRecyclerPresenter(mRecyclerViews,getContext());
    }
    @Override
    public void showListTvShow(MovieModel model) {
        mRecyclerMoviePopular.setHasFixedSize(true);
        mRecyclerMoviePopular.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));

        mRecyclerMovieTrending.setHasFixedSize(true);
        mRecyclerMovieTrending.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));

        mRecyclerPresenter.loadRecyclerView(EXTRA_POPULAR,model);
        mRecyclerPresenter.loadRecyclerView(EXTRA_TRENDING,model);
    }
}
