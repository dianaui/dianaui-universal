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

import com.dianaui.universal.core.client.ui.base.ComplexWidget;
import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.NavbarPull;
import com.dianaui.universal.core.client.ui.constants.Pull;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Sven Jacobs
 * @author Joshua Godi
 * @see NavbarLink
 */
public class NavbarText extends ComplexWidget implements HasResponsiveness {

    public NavbarText() {
        setElement(Document.get().createPElement());
        setStyleName(Styles.NAVBAR_TEXT);
    }

    @Override
    public Pull getPull() {
        return NavbarPull.fromStyleName(getStyleName()).toPull();
    }

    @Override
    public void setPull(final Pull pull) {
        StyleHelper.addUniqueEnumStyleName(this, NavbarPull.class, NavbarPull.fromPull(pull));
    }

    @Override
    public void add(final Widget child) {
        add(child, (Element) getElement());
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

}
