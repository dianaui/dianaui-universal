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
package com.dianaui.universal.core.client.ui.gwt;

import com.dianaui.universal.core.client.ui.base.HasId;
import com.dianaui.universal.core.client.ui.base.HasInlineStyle;
import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * @author Sven Jacobs
 * @author Grant Slender
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class HTMLPanel extends com.google.gwt.user.client.ui.HTMLPanel implements HasId, HasResponsiveness,
        HasInlineStyle {

    public HTMLPanel(final String html) {
        super(html);
    }

    public HTMLPanel(final SafeHtml safeHtml) {
        super(safeHtml);
    }

    public HTMLPanel(final String tag, final String html) {
        super(tag, html);
    }

    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
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
    public void setMarginTop(final double margin) {
        getElement().getStyle().setMarginTop(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginLeft(final double margin) {
        getElement().getStyle().setMarginLeft(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginRight(final double margin) {
        getElement().getStyle().setMarginRight(margin, Style.Unit.PX);
    }

    @Override
    public void setMarginBottom(final double margin) {
        getElement().getStyle().setMarginBottom(margin, Style.Unit.PX);
    }

    @Override
    public void setPaddingTop(final double padding) {
        getElement().getStyle().setPaddingTop(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingLeft(final double padding) {
        getElement().getStyle().setPaddingLeft(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingRight(final double padding) {
        getElement().getStyle().setPaddingRight(padding, Style.Unit.PX);
    }

    @Override
    public void setPaddingBottom(final double padding) {
        getElement().getStyle().setPaddingBottom(padding, Style.Unit.PX);
    }

}
