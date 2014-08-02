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
package com.dianaui.universal.core.ui;

import com.dianaui.universal.core.ui.base.AbstractFormElement;
import com.dianaui.universal.core.ui.constants.Styles;

/**
 * A radio element with a label for use within a {@link Form}.
 * <p/>
 * Basically this is a non-styled {@link RadioButton} encapsulated in a {@link com.dianaui.universal.core.ui.html.Div}.
 *
 * @author Sven Jacobs
 * @see InlineRadio
 * @see CheckBox
 */
public class Radio extends AbstractFormElement {

    public Radio() {
        super(new RadioButton());
        setStyleName(Styles.RADIO);
    }

    /**
     * Creates a check box with the specified text label.
     *
     * @param label the check box's label
     */
    public Radio(final String label) {
        this();
        setText(label);
    }
}
