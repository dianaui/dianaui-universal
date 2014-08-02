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

import com.dianaui.universal.core.client.ui.base.HasValidationState;
import com.dianaui.universal.core.client.ui.base.form.FormElementContainer;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.constants.ValidationState;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class FormGroup extends FormElementContainer implements HasValidationState {

    public FormGroup() {
        setElement(Document.get().createDivElement());
        setStyleName(Styles.FORM_GROUP);
    }

    public void setFeedback(boolean feedback) {
        StyleHelper.toggleStyleName(this, feedback, Styles.HAS_FEEDBACK);
    }

    @Override
    public void add(final Widget w) {
        super.add(w);

        if (w instanceof FormControlFeedback) {
            addStyleName(Styles.HAS_FEEDBACK);
        }
    }

    @Override
    public ValidationState getValidationState() {
        return ValidationState.fromStyleName(getStyleName());
    }

    @Override
    public void setValidationState(final ValidationState state) {
        StyleHelper.addUniqueEnumStyleName(this, ValidationState.class, state);
    }

    @Override
    public void clearValidationState() {
        StyleHelper.removeEnumStyleNames(this, ValidationState.class);
    }

}
