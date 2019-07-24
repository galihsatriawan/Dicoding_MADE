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
    private RecyclerView mRecycler;
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
        mRecycler = view.findViewById(R.id.recycler_tv_show);
    }
    private void initPresenter(){
        mPresenter = new TvShowPresenter(this,getContext());
        mRecyclerPresenter = new TVShowRecyclerPresenter(mRecycler,getContext());
    }
    @Override
    public void showListTvShow(MovieModel model) {
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerPresenter.loadRecyclerView(model);
    }
}
