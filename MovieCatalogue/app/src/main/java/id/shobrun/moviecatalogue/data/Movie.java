package id.shobrun.moviecatalogue.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Movie implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeStringList(this.genre);
        dest.writeString(this.productionCompany);
        dest.writeInt(this.poster);
        dest.writeDouble(this.rating);
        dest.writeStringList(this.languages);
        dest.writeInt(this.duration);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
        dest.writeStringList(this.keywords);
    }

    protected Movie(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.genre = in.createStringArrayList();
        this.productionCompany = in.readString();
        this.poster = in.readInt();
        this.rating = in.readDouble();
        this.languages = in.createStringArrayList();
        this.duration = in.readInt();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
        this.keywords = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
