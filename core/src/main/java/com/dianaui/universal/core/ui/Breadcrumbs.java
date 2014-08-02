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
import com.dianaui.universal.core.ui.html.OrderedList;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.WidgetCollection;

/**
 * @author Joshua Godi
 */
public class Breadcrumbs extends OrderedList {
    private final WidgetCollection children = new WidgetCollection(this);

    public Breadcrumbs() {
        setStyleName(Styles.BREADCRUMB);
    }

    public Breadcrumbs(final Widget... widgets) {
        this();

        for (final Widget widget : widgets) {
            add(widget);
        }
    }

    @Override
    public void add(final Widget w) {
        super.add(w);
        children.add(w);
    }
}
