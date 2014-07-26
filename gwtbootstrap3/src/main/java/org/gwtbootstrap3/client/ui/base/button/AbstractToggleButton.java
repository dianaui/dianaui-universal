package org.gwtbootstrap3.client.ui.base.button;

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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.DropDownButton;
import org.gwtbootstrap3.client.ui.ListDropDown;
import org.gwtbootstrap3.client.ui.base.HasToggle;
import org.gwtbootstrap3.client.ui.base.mixin.ToggleMixin;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.gwtbootstrap3.client.ui.html.Text;

/**
 * Base class for buttons that can be toggle buttons
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see AbstractButton
 * @see org.gwtbootstrap3.client.ui.constants.Toggle
 */
public abstract class AbstractToggleButton extends AbstractIconButton implements HasToggle {

    private final ToggleMixin<AbstractToggleButton> toggleMixin = new ToggleMixin<AbstractToggleButton>(this);
    private final Text separator = new Text(" ");
    private final Caret caret = new Caret();

    protected AbstractToggleButton() {
        this(ButtonType.DEFAULT);
    }

    protected AbstractToggleButton(final ButtonType type) {
        setType(type);
        iconTextMixin.addTextWidgetToParent();
    }

    @Override
    public Toggle getToggle() {
        return toggleMixin.getToggle();
    }

    /**
     * Specifies that this button acts as a toggle, for instance for a parent {@link org.gwtbootstrap3.client.ui.DropDown}
     * or {@link org.gwtbootstrap3.client.ui.ButtonGroup}
     * Adds a {@link Caret} as a child widget.
     *
     * @param toggle Kind of toggle
     */
    @Override
    public void setToggle(final Toggle toggle) {
        toggleMixin.setToggle(toggle);

        // We defer to make sure the elements are available to manipulate their position
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                separator.removeFromParent();
                caret.removeFromParent();

                if (toggle == Toggle.DROPDOWN) {
                    addStyleName(Styles.DROPDOWN_TOGGLE);

                    add(separator, (Element) getElement());
                    add(caret, (Element) getElement());
                }
            }
        });
    }

    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);

        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                if (getToggle() == Toggle.DROPDOWN && getParent() instanceof ListDropDown) {
                    ((ListDropDown) getParent()).toggle();
                } else if (getToggle() == Toggle.DROPDOWN && getParent() instanceof DropDownButton) {
                    ((DropDownButton) getParent()).toggle();
                } else if (getToggle() == Toggle.DROPDOWN && getParent() instanceof ButtonGroup) {
                    ((ButtonGroup) getParent()).toggle();
                }
                break;
        }
    }

    @Override
    protected void onChanged() {
        // fix caret position
        setToggle(getToggle());
    }

}
