package com.em.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.em.MainActivity;
import com.em.R;
import com.em.helper.CallBack;
import com.em.services.StudentService;
import com.em.services.impl.StudentServiceImpl;
import com.em.utils.EmUtils;
import com.em.vo.SClass;
import com.em.vo.Section;
import com.em.vo.Subject;

import java.util.ArrayList;
import java.util.List;


public class SelectActivity extends ActionBarActivity {
    private Button selectButton;
    private Button cancelButton;
    private Spinner selectClass;
    private Spinner selectSection;
    private Spinner selectSubject;
    private StudentService studentService;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        final Activity _this = this;
        selectButton = (Button) findViewById(R.id.select_ok);
        cancelButton = (Button) findViewById(R.id.select_cancel);
        selectClass = (Spinner) findViewById(R.id.select_class);
        selectSection = (Spinner) findViewById(R.id.select_section);
        selectSubject = (Spinner) findViewById(R.id.select_subject);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(_this, MainActivity.class);
                i.putExtra("CLASS_ID", (String) selectClass.getSelectedItem());
                i.putExtra("SECTION_ID", (String) selectSection.getSelectedItem());
                i.putExtra("SUBJECT_ID", (String) selectSubject.getSelectedItem());
                startActivity(i);
            }
        });
        selectClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                studentService.getSections(1, new CallBack<List<Section>>() {
                    @Override
                    public void callBack(List<Section> sections) {
                        selectSection.setAdapter(getSectionList(sections));
                        EmUtils.hideProgressDialog(progressDialog);
                    }
                }, new CallBack<List<Section>>() {
                    @Override
                    public void callBack(List<Section> sections) {
                        Toast.makeText(getApplicationContext(), "Could not fetch the section list.", Toast.LENGTH_SHORT).show();
                        EmUtils.hideProgressDialog(progressDialog);
                    }
                });
                studentService.getSubjects(1, new CallBack<List<Subject>>() {
                    @Override
                    public void callBack(List<Subject> subjects) {
                        selectSubject.setAdapter(getSubjectList(subjects));
                        EmUtils.hideProgressDialog(progressDialog);
                    }
                }, new CallBack<List<Subject>>() {
                    @Override
                    public void callBack(List<Subject> subjects) {
                        Toast.makeText(getApplicationContext(), "Could not fetch the section list.", Toast.LENGTH_SHORT).show();
                        EmUtils.hideProgressDialog(progressDialog);
                    }
                });
                //selectSubject.setAdapter(getSubjectList());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progressDialog = new ProgressDialog(this);
        EmUtils.showProgressDialog(progressDialog);
        studentService = new StudentServiceImpl();
        studentService.getClasses(new CallBack<List<SClass>>() {
            @Override
            public void callBack(List<SClass> sClasses) {
                selectClass.setAdapter(getClassList(sClasses));
                EmUtils.hideProgressDialog(progressDialog);
            }
        }, new CallBack<List<SClass>>() {
            @Override
            public void callBack(List<SClass> sClasses) {
                Toast.makeText(getApplicationContext(), "Could not fetch the class list.", Toast.LENGTH_SHORT).show();
                EmUtils.hideProgressDialog(progressDialog);
            }
        });
    }

    public ArrayAdapter<String> getClassList(List<SClass> sClasses) {
        List<String> list = new ArrayList<String>();
        for (SClass sclass : sClasses) {
            list.add(sclass.getClassId().toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }

    public ArrayAdapter<String> getSectionList(List<Section> sections) {
        List<String> list = new ArrayList<String>();
        for (Section section : sections) {
            list.add(section.getSectionId().toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }

    public ArrayAdapter<String> getSubjectList(List<Subject> subjects) {
        List<String> list = new ArrayList<String>();
        for (Subject subject : subjects) {
            list.add(subject.getSubjectId().toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }
}