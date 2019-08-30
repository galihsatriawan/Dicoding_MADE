package id.shobrun.moviecatalogue.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import id.shobrun.moviecatalogue.database.dao.MovieDao;
import id.shobrun.moviecatalogue.models.data.Movie;

@Database(entities = {Movie.class},version = 1)
public abstract class MovieCatalogueDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    private static MovieCatalogueDatabase INSTANCE;
    public static String DB_NAME = "db_movie_catalogue";
    public static MovieCatalogueDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MovieCatalogueDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MovieCatalogueDatabase.class,
                            DB_NAME
                            ).build();
                }
            }
        }
        return INSTANCE;
    }
}
