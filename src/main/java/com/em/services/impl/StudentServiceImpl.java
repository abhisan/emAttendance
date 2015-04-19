package com.em.services.impl;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.em.AppController;
import com.em.helper.CallBack;
import com.em.helper.JacksonJsonRequest;
import com.em.helper.ResponseEntity;
import com.em.services.StudentService;
import com.em.vo.Student;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private String SERVER_LOCATION = "http://192.168.1.4:8080/rest/";

    public void getStudents(int classId, int sectionId, final CallBack<List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack) {
        String serviceUrl = SERVER_LOCATION + "students?classId=" + classId + "&sectionId=" + sectionId;
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

    public void saveAttendance(List<Student> students, final CallBack<ResponseEntity> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = SERVER_LOCATION + "students";
        /*Map<String, String> params = new HashMap<String, String>();
        for (Student student : students) {
            params.put(student.getStudentId().toString(), Integer.toString(student.getType()));
        }*/
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.POST,
                serviceUrl,
                students,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failureCallBack.callBack(error);
                    }
                },
                new Response.Listener<ResponseEntity>() {
                    @Override
                    public void onResponse(ResponseEntity response) {
                        if (response != null) {
                            successCallBack.callBack(response);
                        }
                    }
                },
                null, ResponseEntity.class) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json; charset=utf-8");
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jacksonJsonRequest);
    }
}
