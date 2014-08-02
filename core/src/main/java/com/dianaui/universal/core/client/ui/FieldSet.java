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

import com.dianaui.universal.core.client.ui.base.ComplexWidget;
import com.dianaui.universal.core.client.ui.base.mixin.EnabledMixin;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasEnabled;

/**
 * @author Sven Jacobs
 * @author Joshua Godi
 * @see Form
 */
public class FieldSet extends ComplexWidget implements HasEnabled {

    private EnabledMixin<FieldSet> enabledMixin;

    public FieldSet() {
        setElement(Document.get().createFieldSetElement());
    }

    @Override
    public boolean isEnabled() {
        return getEnabledMixin().isEnabled();
    }

    @Override
    public void setEnabled(final boolean enabled) {
        getEnabledMixin().setEnabled(enabled);
    }

    private EnabledMixin getEnabledMixin() {
        if (enabledMixin == null) {
            enabledMixin = new EnabledMixin<FieldSet>(this);
        }
        return enabledMixin;
    }

}
