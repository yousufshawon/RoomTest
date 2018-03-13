package com.yousuf.shawon.roomtest.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.yousuf.shawon.roomtest.db.util.DateConverter;

import java.util.Date;

/**
 * Created by yousuf on 3/12/18.
 */

@TypeConverters(DateConverter.class)
@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private long roll;
    private String department;
    private float result;

    private Date time;

    @NonNull
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getRoll() {
        return roll;
    }

    public String getDepartment() {
        return department;
    }

    public float getResult() {
        return result;
    }

    public Date getTime() {
        return time;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(long roll) {
        this.roll = roll;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roll=" + roll +
                ", department='" + department + '\'' +
                ", result=" + result +
                ", time=" + time +
                '}';
    }
}
