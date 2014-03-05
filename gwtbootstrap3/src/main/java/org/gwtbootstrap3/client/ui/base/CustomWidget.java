package org.gwtbootstrap3.client.ui.base;

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

import com.google.gwt.user.client.DOM;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class CustomWidget extends ComplexWidget {

    /**
     * Creates a new widget that is based on the provided html tag.
     *
     * @param tag the html tag used for this widget
     */
    public CustomWidget(String tag) {
        setElement(DOM.createElement(tag));
    }

}
