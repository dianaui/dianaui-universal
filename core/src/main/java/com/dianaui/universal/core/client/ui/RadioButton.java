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

import com.dianaui.universal.core.client.ui.base.*;
import com.dianaui.universal.core.client.ui.base.button.AbstractIconButton;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.base.mixin.ToggleMixin;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;

/**
 * Button representing a radio button used within a {@link ButtonGroup} that has
 * toggle set to {@code Toogle.BUTTONS}.
 * If you are looking for a classic radio button see {@link RadioButton}.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class RadioButton extends Radio implements HasType<ButtonType>, HasSize<ButtonSize>, IsComplexWidget, HasIcon,
        HasIconPosition, HasToggle {

    private final AbstractIconButton iconButton = new AbstractIconButton() {
        @Override
        protected Element createElement() {
            return RadioButton.this.getElement();
        }
    };

    /**
     * Creates a new radio associated with a particular group, and initialized
     * with the given HTML label. All radio buttons associated with the same
     * group name belong to a mutually-exclusive set.
     * <p/>
     * Radio buttons are grouped by their name attribute, so changing their name
     * using the setName() method will also change their associated group.
     *
     * @param name  the group name with which to associate the radio button
     * @param label this radio button's html label
     */
    public RadioButton(final String name, final SafeHtml label) {
        this(name, label.asString(), true);
    }

    /**
     * @param name  the group name with which to associate the radio button
     * @param label this radio button's html label
     * @param dir   the text's direction. Note that {@code DEFAULT} means
     *              direction should be inherited from the widget's parent
     *              element.
     * @see #RadioButtonToggle(String, SafeHtml)
     */
    public RadioButton(final String name, final SafeHtml label, final Direction dir) {
        this(name);
        setHTML(label, dir);
    }

    /**
     * @param name               the group name with which to associate the radio button
     * @param label              this radio button's html label
     * @param directionEstimator A DirectionEstimator object used for automatic direction
     *                           adjustment. For convenience,
     *                           {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
     * @see #RadioButtonToggle(String, SafeHtml)
     */
    public RadioButton(final String name, final SafeHtml label, final DirectionEstimator directionEstimator) {
        this(name);
        setDirectionEstimator(directionEstimator);
        setHTML(label.asString());
    }

    /**
     * Creates a new radio associated with a particular group, and initialized
     * with the given HTML label. All radio buttons associated with the same
     * group name belong to a mutually-exclusive set.
     * <p/>
     * Radio buttons are grouped by their name attribute, so changing their name
     * using the setName() method will also change their associated group.
     *
     * @param name  the group name with which to associate the radio button
     * @param label this radio button's label
     */
    public RadioButton(final String name, final String label) {
        this(name);
        setText(label);
    }

    /**
     * @param name  the group name with which to associate the radio button
     * @param label this radio button's label
     * @param dir   the text's direction. Note that {@code DEFAULT} means
     *              direction should be inherited from the widget's parent
     *              element.
     * @see #RadioButtonToggle(String, SafeHtml)
     */
    public RadioButton(final String name, final String label, final Direction dir) {
        this(name);
        setText(label, dir);
    }

    /**
     * @param name               the group name with which to associate the radio button
     * @param label              this radio button's label
     * @param directionEstimator A DirectionEstimator object used for automatic direction
     *                           adjustment. For convenience,
     *                           {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
     * @see #RadioButtonToggle(String, SafeHtml)
     */
    public RadioButton(final String name, final String label, final DirectionEstimator directionEstimator) {
        this(name);
        setDirectionEstimator(directionEstimator);
        setText(label);
    }

    /**
     * Creates a new radio button associated with a particular group, and
     * initialized with the given label (optionally treated as HTML). All radio
     * buttons associated with the same group name belong to a
     * mutually-exclusive set.
     * <p/>
     * Radio buttons are grouped by their name attribute, so changing their name
     * using the setName() method will also change their associated group.
     *
     * @param name   name the group with which to associate the radio button
     * @param label  this radio button's label
     * @param asHTML <code>true</code> to treat the specified label as HTML
     */
    public RadioButton(final String name, final String label, final boolean asHTML) {
        this(name);
        if (asHTML) {
            setHTML(label);
        } else {
            setText(label);
        }
    }

    @UiConstructor
    public RadioButton(final String name) {
        this(Document.get().createRadioInputElement(name));
    }

    protected RadioButton(final InputElement elem) {
        super(DOM.createDiv());

        setStyleName(Styles.BTN);
        setType(ButtonType.DEFAULT);

        inputElem = elem;

        getElement().appendChild(inputElem);

        // Accessibility: setting tab index to be 0 by default, ensuring element
        // appears in tab sequence. FocusWidget's setElement method already
        // calls setTabIndex, which is overridden below. However, at the time
        // that this call is made, inputElem has not been created. So, we have
        // to call setTabIndex again, once inputElem has been created.
        setTabIndex(0);

        sinkEvents(Event.ONCLICK);
    }

    @Override
    public void sinkEvents(int eventBitsToAdd) {
        // Like CheckBox, we want to hear about inputElem. We
        // also want to know what's going on with the label, to
        // make sure onBrowserEvent is able to record value changes
        // initiated by label events
        if (isOrWasAttached()) {
            Event.sinkEvents(getElement(), eventBitsToAdd | Event.getEventsSunk(getElement()));
        }

        super.sinkEvents(eventBitsToAdd);
    }

    @Override
    public ButtonSize getSize() {
        return ButtonSize.fromStyleName(getStyleName());
    }

    @Override
    public void setSize(final ButtonSize size) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonSize.class, size);
    }

    @Override
    public ButtonType getType() {
        return ButtonType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final ButtonType type) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonType.class, type);
    }

    @Override
    public void setValue(Boolean value, final boolean fireEvents) {
        super.setValue(value, fireEvents);

        ActiveMixin.setActive(this, getValue());
    }

    @Override
    public void onBrowserEvent(Event event) {
        boolean oldValue = getValue();

        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                setValue(!getValue(), false);

                if (getParent() != null && getParent() instanceof ButtonGroup) {
                    ButtonGroup group = (ButtonGroup) getParent();
                    for (int i = 0; i < ((ButtonGroup) getParent()).getWidgetCount(); i++) {
                        if (group.getWidget(i) instanceof RadioButton && group.getWidget(i) != this) {
                            ((RadioButton) group.getWidget(i)).setValue(false, false);
                        }
                    }
                }
                ValueChangeEvent.fireIfNotEqual(RadioButton.this, oldValue, getValue());
                break;
            default:
                super.onBrowserEvent(event);
                break;
        }
    }

    @Override
    public void insert(final Widget child, final int beforeIndex) {
        iconButton.insert(child, beforeIndex);
    }

    public void add(final IsWidget child) {
        iconButton.add(child);
    }

    public boolean remove(final IsWidget isWidget) {
        return iconButton.remove(isWidget);
    }

    @Override
    public void add(final Widget widget) {
        iconButton.add(widget);
    }

    @Override
    public void clear() {
        iconButton.clear();
    }

    public Iterator<Widget> iterator() {
        return iconButton.iterator();
    }

    @Override
    public boolean remove(final Widget widget) {
        return iconButton.remove(widget);
    }

    @Override
    public Widget getWidget(int i) {
        return iconButton.getWidget(i);
    }

    @Override
    public int getWidgetCount() {
        return iconButton.getWidgetCount();
    }

    @Override
    public int getWidgetIndex(final Widget widget) {
        return iconButton.getWidgetIndex(widget);
    }

    @Override
    public int getWidgetIndex(final IsWidget isWidget) {
        return iconButton.getWidgetIndex(isWidget);
    }

    @Override
    public boolean remove(int i) {
        return iconButton.remove(i);
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return iconButton.getFontAwesomeIcon();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        iconButton.setFontAwesomeIcon(iconType);

        fixInputElementPosition();
    }

    @Override
    public String getText() {
        return iconButton.getText();
    }

    @Override
    public void setText(final String text) {
        iconButton.setText(text);

        fixInputElementPosition();
    }

    @Override
    public String getHTML() {
        return iconButton.getHTML();
    }

    @Override
    public void setHTML(final String html) {
        iconButton.setHTML(html);

        fixInputElementPosition();
    }

    @Override
    // TODO
    public void setHTML(final SafeHtml html, final Direction dir) {
        setHTML(html);
    }

    @Override
    // TODO
    public void setText(final String text, final Direction dir) {
        setText(text);
    }

    @Override
    public void clearIcon() {
        iconButton.clearIcon();

        fixInputElementPosition();
    }

    @Override
    public IconSize getIconSize() {
        return iconButton.getIconSize();
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        iconButton.setIconSize(iconSize);

        fixInputElementPosition();
    }

    @Override
    public IconFlip getIconFlip() {
        return iconButton.getIconFlip();
    }

    @Override
    public void setIconFlip(final IconFlip iconFlip) {
        iconButton.setIconFlip(iconFlip);

        fixInputElementPosition();
    }

    @Override
    public IconRotate getIconRotate() {
        return iconButton.getIconRotate();
    }

    @Override
    public void setIconRotate(final IconRotate iconRotate) {
        iconButton.setIconRotate(iconRotate);

        fixInputElementPosition();
    }

    @Override
    public boolean isIconBordered() {
        return iconButton.isIconBordered();
    }

    @Override
    public void setIconBordered(final boolean iconBordered) {
        iconButton.setIconBordered(iconBordered);

        fixInputElementPosition();
    }

    @Override
    public boolean isIconMuted() {
        return iconButton.isIconMuted();
    }

    @Override
    public void setIconMuted(final boolean iconMuted) {
        iconButton.setIconMuted(iconMuted);

        fixInputElementPosition();
    }

    @Override
    public boolean isIconLight() {
        return iconButton.isIconLight();
    }

    @Override
    public void setIconLight(final boolean iconLight) {
        iconButton.setIconLight(iconLight);

        fixInputElementPosition();
    }

    @Override
    public boolean isIconSpin() {
        return iconButton.isIconSpin();
    }

    @Override
    public void setIconSpin(final boolean iconSpin) {
        iconButton.setIconSpin(iconSpin);

        fixInputElementPosition();
    }

    @Override
    public boolean isIconFixedWidth() {
        return iconButton.isIconFixedWidth();
    }

    @Override
    public void setIconFixedWidth(final boolean iconFixedWidth) {
        iconButton.setIconFixedWidth(iconFixedWidth);

        fixInputElementPosition();
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return iconButton.getGlyphicon();
    }

    @Override
    public void setGlyphicon(final GlyphiconType iconType) {
        iconButton.setGlyphicon(iconType);

        fixInputElementPosition();
    }

    @Override
    public IconPosition getIconPosition() {
        return iconButton.getIconPosition();
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        iconButton.setIconPosition(iconPosition);

        fixInputElementPosition();
    }

    @Override
    public Toggle getToggle() {
        return ToggleMixin.getToggle(this);
    }

    @Override
    public void setToggle(final Toggle toggle) {
        ToggleMixin.setToggle(this, toggle);
    }

    private void fixInputElementPosition() {
        if (getElement().getChild(0) != inputElem)
            getElement().insertFirst(inputElem);
    }

}
