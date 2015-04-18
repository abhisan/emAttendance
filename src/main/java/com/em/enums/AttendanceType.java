package com.em.enums;

import android.graphics.Color;

public enum AttendanceType {
    UNDEFINED(0, Color.BLACK), PRESENT(1, Color.GREEN), LEAVE(2, Color.RED), ABSENT(3, Color.YELLOW), LATE(4, Color.YELLOW);
    int type;
    int color;

    AttendanceType(int type, int color) {
        this.color = color;
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public int getType() {
        return type;
    }

    public static AttendanceType getAttendanceType(int type) {
        for (AttendanceType t : AttendanceType.values()) {
            if (t.getType() == type)
                return t;
        }
        return UNDEFINED;
    }
}
