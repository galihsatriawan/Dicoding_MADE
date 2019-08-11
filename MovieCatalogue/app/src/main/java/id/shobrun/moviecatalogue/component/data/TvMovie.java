package id.shobrun.moviecatalogue.component.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class TvMovie implements Parcelable {

    private int id;
    private String name;
    @SerializedName("overview")
    private String description;
    @SerializedName("genre_ids")
    private ArrayList<Integer> genre;
    private String productionCompany;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("vote_average")
    private double rating;
    private ArrayList<String> languages;
    private int duration;
    @SerializedName("first_air_date")
    private Date releaseDate;
    private ArrayList<String> keywords;

    public TvMovie(int id,String name, String description, ArrayList<Integer> genre, String productionCompany, String poster, double rating, ArrayList<String> languages, int duration, Date releaseDate, ArrayList<String> keywords) {
        this.id = id;
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
    public TvMovie(String name, String description, ArrayList<Integer> genre, String productionCompany, String poster, double rating, ArrayList<String> languages, int duration, Date releaseDate, ArrayList<String> keywords) {
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Integer> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Integer> genre) {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeList(this.genre);
        dest.writeString(this.productionCompany);
        dest.writeString(this.poster);
        dest.writeString(this.backdrop);
        dest.writeDouble(this.rating);
        dest.writeStringList(this.languages);
        dest.writeInt(this.duration);
        dest.writeLong(this.releaseDate != null ? this.releaseDate.getTime() : -1);
        dest.writeStringList(this.keywords);
    }

    protected TvMovie(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.genre = new ArrayList<Integer>();
        in.readList(this.genre, Integer.class.getClassLoader());
        this.productionCompany = in.readString();
        this.poster = in.readString();
        this.backdrop = in.readString();
        this.rating = in.readDouble();
        this.languages = in.createStringArrayList();
        this.duration = in.readInt();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
        this.keywords = in.createStringArrayList();
    }

    public static final Creator<TvMovie> CREATOR = new Creator<TvMovie>() {
        @Override
        public TvMovie createFromParcel(Parcel source) {
            return new TvMovie(source);
        }

        @Override
        public TvMovie[] newArray(int size) {
            return new TvMovie[size];
        }
    };
}
