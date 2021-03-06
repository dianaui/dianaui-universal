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
package com.dianaui.universal.core.client.ui.base.mixin;

import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class AttributeMixin {

    /**
     * Sets the attribute on the UiObject
     *
     * @param attributeName  attribute name
     * @param attributeValue attribute value
     */
    public static void setAttribute(final UIObject uiObject, final String attributeName, final String attributeValue) {
        uiObject.getElement().setAttribute(attributeName, attributeValue);
    }

    /**
     * Get the attribute name on the UiObject
     *
     * @param attributeName attribute name
     * @return attribute value
     */
    public static String getAttribute(final UIObject uiObject, final String attributeName) {
        return uiObject.getElement().getAttribute(attributeName);
    }

    /**
     * Removes the attribute from the UiObject
     *
     * @param attributeName attribute name
     */
    public static void removeAttribute(final UIObject uiObject, final String attributeName) {
        uiObject.getElement().removeAttribute(attributeName);
    }

    /**
     * Checks whether or not the UiObject has the element
     *
     * @param attributeName attribute name
     * @return true if has the attribute, false otherwise
     */
    public static boolean hasAttribute(final UIObject uiObject, final String attributeName) {
        return uiObject.getElement().hasAttribute(attributeName);
    }

}
