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
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see AnchorListItem
 */
public class NavTabs extends Nav {

    private TabContent tabContent;

    public NavTabs(TabContent tabContent) {
        this.tabContent = tabContent;

        addStyleName(Styles.NAV_TABS);
    }

    @Override
    public void add(Widget child) {
        super.add(child);

        updateTabItem(child);
    }

    @Override
    public void insert(Widget child, int beforeIndex) {
        super.insert(child, beforeIndex);

        updateTabItem(child);
    }

    public TabContent getContent() {
        return tabContent;
    }

    private void updateTabItem(Widget tab) {
        if (tab instanceof TabListItem) {
            if (((TabListItem) tab).getToggleIndex() == -1) {
                ((TabListItem) tab).setToogleIndex(getWidgetIndex(tab));
            }
        }
    }

}
