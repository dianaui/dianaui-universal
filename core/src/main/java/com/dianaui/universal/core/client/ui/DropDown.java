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

import com.dianaui.universal.core.client.ui.base.AbstractDropDown;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;

/**
 * Dropdown parent container.
 * <h3>UiBinder example</h3>
 * <pre>{@code
 *     <b:DropDown>
 *         <b:Anchor toggle="DROPDOWN">Click to toggle dropdown</b:Anchor>
 *         <b:DropDownMenu>
 *             <b:AnchorListItem>Action 1</b:AnchorListItem>
 *             <b:AnchorListItem>Action 2</b:AnchorListItem>
 *         </b:DropDownMenu>
 *     </b:DropDown>
 * }</pre>
 *
 * @author Sven Jacobs
 * @see DropDownMenu
 */
public class DropDown extends AbstractDropDown {

    public DropDown() {
        super(Document.get().createDivElement());
        setStyleName(Styles.DROPDOWN);
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
