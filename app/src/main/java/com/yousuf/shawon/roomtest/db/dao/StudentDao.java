package com.yousuf.shawon.roomtest.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yousuf.shawon.roomtest.db.entity.Student;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by yousuf on 3/12/18.
 */

@Dao
public interface StudentDao {


    @Insert(onConflict = IGNORE)
    void insert(Student student);

    @Query("SELECT * FROM Student WHERE id = :studentId")
    Student getStudentWithId(int studentId);

    @Query("SELECT * FROM Student ")
    LiveData<List<Student>> getAllStudents();

    @Update(onConflict = REPLACE)
    void updateStudent(Student student);

    @Query("DELETE FROM Student")
    void deleteAll();




}
