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
package com.dianaui.universal.core.client.ui.base;

import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.Pull;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for widgets that contain further widgets.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class ComplexWidget extends ComplexPanel implements HasId, HasResponsiveness, HasInlineStyle, HasPull,
        HasCenterBlock {

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Widget child) {
        add(child, (Element) getElement());
    }

    /**
     * Inserts a widget at a specific index
     *
     * @param child       - widget to be inserted
     * @param beforeIndex - index for the widget
     */
    public void insert(final Widget child, final int beforeIndex) {
        insert(child, (Element) getElement(), beforeIndex, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarginTop(final double margin) {
        getElement().getStyle().setMarginTop(margin, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarginLeft(final double margin) {
        getElement().getStyle().setMarginLeft(margin, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarginRight(final double margin) {
        getElement().getStyle().setMarginRight(margin, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarginBottom(final double margin) {
        getElement().getStyle().setMarginBottom(margin, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaddingTop(final double padding) {
        getElement().getStyle().setPaddingTop(padding, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaddingLeft(final double padding) {
        getElement().getStyle().setPaddingLeft(padding, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaddingRight(final double padding) {
        getElement().getStyle().setPaddingRight(padding, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaddingBottom(final double padding) {
        getElement().getStyle().setPaddingBottom(padding, Style.Unit.PX);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pull getPull() {
        return PullMixin.getPull(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPull(final Pull pull) {
        PullMixin.setPull(this, pull);
    }

    @Override
    public boolean isCenterBlock() {
        return StyleHelper.containsStyle(getStyleName(), Styles.CENTER_BLOCK);
    }

    @Override
    public void setCenterBlock(boolean centerBlock) {
        StyleHelper.toggleStyleName(this, centerBlock, Styles.CENTER_BLOCK);
    }

}
