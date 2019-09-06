package id.shobrun.moviecatalogue.views.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.viewmodels.TvShowViewModel;
import id.shobrun.moviecatalogue.views.DetailTvActivity;
import id.shobrun.moviecatalogue.views.adapter.TVShowAdapter;
import id.shobrun.moviecatalogue.views.iview.ITvShowView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements ITvShowView {
    private final String TAG = getClass().getSimpleName();
    private static final String EXTRA_POPULAR = "movie_popular";
    private RecyclerView mRecyclerMoviePopular;
    private LinearLayout content;
    private RelativeLayout containerMessage;
    private TVShowAdapter tvShowPopularAdapter;
    private ProgressBar progressBar;
    private TextView tvMessage;
    private static TvShowFragment instance ;

    private TvShowViewModel viewModel;
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
        initUI();
        initRecyclerView();
        initViewModel();
//        mPresenter.loadTvShowPopular();
//        mPresenter.loadTvShowTrending();
    }
    @Override
    public void initUI(){
        progressBar = this.getView().findViewById(R.id.progressBar);
        content = this.getView().findViewById(R.id.container_recycler);
        containerMessage = this.getView().findViewById(R.id.container_message);
        tvMessage = this.getView().findViewById(R.id.text_message);
        mRecyclerMoviePopular = this.getView().findViewById(R.id.recycler_tv_show_popular);
    }

    private void initRecyclerView(){
        mRecyclerMoviePopular.setHasFixedSize(true);
        mRecyclerMoviePopular.setLayoutManager(new GridLayoutManager(getContext(),2));

    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        viewModel.getTvShowsPopular().observe(this,getTvShowsPopular);
        viewModel.setAppView(getContext(),this);
        viewModel.loadTvShowPopular();
    }

    private Observer<ArrayList<TvShow>> getTvShowsPopular = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows) {
            if(tvShows!=null){
                hideProgress();
                showListTvShowPopular(tvShows);
            }
        }
    };

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
    public void showListTvShowPopular(final ArrayList<TvShow> tvShows) {
        if(tvShowPopularAdapter==null){
         tvShowPopularAdapter = new TVShowAdapter();
            tvShowPopularAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClicked(View v, int position) {
                    Intent detailTv = new Intent(v.getContext(), DetailTvActivity.class);
                    detailTv.putExtra(DetailTvActivity.EXTRA_TV,tvShows.get(position));
                    v.getContext().startActivity(detailTv);
                }
            });

        }
        tvShowPopularAdapter.setTvShows(tvShows);
        mRecyclerMoviePopular.setAdapter(tvShowPopularAdapter);

    }


    @Override
    public void showMessage(String message) {
        content.setVisibility(View.GONE);
        containerMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(message);
    }

    @Override
    public void showActionBar() {

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadTvShowPopular();
    }
    @Override
    public void updateSearch(String str){
        viewModel.loadSearchTvShow(str);
    }
}
