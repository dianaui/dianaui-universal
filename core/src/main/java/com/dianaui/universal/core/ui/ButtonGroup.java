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

import com.dianaui.universal.core.ui.base.AbstractButtonGroup;
import com.dianaui.universal.core.ui.constants.Styles;

/**
 * Button group containing multiple buttons.
 * <h3>UiBinder example</h3>
 * <pre>{@code
 *     <b:ButtonGroup>
 *         <b:Button>Button 1</b:Button>
 *         <b:Button>Button 2</b:Button>
 *         <b:Button>Button 3</b:Button>
 *     </b:ButtonGroup>
 * }</pre>
 * Is also a container for dropdown buttons:
 * <pre>{@code
 *     <b:ButtonGroup>
 *         <b:Button toggle="DROPDOWN">Dropdown</b:Button>
 *         <b:DropDownMenu>
 *             <b:AnchorListItem>ListItem 1</b:AnchorListItem>
 *             <b:AnchorListItem>ListItem 2</b:AnchorListItem>
 *             <b:AnchorListItem>ListItem 3</b:AnchorListItem>
 *         </b:DropDownMenu>
 *     </b:ButtonGroup>
 * }</pre>
 *
 * @author Sven Jacobs
 * @see Button
 * @see VerticalButtonGroup
 */
public class ButtonGroup extends AbstractButtonGroup {

    public ButtonGroup() {
        super(Styles.BTN_GROUP);
    }

}
