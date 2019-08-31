package id.shobrun.moviecatalogue.views.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.Constants;
import id.shobrun.moviecatalogue.utils.common.OnItemClickListener;
import id.shobrun.moviecatalogue.utils.common.OnViewClickListener;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private List<Movie> movies = new ArrayList<>();

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

    
}
