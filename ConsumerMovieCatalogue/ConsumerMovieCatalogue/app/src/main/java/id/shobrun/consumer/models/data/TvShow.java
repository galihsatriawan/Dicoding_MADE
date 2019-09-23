package id.shobrun.consumer.models.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.ArrayList;

import id.shobrun.consumer.database.MovieCatalogueDatabase;
import id.shobrun.consumer.utils.Constants;

@Entity(tableName = TvShow.TABLE_NAME)
public class TvShow implements Parcelable {
    public static final String TABLE_NAME = "tb_tv_show";
    public static final String _ID = BaseColumns._ID;
    public static final String TITLE = "name";
    public static final String OVERVIEW = "overview";
    public static final String POSTER_PATH = "poster_path";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String RELEASE_DATE = "first_air_date";
    public static final String TAGS = "tags";
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = _ID)
    private int id;
    private String name;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private Double vote_average;
    private String first_air_date;
    private String tags;

    public TvShow(int id, String name, String overview, String poster_path, String backdrop_path, Double vote_average, String first_air_date, String tags) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
        this.first_air_date = first_air_date;
        this.tags = tags;
    }

    @Ignore
    public TvShow(int id){
        this.id = id;
    }
    @Ignore
    private int duration;
    @Ignore
    private ArrayList<String> languages;
    @Ignore
    private ArrayList<String> keywords;
    @Ignore
    private ArrayList<Integer> genre_ids;
    @Ignore
    private String productionCompany;

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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeValue(this.vote_average);
        dest.writeString(this.first_air_date);
        dest.writeString(this.tags);
        dest.writeInt(this.duration);
        dest.writeStringList(this.languages);
        dest.writeStringList(this.keywords);
        dest.writeList(this.genre_ids);
        dest.writeString(this.productionCompany);
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.overview = in.readString();
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.first_air_date = in.readString();
        this.tags = in.readString();
        this.duration = in.readInt();
        this.languages = in.createStringArrayList();
        this.keywords = in.createStringArrayList();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.productionCompany = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
    private static final String SCHEME = "content";
    public static final Uri CONTENT_URI = new Uri.Builder()
            .scheme(SCHEME)
            .authority(Constants.AUTHORITY_MOVIECATALOGUE)
            .appendPath(TABLE_NAME)
            .build();
    public static TvShow fromContentValues(ContentValues values){
        final TvShow tvShow = new TvShow(-1);
        if(values.containsKey(_ID)) tvShow.setId(values.getAsInteger(_ID));
        if(values.containsKey(TITLE)) tvShow.setName(values.getAsString(TITLE));
        if(values.containsKey(OVERVIEW)) tvShow.setOverview(values.getAsString(OVERVIEW));
        if(values.containsKey(POSTER_PATH)) tvShow.setPoster_path(values.getAsString(POSTER_PATH));
        if(values.containsKey(BACKDROP_PATH)) tvShow.setBackdrop_path(values.getAsString(BACKDROP_PATH));
        if(values.containsKey(VOTE_AVERAGE)) tvShow.setVote_average(values.getAsDouble(VOTE_AVERAGE));
        if(values.containsKey(RELEASE_DATE)) tvShow.setFirst_air_date(values.getAsString(RELEASE_DATE));
        if(values.containsKey(TAGS)) tvShow.setTags(values.getAsString(TAGS));
        return tvShow;
    }
}
