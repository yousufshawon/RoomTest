package com.yousuf.shawon.roomtest;

import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.yousuf.shawon.roomtest.databinding.ActivityAddNewBinding;
import com.yousuf.shawon.roomtest.db.AppDatabase;
import com.yousuf.shawon.roomtest.db.entity.Student;
import com.yousuf.shawon.roomtest.util.AppConstants;

import java.util.Calendar;

public class AddNewActivity extends AppCompatActivity implements View.OnClickListener {


    EditText editTextName, editTextRoll, editTextDepartment, editTextResult;
    FloatingActionButton fabDone;

    int referenceStudentId = 0;

    Student student;


    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_new);

        ActivityAddNewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new);

        referenceStudentId = getIntent().getIntExtra(AppConstants.STUDENT_ID, 0);




        initUI(binding);

        if (referenceStudentId!= 0) {
            setTitle(R.string.edit_student);
            student = getStudentWithId(referenceStudentId);
            if (student != null) {
                binding.setStudent(student);
            }
        }else {
            setTitle(R.string.add_student);
        }

    }

    private void initUI(ActivityAddNewBinding binding) {

        editTextName = binding.editTextName;
        editTextRoll = binding.editTextRoll;
        editTextDepartment = binding.editTextDepartment;
        editTextResult = binding.editTextResult;

        fabDone = binding.fabDone;

        fabDone.setOnClickListener(this);

    }



    public void validateStudentData(){

        saveStudent();
    }


    public void saveStudent(){

        if (student == null) {
             student = new Student();
        }

        student.setName(editTextName.getText().toString().trim());
        try {
            long roll = Long.parseLong(editTextRoll.getText().toString().trim());
            student.setRoll(roll);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        student.setDepartment(editTextDepartment.getText().toString().trim());
        try {
            float result = Float.parseFloat(editTextResult.getText().toString().trim());
            student.setResult(result);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        student.setTime(Calendar.getInstance().getTime());

        Log.i(TAG, "Student: " + student.toString());

        if (student.getId()==0) {
            AppDatabase.getDatabase(getApplicationContext()).studentDao().insert(student);
        }else {
            AppDatabase.getDatabase(getApplicationContext()).studentDao().updateStudent(student);
        }


        finish();

    }

    public Student getStudentWithId(int studentId){

        return AppDatabase.getDatabase(getApplicationContext()).studentDao().getStudentWithId(studentId);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.fabDone:
                onFabDoneClick();
                break;
        }
    }

    private void onFabDoneClick() {

        validateStudentData();

    }
}
