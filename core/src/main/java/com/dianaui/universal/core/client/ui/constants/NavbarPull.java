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
package com.dianaui.universal.core.client.ui.constants;

import com.dianaui.universal.core.client.ui.base.helper.EnumHelper;
import com.google.gwt.dom.client.Style;

/**
 * @author Sven Jacobs
 * @author Joshua Godi
 */
public enum NavbarPull implements Style.HasCssName {

    NONE(""),
    LEFT(Styles.NAVBAR_LEFT),
    RIGHT(Styles.NAVBAR_RIGHT);

    private final String cssClass;

    private NavbarPull(final String cssClass) {
        this.cssClass = cssClass;
    }

    public static NavbarPull fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, NavbarPull.class, NONE);
    }

    public static NavbarPull fromPull(final Pull pull) {
        NavbarPull navbarPull;

        if (pull == Pull.LEFT) {
            return NavbarPull.LEFT;
        }

        return NavbarPull.RIGHT;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public Pull toPull() {
        if (this == NavbarPull.NONE) {
            return Pull.NONE;
        }

        return this == NavbarPull.RIGHT ? Pull.RIGHT : Pull.LEFT;
    }

}
