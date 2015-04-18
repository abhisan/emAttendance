package com.em.vo;

import android.graphics.drawable.Drawable;


import com.em.enums.AttendanceType;

public class ListItem {
    private String studentName;
    private Long studentId;
    private AttendanceType type;
    private Drawable studentIcon;

    public ListItem(Long studentId, String studentName, Drawable studentIcon, AttendanceType type) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = type;
        this.studentIcon = studentIcon;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setType(AttendanceType type) {
        this.type = type;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public AttendanceType getType() {
        return type;
    }

    public Drawable getStudentIcon() {
        return studentIcon;
    }

    public void setStudentIcon(Drawable studentIcon) {
        this.studentIcon = studentIcon;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}