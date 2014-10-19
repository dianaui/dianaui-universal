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

import com.dianaui.universal.core.client.event.ShownEvent;
import com.dianaui.universal.core.client.event.ShownHandler;
import com.dianaui.universal.core.client.text.DateTimeFormatParser;
import com.dianaui.universal.core.client.ui.base.ValueBoxBase;
import com.dianaui.universal.core.client.ui.constants.HourFormat;
import com.dianaui.universal.core.client.ui.constants.PopupPosition;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;

import java.util.Date;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class TimeBox extends ValueBoxBase<Date> {

    private final static DateTimeFormat format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.TIME_MEDIUM);
    private final TimePicker picker = new TimePicker();
    private PopupPosition position = PopupPosition.BOTTOM_LEFT;

    public TimeBox() {
        this(format);
    }

    public TimeBox(String format) {
        this(DateTimeFormat.getFormat(format));
    }

    public TimeBox(DateTimeFormat format) {
        super(Document.get().createTextInputElement(),
                new DateTimeFormatRenderer(format), new DateTimeFormatParser(format));
        addStyleName(Styles.FORM_CONTROL);

        picker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                setValue(event.getValue(), true);
            }
        });

        picker.addShownHandler(new ShownHandler() {
            @Override
            public void onShown(ShownEvent event) {
                int left = getAbsoluteLeft();
                int top = getAbsoluteTop();
                boolean showOnTop = false;

                if (position == PopupPosition.BOTTOM_CENTER) {
                    left += (getOffsetWidth() - picker.getOffsetWidth()) / 2;
                } else if (position == PopupPosition.BOTTOM_RIGHT) {
                    left += (getOffsetWidth() - picker.getOffsetWidth());
                } else if (position == PopupPosition.TOP_LEFT) {
                    showOnTop = true;
                } else if (position == PopupPosition.TOP_CENTER) {
                    showOnTop = true;
                    left += (getOffsetWidth() - picker.getOffsetWidth()) / 2;
                } else if (position == PopupPosition.TOP_RIGHT) {
                    showOnTop = true;
                    left += (getOffsetWidth() - picker.getOffsetWidth());
                }

                if (showOnTop) {
                    picker.getWidget(0).addStyleName(Styles.TOP);
                    picker.getWidget(0).removeStyleName(Styles.BOTTOM);
                    top -= picker.getOffsetHeight();
                } else {
                    picker.getWidget(0).addStyleName(Styles.BOTTOM);
                    picker.getWidget(0).removeStyleName(Styles.TOP);
                    top += getOffsetHeight();
                }

                picker.setPosition(left, top);
            }
        });

        addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                picker.show();
            }
        });

        addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_TAB)
                    picker.hide();
            }
        });
    }

    public void setDefaultTime(final String time) {
        if (getValue() == null) {
            picker.setValue(format.parse(time));
        }
    }

    public PopupPosition getPopupPosition() {
        return position;
    }

    public void setPopupPosition(final PopupPosition position) {
        this.position = position;
    }

    public boolean isSecondsEnabled() {
        return picker.isSecondsEnabled();
    }

    public void setSecondsEnabled(final boolean enabled) {
        picker.setSecondsEnabled(enabled);
    }

    public Integer getMinuteStep() {
        return picker.getMinuteStep();
    }

    public void setMinuteStep(final Integer minuteStep) {
        picker.setMinuteStep(minuteStep);
    }

    public Integer getSecondStep() {
        return picker.getSecondStep();
    }

    public void setSecondStep(final Integer secondStep) {
        picker.setSecondStep(secondStep);
    }

    public HourFormat getHourFormat() {
        return picker.getHourFormat();
    }

    public void setHourFormat(final HourFormat format) {
        picker.setHourFormat(format);
    }

    @Override
    public void setValue(final Date value, final boolean fireEvents) {
        if (picker.getValue() != value)
            picker.setValue(value, false);

        super.setValue(value, fireEvents);
    }

}
