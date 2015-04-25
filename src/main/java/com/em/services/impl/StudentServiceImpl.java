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
import com.em.utils.EmUtils;
import com.em.vo.Attendance;
import com.em.vo.SClass;
import com.em.vo.Section;
import com.em.vo.Student;
import com.em.vo.Subject;
import com.em.vo.User;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    public void getServiceTicket(User user, final CallBack<ResponseEntity<String>> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = EmUtils.getrServerUrl() + "ticket";
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.POST,
                serviceUrl,
                user,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failureCallBack.callBack(error);
                    }
                },
                new Response.Listener<ResponseEntity<List<SClass>>>() {
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

    public void getClasses(final CallBack<ResponseEntity<List<SClass>>> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = EmUtils.getrServerUrl() + "classes?userid=" + AppController.getInstance().getUserId() + "&st=" + AppController.getInstance().getToken();
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.GET,
                serviceUrl,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failureCallBack.callBack(error);
                    }
                },
                new Response.Listener<ResponseEntity<List<SClass>>>() {
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

            @Override
            protected TypeReference getTypeReference() {
                return new TypeReference<ResponseEntity<List<SClass>>>() {
                };
            }
        };
        AppController.getInstance().addToRequestQueue(jacksonJsonRequest);
    }

    public void getSections(int classId, final CallBack<ResponseEntity<List<Section>>> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = EmUtils.getrServerUrl() + "classes/" + classId + "/sections?userid=" + AppController.getInstance().getUserId() + "&st=" + AppController.getInstance().getToken();
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.GET,
                serviceUrl,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failureCallBack.callBack(error);
                    }
                },
                new Response.Listener<ResponseEntity<List<Section>>>() {
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

            @Override
            protected TypeReference getTypeReference() {
                return new TypeReference<ResponseEntity<List<Section>>>() {
                };
            }
        };
        AppController.getInstance().addToRequestQueue(jacksonJsonRequest);
    }

    public void getSubjects(int classId, int sectionId, final CallBack<ResponseEntity<List<Subject>>> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = EmUtils.getrServerUrl() + "classes/" + classId + "/sections/" + sectionId + "/subjects?userid=" + AppController.getInstance().getUserId() + "&st=" + AppController.getInstance().getToken();
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.GET,
                serviceUrl,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failureCallBack.callBack(error);
                    }
                },
                new Response.Listener<ResponseEntity<List<Subject>>>() {
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

            @Override
            protected TypeReference getTypeReference() {
                return new TypeReference<ResponseEntity<List<Subject>>>() {
                };
            }
        };
        AppController.getInstance().addToRequestQueue(jacksonJsonRequest);
    }

    public void getStudents(int classId, int sectionId, final CallBack<ResponseEntity<List<Student>>> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = EmUtils.getrServerUrl() + "classes/" + classId + "/sections/" + sectionId + "/students?userid=" + AppController.getInstance().getUserId() + "&st=" + AppController.getInstance().getToken();
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.GET,
                serviceUrl,
                null,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        failureCallBack.callBack(error);
                    }
                },
                new Response.Listener<ResponseEntity<List<Student>>>() {
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

            @Override
            protected TypeReference getTypeReference() {
                return new TypeReference<ResponseEntity<List<Student>>>() {
                };
            }
        };
        AppController.getInstance().addToRequestQueue(jacksonJsonRequest);
    }

    public void getProfileImage(int studentId) {
        //String serviceUrl = EmUtils.getrServerUrl() + "images";
    }

    public void saveAttendance(int classId, int sectionId, int subjectId, List<Attendance> attendances, final CallBack<ResponseEntity> successCallBack, final CallBack<VolleyError> failureCallBack) {
        String serviceUrl = EmUtils.getrServerUrl() + "classes/" + classId + "/sections/" + sectionId + "/attendance?userid=" + AppController.getInstance().getUserId() + "&st=" + AppController.getInstance().getToken();
        JacksonJsonRequest jacksonJsonRequest = new JacksonJsonRequest(
                Request.Method.POST,
                serviceUrl,
                attendances,
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