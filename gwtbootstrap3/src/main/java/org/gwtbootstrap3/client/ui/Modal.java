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

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.shared.event.*;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.modal.ModalContent;
import org.gwtbootstrap3.client.ui.base.modal.ModalDialog;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * Modal dialog.
 * <p/>
 * <h3>UiBinder example</h3>
 * <p/>
 * <pre>
 * {@code
 *     <b:Modal title="Important information" b:id="modal1">
 *         <b:ModalBody>
 *             <g:HTML>Lorem ipsum...</g:HTML>
 *         </b:ModalBody>
 *         <b:ModalFooter>
 *             <b:Button type="PRIMARY">Do something</b:Button>
 *             <b:Button type="DANGER" dismiss="MODAL">Close</b:Button>
 *         </b:ModalFooter>
 *     </b:Modal>
 *     <b:Button target="#modal1" toggle="MODAL">Show modal</b:Button>
 * }
 * </pre>
 * <p/>
 * It's also possible to specify a custom modal header:
 * <p/>
 * <pre>
 * {@code
 *     <b:Modal>
 *         <b:ModalHeader>
 *             <g:HTML>
 *                 <h4>Custom header</h4>
 *             </g:HTML>
 *         </b:ModalHeader>
 *         ...
 *     </b:Modal>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see ModalHeader
 * @see ModalBody
 * @see ModalFooter
 * @see org.gwtbootstrap3.client.shared.event.ShowEvent
 * @see org.gwtbootstrap3.client.shared.event.ShownEvent
 * @see org.gwtbootstrap3.client.shared.event.HideEvent
 * @see org.gwtbootstrap3.client.shared.event.HiddenEvent
 */
public class Modal extends Div implements IsClosable, HasResponsiveness {

    private static int DEFAULT_TRANSITION_MS = 150;

    private final ModalContent content = new ModalContent();
    private ModalHeader header = new ModalHeader();
    private boolean viewing = false;
    private int transitionMs = DEFAULT_TRANSITION_MS;
    private DivElement backdrop;
    private ModalBackdrop backdropType = ModalBackdrop.TRUE;

    public Modal() {
        setStyleName(Styles.MODAL);

        final ModalDialog dialog = new ModalDialog();

        content.add(header);
        dialog.add(content);

        add(dialog);

        initBackdrop();
        setFade(false);
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

    /**
     * Sets backdrop of modal.
     *
     * @param backdrop Backdrop of modal
     * @see org.gwtbootstrap3.client.ui.constants.ModalBackdrop
     */
    public void setBackdrop(final ModalBackdrop backdrop) {
        this.backdropType = backdrop;
    }

    // TODO
    public void setKeyboard(final boolean keyboard) {
        getElement().setAttribute(Attributes.DATA_KEYBOARD, Boolean.toString(keyboard));
    }

    public boolean isViewing() {
        return viewing;
    }

    public void toggle() {
        if (viewing) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        if (!viewing) {
            viewing = true;
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
                    getElement().getStyle().setDisplay(Style.Display.BLOCK);

                    if (!backdropType.equals(ModalBackdrop.FALSE)) {
                        backdrop.getParentElement().getFirstChildElement().getOffsetWidth();
                    }

                    addStyleName(Styles.IN);


                    Modal.this.fireEvent(new ShownEvent());
                }
            };

            timer.schedule(transitionMs);
        }
    }

    public void hide() {
        if (viewing) {
            fireEvent(new HideEvent());

            removeStyleName(Styles.IN);

            // force reflow
            if (backdrop.getParentNode() != null) {
                backdrop.getParentElement().getFirstChildElement().getOffsetWidth();
            }

            Timer timer = new Timer() {
                @Override
                public void run() {
                    getElement().getStyle().clearDisplay();

                    if (backdrop.getParentNode() != null) {
                        backdrop.removeClassName(Styles.IN);
                    }

                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            removeFromParent();

                            if (backdrop.getParentNode() != null) {
                                backdrop.removeFromParent();
                            }

                            viewing = false;
                        }
                    };

                    timer.schedule(transitionMs);

                    Modal.this.fireEvent(new HiddenEvent());
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

    @Override
    public void setClosable(final boolean closable) {
        header.setClosable(closable);

        if (closable) {
            header.getCloseButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    hide();
                }
            });
        }
    }

    @Override
    public boolean isClosable() {
        return header.isClosable();
    }

    @Override
    public void setVisibleOn(final String deviceSizeString) {
        StyleHelper.setVisibleOn(this, deviceSizeString);
    }

    @Override
    public void setHiddenOn(final String deviceSizeString) {
        StyleHelper.setHiddenOn(this, deviceSizeString);
    }

    @Override
    public void add(final Widget w) {
        // User can supply own ModalHeader
        if (w instanceof ModalHeader) {
            header.removeFromParent();
            header = (ModalHeader) w;
        }

        if (w instanceof ModalComponent) {
            content.add(w);
        } else {
            super.add(w);
        }
    }

    @Override
    public void setTitle(final String title) {
        header.setTitle(title);
    }

    private void initBackdrop() {
        backdrop = Document.get().createDivElement();
        backdrop.setClassName(Styles.MODAL_BACKDROP);
    }

}
