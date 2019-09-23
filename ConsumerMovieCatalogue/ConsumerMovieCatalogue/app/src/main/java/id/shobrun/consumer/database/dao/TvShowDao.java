package id.shobrun.consumer.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


import id.shobrun.consumer.models.data.TvShow;
@Dao
public interface TvShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShow(TvShow tvShow);
    @Delete
    void deleteTvShow(TvShow tvShow);

    @Update
    void updateTvShow(TvShow tvShow);

    @Query("SELECT * FROM "+TvShow.TABLE_NAME)
    List<TvShow> getAllTvShow();

    @Query("SELECT * FROM "+TvShow.TABLE_NAME +" WHERE tags = :tags")
    List<TvShow> getAllTvShowByTags(String tags);

    @Query("SELECT * FROM "+TvShow.TABLE_NAME+" WHERE _id = :id")
    TvShow getTvShowById(int id);
}
