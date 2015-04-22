package com.em.services;

import com.android.volley.VolleyError;
import com.em.helper.CallBack;
import com.em.helper.ResponseEntity;
import com.em.vo.Attendance;
import com.em.vo.SClass;
import com.em.vo.Section;
import com.em.vo.Student;
import com.em.vo.Subject;

import org.json.JSONObject;

import java.util.List;
public interface StudentService {
    public void getClasses(final CallBack <List<SClass>> successCallBack, final CallBack<List<SClass>> failureCallBack );
    public void getSections(int classId, final CallBack <List<Section>> successCallBack, final CallBack<List<Section>> failureCallBack );
    public void getSubjects(int classId, final CallBack <List<Subject>> successCallBack, final CallBack<List<Subject>> failureCallBack );
    public void getStudents(int classId, int sectionId, final CallBack <List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack );
    public void saveAttendance(int subjectId, List<Student> students,  final CallBack <ResponseEntity> successCallBack, final CallBack<VolleyError> failureCallBack);
    public void getProfileImage(int studentId);
}
