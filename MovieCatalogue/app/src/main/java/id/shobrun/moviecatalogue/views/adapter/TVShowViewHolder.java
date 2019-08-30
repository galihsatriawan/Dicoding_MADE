package id.shobrun.moviecatalogue.views.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.utils.common.OnViewClickListener;
import id.shobrun.moviecatalogue.contracts.TvShowRecyclerContract;

public class TVShowViewHolder extends RecyclerView.ViewHolder implements TvShowRecyclerContract.TVShowItemView {
    ImageView imgPoster,imgNotification;
    OnViewClickListener onViewClickListener;
    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }
    public TVShowViewHolder(@NonNull View itemView) {
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

    public ImageView getImgNotification() {
        return imgNotification;
    }
}