package com.iokays.dispatch.core.adapter.persistence.quartz.table;

import javax.annotation.processing.Generated;

/**
 * QrtzCalendars is a Querydsl bean type
 */
@Generated("com.querydsl.codegen.BeanSerializer")
public class QrtzCalendars {

    private java.sql.Blob calendar;

    private String calendarName;

    private String schedName;

    public java.sql.Blob getCalendar() {
        return calendar;
    }

    public void setCalendar(java.sql.Blob calendar) {
        this.calendar = calendar;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

}

