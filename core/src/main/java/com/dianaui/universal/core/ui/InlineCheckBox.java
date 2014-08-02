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

import com.dianaui.universal.core.ui.constants.Styles;

/**
 * An inline checkbox with a label for use within a {@link Form}.
 * Multiple InlineCheckBox in the same container will be displayed in one line.
 * <p/>
 * Basically this is a non-styled {@link CheckBoxButton}.
 *
 * @author Sven Jacobs
 * @see CheckBox
 * @see CheckBoxButton
 */
public class InlineCheckBox extends CheckBoxButton {

    public InlineCheckBox() {
        setStyleName(Styles.CHECKBOX_INLINE);
    }

    public InlineCheckBox(final String label) {
        super(label);
        setStyleName(Styles.CHECKBOX_INLINE);
    }
}
