package id.shobrun.moviecatalogue.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import id.shobrun.moviecatalogue.models.data.Movie;

@Dao
public interface MovieDao {
    @Insert
    void insertMovie(Movie movie);
    @Delete
    void deleteMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Query("SELECT * FROM tb_movies")
    List<Movie> getAllMovie();

    @Query("SELECT * FROM tb_movies WHERE tags = :tags")
    List<Movie> getAllMovieByTags(String tags);

    @Query("SELECT * FROM tb_movies WHERE movieId = :id")
    Movie getMovieById(int id);
}
