package com.yousuf.shawon.roomtest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.yousuf.shawon.roomtest.db.dao.StudentDao;
import com.yousuf.shawon.roomtest.db.entity.Student;

/**
 * Created by yousuf on 3/12/18.
 */

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static final String DB_NAME = "studentDb.db";

    private static AppDatabase INSTANCE;

    public abstract StudentDao studentDao();


    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {

            synchronized (AppDatabase.class){

                if (INSTANCE == null) {
                    INSTANCE =
                            // Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                                    // To simplify the codelab, allow queries on the main thread.
                                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                                    .allowMainThreadQueries()
                                    .build();
                }
            }


        }
        return INSTANCE;
    }
}
