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
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.viewmodels.TvShowFavoriteViewModel;
import id.shobrun.moviecatalogue.views.DetailTvActivity;
import id.shobrun.moviecatalogue.views.adapter.TvShowFavoriteAdapter;
import id.shobrun.moviecatalogue.views.iview.ITvShowFavoriteView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment implements ITvShowFavoriteView {
    private final String TAG = getClass().getSimpleName();
    static private TvShowFavoriteFragment INSTANCE;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TvShowFavoriteAdapter tvShowAdapter;
    private TvShowFavoriteViewModel viewModel;
    private LinearLayout content;
    private RelativeLayout containerMessage;
    private TextView tvMessage;
    public static TvShowFavoriteFragment getInstance() {
        if(INSTANCE==null){
            synchronized (MovieFavoriteFragment.class){
                if(INSTANCE==null){
                    INSTANCE = new TvShowFavoriteFragment();
                }
            }
        }
        return INSTANCE;
    }
    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);
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
        recyclerView = this.getView().findViewById(R.id.recycler_tv_show_wishlist);
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
        viewModel = ViewModelProviders.of(this).get(TvShowFavoriteViewModel.class);
        viewModel.setAppView(getContext(),this);
        viewModel.getTvShows().observe(this, new Observer<ArrayList<TvShow>>() {
            @Override
            public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
                Log.d(getClass().getSimpleName(), "onChanged: "+tvShows.size());
                if(tvShows!=null){
                    hideProgress();
                    showListTvShow(tvShows);
                }

            }
        });
        viewModel.loadFavoriteTvShows();
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
    public void showListTvShow(final ArrayList<TvShow> tvShows) {
        if(tvShowAdapter == null) {
            tvShowAdapter = new TvShowFavoriteAdapter();

        }
        tvShowAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent detailMovie = new Intent(v.getContext(), DetailTvActivity.class);
                detailMovie.putExtra(DetailTvActivity.EXTRA_TV,tvShows.get(position));
                v.getContext().startActivity(detailMovie);
            }
        });
        tvShowAdapter.setTvShows(tvShows);
        recyclerView.setAdapter(tvShowAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadFavoriteTvShows();
    }

}
