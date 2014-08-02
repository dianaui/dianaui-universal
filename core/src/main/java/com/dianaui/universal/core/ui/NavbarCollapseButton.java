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

import com.dianaui.universal.core.ui.base.HasPull;
import com.dianaui.universal.core.ui.base.HasResponsiveness;
import com.dianaui.universal.core.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.ui.constants.DeviceSize;
import com.dianaui.universal.core.ui.constants.Pull;
import com.dianaui.universal.core.ui.constants.Styles;
import com.dianaui.universal.core.ui.constants.Toggle;
import com.dianaui.universal.core.ui.html.Span;
import com.google.gwt.user.client.ui.Composite;

/**
 * Special button to toggle collapsible area of {@link Navbar}.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @see NavbarCollapse
 */
public class NavbarCollapseButton extends Composite implements HasResponsiveness, HasPull {

    private final PullMixin<NavbarCollapseButton> pullMixin = new PullMixin<NavbarCollapseButton>(this);
    private final Button button;

    public NavbarCollapseButton() {
        button = new Button();
        button.setStyleName(Styles.NAVBAR_TOGGLE);
        button.setToggle(Toggle.COLLAPSE);

        button.add(newBarIcon());
        button.add(newBarIcon());
        button.add(newBarIcon());

        initWidget(button);
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    @Override
    public Pull getPull() {
        return pullMixin.getPull();
    }

    @Override
    public void setPull(final Pull pull) {
        pullMixin.setPull(pull);
    }

    private Span newBarIcon() {
        final Span span = new Span();
        span.setStyleName(Styles.ICON_BAR);
        return span;
    }

}
