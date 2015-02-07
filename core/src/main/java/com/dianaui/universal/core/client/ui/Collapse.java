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

import com.dianaui.universal.core.client.event.*;
import com.dianaui.universal.core.client.ui.base.CustomWidget;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Grant Slender
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see ShowEvent
 * @see ShownEvent
 * @see HideEvent
 * @see HiddenEvent
 */
public class Collapse extends CustomWidget {

    private static int DEFAULT_TRANSITION_MS = 350;

    private int transitionMs = DEFAULT_TRANSITION_MS;
    private boolean toggle = true;
    private boolean transitioning = false;

    public Collapse() {
        this(DivElement.TAG);
    }

    public Collapse(String tag) {
        super(tag);

        // Set the default styles
        setStyleName(Styles.COLLAPSE);
    }

    @Override
    public void add(final Widget child) {
        if (getWidgetCount() > 0) {
            throw new IllegalStateException("Collapse can only contain one child widget");
        }
        super.add(child);
    }

    /**
     * Sets the default state to show or hide. Show is true.
     *
     * @param toggle toggle
     */
    public void setToggle(final boolean toggle) {
        this.toggle = toggle;
    }

    /**
     * Causes the collapse to show or hide
     */
    public void toggle() {
        if (isViewing()) {
            hide();
        } else {
            show();
        }
    }

    /**
     * Causes the collapse to show
     */
    public void show() {
        if (isAttached()) {
            if (transitioning || isViewing()) {
                return;
            }

            transitioning = true;

            fireEvent(new ShowEvent());

            removeStyleName(Styles.COLLAPSE);
            addStyleName(Styles.COLLAPSING);

            if (getElement().getChildCount() > 0) {
                getElement().getStyle().setHeight(getElement().getFirstChildElement().getOffsetHeight(), Style.Unit.PX);
            }

            Timer timer = new Timer() {
                @Override
                public void run() {
                    onShow();

                    transitioning = false;

                    fireEvent(new ShownEvent());
                }
            };

            timer.schedule(transitionMs);
        } else {
            onShow();
        }
    }

    /**
     * Causes the collapse to hide
     */
    public void hide() {
        if (isAttached()) {
            if (transitioning || !isViewing()) {
                return;
            }

            transitioning = true;

            fireEvent(new HideEvent());

            addStyleName(Styles.COLLAPSING);
            removeStyleName(Styles.COLLAPSE);
            removeStyleName(Styles.IN);

            getElement().getStyle().setHeight(0, Style.Unit.PX);

            // force reflow
            if (getElement().getChildCount() > 0) {
                getElement().getFirstChildElement().getOffsetHeight();
            }

            Timer timer = new Timer() {
                @Override
                public void run() {
                    onHide();

                    transitioning = false;

                    fireEvent(new HiddenEvent());
                }
            };

            timer.schedule(transitionMs);
        } else {
            onHide();
        }
    }

    public boolean isViewing() {
        return this.getElement().hasClassName(Styles.IN);
    }

    public boolean isCollapsing() {
        return this.getElement().hasClassName(Styles.COLLAPSING);
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

    @Override
    protected void onAttach() {
        super.onAttach();

        if (toggle) {
            show();
        } else {
            onHide();
        }
    }

    private void onShow() {
        removeStyleName(Styles.COLLAPSING);
        addStyleName(Styles.COLLAPSE);
        addStyleName(Styles.IN);
        getElement().getStyle().setProperty("height", "auto");
    }

    private void onHide() {
        removeStyleName(Styles.COLLAPSING);
        addStyleName(Styles.COLLAPSE);

        // for not attached widgets
        removeStyleName(Styles.IN);
        getElement().getStyle().setHeight(0, Style.Unit.PX);
    }

}
