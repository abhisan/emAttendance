package com.em.vo;

import android.graphics.drawable.Drawable;

public class Student {
    private String studentName;
    private Long studentId;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
