package com.em;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.em.helper.CallBack;
import com.em.adapters.SectionsPagerAdapter;
import com.em.helper.ResponseEntity;
import com.em.services.StudentService;
import com.em.services.impl.StudentServiceImpl;
import com.em.utils.EmUtils;
import com.em.vo.Student;

import java.util.List;

public class MainActivity extends ActionBarActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private List<Student> students;
    private ProgressDialog progressDialog;
    private StudentService studentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context _this = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        progressDialog = new ProgressDialog(this);
        EmUtils.showProgressDialog(progressDialog);
        studentService = new StudentServiceImpl();
        studentService.getStudents(1, 1, new CallBack<List<Student>>() {
            @Override
            public void callBack(List<Student> _students) {
                students = _students;
                mSectionsPagerAdapter.setDataProvider(students);
                mViewPager.setAdapter(mSectionsPagerAdapter);
                EmUtils.hideProgressDialog(progressDialog);
            }
        }, new CallBack<List<Student>>() {
            @Override
            public void callBack(List<Student> _students) {
                students = _students;
                Toast.makeText(getApplicationContext(), "Could not fetch the student list.", Toast.LENGTH_SHORT).show();
                EmUtils.hideProgressDialog(progressDialog);
            }
        });
    }

    public void selectFragment(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_save) {
            Toast.makeText(getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
            EmUtils.showProgressDialog(progressDialog);
            studentService.saveAttendance(students, new CallBack<ResponseEntity>() {
                @Override
                public void callBack(ResponseEntity responseEntity) {
                    Toast.makeText(getApplicationContext(), "Attendance saved successfully.", Toast.LENGTH_SHORT).show();
                    EmUtils.hideProgressDialog(progressDialog);
                }
            }, new CallBack<ResponseEntity>() {
                @Override
                public void callBack(ResponseEntity responseEntity) {
                    Toast.makeText(getApplicationContext(), "Could not save the attendance.", Toast.LENGTH_SHORT).show();
                    EmUtils.hideProgressDialog(progressDialog);
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}