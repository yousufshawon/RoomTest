package com.yousuf.shawon.roomtest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yousuf.shawon.roomtest.databinding.ActivityMainBinding;
import com.yousuf.shawon.roomtest.db.AppDatabase;
import com.yousuf.shawon.roomtest.db.dao.StudentDao;
import com.yousuf.shawon.roomtest.db.entity.Student;
import com.yousuf.shawon.roomtest.util.AppConstants;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fabAdd;
    ListView listView;

    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initUI(binding);

        getData();

    }



    private void initUI(ActivityMainBinding binding) {

        fabAdd = binding.fabAdd;
        listView = binding.listViewStudent;

        fabAdd.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                try {
                    Student student = (Student) listView.getAdapter().getItem(position);
                    startNewStudentActivityWithId(student.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "Error: Can't convert Adapter Object to Student");
                }


            }
        });

    }

    private void startNewStudentActivityWithId(int studentId) {

        Intent intent = new Intent(this, AddNewActivity.class);
        intent.putExtra(AppConstants.STUDENT_ID, studentId);

        startActivity(intent);
    }

    private void getData() {

        AppDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext());
        StudentDao studentDao = appDatabase.studentDao();

        final LiveData<List<Student>> listLiveData = studentDao.getAllStudents();
        listLiveData.observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                Log.i(TAG, "Students: " + Arrays.toString(students.toArray()));
                listView.setAdapter(new ArrayAdapter<Student>(getContext(), android.R.layout.simple_list_item_1, students ));
            }
        });


    }

    private Context getContext() {
        return this;
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.fabAdd:

                onFabAddClick();

                break;

        }

    }

    private void onFabAddClick() {

        startActivity(new Intent(this, AddNewActivity.class));

    }
}
