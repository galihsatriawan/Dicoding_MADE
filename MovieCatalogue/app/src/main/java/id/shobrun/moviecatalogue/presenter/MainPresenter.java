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
        this.moviesData = new MoviesData();
    }

    public void prepare() {
        moviesData.setName(this.<String>getData(R.array.movies_name, "string"));
        moviesData.setDescription(this.<String>getData(R.array.movies_description, "string"));
        moviesData.setGenre(getDataMultiDimens(R.array.movies_genre, "#"));
        moviesData.setProductionCompany(this.<String>getData(R.array.movies_production_company, "string"));
        moviesData.setPoster(this.getDrawable(R.array.movies_poster));
        moviesData.setRating(this.<Double>getData(R.array.movies_rating, "double"));
        moviesData.setLanguages(getDataMultiDimens(R.array.movies_languages, "#"));
        moviesData.setDuration(this.<Integer>getData(R.array.movies_duration, "int"));
        moviesData.setReleaseDate(this.<Date>getData(R.array.movies_release, "date"));
        moviesData.setKeywords(getDataMultiDimens(R.array.movies_keywords, "|"));
    }

    public ArrayList<Movie> movies() {
        ArrayList<Movie> movies = new ArrayList();
        int length_sample = moviesData.getName().size();
        for (int i = 0; i < length_sample; i++) {
            Movie movie = new Movie(
                    moviesData.getName().get(i),
                    moviesData.getDescription().get(i),
                    moviesData.getGenre().get(i),
                    moviesData.getProductionCompany().get(i),
                    moviesData.getPoster().get(i),
                    moviesData.getRating().get(i),
                    moviesData.getLanguages().get(i),
                    moviesData.getDuration().get(i),
                    moviesData.getReleaseDate().get(i),
                    moviesData.getKeywords().get(i)
            );
            movies.add(movie);
        }
        return movies;
    }

    public void loadMovie() {
        prepare();
        ArrayList<Movie> movies = movies();
        MainModel model = new MainModel(movies);
        mView.showListMovieCatalogue(model);
    }

    /*
        Convert Data Resources
     */

    public <T> ArrayList<T> getData(int resources_id, String cast) {
        ArrayList<T> result = new ArrayList<>();
        String[] datas = ctx.getResources().getStringArray(resources_id);
        for (String data : datas) {
            switch (cast) {
                case "double": {
                    Double temp = Double.parseDouble(data);
                    result.add((T) temp);
                    break;
                }
                case "int": {
                    Integer temp = Integer.parseInt(data);
                    result.add((T) temp);
                    break;
                }
                case "date": {
                    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
                    Date temp = new Date();
                    try {
                        temp = dtf.parse(data);
                    } catch (ParseException e) {
                        e.toString();
                    }
                    result.add((T) temp);
                    break;
                }
                default: {
                    String temp = data;
                    result.add((T) temp);
                    break;
                }
            }
        }
        return result;
    }
    public ArrayList<Integer> getDrawable(int resource_id){
        ArrayList<Integer> result = new ArrayList<>();
        TypedArray datas = ctx.getResources().obtainTypedArray(resource_id);
        for(int i = 0;i<datas.length();i++){
            int data = datas.getResourceId(i,-1);
            result.add(data);
        }
        return  result;
    }
    public ArrayList<ArrayList<String>> getDataMultiDimens(int resource_id, String splitter) {
        ArrayList<String> datas = getData(resource_id, "string");
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        for (String data : datas) {
            ArrayList<String> temp = new ArrayList<>();
            String[] temp_split = data.split(splitter);
            for (String str : temp_split) temp.add(str);
            result.add(temp);
        }
        return result;
    }

}
