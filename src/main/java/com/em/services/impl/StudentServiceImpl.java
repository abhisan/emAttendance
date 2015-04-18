package com.em.services.impl;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.em.AppController;
import com.em.helper.CallBack;
import com.em.helper.ResponseEntity;
import com.em.services.StudentService;
import com.em.vo.Student;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private String SERVER_LOCATION = "http://192.168.1.2:8080/rest/";
    public void getStudents(int classId, int sectionId, final CallBack<List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack) {
        String serviceUrl = SERVER_LOCATION+"students?classId=" + classId + "&sectionId=" + sectionId;
        JacksonRequest jacksonRequest = new JacksonRequest<List<Student>>(Request.Method.GET, serviceUrl,
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

    public void saveAttendance(List<Student> students, final CallBack<ResponseEntity> successCallBack, final CallBack<ResponseEntity> failureCallBack) {
        String serviceUrl = SERVER_LOCATION+"students";
        Map<String, String> params = new HashMap<String, String>();
        for (Student student : students) {
            params.put(student.getStudentId().toString(), Integer.toString(student.getType()));
        }
        JacksonRequest jacksonRequest = new JacksonRequest<ResponseEntity>(Request.Method.POST, serviceUrl, params,
                new JacksonRequestListener<ResponseEntity>() {
                    @Override
                    public void onResponse(ResponseEntity response, int statusCode, VolleyError error) {
                        if (error == null) {
                            if (response != null) {
                                successCallBack.callBack(response);
                            } else {
                                failureCallBack.callBack(response);
                            }
                        } else {
                            failureCallBack.callBack(response);
                        }
                    }

                    @Override
                    public JavaType getReturnType() {
                        return SimpleType.construct(ResponseEntity.class);
                    }
                }
        );
    }
}
