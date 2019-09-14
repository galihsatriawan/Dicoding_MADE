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

import id.shobrun.moviecatalogue.database.MovieCatalogueDatabase;
import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;

public class MovieCatalogueProvider extends ContentProvider {
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final int MOVIE_TAGS = 3;
    public static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieDao movieDao;

    static {
        sUriMatcher.addURI(MovieCatalogueDatabase.AUTHORITY, Movie.TABLE_NAME, MOVIE);
        sUriMatcher.addURI(MovieCatalogueDatabase.AUTHORITY, Movie.TABLE_NAME + "/#", MOVIE_ID);
        sUriMatcher.addURI(MovieCatalogueDatabase.AUTHORITY, Movie.TABLE_NAME + "/*", MOVIE_TAGS);
    }

    @Override
    public boolean onCreate() {
        movieDao = MovieCatalogueDatabase.getDatabase(getContext()).movieDao();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        final Context context = getContext();
        if (context == null) {
            return null;
        }
        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                cursor = movieDao.selectAllProvider();
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            }
            case MOVIE_ID: {
                cursor = movieDao.selectByIdProvider(Integer.parseInt(uri.getLastPathSegment()));
                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            }
            case MOVIE_TAGS: {
                cursor = movieDao.selectByTagProvider(uri.getLastPathSegment());
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
        final Context context = getContext();

        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                if (context != null) {
                    return null;
                }
                final long id = movieDao.insertProvider(Movie.fromContentValues(values));
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
        final Context context = getContext();

        switch (sUriMatcher.match(uri)) {
            case MOVIE:
            case MOVIE_TAGS: {
                throw new IllegalArgumentException("Invalid URI, only can update with ID" + uri);
            }
            case MOVIE_ID: {
                if (context != null) {
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
        final Context context = getContext();

        switch (sUriMatcher.match(uri)) {
            case MOVIE:
            case MOVIE_TAGS: {
                throw new IllegalArgumentException("Invalid URI, only can update with ID" + uri);
            }
            case MOVIE_ID: {
                if (context != null) {
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
