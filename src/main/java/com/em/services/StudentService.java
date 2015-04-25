package com.em.services;

import com.android.volley.VolleyError;
import com.em.helper.CallBack;
import com.em.helper.ResponseEntity;
import com.em.vo.Attendance;
import com.em.vo.SClass;
import com.em.vo.Section;
import com.em.vo.Student;
import com.em.vo.Subject;
import com.em.vo.User;

import org.json.JSONObject;

import java.util.List;
public interface StudentService {
    public void getServiceTicket(User user, final CallBack <ResponseEntity<String>> successCallBack, final CallBack<VolleyError> failureCallBack );
    public void getClasses(final CallBack <ResponseEntity<List<SClass>>> successCallBack, final CallBack<VolleyError> failureCallBack );
    public void getSections(int classId, final CallBack <ResponseEntity<List<Section>>> successCallBack, final CallBack<VolleyError> failureCallBack );
    public void getSubjects(int classId, int sectionId, final CallBack <ResponseEntity<List<Subject>>> successCallBack, final CallBack<VolleyError> failureCallBack );
    public void getStudents(int classId, int sectionId, final CallBack <ResponseEntity<List<Student>>> successCallBack, final CallBack<VolleyError> failureCallBack );
    public void saveAttendance(int classId, int sectionId, int subjectId,List<Attendance> students,  final CallBack <ResponseEntity> successCallBack, final CallBack<VolleyError> failureCallBack);
    public void getProfileImage(int studentId);
}
