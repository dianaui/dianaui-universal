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

import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DropDownButtonGwtTest extends TestCore {

    public void testButtonText() {
        DropDownButton button = new DropDownButton();
        assertEquals("...", button.getToggleButton().getText());

        button.setText("new");
        assertEquals("new", button.getToggleButton().getText());

        button = new DropDownButton("Test");
        assertEquals("Test", button.getToggleButton().getText());
    }

    public void testShowMenuOnClick() {
        DropDownButton button = new DropDownButton();
        RootPanel.get().add(button);

        assertTrue(button.isAttached());
        doesNotHaveStyle(Styles.OPEN, button);

        click(button.getToggleButton());
        hasStyle(Styles.OPEN, button);

        click(button.getToggleButton());
        doesNotHaveStyle(Styles.OPEN, button);

        button.removeFromParent();
    }

}
