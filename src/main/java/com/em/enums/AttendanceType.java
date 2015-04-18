package com.em.enums;

import android.graphics.Color;

public enum AttendanceType {
    LEAVE(Color.RED),PRESENT(Color.GREEN),ABSENT(Color.YELLOW), LATE(Color.YELLOW), UNDEFINED(Color.BLACK);

    int color;
    AttendanceType(int color){
        this.color = color;
    }
    public int getColor(){
        return color;
    }
}
