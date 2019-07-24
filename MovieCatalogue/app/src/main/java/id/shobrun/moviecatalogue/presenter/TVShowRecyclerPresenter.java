package id.shobrun.moviecatalogue.presenter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.adapter.TVShowAdapter;
import id.shobrun.moviecatalogue.component.adapter.TVShowViewHolder;
import id.shobrun.moviecatalogue.component.common.OnItemClickListener;
import id.shobrun.moviecatalogue.component.common.OnViewClickListener;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.model.MovieModel;
import id.shobrun.moviecatalogue.ui.DetailMovieActivity;

public class TVShowRecyclerPresenter {
    private MovieModel movieModel;
    private ArrayList<Movie> movies = new ArrayList<>();
    private HashMap<String,RecyclerView> mRecyclerViews;
    private Context ctx;
    private int[] notifications = {R.drawable.ic_notifications_white_24dp,R.drawable.ic_notifications_active_white_24dp};
    public TVShowRecyclerPresenter(HashMap<String,RecyclerView> mRecyclerViews, Context ctx) {
        this.ctx = ctx;
        this.movieModel = new MovieModel(ctx);
        this.movies.addAll(movieModel.getAllMovies());
        this.mRecyclerViews = mRecyclerViews;
    }

    public TVShowRecyclerPresenter(HashMap<String,RecyclerView> mRecyclerViews) {
        this.mRecyclerViews = mRecyclerViews;
    }

    public void onBindItemViewHolder(final TVShowViewHolder view, int position){
        final Movie movie = movies.get(position);
        view.setPoster(movie.getPoster());
        view.setNotification(notifications[0]);
        view.setOnViewClickListener(new OnViewClickListener() {
            @Override
            public void onViewClicked(View v) {
                int imgNotif = (int)view.getImgNotification().getTag();

                if(imgNotif == notifications[0]){
                    view.setNotification(notifications[1]);
                    Toast.makeText(view.itemView.getContext(),"Notification for "+movie.getName()+" has on",Toast.LENGTH_SHORT).show();
                }else{
                    view.setNotification(notifications[0]);
                    Toast.makeText(view.itemView.getContext(),"Notification for "+movie.getName()+" has off",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onViewClicked(View v1, View v2) {

            }
        });
    }

    public int getMovieCount(){
        return movies.size();
    }

    public void loadRecyclerView(String id,MovieModel model){
        movieModel = model;
        movies = model.getAllMovies();
        TVShowAdapter tvShowAdapter = new TVShowAdapter(this);
        tvShowAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent detail = new Intent(v.getContext(), DetailMovieActivity.class);
                detail.putExtra(DetailMovieActivity.EXTRA_MOVIE,movies.get(position));
                v.getContext().startActivity(detail);
            }
        });
        mRecyclerViews.get(id).setAdapter(tvShowAdapter);
    }

}
