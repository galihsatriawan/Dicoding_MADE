package id.shobrun.moviecatalogue.views.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.utils.common.OnViewClickListener;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder viewHolder, int position) {
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
        viewHolder.setOnViewClickListener(new OnViewClickListener() {
            @Override
            public void onViewClicked(View view) {

            }

            @Override
            public void onViewClicked(View v1, View v2) {
                if(v1.getVisibility()==View.VISIBLE){
                    viewHolder.showView(v2);
                    viewHolder.hideView(v1);
                    Toast.makeText(viewHolder.itemView.getContext(),movie.getTitle()+" has removed",Toast.LENGTH_SHORT).show();
                }else{
                    viewHolder.showView(v1);
                    viewHolder.hideView(v2);
                    Toast.makeText(viewHolder.itemView.getContext(),movie.getTitle()+" has added",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements IMovieItemView {
        private TextView tvTitle,tvExcerpt,tvRating;
        private ImageView imgPoster,imgWishlistOff,imgWishlistOn;
        private Button btnAddToWishlist;
        OnViewClickListener onViewClickListener;

        void setOnViewClickListener(OnViewClickListener onViewClickListener) {
            this.onViewClickListener = onViewClickListener;
        }

        MovieViewHolder(View view){
            super(view);
            tvTitle = view.findViewById(R.id.text_title);
            tvExcerpt = view.findViewById(R.id.text_excerpt);
            tvRating = view.findViewById(R.id.text_rating);
            imgPoster = view.findViewById(R.id.image_poster);
            imgWishlistOff = view.findViewById(R.id.image_wishlist_off);
            imgWishlistOn = view.findViewById(R.id.image_wishlist_on);
            btnAddToWishlist = view.findViewById(R.id.button_add_wishlist);
            btnAddToWishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewClickListener.onViewClicked(imgWishlistOn,imgWishlistOff);
                }
            });
        }

        @Override
        public void setTitle(String title) {
            tvTitle.setText(title);
        }

        @Override
        public void setExcerpt(String excerpt) {
            String ex = excerpt.substring(0,13)+" [...]";
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

        @Override
        public void showView(View v) {
            v.setVisibility(View.VISIBLE);
        }

        @Override
        public void hideView(View v) {
            v.setVisibility(View.GONE);
        }
    }
    
}
