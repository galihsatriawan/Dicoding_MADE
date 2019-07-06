package id.shobrun.moviecatalogue.data;

import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String name;
    private String description;
    private ArrayList<String> genre;
    private String productionCompany;
    private int poster;
    private double rating;
    private ArrayList<String> languages;
    private int duration;
    private Date releaseDate;
    private ArrayList<String> keywords;

    public Movie(String name, String description, ArrayList<String> genre, String productionCompany, int poster, double rating, ArrayList<String> languages, int duration, Date releaseDate, ArrayList<String> keywords) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.productionCompany = productionCompany;
        this.poster = poster;
        this.rating = rating;
        this.languages = languages;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }
}
