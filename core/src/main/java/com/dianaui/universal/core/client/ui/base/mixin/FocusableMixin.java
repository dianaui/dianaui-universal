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

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class FocusableMixin {

    public static int getTabIndex(final UIObject uiObject) {
        return uiObject.getElement().getTabIndex();
    }

    public static void setTabIndex(final UIObject uiObject, final int index) {
        uiObject.getElement().setTabIndex(index);
    }

    public static void setAccessKey(final UIObject uiObject, final char key) {
        final Element element = uiObject.getElement();
        final String accessKey = Character.toString(key);

        if (AnchorElement.is(element)) {
            AnchorElement.as(element).setAccessKey(accessKey);
        } else if (ButtonElement.is(element)) {
            ButtonElement.as(element).setAccessKey(accessKey);
        } else if (InputElement.is(element)) {
            InputElement.as(element).setAccessKey(accessKey);
        }
    }

    public static void setFocus(final UIObject uiObject, final boolean focused) {
        if (focused) {
            uiObject.getElement().focus();
        } else {
            uiObject.getElement().blur();
        }
    }

}
