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
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.DirectionalTextHelper;

/**
 * Button representing a checkbox used within a {@link ButtonGroup} that has
 * toggle set to {@code Toogle.BUTTONS}.
 * If you are looking for a classic checkbox see {@link CheckBox}.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class CheckBoxButton extends CheckBox implements HasActive, HasType<ButtonType>, HasSize<ButtonSize> {

    /**
     * Creates a check box button with the specified text label.
     *
     * @param label the check box's label
     */
    public CheckBoxButton(final SafeHtml label) {
        this(label.asString(), true);
    }

    /**
     * Creates a check box button with the specified text label.
     *
     * @param label the check box's label
     * @param dir   the text's direction. Note that {@code DEFAULT} means
     *              direction should be inherited from the widget's parent
     *              element.
     */
    public CheckBoxButton(final SafeHtml label, final Direction dir) {
        this();
        setHTML(label, dir);
    }

    /**
     * Creates a check box button with the specified text label.
     *
     * @param label              the check box's label
     * @param directionEstimator A DirectionEstimator object used for automatic direction
     *                           adjustment. For convenience,
     *                           {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
     */
    public CheckBoxButton(final SafeHtml label, final DirectionEstimator directionEstimator) {
        this();
        setDirectionEstimator(directionEstimator);
        setHTML(label.asString());
    }

    /**
     * Creates a check box button with the specified text label.
     *
     * @param label the check box's label
     */
    public CheckBoxButton(final String label) {
        this();
        setText(label);
    }

    /**
     * Creates a check box button with the specified text label.
     *
     * @param label the check box's label
     * @param dir   the text's direction. Note that {@code DEFAULT} means
     *              direction should be inherited from the widget's parent
     *              element.
     */
    public CheckBoxButton(final String label, final Direction dir) {
        this();
        setText(label, dir);
    }

    /**
     * Creates a label with the specified text and a default direction
     * estimator.
     *
     * @param label              the check box's label
     * @param directionEstimator A DirectionEstimator object used for automatic direction
     *                           adjustment. For convenience,
     *                           {@link #DEFAULT_DIRECTION_ESTIMATOR} can be used.
     */
    public CheckBoxButton(final String label, final DirectionEstimator directionEstimator) {
        this();
        setDirectionEstimator(directionEstimator);
        setText(label);
    }

    /**
     * Creates a check box button with the specified text label.
     *
     * @param label  the check box's label
     * @param asHTML <code>true</code> to treat the specified label as html
     */
    public CheckBoxButton(final String label, final boolean asHTML) {
        this();
        if (asHTML) {
            setHTML(label);
        } else {
            setText(label);
        }
    }

    public CheckBoxButton() {
        this(Document.get().createCheckInputElement());
    }

    protected CheckBoxButton(final InputElement elem) {
        super(DOM.createLabel());

        setStyleName(Styles.BTN);
        setType(ButtonType.DEFAULT);

        inputElem = elem;
        labelElem = Document.get().createSpanElement();

        getElement().appendChild(inputElem);
        getElement().appendChild(labelElem);

        directionalTextHelper = new DirectionalTextHelper(labelElem, true);

        // Accessibility: setting tab index to be 0 by default, ensuring element
        // appears in tab sequence. FocusWidget's setElement method already
        // calls setTabIndex, which is overridden below. However, at the time
        // that this call is made, inputElem has not been created. So, we have
        // to call setTabIndex again, once inputElem has been created.
        setTabIndex(0);
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
    public boolean isActive() {
        return ActiveMixin.isActive(this);
    }

    @Override
    public void setActive(final boolean active) {
        setValue(active);
        ActiveMixin.setActive(this, active);
    }

}
