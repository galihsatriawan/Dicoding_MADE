package id.shobrun.moviecatalogue.views.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.TvShow;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;

public class TvShowFavoriteAdapter extends RecyclerView.Adapter<TvShowFavoriteAdapter.TvShowFavoriteViewHolder> {
    private List<TvShow> tvShows = new ArrayList<>();

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public TvShowFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new TvShowFavoriteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tv_show_wishlist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowFavoriteViewHolder viewHolder, int position) {
        final int i = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(v,i);
            }
        });
        final TvShow tvShow = tvShows.get(position);
        int len = (tvShow.getName().length()<20)?tvShow.getName().length():20;
        viewHolder.setTitle(tvShow.getName().substring(0,len-1));
        viewHolder.setExcerpt(tvShow.getOverview());
        viewHolder.setRating(tvShow.getVote_average());
        viewHolder.setPoster(Constants.BACKDROP_BASE_URL+tvShow.getBackdrop_path());
    }
    public void setTvShows(ArrayList<TvShow> tvShows) {
        this.tvShows = tvShows;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return tvShows.size();
    }


    class TvShowFavoriteViewHolder extends RecyclerView.ViewHolder implements ITvShowFavoriteItemView {
        private TextView tvTitle,tvExcerpt,tvRating;
        private ImageView imgPoster;
        public TvShowFavoriteViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.text_title);
            tvExcerpt = view.findViewById(R.id.text_excerpt);
            tvRating = view.findViewById(R.id.text_rating);
            imgPoster = view.findViewById(R.id.image_poster);
        }


        @Override
        public void setTitle(String title) {
            tvTitle.setText(title);
        }

        @Override
        public void setExcerpt(String excerpt) {
            String ex = excerpt.substring(0,17)+" [...]";
            tvExcerpt.setText(ex);
        }

        @Override
        public void setRating(double rating) {
            tvRating.setText(String.valueOf(rating));
        }

        @Override
        public void setPoster(String poster) {
            Glide.with(this.itemView.getContext()).load(poster).into(imgPoster);
        }
    }
}
