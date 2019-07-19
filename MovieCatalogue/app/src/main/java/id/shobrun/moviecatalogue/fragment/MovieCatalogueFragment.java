package id.shobrun.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.data.Movie;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment {
    private static MovieCatalogueFragment instance;
    public static MovieCatalogueFragment getMovieCatalogueInstance(){
        if(instance == null){
            instance = new MovieCatalogueFragment();
        }
        return instance;
    }
    public MovieCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_catalogue, container, false);
    }

}
