package id.shobrun.moviecatalogue.data;

import android.content.Context;
import android.content.res.TypedArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import id.shobrun.moviecatalogue.R;

public class MoviesData {
    private MoviesData moviesData ;
    private Context ctx;
    public MoviesData(Context ctx){
        this.ctx = ctx;
        moviesData = this;
        prepare();
    }

    private ArrayList<Integer> id;
    private ArrayList<String> name;
    private ArrayList<String> description;
    private ArrayList<ArrayList<String>> genre;
    private ArrayList<String> productionCompany;
    private ArrayList<Integer> poster;
    private ArrayList<Double> rating;
    private ArrayList<ArrayList<String>> languages;
    private ArrayList<Integer> duration;
    private ArrayList<Date> releaseDate;
    private ArrayList<ArrayList<String>> keywords;

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    public ArrayList<ArrayList<String>> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<ArrayList<String>> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(ArrayList<String> productionCompany) {
        this.productionCompany = productionCompany;
    }

    public ArrayList<Integer> getPoster() {
        return poster;
    }

    public void setPoster(ArrayList<Integer> poster) {
        this.poster = poster;
    }

    public ArrayList<Double> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Double> rating) {
        this.rating = rating;
    }

    public ArrayList<ArrayList<String>> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<ArrayList<String>> languages) {
        this.languages = languages;
    }

    public ArrayList<Integer> getDuration() {
        return duration;
    }

    public void setDuration(ArrayList<Integer> duration) {
        this.duration = duration;
    }

    public ArrayList<Date> getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ArrayList<Date> releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<ArrayList<String>> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<ArrayList<String>> keywords) {
        this.keywords = keywords;
    }


    private  void prepare() {
        moviesData.setId(this.<Integer>getData(R.array.movies_id,"int"));
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

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movies = new ArrayList();
        int length_sample = moviesData.getName().size();
        for (int i = 0; i < length_sample; i++) {
            Movie movie = new Movie(
                    moviesData.getId().get(i),
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
                    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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
                    result.add((T) data);
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
