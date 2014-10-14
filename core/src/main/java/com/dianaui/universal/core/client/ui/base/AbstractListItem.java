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
package com.dianaui.universal.core.client.ui.base;

import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.client.ui.constants.Pull;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasEnabled;

/**
 * Base class for list items.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see com.dianaui.universal.core.client.ui.ListItem
 * @see com.dianaui.universal.core.client.ui.AnchorListItem
 * @see com.dianaui.universal.core.client.ui.ListDropDown
 */
public abstract class AbstractListItem extends ComplexWidget implements HasId, HasEnabled, HasPull, HasActive,
        HasResponsiveness {

    protected AbstractListItem() {
        setElement(Document.get().createLIElement());
    }

    @Override
    public boolean isEnabled() {
        return !StyleHelper.containsStyle(getStyleName(), Styles.DISABLED);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            removeStyleName(Styles.DISABLED);
        } else {
            addStyleName(Styles.DISABLED);
        }
    }

    @Override
    public Pull getPull() {
        return PullMixin.getPull(this);
    }

    @Override
    public void setPull(final Pull pull) {
        PullMixin.setPull(this, pull);
    }

    @Override
    public boolean isActive() {
        return ActiveMixin.isActive(this);
    }

    @Override
    public void setActive(final boolean active) {
        ActiveMixin.setActive(this, active);
    }

}
