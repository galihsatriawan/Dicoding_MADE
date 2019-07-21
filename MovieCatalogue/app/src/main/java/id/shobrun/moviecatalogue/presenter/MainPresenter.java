package id.shobrun.moviecatalogue.presenter;

import android.content.Context;
import android.content.res.TypedArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import id.shobrun.moviecatalogue.R;
import id.shobrun.moviecatalogue.data.Movie;
import id.shobrun.moviecatalogue.data.MoviesData;
import id.shobrun.moviecatalogue.model.MainModel;
import id.shobrun.moviecatalogue.view.MainView;

public class MainPresenter {
    private MainView mView;
    private Context ctx;
    private MoviesData moviesData;

    public MainPresenter(Context ctx, MainView mView) {
        this.ctx = ctx;
        this.mView = mView;
    }



    public void loadMovie() {
    }



}
