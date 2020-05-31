package com.example.randomly2.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;


import com.example.randomly2.room.daos.FeedsDao;
import com.example.randomly2.room.tables.Feed;

@Database(entities = {Feed.class},
        version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {


    private static volatile MyRoomDatabase sInstance;

    private static final String DATABASE_NAME = "room_database_feeds";
    private static final Object LOCK = new Object();

    public static MyRoomDatabase getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, MyRoomDatabase.DATABASE_NAME)
                            .addMigrations(MIGRATION_1_2).build();
                }
            }
        }

        return sInstance;
    }


    private static final String SQL_FEEDS = "CREATE TABLE feeds (" +
            "page_id INTEGER  NOT NULL," +
            "id TEXT  NOT NULL," +
            "thumbnail_image TEXT  NOT NULL  ," +
            "event_name TEXT PRIMARY KEY  NOT NULL," +
            "event_date INTEGER  NOT NULL  ," +
            "views INTEGER  NOT NULL   ," +
            "likes INTEGER  ," +
            "shares INTEGER  NOT NULL )";


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE IF EXISTS feeds");
            database.execSQL(SQL_FEEDS);
        }
    };

    public abstract FeedsDao getFeedDao();


}