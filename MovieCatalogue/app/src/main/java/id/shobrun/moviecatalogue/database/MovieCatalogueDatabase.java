package id.shobrun.moviecatalogue.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.database.dao.TvShowDao;
import id.shobrun.moviecatalogue.models.data.Movie;
import id.shobrun.moviecatalogue.models.data.TvShow;

@Database(entities = {Movie.class, TvShow.class},version = 1,exportSchema = false)
public abstract class MovieCatalogueDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract TvShowDao tvShowDao();

    public static final String AUTHORITY = "id.shobrun.moviecatalogue";

    private static MovieCatalogueDatabase INSTANCE;
    private static String DB_NAME = "db_movie_catalogue";

    public static MovieCatalogueDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MovieCatalogueDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MovieCatalogueDatabase.class,
                            DB_NAME
                            ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
