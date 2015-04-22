package com.em.services.impl;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.em.AppController;
import com.em.helper.CallBack;
import com.em.helper.JacksonJsonRequest;
import com.em.helper.ResponseEntity;
import com.em.services.StudentService;
import com.em.vo.SClass;
import com.em.vo.Section;
import com.em.vo.Student;
import com.em.vo.Subject;
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

    public void getProfileImage(int studentId) {
        String serviceUrl = SERVER_LOCATION + "students";
    }

    public void getClasses(final CallBack<List<SClass>> successCallBack, final CallBack<List<SClass>> failureCallBack) {
        String serviceUrl = SERVER_LOCATION + "classes";
        JacksonRequest jacksonRequest = new JacksonRequest<List<SClass>>(Request.Method.GET, serviceUrl,
                new JacksonRequestListener<List<SClass>>() {
                    @Override
                    public void onResponse(List<SClass> response, int statusCode, VolleyError error) {
                        if (error == null) {
                            if (response != null) {
                                successCallBack.callBack(response);
                            } else {
                                failureCallBack.callBack(new ArrayList<SClass>());
                            }
                        } else {
                            failureCallBack.callBack(new ArrayList<SClass>());
                        }
                    }

                    @Override
                    public JavaType getReturnType() {
                        return TypeFactory.defaultInstance().constructParametricType(List.class, SClass.class);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jacksonRequest);
    }

    public void getSections(int classId, final CallBack<List<Section>> successCallBack, final CallBack<List<Section>> failureCallBack) {
        String serviceUrl = SERVER_LOCATION + "sections";
        JacksonRequest jacksonRequest = new JacksonRequest<List<Section>>(Request.Method.GET, serviceUrl,
                new JacksonRequestListener<List<Section>>() {
                    @Override
                    public void onResponse(List<Section> response, int statusCode, VolleyError error) {
                        if (error == null) {
                            if (response != null) {
                                successCallBack.callBack(response);
                            } else {
                                failureCallBack.callBack(new ArrayList<Section>());
                            }
                        } else {
                            failureCallBack.callBack(new ArrayList<Section>());
                        }
                    }

                    @Override
                    public JavaType getReturnType() {
                        return TypeFactory.defaultInstance().constructParametricType(List.class, Section.class);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jacksonRequest);
    }

    public void getSubjects(int classId, final CallBack<List<Subject>> successCallBack, final CallBack<List<Subject>> failureCallBack) {
        String serviceUrl = SERVER_LOCATION + "subjects";
        JacksonRequest jacksonRequest = new JacksonRequest<List<Subject>>(Request.Method.GET, serviceUrl,
                new JacksonRequestListener<List<Subject>>() {
                    @Override
                    public void onResponse(List<Subject> response, int statusCode, VolleyError error) {
                        if (error == null) {
                            if (response != null) {
                                successCallBack.callBack(response);
                            } else {
                                failureCallBack.callBack(new ArrayList<Subject>());
                            }
                        } else {
                            failureCallBack.callBack(new ArrayList<Subject>());
                        }
                    }

                    @Override
                    public JavaType getReturnType() {
                        return TypeFactory.defaultInstance().constructParametricType(List.class, Subject.class);
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jacksonRequest);
    }

    public void getStudents(int classId, int sectionId, final CallBack<List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(AppController.getInstance().getApplicationContext());
        //SharedPreferences.Editor editor = sharedPrefs.edit();
        //editor.clear();
        //editor.commit();
        String serverLocation = sharedPrefs.getString("server_location", "");
        String serverPort = sharedPrefs.getString("server_port", "");
        classId = Integer.parseInt(sharedPrefs.getString("class_id", "-1"));
        sectionId = Integer.parseInt(sharedPrefs.getString("section_id", "-1"));
        String serviceUrl = SERVER_LOCATION + "students";
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

    public void saveAttendance(int subjectId,  List<Student> students, final CallBack<ResponseEntity> successCallBack, final CallBack<VolleyError> failureCallBack) {
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
