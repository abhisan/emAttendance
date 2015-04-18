package com.em.services.impl;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.em.AppController;
import com.em.helper.CallBack;
import com.em.services.StudentService;
import com.em.vo.Attendance;
import com.em.vo.Student;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    public void getStudents(int classId, int sectionId, final CallBack<List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack) {
        String url = "http://192.168.1.2/rest/students.json?classId=" + classId + "&sectionId=" + sectionId;
        JacksonRequest jacksonRequest = new JacksonRequest<List<Student>>(Request.Method.GET, url,
                new JacksonRequestListener<List<Student>>() {
                    @Override
                    public void onResponse(List<Student> response, int statusCode, VolleyError error) {
                        if (error == null) {
                            if (response != null) {
                                successCallBack.callBack(response);
                            } else {
                                failureCallBack.callBack(new ArrayList<Student>());
                            }
                        } else {
                            failureCallBack.callBack(new ArrayList<Student>());
                        }
                    }
                    @Override
                    public JavaType getReturnType() {
                        return TypeFactory.defaultInstance().constructParametricType(List.class, Student.class);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jacksonRequest);
    }

    public void saveAttendance(List<Attendance> attendances, final CallBack successCallBack, final CallBack failureCallBack) {

    }
}