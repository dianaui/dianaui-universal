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

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.gwtbootstrap3.client.shared.event.*;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.*;
import org.gwtbootstrap3.client.ui.html.Div;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Joshua Godi
 * @author Pontus Enmark
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Tooltip implements IsWidget, HasWidgets, HasOneWidget, HasId, HasHover {

    protected static int DEFAULT_TRANSITION_MS = 150;
    private int transitionMs = DEFAULT_TRANSITION_MS;
    private boolean viewing = false;
    private Trigger trigger = Trigger.HOVER;
    private int hideDelayMs = 0;
    private int showDelayMs = 0;
    private Widget widget;
    private Div tooltip = new Div();
    private HandlerRegistration inHandler;
    private HandlerRegistration outHandler;
    private HandlerRegistration detachHandler;
    private boolean hiding = false;

    public Tooltip() {
        tooltip.getElement().setInnerHTML("<div class=\"" + Styles.TOOLTIP_INNER + "\"></div>" +
                "<div class=\"" + Styles.TOOLTIP_ARROW + "\"></div>");
        tooltip.getElement().getStyle().setZIndex(1050);
        tooltip.setStyleName(Styles.TOOLTIP);

        setFade(true);
        setPlacement(Placement.TOP);
    }

    public Tooltip(final String text) {
        this(text, false, null);
    }

    public Tooltip(final String text, final Widget widget) {
        this(text);
        add(widget);
    }

    public Tooltip(final String text, final Placement placement) {
        this(text, false, placement);
    }

    public Tooltip(final String text, final Placement placement, final Widget widget) {
        this(text, placement);
        add(widget);
    }

    public Tooltip(final String text, final boolean isHtml, final Placement placement) {
        this();

        if (isHtml) {
            setHTML(text);
        } else {
            setText(text);
        }

        if (placement != null) {
            setPlacement(placement);
        }
    }

    public Tooltip(final String text, final boolean isHtml, final Placement placement, Widget widget) {
        this(text, isHtml, placement);
        add(widget);
    }

    public Tooltip(final Widget w) {
        this();
        setWidget(w);
    }

    /**
     * If set Tooltip will fade in/out.
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

    public String getText() {
        return tooltip.getElement().getFirstChildElement().getInnerText();
    }

    public void setText(final String text) {
        tooltip.getElement().getFirstChildElement().setInnerText(text);
    }

    public String getHTML() {
        return tooltip.getElement().getFirstChildElement().getInnerHTML();
    }

    public void setHTML(final String html) {
        tooltip.getElement().getFirstChildElement().setInnerHTML(html);
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

            Timer showTimer = new Timer() {
                @Override
                public void run() {
                    tooltip.fireEvent(new ShowEvent());
                    RootPanel.get().add(tooltip);

                    tooltip.getElement().getStyle().setDisplay(Style.Display.BLOCK);

                    // positioning
                    int left = widget.getAbsoluteLeft();
                    int top = widget.getAbsoluteTop();
                    Placement placement = getPlacement();

                    if (placement == Placement.TOP) {
                        top -= tooltip.getOffsetHeight();

                        if (tooltip.getOffsetWidth() > widget.getOffsetWidth()) {
                            left -= (tooltip.getOffsetWidth() - widget.getOffsetWidth()) / 2;
                        } else {
                            left += (widget.getOffsetWidth() - tooltip.getOffsetWidth()) / 2;
                        }
                    } else if (placement == Placement.BOTTOM) {
                        top += widget.getOffsetHeight();

                        if (tooltip.getOffsetWidth() > widget.getOffsetWidth()) {
                            left -= (tooltip.getOffsetWidth() - widget.getOffsetWidth()) / 2;
                        } else {
                            left += (widget.getOffsetWidth() - tooltip.getOffsetWidth()) / 2;
                        }
                    } else if (placement == Placement.LEFT) {
                        left -= tooltip.getOffsetWidth();

                        if (tooltip.getOffsetHeight() > widget.getOffsetHeight()) {
                            top -= (tooltip.getOffsetHeight() - widget.getOffsetHeight()) / 2;
                        } else {
                            top += (widget.getOffsetHeight() - tooltip.getOffsetHeight()) / 2;
                        }
                    } else if (placement == Placement.RIGHT) {
                        left += widget.getOffsetWidth();

                        if (tooltip.getOffsetHeight() > widget.getOffsetHeight()) {
                            top -= (tooltip.getOffsetHeight() - widget.getOffsetHeight()) / 2;
                        } else {
                            top += (widget.getOffsetHeight() - tooltip.getOffsetHeight()) / 2;
                        }
                    }

                    setPosition(left, top);

                    Timer transitionTimer = new Timer() {
                        @Override
                        public void run() {
                            addStyleName(Styles.IN);

                            tooltip.fireEvent(new ShownEvent());
                        }
                    };

                    transitionTimer.schedule(transitionMs);
                }
            };

            showTimer.schedule(showDelayMs);
        }
    }

    public void hide() {
        if (isViewing() && !hiding) {
            hiding = true;

            Timer showTimer = new Timer() {
                @Override
                public void run() {
                    tooltip.fireEvent(new HideEvent());

                    removeStyleName(Styles.IN);
                    addStyleName(Styles.OUT);

                    Timer transitionTimer = new Timer() {
                        @Override
                        public void run() {
                            tooltip.removeFromParent();

                            setViewing(false);
                            hiding = false;

                            tooltip.fireEvent(new HiddenEvent());
                        }
                    };

                    transitionTimer.schedule(transitionMs);
                }
            };

            showTimer.schedule(hideDelayMs);
        }
    }

    public void setPosition(int left, int top) {
        tooltip.getElement().getStyle().setLeft(left, Style.Unit.PX);
        tooltip.getElement().getStyle().setTop(top, Style.Unit.PX);
    }

    public void setStyleName(String style) {
        tooltip.setStyleName(style);
    }

    public void addStyleName(String style) {
        tooltip.addStyleName(style);
    }

    public void removeStyleName(String style) {
        tooltip.removeStyleName(style);
    }

    public HandlerRegistration addHideHandler(final HideHandler handler) {
        return tooltip.addHandler(handler, HideEvent.getType());
    }

    public HandlerRegistration addHiddenHandler(final HiddenHandler handler) {
        return tooltip.addHandler(handler, HiddenEvent.getType());
    }

    public HandlerRegistration addShowHandler(final ShowHandler handler) {
        return tooltip.addHandler(handler, ShowEvent.getType());
    }

    public HandlerRegistration addShownHandler(final ShownHandler handler) {
        return tooltip.addHandler(handler, ShownEvent.getType());
    }

    @Override
    public void setWidget(final Widget w) {
        // Validate
        if (w == widget) {
            return;
        }

        // Detach new child
        if (w != null) {
            w.removeFromParent();
        }

        // Remove old child
        if (widget != null) {
            remove(widget);
        }

        // Logical attach, but don't physical attach; done by jquery.
        widget = w;
        if (widget == null) {
            return;
        }

        postSetWidget();
    }

    @Override
    public void add(final Widget child) {
        if (getWidget() != null) {
            throw new IllegalStateException("Can only contain one child widget");
        }
        setWidget(child);
    }

    @Override
    public Widget getWidget() {
        return widget;
    }

    @Override
    public void setWidget(final IsWidget w) {
        widget = (w == null) ? null : w.asWidget();

        postSetWidget();
    }

    @Override
    public String getId() {
        return tooltip.getId();
    }

    @Override
    public void setId(final String id) {
        tooltip.setId(id);
    }

    @Override
    public Placement getPlacement() {
        return Placement.fromStyleName(tooltip.getStyleName());
    }

    @Override
    public void setPlacement(final Placement placement) {
        StyleHelper.addUniqueEnumStyleName(tooltip, Placement.class, placement);
    }

    @Override
    public Trigger getTrigger() {
        return trigger;
    }

    @Override
    public void setTrigger(final Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public int getShowDelayMs() {
        return showDelayMs;
    }

    @Override
    public void setShowDelayMs(final int showDelayMs) {
        this.showDelayMs = showDelayMs;
    }

    @Override
    public int getHideDelayMs() {
        return hideDelayMs;
    }

    @Override
    public void setHideDelayMs(final int hideDelayMs) {
        this.hideDelayMs = hideDelayMs;
    }

    @Override
    public void clear() {
        widget = null;
    }

    @Override
    public Iterator<Widget> iterator() {
        // Simple iterator for the widget
        return new Iterator<Widget>() {
            boolean hasElement = widget != null;
            Widget returned = null;

            @Override
            public boolean hasNext() {
                return hasElement;
            }

            @Override
            public Widget next() {
                if (!hasElement || (widget == null)) {
                    throw new NoSuchElementException();
                }
                hasElement = false;
                return (returned = widget);
            }

            @Override
            public void remove() {
                if (returned != null) {
                    Tooltip.this.remove(returned);
                }
            }
        };
    }

    @Override
    public boolean remove(final Widget w) {
        // Validate.
        if (widget != w) {
            return false;
        }

        // Logical detach.
        clear();
        return true;
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public String toString() {
        return asWidget().toString();
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
