package id.shobrun.consumer.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import id.shobrun.consumer.database.dao.MovieDao;
import id.shobrun.consumer.database.dao.TvShowDao;
import id.shobrun.consumer.models.data.Movie;
import id.shobrun.consumer.models.data.TvShow;

@Database(entities = {Movie.class, TvShow.class},version = 2,exportSchema = false)
public abstract class MovieCatalogueDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract TvShowDao tvShowDao();



    private static MovieCatalogueDatabase INSTANCE;
    private static String DB_NAME = "db_consumer_movie_catalogue";

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
