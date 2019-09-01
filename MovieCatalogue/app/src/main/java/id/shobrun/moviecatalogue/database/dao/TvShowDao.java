package id.shobrun.moviecatalogue.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


import id.shobrun.moviecatalogue.models.data.TvShow;
@Dao
public interface TvShowDao {
    @Insert
    void insertTvShow(TvShow tvShow);
    @Delete
    void deleteTvShow(TvShow tvShow);

    @Update
    void updateTvShow(TvShow tvShow);

    @Query("SELECT * FROM tb_tv_show")
    List<TvShow> getAllTvShow();

    @Query("SELECT * FROM tb_tv_show WHERE tags = :tags")
    List<TvShow> getAllTvShowByTags(String tags);

    @Query("SELECT * FROM tb_tv_show WHERE tvShowId = :id")
    TvShow getTvShowById(int id);
}
