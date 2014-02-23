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
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.shared.event.HiddenEvent;
import org.gwtbootstrap3.client.shared.event.HideEvent;
import org.gwtbootstrap3.client.shared.event.ShowEvent;
import org.gwtbootstrap3.client.shared.event.ShownEvent;
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
 * @see org.gwtbootstrap3.client.shared.event.ModalShowEvent
 * @see org.gwtbootstrap3.client.shared.event.ModalShownEvent
 * @see org.gwtbootstrap3.client.shared.event.ModalHideEvent
 * @see org.gwtbootstrap3.client.shared.event.ModalHiddenEvent
 */
public class Modal extends FlowPanel implements IsClosable, HasResponsiveness {

    private static int transitionDuration = 150;

    private final ModalContent content = new ModalContent();
    private ModalHeader header = new ModalHeader();

    private boolean viewing = false;

    private DivElement backdrop;

    public Modal() {
        setStyleName(Styles.MODAL);

        final ModalDialog dialog = new ModalDialog();

        content.add(header);
        dialog.add(content);

        add(dialog);

        initBackdrop();
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

    public void setTitle(final String title) {
        header.setTitle(title);
    }

    @Override
    public void setClosable(final boolean closable) {
        header.setClosable(closable);
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

    /**
     * If set Modal will fade in/out.
     *
     * @param fade If {@code true} modal will fade in/out
     */
    public void setFade(final boolean fade) {
        if (fade) {
            addStyleName(Styles.FADE);
        } else {
            removeStyleName(Styles.FADE);
        }
    }

    /**
     * Sets backdrop of modal.
     *
     * @param backdrop Backdrop of modal
     * @see org.gwtbootstrap3.client.ui.constants.ModalBackdrop
     */
    public void setBackdrop(final ModalBackdrop backdrop) {
        if (backdrop != null) {
            getElement().setAttribute(Attributes.DATA_BACKDROP, backdrop.getBackdrop());
        } else {
            getElement().removeAttribute(Attributes.DATA_BACKDROP);
        }
    }

    public void setKeyboard(final boolean keyboard) {
        getElement().setAttribute(Attributes.DATA_KEYBOARD, Boolean.toString(keyboard));
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
                        if (Event.ONCLICK == event.getTypeInt() && event.getEventTarget().equals(getElement())) {
                            hide();
                        }
                    }
                });
            }

            RootPanel.getBodyElement().appendChild(backdrop);

            // force reflow
            backdrop.getParentElement().getFirstChildElement().getOffsetWidth();

            backdrop.addClassName(Styles.IN);

            Timer timer = new Timer() {
                @Override
                public void run() {
                    getElement().getStyle().setDisplay(Style.Display.BLOCK);

                    backdrop.getParentElement().getFirstChildElement().getOffsetWidth();

                    addStyleName(Styles.IN);


                    Modal.this.fireEvent(new ShownEvent());
                }
            };

            timer.schedule(transitionDuration);
        }
    }

    public void hide() {
        if (viewing) {
            fireEvent(new HideEvent());

            removeStyleName(Styles.IN);

            backdrop.getParentElement().getFirstChildElement().getOffsetWidth();

            Timer timer = new Timer() {
                @Override
                public void run() {
                    getElement().getStyle().clearDisplay();

                    backdrop.removeClassName(Styles.IN);

                    Timer timer = new Timer() {
                        @Override
                        public void run() {
                            removeFromParent();
                            backdrop.removeFromParent();

                            viewing = false;
                        }
                    };

                    timer.schedule(transitionDuration);

                    Modal.this.fireEvent(new HiddenEvent());
                }
            };

            timer.schedule(transitionDuration);
        }
    }

    private void initBackdrop() {
        backdrop = Document.get().createDivElement();
        backdrop.setClassName(Styles.MODAL_BACKDROP);
        backdrop.addClassName(Styles.FADE);
    }

}
