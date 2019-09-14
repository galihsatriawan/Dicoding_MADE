package id.shobrun.moviecatalogue.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import id.shobrun.moviecatalogue.models.data.Movie;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);
    @Delete
    void deleteMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Query("SELECT * FROM "+ Movie.TABLE_NAME)
    List<Movie> getAllMovie();

    @Query("SELECT * FROM "+Movie.TABLE_NAME+" WHERE "+Movie.TAGS+" = :tags")
    List<Movie> getAllMovieByTags(String tags);

    @Query("SELECT * FROM "+Movie.TABLE_NAME+" WHERE "+Movie._ID+" = :id")
    Movie getMovieById(int id);


    @Query("SELECT * FROM "+Movie.TABLE_NAME)
    Cursor selectAllProvider();

    @Query("SELECT * FROM "+Movie.TABLE_NAME +" WHERE "+Movie._ID+" = :id")
    Cursor selectByIdProvider(int id);

    @Query("SELECT * FROM "+Movie.TABLE_NAME +" WHERE "+Movie.TAGS+" = :tags")
    Cursor selectByTagProvider(String tags);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    int insertProvider(Movie movie);

    @Query("DELETE FROM "+Movie.TABLE_NAME+" WHERE "+Movie._ID+" = :id")
    int deleteByIdProvider(int id);

    @Update
    int updateProvider(Movie movie);


}
