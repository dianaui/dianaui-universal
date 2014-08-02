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
package com.dianaui.universal.core.ui.base.modal;

import com.dianaui.universal.core.event.*;
import com.dianaui.universal.core.ui.constants.ModalBackdrop;
import com.dianaui.universal.core.ui.constants.Styles;
import com.dianaui.universal.core.ui.html.Div;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see com.dianaui.universal.core.event.ShowEvent
 * @see com.dianaui.universal.core.event.ShownEvent
 * @see com.dianaui.universal.core.event.HideEvent
 * @see com.dianaui.universal.core.event.HiddenEvent
 */
public abstract class ModalWithBackdrop extends Div {

    protected static int DEFAULT_TRANSITION_MS = 150;
    protected int transitionMs = DEFAULT_TRANSITION_MS;
    protected DivElement backdrop;
    protected ModalBackdrop backdropType = ModalBackdrop.TRUE;
    private boolean viewing = false;

    public ModalWithBackdrop() {
        setStyleName(Styles.MODAL);

        initBackdrop();

        setFade(false);
    }

    /**
     * Sets backdrop of modal.
     *
     * @param backdrop Backdrop of modal
     * @see com.dianaui.universal.core.ui.constants.ModalBackdrop
     */
    public void setBackdrop(final ModalBackdrop backdrop) {
        this.backdropType = backdrop;
    }

    /**
     * If set Modal will fade in/out.
     *
     * @param fade If {@code true} modal will fade in/out
     */
    public void setFade(final boolean fade) {
        if (fade) {
            addStyleName(Styles.FADE);
            backdrop.addClassName(Styles.FADE);
            transitionMs = DEFAULT_TRANSITION_MS;
        } else {
            removeStyleName(Styles.FADE);
            backdrop.removeClassName(Styles.FADE);
            transitionMs = 0;
        }
    }

    public boolean isViewing() {
        return viewing;
    }

    protected void setViewing(boolean viewing) {
        this.viewing = viewing;
    }

    public void toggle() {
        if (isViewing()) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        if (!isViewing()) {
            setViewing(true);

            fireEvent(new ShowEvent());

            if (!isAttached()) {
                RootPanel.get().add(this);

                Event.sinkEvents(getElement(), Event.ONCLICK);
                Event.setEventListener(getElement(), new EventListener() {
                    @Override
                    public void onBrowserEvent(Event event) {
                        if (Event.ONCLICK == event.getTypeInt() && event.getEventTarget().equals(getElement()) &&
                                backdropType.equals(ModalBackdrop.TRUE)) {
                            hide();
                        }
                    }
                });
            }

            if (!backdropType.equals(ModalBackdrop.FALSE)) {
                RootPanel.getBodyElement().appendChild(backdrop);

                // force reflow
                backdrop.getParentElement().getFirstChildElement().getOffsetWidth();

                backdrop.addClassName(Styles.IN);
            }

            Timer timer = new Timer() {
                @Override
                public void run() {
                    onShow();

                    if (!backdropType.equals(ModalBackdrop.FALSE)) {
                        backdrop.getParentElement().getFirstChildElement().getOffsetWidth();
                    }

                    addStyleName(Styles.IN);

                    ModalWithBackdrop.this.fireEvent(new ShownEvent());
                }
            };

            timer.schedule(transitionMs);
        }
    }

    public void hide() {
        if (isViewing()) {
            fireEvent(new HideEvent());

            removeStyleName(Styles.IN);

            // force reflow
            if (backdrop.getParentNode() != null) {
                backdrop.getParentElement().getFirstChildElement().getOffsetWidth();
            }

            Timer timer = new Timer() {
                @Override
                public void run() {
                    onHide();

                    if (backdrop.getParentNode() != null) {
                        backdrop.removeClassName(Styles.IN);
                    }

                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            deattach();

                            if (backdrop.getParentNode() != null) {
                                backdrop.removeFromParent();
                            }

                            setViewing(false);
                        }
                    };

                    timer.schedule(transitionMs);

                    ModalWithBackdrop.this.fireEvent(new HiddenEvent());
                }
            };

            timer.schedule(transitionMs);
        }
    }

    public HandlerRegistration addHideHandler(final HideHandler handler) {
        return addHandler(handler, HideEvent.getType());
    }

    public HandlerRegistration addHiddenHandler(final HiddenHandler handler) {
        return addHandler(handler, HiddenEvent.getType());
    }

    public HandlerRegistration addShowHandler(final ShowHandler handler) {
        return addHandler(handler, ShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(final ShownHandler handler) {
        return addHandler(handler, ShownEvent.getType());
    }

    protected void onShow() {
        getElement().getStyle().setDisplay(Style.Display.BLOCK);
    }

    protected void onHide() {
        getElement().getStyle().clearDisplay();
    }

    protected void deattach() {
        removeFromParent();
    }

    protected void initBackdrop() {
        backdrop = Document.get().createDivElement();
        backdrop.setClassName(Styles.MODAL_BACKDROP);
    }

}
