package com.em.vo;

import android.graphics.drawable.Drawable;

import com.em.enums.AttendanceType;

public class Student {
    private String studentName;
    private Long studentId;
    private AttendanceType type;

    public Drawable getStudentIcon() {
        return studentIcon;
    }

    public void setStudentIcon(Drawable studentIcon) {
        this.studentIcon = studentIcon;
    }

    private Drawable studentIcon;

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentId() {
        return studentId;
    }
    public AttendanceType getType() {
        return type;
    }
    public void setType(AttendanceType type) {
        this.type = type;
    }

}
