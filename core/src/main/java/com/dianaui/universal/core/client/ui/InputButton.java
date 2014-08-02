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

import com.dianaui.universal.core.client.ui.base.HasFormValue;
import com.dianaui.universal.core.client.ui.base.button.AbstractToggleButton;
import com.dianaui.universal.core.client.ui.constants.Attributes;
import com.dianaui.universal.core.client.ui.constants.ElementTags;
import com.dianaui.universal.core.client.ui.constants.TypeAttrType;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.ui.HasName;

/**
 * Button based on {@code <input>} element.
 *
 * @author Sven Jacobs
 * @see Button
 * @see AbstractToggleButton
 */
public class InputButton extends AbstractToggleButton implements HasName, HasFormValue {

    public InputButton() {
        this(TypeAttrType.BUTTON);
    }

    public InputButton(final TypeAttrType type) {
        setTypeAttr(type);
    }

    @Override
    public String getText() {
        return getElement().getAttribute(Attributes.VALUE);
    }

    @Override
    public void setText(final String text) {
        getElement().setAttribute(Attributes.VALUE, text);
    }

    public void setTypeAttr(final TypeAttrType type) {
        getElement().setAttribute(Attributes.TYPE, type.getInputType());
    }

    @Override
    public String getName() {
        return InputElement.as(getElement()).getName();
    }

    @Override
    public void setName(final String name) {
        InputElement.as(getElement()).setName(name);
    }

    @Override
    public String getFormValue() {
        return InputElement.as(getElement()).getValue();
    }

    @Override
    public void setFormValue(final String value) {
        InputElement.as(getElement()).setValue(value);
    }

    @Override
    protected Element createElement() {
        return Document.get().createElement(ElementTags.INPUT);
    }

}
