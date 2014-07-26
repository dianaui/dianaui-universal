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
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.gwtbootstrap3.client.ui.base.AbstractListItem;
import org.gwtbootstrap3.client.ui.base.HasPull;
import org.gwtbootstrap3.client.ui.base.mixin.PullMixin;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.Pull;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.UnorderedList;

/**
 * Container for drop down menu items.
 * <strong>Must</strong> be encapsulated in a {@link ButtonGroup} to build button dropdowns.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see ButtonGroup
 */
public class DropDownMenu extends UnorderedList implements HasPull {

    private static final String MENU = "menu";

    private final PullMixin<DropDownMenu> pullMixin = new PullMixin<DropDownMenu>(this);

    private Widget relativeWidget;
    private HandlerRegistration resizeHandler;

    public DropDownMenu() {
        setStyleName(Styles.DROPDOWN_MENU);
        getElement().setAttribute(Attributes.ROLE, MENU);
    }

    @Override
    public Pull getPull() {
        return pullMixin.getPull();
    }

    @Override
    public void setPull(final Pull pull) {
        pullMixin.setPull(pull);
    }

    public void show(final Widget relativeWidget) {
        if (this.relativeWidget != relativeWidget) {
            this.relativeWidget = relativeWidget;

            if (resizeHandler != null) {
                resizeHandler.removeHandler();
                resizeHandler = null;
            }
        }

        if (!isAttached()) {
            RootPanel.get().add(this);
        }

        relativeTo(relativeWidget);

        getElement().getStyle().setDisplay(Style.Display.BLOCK);
        getElement().getStyle().setZIndex(1500);

        if (resizeHandler == null) {
            resizeHandler = Window.addResizeHandler(new ResizeHandler() {
                @Override
                public void onResize(ResizeEvent event) {
                    if (isShowing()) {
                        relativeTo(relativeWidget);
                    }
                }
            });
        }
    }

    public void hide() {
        getElement().getStyle().clearDisplay();
        removeFromParent();
    }

    public boolean isShowing() {
        return isAttached() && getElement().getStyle().getDisplay().equals(Style.Display.BLOCK.getCssName());
    }

    @Override
    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);

        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                if (getParent() instanceof ListDropDown) {
                    ((ListDropDown) getParent()).hide();
                }
                break;
        }
    }

    /**
     * Returns the <code>AbstractListItem</code> that is currently selected (highlighted)
     * by the user. If none of the items in the menu are currently selected, then
     * <code>null</code> will be returned.
     *
     * @return the <code>AbstractListItem</code> that is currently selected, or
     * <code>null</code> if no items are currently selected
     */
    public AbstractListItem getSelectedItem() {
        for (int i = 0; i < getWidgetCount(); i++) {
            Widget item = getWidget(i);

            if (item instanceof AbstractListItem && ((AbstractListItem) item).isActive()) {
                return (AbstractListItem) item;
            }
        }
        return null;
    }

    /**
     * Returns the index of the menu item that is currently selected.
     *
     * @return returns the selected item
     */
    public int getSelectedItemIndex() {
        // The index of the currently selected item can only be
        // obtained if the menu is showing.
        AbstractListItem selectedItem = getSelectedItem();
        if (selectedItem != null) {
            return getWidgetIndex(selectedItem);
        }
        return -1;
    }

    /**
     * Selects the item at the specified index in the menu. Selecting the item
     * does not perform the item's associated action; it only changes the style
     * of the item.
     *
     * @param index index
     */
    public void selectItem(int index) {
        for (int i = 0; i < getWidgetCount(); i++) {
            Widget item = getWidget(i);

            if (item instanceof AbstractListItem) {
                ((AbstractListItem) item).setActive(i == index);
            }
        }
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        sinkEvents(Event.ONCLICK);
    }

    private void relativeTo(Widget relativeWidget) {
        getElement().getStyle().setLeft(relativeWidget.getAbsoluteLeft(), Style.Unit.PX);
        getElement().getStyle().setTop(relativeWidget.getAbsoluteTop() + relativeWidget.getOffsetHeight(), Style.Unit.PX);
    }

}
