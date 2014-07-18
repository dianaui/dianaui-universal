package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GWT Widgets
 * %%
 * Copyright (C) 2014 GWT Widgets
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

import com.google.gwt.dom.client.*;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import org.gwtbootstrap3.client.ui.base.helper.CalendarModel;
import org.gwtbootstrap3.client.ui.base.modal.ModalWithBackdrop;
import org.gwtbootstrap3.client.ui.constants.*;
import org.gwtbootstrap3.client.ui.html.Div;
import org.gwtbootstrap3.client.ui.html.UnorderedList;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DateTimePicker extends ModalWithBackdrop implements LeafValueEditor<Date>, HasValue<Date> {

    private static String TABLE_START = "<table class=\"table-condensed\">";
    private static String TABLE_END = "</table>";
    private static String TBODY_START = "<tbody>";
    private static String TBODY_END = "</tbody>";
    private final Div container;
    private CalendarModel calendarModel = new CalendarModel();
    private DayOfWeekFormat dayOfWeekFormat;
    private HourFormat hourFormat;
    private Date value = new Date();
    private boolean dateEnabled = true;
    private boolean timeEnabled = true;
    private boolean autoClose = true;
    private Collapse dateCollapse;
    private Div dateContainer;
    private Collapse timeCollapse;
    private Div timeContainer;
    private Days days;
    private Months months;
    private Years years;
    private Time time;
    private Hours hours;
    private Minutes minutes;

    public DateTimePicker() {
        setFade(false);
        setBackdrop(ModalBackdrop.FALSE);

        container = new Div();
        container.addStyleName(Styles.DATETIMEPICKER);
        container.addStyleName(Styles.DROPDOWN_MENU);

        add(container);
    }

    public CalendarModel getCalendarModel() {
        return calendarModel;
    }

    public DayOfWeekFormat getDayOfWeekFormat() {
        return dayOfWeekFormat;
    }

    public void setDayOfWeekFormat(DayOfWeekFormat format) {
        this.dayOfWeekFormat = format;

        calendarModel.setDayOfWeekFormat(format.getFormat());

        checkForRedraw();
    }

    public HourFormat getHourFormat() {
        return hourFormat;
    }

    public void setHourFormat(HourFormat format) {
        this.hourFormat = format;

        calendarModel.setHourFormat(format.getFormat());

        checkForRedraw();
    }

    public boolean isDateEnabled() {
        return dateEnabled;
    }

    public void setDateEnabled(boolean enabled) {
        if (!timeEnabled && !enabled) {
            throw new RuntimeException("Can not disable date when time disabled");
        }
        this.dateEnabled = enabled;

        checkForRedraw();
    }

    public boolean isTimeEnabled() {
        return timeEnabled;
    }

    public void setTimeEnabled(boolean enabled) {
        if (!dateEnabled && !enabled) {
            throw new RuntimeException("Can not disable time when date disabled");
        }
        this.timeEnabled = enabled;

        checkForRedraw();
    }

    public boolean isAutoClose() {
        return autoClose;
    }

    public void setAutoClose(boolean enabled) {
        this.autoClose = enabled;
    }

    @Override
    protected void onShow() {
        container.clear();

        if (dateEnabled && timeEnabled) {
            UnorderedList list = new UnorderedList();
            list.setStyleName(Styles.LIST_UNSTYLED);

            if (dateCollapse == null) {
                dateCollapse = new Collapse(LIElement.TAG);

                initDateContainer();
                dateCollapse.add(dateContainer);
            }

            if (timeCollapse == null) {
                timeCollapse = new Collapse(LIElement.TAG);
                timeCollapse.setToggle(false);

                initTimeContainer();
                timeCollapse.add(timeContainer);
            }

            AnchorListItem switchItem = new AnchorListItem();
            switchItem.setStyleName(Styles.DATETIMEPICKER_SWITCH);
            switchItem.setGlyphicon(GlyphiconType.TIME);
            switchItem.getAnchor().setStyleName(Styles.BTN);
            switchItem.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    days.setDate(CalendarUtil.copyDate(value));
                    time.setDate(CalendarUtil.copyDate(value));
                    dateCollapse.toggle();
                    timeCollapse.toggle();
                }
            });

            list.add(dateCollapse);
            list.add(switchItem);
            list.add(timeCollapse);

            container.add(list);

            days.setDate(CalendarUtil.copyDate(value));
        } else if (dateEnabled) {
            initDateContainer();

            container.add(dateContainer);

            days.setDate(CalendarUtil.copyDate(value));
        } else {
            initTimeContainer();

            container.add(timeContainer);

            time.setDate(CalendarUtil.copyDate(value));
        }

        Event.setEventListener(getElement(), new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt() && event.getEventTarget().equals(getElement())) {
                    hide();
                }
            }
        });

        setVisible(true);

        super.onShow();
    }

    private void initTimeContainer() {
        if (timeContainer == null) {
            timeContainer = new Div();
            timeContainer.setStyleName(Styles.TIMEPICKER);
        }

        if (time == null) {
            time = new Time();
            time.setDate(CalendarUtil.copyDate(value));
            time.setVisible(true);
        }

        timeContainer.add(time);
    }

    private void initDateContainer() {
        if (dateContainer == null) {
            dateContainer = new Div();
            dateContainer.setStyleName(Styles.DATEPICKER);
        }

        if (days == null) {
            days = new Days();
            days.setVisible(true);
        }

        dateContainer.add(days);
    }

    public void setPosition(int left, int top) {
        container.getElement().getStyle().setLeft(left, Style.Unit.PX);
        container.getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    @Override
    public void setVisible(boolean visible) {
        Style style = container.getElement().getStyle();

        if (visible) {
            style.setDisplay(Style.Display.BLOCK);
            style.setZIndex(9999);
            style.setPosition(Style.Position.ABSOLUTE);
            style.setProperty("right", "auto");
        } else {
            super.setVisible(false);

            style.clearDisplay();
            style.clearZIndex();
            style.clearPosition();
            style.clearTop();
            style.clearLeft();
        }
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public void setValue(Date value) {
        if (value != null) {
            this.value = value;
        }
    }

    @Override
    public void setValue(Date value, boolean fireEvents) {
        setValue(value);

        if (fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public int getOffsetHeight() {
        return container.getOffsetHeight();
    }

    @Override
    public int getOffsetWidth() {
        return container.getOffsetWidth();
    }

    private void checkForRedraw() {
        if (isAttached() && isVisible()) {
            show();
        }
    }

    /**
     * Remove all children of the node.
     *
     * @param element base element
     * @return node
     */
    public final native Node removeAllChildren(Element element) /*-{
        while (element.lastChild) {
            element.removeChild(element.lastChild);
        }
    }-*/;

    private abstract class Picker extends Div {

        Date date;
        TableElement table;

        public Picker() {
            table = DOM.createTable().cast();
            table.setClassName(Styles.TABLE_CONDENSED);

            setVisible(false);

            sinkEvents(Event.ONCLICK);
        }

        public abstract void setDate(Date date);

        @Override
        public void setVisible(boolean visible) {
            if (visible) {
                getElement().getStyle().setDisplay(Style.Display.BLOCK);
            } else {
                super.setVisible(false);
            }
        }

    }

    private abstract class DatePicker extends Picker {

        @Override
        public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);

            switch (DOM.eventGetType(event)) {
                case Event.ONCLICK:
                    Element target = Element.as(event.getEventTarget());
                    if (target.getClassName().equals(Styles.PREV)) {
                        onPrevClicked();
                    } else if (target.getClassName().equals(Styles.SWITCH)) {
                        onSwitchClicked();
                    } else if (target.getClassName().equals(Styles.NEXT)) {
                        onNextClicked();
                    } else if (target.getTagName().toLowerCase().equals(TableCellElement.TAG_TD) ||
                            target.getTagName().toLowerCase().equals(SpanElement.TAG)) {
                        onItemClicked(target);
                    }
                    break;
            }
        }

        abstract protected void onPrevClicked();

        abstract protected void onSwitchClicked();

        abstract protected void onNextClicked();

        abstract protected void onItemClicked(Element target);

    }

    private class Days extends DatePicker {

        public Days() {
            setStyleName(Styles.DATEPICKER_DAYS);
        }

        public void setDate(Date date) {
            this.date = date;

            removeAllChildren(getElement());

            int[] daysOfWeek = CalendarUtil.getStartingDayOfWeek() == 0 ?
                    new int[]{0, 1, 2, 3, 4, 5, 6} : new int[]{1, 2, 3, 4, 5, 6, 0};

            String html = TABLE_START;

            // generate heading
            html += "<thead><tr>";
            html += "<th class=\"prev\">‹</th>";
            html += "<th colspan=\"5\" class=\"switch\">" + calendarModel.getMonthAndYearFormatter().format(date) + "</th>";
            html += "<th class=\"next\">›</th></tr><tr>";

            for (int day : daysOfWeek) {
                html += "<th class=\"dow\">" + calendarModel.formatDayOfWeek(day) + "</th>";
            }

            html += "</tr></thead>";

            // generate content
            html += TBODY_START;
            Date startDate = toStartDate(CalendarUtil.copyDate(date));

            for (int row = 1; row <= CalendarModel.WEEKS_IN_MONTH; row++) {
                html += "<tr>";

                for (int column = 0; column < CalendarModel.DAYS_IN_WEEK; column++) {
                    String postfix = date.getMonth() > startDate.getMonth() ? " old" :
                            (date.getMonth() < startDate.getMonth() ? " new" : "");

                    if (CalendarUtil.isSameDate(startDate, new Date())) {
                        postfix += " today";
                    }

                    if (CalendarUtil.isSameDate(startDate, value)) {
                        postfix += " active";
                    }

                    html += "<td class=\"day" + postfix + "\">" + calendarModel.formatDayOfMonth(startDate) + "</td>";

                    CalendarUtil.addDaysToDate(startDate, 1);
                }

                html += "</tr>";
            }

            html += TBODY_END + TABLE_END;

            getElement().setInnerHTML(html);
        }

        @Override
        protected void onPrevClicked() {
            CalendarUtil.setToFirstDayOfMonth(date);
            CalendarUtil.addMonthsToDate(date, -1);
            setDate(date);
        }

        @Override
        protected void onSwitchClicked() {
            if (months == null) {
                months = new Months();
            }
            dateContainer.add(months);

            months.setDate(date);
            months.setVisible(true);

            setVisible(false);
        }

        @Override
        protected void onNextClicked() {
            CalendarUtil.setToFirstDayOfMonth(date);
            CalendarUtil.addMonthsToDate(date, 1);
            setDate(date);
        }

        @Override
        protected void onItemClicked(Element target) {
            Element tbody = target.getParentElement().getParentElement();

            for (int row = 0; row < tbody.getChildCount(); row++) {
                Element tr = tbody.getChild(row).cast();

                for (int column = 0; column < tr.getChildCount(); column++) {
                    if (target.equals(tr.getChild(column))) {
                        Date startDate = toStartDate(CalendarUtil.copyDate(date));
                        int plus = ((row * CalendarModel.DAYS_IN_WEEK)) + column;

                        CalendarUtil.addDaysToDate(startDate, plus);

                        setValue(startDate, true);

                        // refresh view
                        setDate(startDate);

                        if (autoClose) {
                            hide();
                        }
                    }
                }
            }
        }

        private Date toStartDate(Date date) {
            CalendarUtil.setToFirstDayOfMonth(date);
            CalendarUtil.addDaysToDate(date, -(date.getDay() - CalendarUtil.getStartingDayOfWeek()));
            return date;
        }

    }

    private class Months extends DatePicker {

        public Months() {
            setStyleName(Styles.DATEPICKER_MONTHS);
        }

        @Override
        public void setDate(Date date) {
            this.date = date;

            removeAllChildren(getElement());

            String html = TABLE_START;

            // generate heading
            html += "<thead>";
            html += "<tr><th class=\"prev\">‹</th>";
            html += "<th colspan=\"5\" class=\"switch\">" + calendarModel.getYearFormatter().format(date) + "</th>";
            html += "<th class=\"next\">›</th></tr>";
            html += "</thead>";

            // generate content
            html += TBODY_START;
            html += "<tr><td colspan=\"7\">";
            for (int month = 0; month < CalendarModel.MONTHS_IN_YEAR; month++) {
                String postfix = month == value.getMonth() ? " active" : "";
                html += "<span class=\"month" + postfix + "\">" + calendarModel.formatMonth(month) + "</span>";
            }
            html += "</td><tr>";

            html += TBODY_END + TABLE_END;

            getElement().setInnerHTML(html);
        }

        @Override
        protected void onPrevClicked() {
            date.setYear(date.getYear() - 1);
            setDate(date);
        }

        @Override
        protected void onSwitchClicked() {
            if (years == null) {
                years = new Years();
            }
            dateContainer.add(years);

            years.setDate(date);
            years.setVisible(true);

            setVisible(false);
        }

        @Override
        protected void onNextClicked() {
            date.setYear(date.getYear() + 1);
            setDate(date);
        }

        @Override
        protected void onItemClicked(Element target) {
            for (int i = 0; i < target.getParentElement().getChildCount(); i++) {
                if (target.getParentElement().getChild(i).equals(target)) {
                    CalendarUtil.setToFirstDayOfMonth(date);
                    date.setMonth(i);

                    days.setDate(date);
                    days.setVisible(true);

                    setVisible(false);
                }
            }
        }

    }

    private class Years extends DatePicker {

        public Years() {
            setStyleName(Styles.DATEPICKER_DAYS);
        }

        @Override
        public void setDate(Date date) {
            this.date = date;

            removeAllChildren(getElement());

            String html = TABLE_START;

            // round year
            date.setYear(BigDecimal.valueOf(date.getYear() / 10).intValue() * 10);

            Date endDate = CalendarUtil.copyDate(date);
            endDate.setYear(date.getYear() + 9);

            String startYear = calendarModel.getYearFormatter().format(date);
            String endYear = calendarModel.getYearFormatter().format(endDate);

            // generate heading
            html += "<thead>";
            html += "<tr><th class=\"prev\">‹</th>";
            html += "<th colspan=\"5\" class=\"switch\">" + startYear + "-" + endYear + "</th>";
            html += "<th class=\"next\">›</th></tr>";
            html += "</thead>";

            // generate content
            Date startDate = CalendarUtil.copyDate(date);
            startDate.setYear(date.getYear() - 1);

            html += "<tr><td colspan=\"7\">";
            for (int i = 0; i < 12; i++) {
                String postfix = startDate.getYear() < date.getYear() ||
                        startDate.getYear() > endDate.getYear() ? " old" : "";
                postfix += startDate.getYear() == value.getYear() ? " active" : "";

                html += "<span class=\"year" + postfix + "\">" + calendarModel.getYearFormatter().format(startDate) +
                        "</span>";

                startDate.setYear(startDate.getYear() + 1);
            }
            html += "</td><tr>" + TABLE_END;

            getElement().setInnerHTML(html);
        }

        @Override
        protected void onPrevClicked() {
            date.setYear(date.getYear() - 10);
            setDate(date);
        }

        @Override
        protected void onSwitchClicked() {
            // nothing
        }

        @Override
        protected void onNextClicked() {
            date.setYear(date.getYear() + 10);
            setDate(date);
        }

        @Override
        protected void onItemClicked(Element target) {
            for (int i = 0; i < target.getParentElement().getChildCount(); i++) {
                if (target.getParentElement().getChild(i).equals(target)) {
                    CalendarUtil.setToFirstDayOfMonth(date);
                    date.setYear(date.getYear() + i - 1);

                    months.setDate(date);
                    months.setVisible(true);

                    setVisible(false);
                }
            }
        }

    }

    private class Time extends Picker {

        String incrementHoursClass = "incrementHours";
        String incrementMinutesClass = "incrementMinutes";
        String decrementHoursClass = "decrementHours";
        String decrementMinutesClass = "decrementMinutes";
        String togglePeriodClass = "togglePeriod";

        public Time() {
            setStyleName(Styles.TIMEPICKER_PICKER);
        }

        @Override
        public void setDate(Date date) {
            this.date = date;

            removeAllChildren(getElement());

            String html = TABLE_START + TBODY_START;

            // header
            html += "<tr>" +
                    "<td>" +
                    "<a class=\"btn " + incrementHoursClass + "\"><span class=\"glyphicon glyphicon-chevron-up\"></span></a>" +
                    "</td>" +
                    separator("") +
                    "<td>" +
                    "<a class=\"btn " + incrementMinutesClass + "\"><span class=\"glyphicon glyphicon-chevron-up\"></span></a>" +
                    "</td>" +
                    (hourFormat == null || hourFormat == HourFormat._12 ? separator("") : "") +
                    "</tr>";

            // content
            html += "<tr>" +
                    "<td><span class=\"" + Styles.TIMEPICKER_HOUR + " \">" + calendarModel.getHourFormatter().format(date) + "</span></td>" +
                    separator(":") +
                    "<td><span class=\"" + Styles.TIMEPICKER_MINUTE + "\">" + calendarModel.getMinuteFormatter().format(date) + "</span></td>" +
                    (hourFormat == null || hourFormat == HourFormat._12 ?
                            separator("") +
                                    "<td><button type=\"button\" class=\"btn btn-primary " + togglePeriodClass + "\">" +
                                    (date.getHours() > 11 ? "PM" : "AM") +
                                    "</button></td>" : "") +
                    "</tr>";

            // footer
            html += "<tr>" +
                    "<td>" +
                    "<a class=\"btn " + decrementHoursClass + "\"><span class=\"glyphicon glyphicon-chevron-down\"></span></a>" +
                    "</td>" +
                    separator("") +
                    "<td>" +
                    "<a class=\"btn " + decrementMinutesClass + "\"><span class=\"glyphicon glyphicon-chevron-down\"></span></a>" +
                    "</td>" +
                    (hourFormat == null || hourFormat == HourFormat._12 ? separator("") : "") +
                    "</tr>";

            html += TBODY_END + TABLE_END;

            getElement().setInnerHTML(html);
        }

        @Override
        public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);

            switch (DOM.eventGetType(event)) {
                case Event.ONCLICK:
                    Element target = Element.as(event.getEventTarget());
                    if (target.getClassName().contains(incrementHoursClass) ||
                            target.getParentElement().getClassName().contains(incrementHoursClass)) {
                        onIncrementHoursClicked();
                    } else if (target.getClassName().contains(incrementMinutesClass) ||
                            target.getParentElement().getClassName().contains(incrementMinutesClass)) {
                        onIncrementMinutesClicked();
                    } else if (target.getClassName().contains(decrementHoursClass) ||
                            target.getParentElement().getClassName().contains(decrementHoursClass)) {
                        onDecrementHoursClicked();
                    } else if (target.getClassName().contains(decrementMinutesClass) ||
                            target.getParentElement().getClassName().contains(decrementMinutesClass)) {
                        onDecrementMinutesClicked();
                    } else if (target.getClassName().contains(decrementMinutesClass) ||
                            target.getParentElement().getClassName().contains(decrementMinutesClass)) {
                        onDecrementMinutesClicked();
                    } else if (target.getClassName().contains(decrementMinutesClass) ||
                            target.getParentElement().getClassName().contains(decrementMinutesClass)) {
                        onDecrementMinutesClicked();
                    } else if (target.getClassName().contains(togglePeriodClass) ||
                            target.getParentElement().getClassName().contains(togglePeriodClass)) {
                        onTogglePeriodClicked();
                    } else if (target.getClassName().contains(Styles.TIMEPICKER_HOUR)) {
                        onHourClicked();
                    } else if (target.getClassName().contains(Styles.TIMEPICKER_MINUTE)) {
                        onMinuteClicked();
                    }

                    break;
            }
        }

        private void onDecrementMinutesClicked() {
            date.setMinutes(date.getMinutes() - 1);
            updateValue();
        }

        private void onDecrementHoursClicked() {
            date.setHours(date.getHours() - 1);
            updateValue();
        }

        private void onIncrementMinutesClicked() {
            date.setMinutes(date.getMinutes() + 1);
            updateValue();
        }

        private void onIncrementHoursClicked() {
            date.setHours(date.getHours() + 1);
            updateValue();
        }

        private void onTogglePeriodClicked() {
            if (date.getHours() > 11) {
                date.setHours(date.getHours() - 12);
            } else {
                date.setHours(date.getHours() + 12);
            }
            updateValue();
        }

        private void onHourClicked() {
            if (hours == null) {
                hours = new Hours();
            }
            timeContainer.add(hours);

            hours.setDate(date);
            hours.setVisible(true);

            setVisible(false);
        }

        private void onMinuteClicked() {
            if (minutes == null) {
                minutes = new Minutes();
            }
            timeContainer.add(minutes);

            minutes.setDate(date);
            minutes.setVisible(true);

            setVisible(false);
        }

        private void updateValue() {
            setDate(date);
            setValue(date, true);
        }

        private String separator(String text) {
            return "<td class=\"separator\">" + text + "</td>";
        }

    }

    private class Hours extends Picker {

        public Hours() {
            setStyleName(Styles.TIMEPICKER_HOURS);
        }

        @Override
        public void setDate(Date date) {
            this.date = date;

            removeAllChildren(getElement());

            String html = TABLE_START;

            // generate content
            Date startDate = CalendarUtil.copyDate(date);
            startDate.setHours(hourFormat == HourFormat._23 ? 0 : 1);

            html += TBODY_START;

            for (int row = 0; row < (hourFormat == null || hourFormat == HourFormat._12 ? 3 : 6); row++) {
                html += "<tr>";

                for (int column = 0; column < 4; column++) {
                    html += "<td class=\"hour\">" + calendarModel.getHourFormatter().format(startDate) + "</td>";

                    startDate.setHours(startDate.getHours() + 1);
                }

                html += "</tr>";
            }

            html += TBODY_END + TABLE_END;

            getElement().setInnerHTML(html);
        }

        @Override
        public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);

            switch (DOM.eventGetType(event)) {
                case Event.ONCLICK:
                    Element target = Element.as(event.getEventTarget());
                    if (target.getTagName().toLowerCase().equals(TableCellElement.TAG_TD)) {
                        onItemClicked(target);
                    }
                    break;
            }
        }

        private void onItemClicked(Element target) {
            Element tbody = target.getParentElement().getParentElement();

            for (int row = 0; row < tbody.getChildCount(); row++) {
                Element tr = tbody.getChild(row).cast();

                for (int column = 0; column < tr.getChildCount(); column++) {
                    if (target.equals(tr.getChild(column))) {
                        int hours = ((row * 4)) + column;

                        if (hourFormat == null || hourFormat == HourFormat._12 || hourFormat == HourFormat._24) {
                            hours++;
                        }

                        date.setHours(hours);

                        setValue(date, true);

                        time.setDate(date);
                        time.setVisible(true);

                        setVisible(false);
                    }
                }
            }
        }

    }

    private class Minutes extends Picker {

        public Minutes() {
            setStyleName(Styles.TIMEPICKER_MINUTES);
        }

        @Override
        public void setDate(Date date) {
            this.date = date;

            removeAllChildren(getElement());

            String html = TABLE_START;

            // generate content
            Date startDate = CalendarUtil.copyDate(date);
            startDate.setMinutes(0);

            html += TBODY_START;

            for (int row = 0; row < 3; row++) {
                html += "<tr>";

                for (int column = 0; column < 4; column++) {
                    html += "<td class=\"minute\">" + calendarModel.getMinuteFormatter().format(startDate) + "</td>";

                    startDate.setMinutes(startDate.getMinutes() + 5);
                }

                html += "</tr>";
            }

            html += TBODY_END + TABLE_END;

            getElement().setInnerHTML(html);
        }

        @Override
        public void onBrowserEvent(Event event) {
            super.onBrowserEvent(event);

            switch (DOM.eventGetType(event)) {
                case Event.ONCLICK:
                    Element target = Element.as(event.getEventTarget());
                    if (target.getTagName().toLowerCase().equals(TableCellElement.TAG_TD)) {
                        onItemClicked(target);
                    }
                    break;
            }
        }

        private void onItemClicked(Element target) {
            Element tbody = target.getParentElement().getParentElement();

            for (int row = 0; row < tbody.getChildCount(); row++) {
                Element tr = tbody.getChild(row).cast();

                for (int column = 0; column < tr.getChildCount(); column++) {
                    if (target.equals(tr.getChild(column))) {
                        int minutes = (((row * 4)) + column) * 5;

                        date.setMinutes(minutes);

                        setValue(date, true);

                        time.setDate(date);
                        time.setVisible(true);

                        setVisible(false);
                    }
                }
            }
        }

    }

}
