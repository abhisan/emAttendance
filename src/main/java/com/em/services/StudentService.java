package com.em.services;

import com.em.helper.CallBack;
import com.em.helper.ResponseEntity;
import com.em.vo.Attendance;
import com.em.vo.Student;

import java.util.List;
public interface StudentService {
    public void getStudents(int classId, int sectionId, final CallBack <List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack );
    public void saveAttendance(List<Student> students,  final CallBack <ResponseEntity> successCallBack, final CallBack<ResponseEntity> failureCallBack);
}
