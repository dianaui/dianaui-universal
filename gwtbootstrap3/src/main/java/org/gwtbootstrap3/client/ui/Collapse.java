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

import com.google.gwt.event.shared.HandlerRegistration;
import org.gwtbootstrap3.client.shared.event.*;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * @author Grant Slender
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Collapse extends Div {
    private static final String TOGGLE = "toggle";
    private static final String SHOW = "show";
    private static final String HIDE = "hdie";

    private boolean toggle = true;

    public Collapse() {
        // Set the default styles
        setStyleName(Styles.COLLAPSE);
    }

    /**
     * Sets the default state to show or hide. Show is true.
     */
    public void setToggle(final boolean toggle) {
        this.toggle = toggle;
    }

    /**
     * Causes the collapse to show or hide
     */
    public void toggle() {
        // TODO
        // fireMethod(getElement(), TOGGLE);
    }

    /**
     * Causes the collapse to show
     */
    public void show() {
        // TODO
        // fireMethod(getElement(), SHOW);
    }

    /**
     * Causes the collapse to hide
     */
    public void hide() {
        // TODO
        // fireMethod(getElement(), HIDE);
    }

    public HandlerRegistration addShowHandler(final ShowHandler showHandler) {
        return addHandler(showHandler, ShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(final ShownHandler shownHandler) {
        return addHandler(shownHandler, ShownEvent.getType());
    }

    public HandlerRegistration addHideHandler(final HideHandler hideHandler) {
        return addHandler(hideHandler, HideEvent.getType());
    }

    public HandlerRegistration addHiddenHandler(final HiddenHandler hiddenHandler) {
        return addHandler(hiddenHandler, HiddenEvent.getType());
    }

}
