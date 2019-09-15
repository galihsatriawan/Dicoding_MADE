package id.shobrun.moviecatalogue.utils.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.utils.Helper;

@SuppressWarnings("ConstantConditions")
public class MovieCatalogueProvider extends ContentProvider {
    private final String TAG = getClass().getSimpleName();
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final int MOVIE_TAGS = 3;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieDao movieDao;
    private Context context;

    static {
        sUriMatcher.addURI(MovieCatalogueDatabase.AUTHORITY, Movie.TABLE_NAME, MOVIE);
        sUriMatcher.addURI(MovieCatalogueDatabase.AUTHORITY, Movie.TABLE_NAME + "/#", MOVIE_ID);
    }

    @Override
    public boolean onCreate() {
        context = getContext();
        Log.d(TAG, "onCreate: "+context);
        movieDao = MovieCatalogueDatabase.getDatabase(context).movieDao();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        if (context == null) {
            return null;
        }
        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                if(selection == null){
                    cursor = movieDao.selectAllProvider();
                    cursor.setNotificationUri(context.getContentResolver(), uri);
                }else{

                    cursor = movieDao.selectByTagProvider(Helper.addWildcard(selectionArgs[0]));
                    cursor.setNotificationUri(context.getContentResolver(), uri);
                }

                return cursor;
            }
            case MOVIE_ID: {
                cursor = movieDao.selectByIdProvider(Integer.parseInt(uri.getLastPathSegment()));
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            }
            default: {
                throw new IllegalArgumentException("Unknown URI:" + uri);
            }
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                return "vnd.android.cursor.dir/" + MovieCatalogueDatabase.AUTHORITY + "." + Movie.TABLE_NAME;
            }
            case MOVIE_ID:
            case MOVIE_TAGS: {
                return "vnd.android.cursor.item/" + MovieCatalogueDatabase.AUTHORITY + "." + Movie.TABLE_NAME;
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                if (context == null) {
                    Log.d(TAG, "insert: null context");
                    return null;
                }
                Movie movie = Movie.fromContentValues(values);
                final long id = movieDao.insertProvider(movie);
                Log.d(TAG, "insert: id : "+id+" Movie Id :"+movie.getId() );

                if (id != 0) {
                    context.getContentResolver().notifyChange(uri, null);
                    return ContentUris.withAppendedId(uri, id);
                }
            }
            case MOVIE_ID:
            case MOVIE_TAGS: {
                throw new IllegalArgumentException("Invalid URI cannot insert with ID or Tags : " + uri);
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (sUriMatcher.match(uri)) {
            case MOVIE:
            case MOVIE_TAGS: {
                throw new IllegalArgumentException("Invalid URI, only can update with ID" + uri);
            }
            case MOVIE_ID: {
                if (context == null) {
                    return 0;
                }
                final int count = movieDao.deleteByIdProvider(Integer.parseInt(uri.getLastPathSegment()));
                context.getContentResolver().notifyChange(uri,null);
                return count;
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (sUriMatcher.match(uri)) {
            case MOVIE_ID:
            case MOVIE_TAGS: {
                throw new IllegalArgumentException("Invalid URI, only can update with ID" + uri);
            }
            case MOVIE: {
                if (context == null) {
                    return 0;
                }
                final int count = movieDao.updateProvider(Movie.fromContentValues(values));
                context.getContentResolver().notifyChange(uri,null);
                return count;
            }
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }
    }
}
