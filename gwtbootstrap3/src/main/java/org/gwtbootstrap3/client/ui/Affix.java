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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.UIObject;

/**
 * An Affix is an element/container that gets "pinned" as soon as a certain
 * amount of pixels have been scrolled.
 * <p/>
 * Any element/container can become an Affix. Usually used for sidebar
 * navigation.
 * <p/>
 * <strong>Note:</strong> Bootstrap adds/removes classes from Affix based on
 * scroll position which requires custom styling. See Bootstrap's <a
 * href="http://getbootstrap.com/javascript/#affix">documentation</a>.
 *
 * @author Sven Jacobs
 */
public class Affix {

    /**
     * Applys affix functionality to specified element.
     *
     * @param element Element to "affixnize"
     */
    public static void affix(final Element element) {
        // TODO
        // internalAffix(element, 10);
    }

    /**
     * Applys affix functionality to specified element.
     *
     * @param element Element to "affixnize"
     * @param offset  Offset of affix
     */
    public static void affix(final Element element, final int offset) {
        // TODO
        // internalAffix(element, offset);
    }

    /**
     * Applys affix functionality to specified object.
     *
     * @param object Object to "affixnize"
     */
    public static void affix(final UIObject object) {
        affix(object.getElement());
    }

    /**
     * Applys affix functionality to specified object.
     *
     * @param object Object to "affixnize"
     * @param offset Offset of affix
     */
    public static void affix(final UIObject object, final int offset) {
        affix(object.getElement(), offset);
    }

}
