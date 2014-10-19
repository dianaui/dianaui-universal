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
package com.dianaui.universal.core.client.ui;

import com.dianaui.universal.core.client.ui.base.modal.ModalWithBackdrop;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasValue;

import java.util.Date;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class TimePicker extends ModalWithBackdrop implements LeafValueEditor<Date>, HasValue<Date> {

    private HTMLPanel container;
    private HourFormat hourFormat = HourFormat._23;
    private Date value;
    private Integer minuteStep = 15;
    private Integer secondStep = 15;
    private boolean secondsEnabled = false;

    private final Anchor incrementHour = new Anchor(IconType.CHEVRON_UP);
    private final Anchor incrementMinute = new Anchor(IconType.CHEVRON_UP);
    private final Anchor incrementSecond = new Anchor(IconType.CHEVRON_UP);
    private final Anchor decrementHour = new Anchor(IconType.CHEVRON_DOWN);
    private final Anchor decrementMinute = new Anchor(IconType.CHEVRON_DOWN);
    private final Anchor decrementSecond = new Anchor(IconType.CHEVRON_DOWN);
    private final IntegerBox hourBox = new IntegerBox();
    private final IntegerBox minuteBox = new IntegerBox();
    private final IntegerBox secondBox = new IntegerBox();
    private final Label togglePeriodLabel = new Label(LabelType.PRIMARY);

    public TimePicker() {
        setFade(false);
        setBackdrop(ModalBackdrop.FALSE);

        ClickHandler clickHandler = new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (event.getSource() != null) {
                    if (event.getSource().equals(incrementHour)) {
                        onIncrementHoursClicked();
                    } else if (event.getSource().equals(incrementMinute)) {
                        onIncrementMinutesClicked();
                    } else if (event.getSource().equals(incrementSecond)) {
                        onIncrementSecondsClicked();
                    } else if (event.getSource().equals(decrementHour)) {
                        onDecrementHoursClicked();
                    } else if (event.getSource().equals(decrementMinute)) {
                        onDecrementMinutesClicked();
                    } else if (event.getSource().equals(decrementSecond)) {
                        onDecrementSecondsClicked();
                    }
                }
            }
        };
        incrementHour.addClickHandler(clickHandler);
        incrementMinute.addClickHandler(clickHandler);
        incrementSecond.addClickHandler(clickHandler);
        decrementHour.addClickHandler(clickHandler);
        decrementMinute.addClickHandler(clickHandler);
        decrementSecond.addClickHandler(clickHandler);

        ValueChangeHandler<Integer> valueChangeHandler = new ValueChangeHandler<Integer>() {
            @Override
            public void onValueChange(ValueChangeEvent<Integer> event) {
                if (event.getSource() != null) {
                    if (event.getSource().equals(hourBox)) {
                        onHoursChanged(event.getValue());
                    } else if (event.getSource().equals(minuteBox)) {
                        onMinutesChanged(event.getValue());
                    } else if (event.getSource().equals(secondBox)) {
                        onSecondsChanged(event.getValue());
                    }
                }
            }
        };
        hourBox.addValueChangeHandler(valueChangeHandler);
        minuteBox.addValueChangeHandler(valueChangeHandler);
        secondBox.addValueChangeHandler(valueChangeHandler);
        hourBox.setMaxLength(2);
        minuteBox.setMaxLength(2);
        secondBox.setMaxLength(2);
        hourBox.setStyleName(Styles.TIMEPICKER_WIDGET_HOUR);
        minuteBox.setStyleName(Styles.TIMEPICKER_WIDGET_MINUTE);
        secondBox.setStyleName(Styles.TIMEPICKER_WIDGET_SECOND);

        togglePeriodLabel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                onTogglePeriodClicked();
            }
        });
    }

    public void setPosition(final int left, final int top) {
        container.getElement().getStyle().setLeft(left, Style.Unit.PX);
        container.getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public HourFormat getHourFormat() {
        return hourFormat;
    }

    public void setHourFormat(final HourFormat format) {
        this.hourFormat = format;

        checkForRedraw();
    }

    public boolean isSecondsEnabled() {
        return secondsEnabled;
    }

    public void setSecondsEnabled(final boolean enabled) {
        secondsEnabled = enabled;

        checkForRedraw();
    }

    public Integer getMinuteStep() {
        return minuteStep;
    }

    public void setMinuteStep(final Integer minuteStep) {
        this.minuteStep = minuteStep;
    }

    public Integer getSecondStep() {
        return secondStep;
    }

    public void setSecondStep(final Integer secondStep) {
        this.secondStep = secondStep;
    }

    @Override
    public int getOffsetHeight() {
        return container.getOffsetHeight();
    }

    @Override
    public int getOffsetWidth() {
        return container.getOffsetWidth();
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void setValue(final Date value) {
        if (value != null) {
            this.value = value;
            int hours = value.getHours();
            if (hourFormat == HourFormat._24 && hours == 0)
                hours = 24;
            if (hourFormat == HourFormat._12) {
                togglePeriodLabel.setText("AM");
                if (hours == 0) {
                    hours = 12;
                    togglePeriodLabel.setText("PM");
                } else if (hours > 12) {
                    hours = hours - 12;
                    togglePeriodLabel.setText("PM");
                }
            }
            hourBox.setValue(hours);
            minuteBox.setValue(value.getMinutes());
            secondBox.setValue(value.getSeconds());
            return;
        }
        hourBox.setValue(null);
        minuteBox.setValue(null);
        secondBox.setValue(null);
    }

    @Override
    public void setValue(final Date value, final boolean fireEvents) {
        setValue(value);

        if (fireEvents)
            ValueChangeEvent.fire(this, value);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Date> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    protected void onShow() {
        initContainer();

        // redraw inputs
        setValue(value != null ? value : new Date());

        Event.setEventListener(getElement(), new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (Event.ONCLICK == event.getTypeInt() && event.getEventTarget().equals(getElement()))
                    hide();
            }
        });

        container.addStyleName(Styles.OPEN);

        super.onShow();
    }

    @Override
    protected void onHide() {
        super.onHide();

        container.removeStyleName(Styles.OPEN);

        clear();
        container = null;
    }

    @SuppressWarnings("deprecation")
    private void onIncrementHoursClicked() {
        checkValue();
        value.setHours(value.getHours() + 1);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onIncrementMinutesClicked() {
        checkValue();
        value.setMinutes(value.getMinutes() + minuteStep);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onIncrementSecondsClicked() {
        checkValue();
        value.setSeconds(value.getSeconds() + secondStep);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onDecrementHoursClicked() {
        checkValue();
        value.setHours(value.getHours() - 1);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onDecrementMinutesClicked() {
        checkValue();
        value.setMinutes(value.getMinutes() - minuteStep);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onDecrementSecondsClicked() {
        checkValue();
        value.setSeconds(value.getSeconds() - secondStep);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onTogglePeriodClicked() {
        checkValue();
        if (value.getHours() >= 12) {
            value.setHours(value.getHours() - 12);
        } else {
            value.setHours(value.getHours() + 12);
        }
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onHoursChanged(final Integer newValue) {
        checkValue();
        value.setHours(newValue);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onMinutesChanged(final Integer newValue) {
        checkValue();
        value.setSeconds(newValue);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void onSecondsChanged(final Integer newValue) {
        checkValue();
        value.setSeconds(newValue);
        setValue(value, true);
    }

    @SuppressWarnings("deprecation")
    private void checkValue() {
        if (value == null)
            value = new Date();
        if (hourBox.getValue() != null) {
            int hours = hourBox.getValue();
            if (hourFormat == HourFormat._24 && hours == 24)
                hours = 0;
            if (hourFormat == HourFormat._12 && togglePeriodLabel.getText().equals("PM")) {
                hours += 12;
            }
            value.setHours(hours);
        }
        if (minuteBox.getValue() != null)
            value.setMinutes(minuteBox.getValue());
        if (secondBox.getValue() != null)
            value.setSeconds(secondBox.getValue());
    }

    private void checkForRedraw() {
        if (isAttached() && isVisible())
            show();
    }

    private void initContainer() {
        final String incrementHourId = HTMLPanel.createUniqueId();
        final String incrementMinuteId = HTMLPanel.createUniqueId();
        final String incrementSecondId = HTMLPanel.createUniqueId();
        final String hourContainerId = HTMLPanel.createUniqueId();
        final String minuteContainerId = HTMLPanel.createUniqueId();
        final String secondContainerId = HTMLPanel.createUniqueId();
        final String decrementHourId = HTMLPanel.createUniqueId();
        final String decrementMinuteId = HTMLPanel.createUniqueId();
        final String decrementSecondId = HTMLPanel.createUniqueId();
        final String periodId = HTMLPanel.createUniqueId();

        container = new HTMLPanel(new SafeHtml() {
            private String getSeparator(final String separator) {
                return "<td class=\"" + Styles.SEPARATOR + "\">" + separator + "</td>";
            }

            @Override
            public String asString() {
                return "<table>" +
                        "<tr>" +
                        "<td id=\"" + incrementHourId + "\"></td>" +
                        getSeparator("&nbsp;") +
                        "<td id=\"" + incrementMinuteId + "\"></td>" +
                        (secondsEnabled ? getSeparator("&nbsp;") + "<td id=\"" + incrementSecondId + "\"></td>" : "") +
                        "</tr>" +
                        "<tr>" +
                        "<td id=\"" + hourContainerId + "\"></td>" +
                        getSeparator(":") +
                        "<td id=\"" + minuteContainerId + "\"></td>" +
                        (secondsEnabled ? getSeparator(":") + "<td id=\"" + secondContainerId + "\"></td>" : "") +
                        (hourFormat == HourFormat._12 ? "<td id=\"" + periodId + "\"></td>" : "") +
                        "</tr>" +
                        "<tr>" +
                        "<td id=\"" + decrementHourId + "\"></td>" +
                        getSeparator("&nbsp;") +
                        "<td id=\"" + decrementMinuteId + "\"></td>" +
                        (secondsEnabled ? getSeparator("&nbsp;") + "<td id=\"" + decrementSecondId + "\"></td>" : "") +
                        "</tr>" +
                        "</table>";
            }
        });
        container.setStyleName(Styles.TIMEPICKER_WIDGET);
        container.addStyleName(Styles.DROPDOWN_MENU);

        // attach widgets
        container.add(incrementHour, incrementHourId);
        container.add(incrementMinute, incrementMinuteId);
        if (secondsEnabled)
            container.add(incrementSecond, incrementSecondId);
        container.add(hourBox, hourContainerId);
        container.add(minuteBox, minuteContainerId);
        if (secondsEnabled)
            container.add(secondBox, secondContainerId);
        container.add(decrementHour, decrementHourId);
        container.add(decrementMinute, decrementMinuteId);
        if (secondsEnabled)
            container.add(decrementSecond, decrementSecondId);
        if (hourFormat == HourFormat._12)
            container.add(togglePeriodLabel, periodId);

        add(container);
    }

}
