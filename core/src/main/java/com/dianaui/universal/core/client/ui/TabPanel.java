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

import com.dianaui.universal.core.client.ui.base.HasTabPosition;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.constants.TabPosition;
import com.dianaui.universal.core.client.ui.html.Div;

/**
 * Container widget for tabs
 * <a href="http://getbootstrap.com/javascript/#tabs">Bootstrap Documentation</a>
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 * <b:TabPanel>
 *    <b:NavTabs/>
 *    <b:TabContent/>
 * </b:TabPanel>
 * }
 * </pre>
 *
 * @author Joshua Godi
 * @see TabContent
 * @see NavTabs
 */
public class TabPanel extends Div implements HasTabPosition {

    /**
     * Creates the widget with the default styles
     */
    public TabPanel() {
        setStyleName(Styles.TABBABLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TabPosition getTabPosition() {
        return TabPosition.fromStyleName(getStyleName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTabPosition(final TabPosition tabPosition) {
        StyleHelper.addUniqueEnumStyleName(this, TabPosition.class, tabPosition);
    }

}
