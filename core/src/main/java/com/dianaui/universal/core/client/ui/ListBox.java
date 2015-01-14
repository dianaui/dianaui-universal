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

import com.dianaui.universal.core.client.ui.base.HasId;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.constants.Styles;

/**
 * Convenience derivation from GWT's ListBox that can have an ID.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see com.google.gwt.user.client.ui.ListBox
 */
public class ListBox extends com.google.gwt.user.client.ui.ListBox implements HasId {

    /**g
     * Creates an empty list box in single selection mode.
     */
    public ListBox() {
        setStyleName(Styles.FORM_CONTROL);
    }

    /**
     * Creates an empty list box.
     *
     * @param isMultipleSelect specifies if multiple selection is enabled
     */
    public ListBox(final boolean isMultipleSelect) {
        this();
        setMultipleSelect(isMultipleSelect);
    }

    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

}
