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
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.modal.ModalWithBackdrop;
import org.gwtbootstrap3.client.ui.constants.ModalBackdrop;
import org.gwtbootstrap3.client.ui.constants.Placement;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.Trigger;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Popover extends ModalWithBackdrop implements HasHover {

    private PopoverHeader header = new PopoverHeader();
    private PopoverBody body = new PopoverBody();

    private Trigger trigger = Trigger.HOVER;
    private int hideDelayMs = 0;
    private int showDelayMs = 0;
    private Widget widget;
    private HandlerRegistration inHandler;
    private HandlerRegistration outHandler;
    private HandlerRegistration detachHandler;

    public Popover() {
        this(null, null);
    }

    public Popover(Placement placement) {
        this(null, placement);
    }

    public Popover(String heading) {
        this(heading, null);
    }

    public Popover(String heading, Placement placement) {
        removeStyleName(Styles.MODAL);
        addStyleName(Styles.POPOVER);

        setFade(true);
        setBackdrop(ModalBackdrop.FALSE);
        if (placement != null) {
            setPlacement(placement);
        } else {
            setPlacement(Placement.TOP);
        }

        if (heading != null) {
            setHeading(heading);
        }

        super.add(body);

        DivElement arrow = Document.get().createDivElement();
        arrow.setClassName(Styles.ARROW);

        getElement().appendChild(arrow);
    }

    public String getHeading() {
        return header.getText();
    }

    public void setHeading(String heading) {
        super.insert(header, 0);
        header.setText(heading);
    }

    public PopoverHeader getHeader() {
        return header;
    }

    public PopoverBody getBody() {
        return body;
    }

    public void setPosition(int left, int top) {
        getElement().getStyle().setLeft(left, Style.Unit.PX);
        getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public void bind(Widget widget) {
        this.widget = widget;

        postSetWidget();
    }

    @Override
    public void add(Widget w) {
        // User can supply own ModalHeader
        if (w instanceof PopoverHeader) {
            header.removeFromParent();
            header = (PopoverHeader) w;
            super.insert(header, 0);
        } else if (w instanceof PopoverBody) {
            body.removeFromParent();
            body = (PopoverBody) w;
            super.add(body);
        } else {
            body.add(w);
        }
    }

    @Override
    public void insert(Widget child, int beforeIndex) {
        throw new UnsupportedOperationException("Popover does not support insert widgets");
    }

    @Override
    public int getWidgetCount() {
        return body.getWidgetCount();
    }

    @Override
    public boolean remove(Widget w) {
        return body.remove(w);
    }

    @Override
    public boolean remove(int index) {
        return body.remove(index);
    }

    @Override
    public void clear() {
        body.clear();
    }

    @Override
    public void setPlacement(final Placement placement) {
        StyleHelper.addUniqueEnumStyleName(this, Placement.class, placement);
    }

    @Override
    public Placement getPlacement() {
        return Placement.fromStyleName(getStyleName());
    }

    @Override
    public void setTrigger(final Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public Trigger getTrigger() {
        return trigger;
    }

    @Override
    public void setShowDelayMs(final int showDelayMs) {
        this.showDelayMs = showDelayMs;
    }

    @Override
    public int getShowDelayMs() {
        return showDelayMs;
    }

    @Override
    public void setHideDelayMs(final int hideDelayMs) {
        this.hideDelayMs = hideDelayMs;
    }

    @Override
    public int getHideDelayMs() {
        return hideDelayMs;
    }

    protected void onShow() {
        super.onShow();

        // positioning
        int left = widget.getAbsoluteLeft();
        int top = widget.getAbsoluteTop();
        Placement placement = getPlacement();

        if (placement == Placement.TOP) {
            top -= getOffsetHeight();

            if (getOffsetWidth() > widget.getOffsetWidth()) {
                left -= (getOffsetWidth() - widget.getOffsetWidth()) / 2;
            } else {
                left += (widget.getOffsetWidth() - getOffsetWidth()) / 2;
            }
        } else if (placement == Placement.BOTTOM) {
            top += widget.getOffsetHeight();

            if (getOffsetWidth() > widget.getOffsetWidth()) {
                left -= (getOffsetWidth() - widget.getOffsetWidth()) / 2;
            } else {
                left += (widget.getOffsetWidth() - getOffsetWidth()) / 2;
            }
        } else if (placement == Placement.LEFT) {
            left -= getOffsetWidth();

            if (getOffsetHeight() > widget.getOffsetHeight()) {
                top -= (getOffsetHeight() - widget.getOffsetHeight()) / 2;
            } else {
                top += (widget.getOffsetHeight() - getOffsetHeight()) / 2;
            }
        } else if (placement == Placement.RIGHT) {
            left += widget.getOffsetWidth();

            if (getOffsetHeight() > widget.getOffsetHeight()) {
                top -= (getOffsetHeight() - widget.getOffsetHeight()) / 2;
            } else {
                top += (widget.getOffsetHeight() - getOffsetHeight()) / 2;
            }
        }

        setPosition(left, top);
    }

    private void postSetWidget() {
        if (widget != null) {
            widget.addAttachHandler(new AttachEvent.Handler() {
                @Override
                public void onAttachOrDetach(AttachEvent event) {
                    activateTrigger();
                }
            });
        }
    }

    private void activateTrigger() {
        if (inHandler != null) {
            inHandler.removeHandler();
        }

        if (outHandler != null) {
            outHandler.removeHandler();
            outHandler = null;
        }

        if (detachHandler != null) {
            detachHandler.removeHandler();
        }

        if (trigger == Trigger.DEFAULT || trigger == Trigger.HOVER) {
            widget.sinkEvents(Event.ONMOUSEOVER);
            widget.sinkEvents(Event.ONMOUSEOUT);

            inHandler = widget.addHandler(new MouseOverHandler() {
                @Override
                public void onMouseOver(MouseOverEvent event) {
                    show();
                }
            }, MouseOverEvent.getType());

            outHandler = widget.addHandler(new MouseOutHandler() {
                @Override
                public void onMouseOut(MouseOutEvent event) {
                    hide();
                }
            }, MouseOutEvent.getType());
        } else if (trigger == Trigger.CLICK) {
            widget.sinkEvents(Event.ONCLICK);

            inHandler = widget.addHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    toggle();
                }
            }, ClickEvent.getType());
        } else if (trigger == Trigger.FOCUS) {
            widget.sinkEvents(Event.ONFOCUS);
            widget.sinkEvents(Event.ONBLUR);

            inHandler = widget.addHandler(new FocusHandler() {
                @Override
                public void onFocus(FocusEvent event) {
                    show();
                }
            }, FocusEvent.getType());
            outHandler = widget.addHandler(new BlurHandler() {
                @Override
                public void onBlur(BlurEvent event) {
                    hide();
                }
            }, BlurEvent.getType());
        }

        detachHandler = widget.addAttachHandler(new AttachEvent.Handler() {
            @Override
            public void onAttachOrDetach(AttachEvent event) {
                if (!event.isAttached()) {
                    hide();
                }
            }
        });
    }

}
