package com.em.services;

import com.em.helper.CallBack;
import com.em.vo.Attendance;
import com.em.vo.Student;

import java.util.List;
public interface StudentService {
    public void getStudents(int classId, int sectionId, final CallBack <List<Student>> successCallBack, final CallBack<List<Student>> failureCallBack );
    public void saveAttendance(List<Attendance> attendances,  final CallBack successCallBack, final CallBack failureCallBack);
}
