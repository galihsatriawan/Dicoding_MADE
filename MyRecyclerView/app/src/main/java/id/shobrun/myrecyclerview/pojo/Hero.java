package id.shobrun.myrecyclerview.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable {
    private String name;
    private String from;
    private String photo;

    public Hero(String name, String from, String photo) {
        this.name = name;
        this.from = from;
        this.photo = photo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.from);
        dest.writeString(this.photo);
    }

    public Hero() {
    }

    protected Hero(Parcel in) {
        this.name = in.readString();
        this.from = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
