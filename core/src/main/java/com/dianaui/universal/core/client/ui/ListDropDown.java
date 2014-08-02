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

import com.dianaui.universal.core.client.ui.base.AbstractListItem;
import com.dianaui.universal.core.client.ui.base.button.AbstractToggleButton;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Drop down item within a list, e.g. {@link NavTabs}, {@link NavPills} or
 * {@link Navbar}.
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 *     <b:NavTabs>
 *         <b:AnchorListItem active="true">Item 1</b:AnchorListItem>
 *         <b:AnchorListItem>Item 2</b:AnchorListItem>
 *         <b:ListDropDown>
 *             <b:AnchorButton toggle="DROPDOWN">Dropdown</b:AnchorButton>
 *             <b:DropDownMenu>
 *                 <b:AnchorListItem>Dropdown 1</b:AnchorListItem>
 *                 <b:AnchorListItem>Dropdown 2</b:AnchorListItem>
 *                 <b:AnchorListItem>Dropdown 3</b:AnchorListItem>
 *             </b:DropDownMenu>
 *         </b:ListDropDown>
 *         <b:AnchorListItem>Item 3</b:AnchorListItem>
 *     </b:NavTabs>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see NavTabs
 * @see NavPills
 * @see Navbar
 */
public class ListDropDown extends AbstractListItem {

    public ListDropDown() {
        setStyleName(Styles.DROPDOWN);
    }

    @Override
    public void add(final Widget child) {
        if (child instanceof AbstractToggleButton) {
            if (!(child instanceof AnchorButton)) {
                throw new IllegalArgumentException("Only buttons of type AnchorButton can be added to ListDropDown");
            }

            child.setStyleName(Styles.DROPDOWN_TOGGLE);
        }

        add(child, (Element) getElement());
    }

    public void toggle() {
        if (isOpen()) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        addStyleName(Styles.OPEN);
    }

    public void hide() {
        removeStyleName(Styles.OPEN);
    }

    public boolean isOpen() {
        return StyleHelper.containsStyle(getStyleName(), Styles.OPEN);
    }

}
