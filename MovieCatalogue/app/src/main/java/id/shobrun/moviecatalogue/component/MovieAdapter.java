package id.shobrun.moviecatalogue.component;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.presenter.MovieRecyclerPresenter;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    MovieRecyclerPresenter mPresenter;

    public MovieAdapter(MovieRecyclerPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        mPresenter.onBindItemViewHolder(movieViewHolder,i);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getMoviesCount();
    }
}
