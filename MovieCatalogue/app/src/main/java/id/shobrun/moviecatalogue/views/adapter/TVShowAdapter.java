package id.shobrun.moviecatalogue.views.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.utils.common.OnViewClickListener;
import id.shobrun.moviecatalogue.views.iview.ITvShowView;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {
    private ArrayList<TvShow> tvShows;
    private OnItemClickListener mOnItemClickListener;
    private int[] notifications = {R.drawable.ic_notifications_white_24dp,R.drawable.ic_notifications_active_white_24dp};
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public TVShowAdapter() {

    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TVShowViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_tv_show,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TVShowViewHolder view,int position) {
        final int i = position;
        view.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(v,i);
            }
        });
        final TvShow tvShow= tvShows.get(position);
        view.setPoster(Constants.IMAGE_BASE_URL +tvShow.getPoster_path());
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

            @Override
            public <T> void onViewClicked(T obj, View v1, View v2) {

            }
        });
    }

    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder implements ITvShowView.TVShowItemView {
        ImageView imgPoster,imgNotification;
        OnViewClickListener onViewClickListener;
        void setOnViewClickListener(OnViewClickListener onViewClickListener) {
            this.onViewClickListener = onViewClickListener;
        }
        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.image_poster);
            imgNotification = itemView.findViewById(R.id.image_notification);
            imgNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewClickListener.onViewClicked(v);
                }
            });
        }

        @Override
        public void setPoster(String poster) {
            Log.d(this.getClass().getSimpleName(), "setPoster: "+poster);
            Glide.with(this.itemView.getContext()).load(poster).into(imgPoster);
        }

        @Override
        public void setNotification(int notif) {
            Glide.with(this.itemView.getContext()).load(notif).into(imgNotification);
        }

        ImageView getImgNotification() {
            return imgNotification;
        }
    }
}
