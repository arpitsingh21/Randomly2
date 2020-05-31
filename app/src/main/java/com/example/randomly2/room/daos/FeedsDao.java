package com.example.randomly2.room.daos;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.randomly2.data.PostResponse;
import com.example.randomly2.room.tables.Feed;

import java.util.List;

@Dao
public interface FeedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] bulkInsert(List<Feed> list);

    @Query("SELECT * from feed WHERE " + "page_id ==:page_id")
    List<Feed> getFeedByPage(int page_id);

    @Query("DELETE FROM feed")
    void nukeTable();
}
