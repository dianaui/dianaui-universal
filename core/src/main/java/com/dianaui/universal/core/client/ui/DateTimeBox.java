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
import com.dianaui.universal.core.client.ui.constants.PopupPosition;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;

import java.util.Date;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DateTimeBox extends ValueBoxBase<Date> {

    private static DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM);
    private DateTimePicker picker = new DateTimePicker();
    private PopupPosition position = PopupPosition.BOTTOM_LEFT;

    public DateTimeBox() {
        this(format);
    }

    public DateTimeBox(DateTimeFormat format) {
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
                if (event.getNativeKeyCode() == KeyCodes.KEY_TAB) {
                    picker.hide();
                }
            }
        });
    }

    public DateTimeBox(String format) {
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
