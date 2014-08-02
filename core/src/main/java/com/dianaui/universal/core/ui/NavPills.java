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

import com.dianaui.universal.core.ui.base.HasStacked;
import com.dianaui.universal.core.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.ui.constants.Styles;

/**
 * @author Sven Jacobs
 * @see AnchorListItem
 */
public class NavPills extends Nav implements HasStacked {

    public NavPills() {
        addStyleName(Styles.NAV_PILLS);
    }

    @Override
    public boolean isStacked() {
        return StyleHelper.containsStyle(getStyleName(), Styles.NAV_STACKED);
    }

    @Override
    public void setStacked(final boolean stacked) {
        if (stacked) {
            addStyleName(Styles.NAV_STACKED);
        } else {
            removeStyleName(Styles.NAV_STACKED);
        }
    }
}
