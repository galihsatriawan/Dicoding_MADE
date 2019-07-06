package id.shobrun.moviecatalogue.data;

import java.util.ArrayList;
import java.util.Date;

public class MoviesData {
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
}
