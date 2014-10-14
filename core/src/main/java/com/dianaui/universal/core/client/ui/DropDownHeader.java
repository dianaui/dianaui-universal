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

import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.HTMLMixin;
import com.dianaui.universal.core.client.ui.base.mixin.TextMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * Header element within {@link DropDownMenu}
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 *     <b:DropDownMenu>
 *         <b:DropDownHeader>Header 1</b:DropDownHeader>
 *         <b:AnchorListItem>Action 1</b:AnchorListItem>
 *         <b:AnchorListItem>Action 2</b:AnchorListItem>
 *         <b:DropDownHeader>Header 2</b:DropDownHeader>
 *         <b:AnchorListItem>Action 3</b:AnchorListItem>
 *         <b:AnchorListItem>Action 4</b:AnchorListItem>
 *     </b:DropDownMenu>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DropDownHeader extends Widget implements HasHTML, HasResponsiveness {

    public DropDownHeader() {
        setElement(Document.get().createLIElement());
        setStyleName(Styles.DROPDOWN_HEADER);
    }

    public DropDownHeader(final String text) {
        this();
        setText(text);
    }

    @Override
    public String getText() {
        return TextMixin.getText(this);
    }

    @Override
    public void setText(final String text) {
        TextMixin.setText(this, text);
    }

    @Override
    public String getHTML() {
        return HTMLMixin.getHTML(this);
    }

    @Override
    public void setHTML(final String html) {
        HTMLMixin.setHTML(this, html);
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
