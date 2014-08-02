/*
 * #%L
 * Diana UI Core
 * %%
 * Copyright (C) 2014 Diana UI
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.dianaui.universal.core.client.ui.base.helper;

import com.dianaui.universal.core.client.ui.constants.DayOfWeekFormat;
import com.dianaui.universal.core.client.ui.constants.HourFormat;
import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

/**
 * Model used to get calendar information for {@link com.dianaui.universal.core.client.ui.DateTimePicker} and its
 * subclasses.
 * Based on GWT {@link com.google.gwt.user.datepicker.client.CalendarModel}.
 *
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
@SuppressWarnings(/* Required to use Date API in gwt */{"deprecation"})
public class CalendarModel {

    /**
     * The number of weeks normally displayed in a month.
     */
    public static final int WEEKS_IN_MONTH = 6;

    /**
     * Number of days normally displayed in a week.
     */
    public static final int DAYS_IN_WEEK = 7;

    public static final int MONTHS_IN_YEAR = 12;

    private static final int MAX_DAYS_IN_MONTH = 32;

    private static final String[] dayOfWeekNames = new String[7];

    private static String[] dayOfMonthNames = new String[32];

    private final String[] monthOfYearNames = new String[12];

    private String dayOfWeekFormat;
    private String hourFormat;
    private String minuteFormat;

    /**
     * Constructor.
     */
    public CalendarModel() {
        refresh();
    }

    /**
     * Formats a date's day of month. For example "1".
     *
     * @param date the date
     * @return the formated day of month
     */
    public String formatDayOfMonth(Date date) {
        return dayOfMonthNames[date.getDate()];
    }

    /**
     * Format a day in the week. So, for example "Monday".
     *
     * @param dayInWeek the day in week to format
     * @return the formatted day in week
     */
    public String formatDayOfWeek(int dayInWeek) {
        return dayOfWeekNames[dayInWeek];
    }

    /**
     * Format a month in the year. So, for example "January".
     *
     * @param month A number from 0 (for January) to 11 (for December) identifying the month wanted.
     * @return the formatted month
     */
    public String formatMonth(int month) {
        return monthOfYearNames[month];
    }

    /**
     * Gets the date of month formatter.
     *
     * @return the day of month formatter
     */
    public DateTimeFormat getDayOfMonthFormatter() {
        return DateTimeFormat.getFormat("d");
    }

    public void setDayOfWeekFormat(String format) {
        dayOfWeekFormat = format;

        refresh();
    }

    /**
     * Gets the day of week formatter.
     *
     * @return the day of week formatter
     */
    public DateTimeFormat getDayOfWeekFormatter() {
        if (dayOfWeekFormat != null) {
            return DateTimeFormat.getFormat(dayOfWeekFormat);
        }
        return DateTimeFormat.getFormat(DayOfWeekFormat.SHORT.getFormat());
    }

    /**
     * Gets the month and year formatter.
     *
     * @return the month and year formatter
     */
    public DateTimeFormat getMonthAndYearFormatter() {
        return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.YEAR_MONTH);
    }

    /**
     * Gets the month formatter.
     *
     * @return the month formatter
     */
    public DateTimeFormat getMonthFormatter() {
        return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.MONTH_ABBR);
    }

    /**
     * Gets the year formatter.
     *
     * @return the year formatter
     */
    public DateTimeFormat getYearFormatter() {
        return DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.YEAR);
    }

    /**
     * Gets the hour formatter.
     *
     * @return the month formatter
     */
    public DateTimeFormat getHourFormatter() {
        if (hourFormat != null) {
            return DateTimeFormat.getFormat(hourFormat);
        }
        return DateTimeFormat.getFormat(HourFormat._12.getFormat());
    }

    public void setHourFormat(String format) {
        hourFormat = format;
    }

    /**
     * Gets the minute formatter.
     *
     * @return the month formatter
     */
    public DateTimeFormat getMinuteFormatter() {
        if (minuteFormat != null) {
            return DateTimeFormat.getFormat(minuteFormat);
        }
        return DateTimeFormat.getFormat("mm");
    }

    public void setMinuteFormat(String format) {
        minuteFormat = format;
    }

    /**
     * Refresh the current model as needed.
     */
    protected void refresh() {
        // Finding day of week names
        Date date = new Date();
        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            date.setDate(i);
            int dayOfWeek = date.getDay();
            dayOfWeekNames[dayOfWeek] = getDayOfWeekFormatter().format(date);
        }

        // Finding day of month names
        date.setMonth(0);

        for (int i = 1; i < MAX_DAYS_IN_MONTH; ++i) {
            date.setDate(i);
            dayOfMonthNames[i] = getDayOfMonthFormatter().format(date);
        }

        // finding month names
        date.setDate(1);

        for (int i = 0; i < MONTHS_IN_YEAR; ++i) {
            date.setMonth(i);
            monthOfYearNames[i] = getMonthFormatter().format(date);
        }
    }

}
