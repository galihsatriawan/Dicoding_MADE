package id.shobrun.moviecatalogue.utils;

import android.content.ContentValues;
import android.net.Uri;


public class DataPair {
    public Uri uri;
    public ContentValues values;
    public String[] argument;
    public String selection;

    public DataPair(Uri uri, String selection,String[] argument) {
        this.uri = uri;
        this.argument = argument;
        this.selection = selection;
    }


    public DataPair(Uri uri,ContentValues values){
        this.uri = uri;
        this.values = values;
    }


}
