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
import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.constants.ProgressType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Div;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Progress extends Div implements HasType<ProgressType>, HasActive {

    public Progress() {
        setStyleName(Styles.PROGRESS);
    }

    @Override
    public boolean isActive() {
        return ActiveMixin.isActive(this);
    }

    @Override
    public void setActive(final boolean active) {
        ActiveMixin.setActive(this, active);
    }

    @Override
    public ProgressType getType() {
        return ProgressType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final ProgressType type) {
        StyleHelper.addUniqueEnumStyleName(this, ProgressType.class, type);
    }

}
