package id.shobrun.moviecatalogue.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.data.Movie;

public class MovieAdapter extends BaseAdapter {
    Context context;
    private ArrayList<Movie> mMovies;

    public MovieAdapter(Context context) {
        this.context = context;
        this.mMovies = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null ){
            view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        }
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        Movie movie = (Movie)getItem(position);
        viewHolder.bind(movie);
        return view;
    }

    public void setmMovies(ArrayList<Movie> mMovies) {
        this.mMovies = mMovies;
    }
}
