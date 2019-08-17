package id.shobrun.moviecatalogue.presenters;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.component.adapter.TVShowAdapter;
import id.shobrun.moviecatalogue.component.adapter.TVShowViewHolder;
import id.shobrun.moviecatalogue.component.common.OnItemClickListener;
import id.shobrun.moviecatalogue.component.common.OnViewClickListener;
import id.shobrun.moviecatalogue.component.data.TvShow;
import id.shobrun.moviecatalogue.contracts.TvShowRecyclerContract;
import id.shobrun.moviecatalogue.models.TvShowModel;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.views.DetailMovieActivity;

public class TVShowRecyclerPresenter implements TvShowRecyclerContract.RecyclerPresenter {
    private TvShowModel tvShowModel;
    private ArrayList<TvShow> tvShows= new ArrayList<>();
    private HashMap<String,RecyclerView> mRecyclerViews;
    private Context ctx;
    private int[] notifications = {R.drawable.ic_notifications_white_24dp,R.drawable.ic_notifications_active_white_24dp};
    public TVShowRecyclerPresenter(HashMap<String,RecyclerView> mRecyclerViews, Context ctx) {
        this.ctx = ctx;
        this.tvShowModel= new TvShowModel(ctx);
        this.mRecyclerViews = mRecyclerViews;
    }

    public TVShowRecyclerPresenter(HashMap<String,RecyclerView> mRecyclerViews) {
        this.mRecyclerViews = mRecyclerViews;
    }
    @Override
    public void onBindItemViewHolder(final TVShowViewHolder view, int position){
        final TvShow tvShow= tvShows.get(position);
        view.setPoster(Constants.IMAGE_BASE_URL +tvShow.getPoster());
        view.setNotification(notifications[0]);
        view.setOnViewClickListener(new OnViewClickListener() {
            @Override
            public void onViewClicked(View v) {
                Drawable imgNotif = view.itemView.getContext().getDrawable(notifications[1]);
                if(view.getImgNotification().getDrawable().equals(imgNotif) ){
                    view.setNotification(notifications[0]);
                    Toast.makeText(view.itemView.getContext(),"Notification for "+tvShow.getName()+" has off",Toast.LENGTH_SHORT).show();
                }else{
                    view.setNotification(notifications[1]);
                    Toast.makeText(view.itemView.getContext(),"Notification for "+tvShow.getName()+" has on",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onViewClicked(View v1, View v2) {

            }
        });
    }
    @Override
    public int getTvShowCount(){
        return tvShows.size();
    }
    @Override
    public void loadRecyclerView(String id, final ArrayList<TvShow> tvShows){
        this.tvShows= tvShows;
        TVShowAdapter tvShowAdapter = new TVShowAdapter(this);
        tvShowAdapter.notifyDataSetChanged();
        tvShowAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(View v, int position) {

            }
        });
        mRecyclerViews.get(id).setAdapter(tvShowAdapter);
    }

}
