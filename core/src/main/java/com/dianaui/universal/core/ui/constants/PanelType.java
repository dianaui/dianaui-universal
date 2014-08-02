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
package com.dianaui.universal.core.ui.constants;

import com.dianaui.universal.core.ui.base.helper.EnumHelper;
import com.google.gwt.dom.client.Style;

/**
 * @author Joshua Godi
 */
public enum PanelType implements Type, Style.HasCssName {
    DEFAULT("panel-default"),
    PRIMARY("panel-primary"),
    SUCCESS("panel-success"),
    INFO("panel-info"),
    WARNING("panel-warning"),
    DANGER("panel-danger");

    private final String cssClass;

    private PanelType(final String cssClass) {
        this.cssClass = cssClass;
    }

    public static PanelType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, PanelType.class, DEFAULT);
    }

    @Override
    public String getCssName() {
        return cssClass;
    }
}
