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

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteViewHolder> {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public MovieFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MovieFavoriteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_wishlist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieFavoriteViewHolder viewHolder, int position) {
        final int i = position;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClicked(v,i);
            }
        });
        final Movie movie = movies.get(position);
        int len = (movie.getTitle().length()<20)?movie.getTitle().length():20;
        viewHolder.setTitle(movie.getTitle().substring(0,len-1));
        viewHolder.setExcerpt(movie.getOverview());
        viewHolder.setRating(movie.getVote_average());
        viewHolder.setPoster(Constants.BACKDROP_BASE_URL+movie.getBackdrop_path());
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }


    class MovieFavoriteViewHolder extends RecyclerView.ViewHolder implements IMovieFavoriteItemView {
        private TextView tvTitle,tvExcerpt,tvRating;
        private ImageView imgPoster,imgWishlistOff,imgWishlistOn;
        MovieFavoriteViewHolder(@NonNull View view) {
            super(view);
            tvTitle = view.findViewById(R.id.text_title);
            tvExcerpt = view.findViewById(R.id.text_excerpt);
            tvRating = view.findViewById(R.id.text_rating);
            imgPoster = view.findViewById(R.id.image_poster);
            imgWishlistOff = view.findViewById(R.id.image_wishlist_off);
            imgWishlistOn = view.findViewById(R.id.image_wishlist_on);
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
