package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import org.gwtbootstrap3.client.ui.base.form.AbstractForm;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.FormType;
import org.gwtbootstrap3.client.ui.constants.HasType;

/**
 * A form.
 *
 * @author Sven Jacobs
 * @see FormGroup
 * @see FormLabel
 * @see FormControlStatic
 * @see Legend
 */
public class Form extends AbstractForm implements HasType<FormType> {

    @Override
    public FormType getType() {
        return FormType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final FormType type) {
        StyleHelper.addUniqueEnumStyleName(this, FormType.class, type);
    }
}
