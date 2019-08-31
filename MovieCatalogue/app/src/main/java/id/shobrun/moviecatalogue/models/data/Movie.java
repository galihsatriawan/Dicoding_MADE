package id.shobrun.moviecatalogue.models.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "tb_movies")
public class Movie implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieId")
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private Double vote_average;
    private String release_date;
    @ColumnInfo(name = "tags")
    private String tags;
    @Ignore
    private ArrayList<Integer> genre_ids;
    @Ignore
    private String productionCompany;
    @Ignore
    private ArrayList<String> keywords;
    @Ignore
    private ArrayList<String> languages;
    @Ignore
    private int duration;
    public Movie(int id, String title, String overview, String poster_path, String backdrop_path, Double vote_average, String release_date, String tags) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.tags = tags;
    }
    public Movie(int id, String title, String overview, ArrayList<Integer> genre_ids, String poster_path, String backdrop_path, Double vote_average, String release_date, String tags, String productionCompany, ArrayList<String> keywords, ArrayList<String> languages, int duration) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.genre_ids = genre_ids;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.tags = tags;
        this.productionCompany = productionCompany;
        this.keywords = keywords;
        this.languages = languages;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeList(this.genre_ids);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeValue(this.vote_average);
        dest.writeString(this.release_date);
        dest.writeString(this.tags);
        dest.writeString(this.productionCompany);
        dest.writeStringList(this.keywords);
        dest.writeStringList(this.languages);
        dest.writeInt(this.duration);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.release_date = in.readString();
        this.tags = in.readString();
        this.productionCompany = in.readString();
        this.keywords = in.createStringArrayList();
        this.languages = in.createStringArrayList();
        this.duration = in.readInt();
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

