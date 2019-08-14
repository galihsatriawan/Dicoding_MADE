package id.shobrun.moviecatalogue.contracts;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.component.adapter.TVShowViewHolder;
import id.shobrun.moviecatalogue.component.data.TvShow;

public interface TvShowRecyclerContract {

    interface TVShowItemView {
        void setPoster(String poster);
        void setNotification(int notif);
    }

    interface RecyclerPresenter{
        void onBindItemViewHolder(final TVShowViewHolder view, int position);
        void loadRecyclerView(String id, final ArrayList<TvShow> tvShows);
        int getTvShowCount();
    }

}
