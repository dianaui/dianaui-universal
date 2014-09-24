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

import com.dianaui.universal.core.client.ui.base.HasActive;
import com.dianaui.universal.core.client.ui.base.HasSize;
import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.constants.ButtonSize;
import com.dianaui.universal.core.client.ui.constants.ButtonType;
import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * Button representing a radio button used within a {@link ButtonGroup} that has
 * toggle set to {@code Toogle.BUTTONS}.
 * If you are looking for a classic radio button see {@link RadioButton}.
 *
 * @author Sven Jacobs
 */
public class RadioButton extends Radio implements HasActive, HasType<ButtonType>, HasSize<ButtonSize> {

    private final ActiveMixin<RadioButton> activeMixin = new ActiveMixin<RadioButton>(this);

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
    public RadioButton(String name, SafeHtml label) {
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
    public RadioButton(String name, SafeHtml label, Direction dir) {
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
    public RadioButton(String name, SafeHtml label, DirectionEstimator directionEstimator) {
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
    public RadioButton(String name, String label) {
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
    public RadioButton(String name, String label, Direction dir) {
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
    public RadioButton(String name, String label, DirectionEstimator directionEstimator) {
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
    public RadioButton(String name, String label, boolean asHTML) {
        this(name);
        if (asHTML) {
            setHTML(label);
        } else {
            setText(label);
        }
    }

    @UiConstructor
    public RadioButton(String name) {
        super(Document.get().createRadioInputElement(name));
    }

    @Override
    public void setSize(ButtonSize size) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonSize.class, size);
    }

    @Override
    public ButtonSize getSize() {
        return ButtonSize.fromStyleName(getStyleName());
    }

    @Override
    public void setType(ButtonType type) {
        StyleHelper.addUniqueEnumStyleName(this, ButtonType.class, type);
    }

    @Override
    public ButtonType getType() {
        return ButtonType.fromStyleName(getStyleName());
    }

    @Override
    public void setActive(boolean active) {
        setValue(active);
        activeMixin.setActive(active);
    }

    @Override
    public boolean isActive() {
        return activeMixin.isActive();
    }

}