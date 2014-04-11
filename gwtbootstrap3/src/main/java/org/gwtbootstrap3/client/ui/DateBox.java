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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import org.gwtbootstrap3.client.shared.event.ShownEvent;
import org.gwtbootstrap3.client.shared.event.ShownHandler;
import org.gwtbootstrap3.client.text.DateTimeFormatParser;
import org.gwtbootstrap3.client.ui.base.ValueBoxBase;
import org.gwtbootstrap3.client.ui.constants.PopupPosition;
import org.gwtbootstrap3.client.ui.constants.Styles;

import java.util.Date;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DateBox extends ValueBoxBase<Date> {

    private static DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM);
    private DateTimePicker picker = new DateTimePicker();
    private PopupPosition position = PopupPosition.BOTTOM_CENTER;

    public DateBox() {
        this(format);
    }

    public DateBox(DateTimeFormat format) {
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

                if (position == PopupPosition.BOTTOM_LEFT) {
                    top += getOffsetHeight();
                } else if (position == PopupPosition.BOTTOM_CENTER) {
                    top += getOffsetHeight();
                    left += (getOffsetWidth() - picker.getOffsetWidth()) / 2;
                } else if (position == PopupPosition.BOTTOM_RIGHT) {
                    top += getOffsetHeight();
                    left += (getOffsetWidth() - picker.getOffsetWidth());
                } else if (position == PopupPosition.TOP_LEFT) {
                    top -= picker.getOffsetHeight();
                } else if (position == PopupPosition.TOP_CENTER) {
                    top -= picker.getOffsetHeight();
                    left += (getOffsetWidth() - picker.getOffsetWidth()) / 2;
                } else if (position == PopupPosition.TOP_RIGHT) {
                    top -= picker.getOffsetHeight();
                    left += (getOffsetWidth() - picker.getOffsetWidth());
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

        addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                picker.hide();
            }
        });
    }

    public DateBox(String format) {
        this(DateTimeFormat.getFormat(format));
    }

    public boolean isDateEnabled() {
        return picker.isDateEnabled();
    }

    public void setDateEnabled(boolean enabled) {
        picker.setDateEnabled(enabled);
    }

    public boolean isTimeEnabled() {
        return picker.isTimeEnabled();
    }

    public void setTimeEnabled(boolean enabled) {
        picker.setTimeEnabled(enabled);
    }

    public boolean isAutoClose() {
        return picker.isAutoClose();
    }

    public void setAutoClose(boolean enabled) {
        picker.setAutoClose(enabled);
    }

    public PopupPosition getPopupPosition() {
        return position;
    }

    public void setPopupPosition(PopupPosition position) {
        this.position = position;
    }

}
