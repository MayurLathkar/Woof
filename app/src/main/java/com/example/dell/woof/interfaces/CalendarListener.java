package com.example.dell.woof.interfaces;

import java.util.Date;

/**
 * Created by Nilanchala on 9/14/15.
 */
public interface CalendarListener {
    void onDateSelected(Date date);
    void onMonthChanged(Date time);
}
