package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GWT Widgets
 * %%
 * Copyright (C) 2014 GWT Widgets
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

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class TabContent extends Div implements HasValueChangeHandlers<Integer> {

    private int selected = 0;

    public TabContent() {
        setStyleName(Styles.TAB_CONTENT);
    }

    @Override
    public void add(final Widget child) {
        if (!(child instanceof TabPane)) {
            throw new IllegalArgumentException("TabContent must have children of type TabPane.");
        }
        super.add(child);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public void selectTab(final int index) {
        for (int i = 0; i < getWidgetCount(); i++) {
            final TabPane tab = (TabPane) getWidget(i);

            if (tab.isActive()) {
                tab.setActive(false);
            }
        }

        ((TabPane) getWidget(index)).setActive(true);

        ValueChangeEvent.fireIfNotEqual(this, selected, index);

        this.selected = index;
    }

    public int getSelectedTab() {
        return selected;
    }

}
