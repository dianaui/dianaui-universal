package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GWT Widgets
 * %%
 * Copyright (C) 2014 GWT Widgets
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

import com.google.gwt.user.client.ui.HasText;
import org.gwtbootstrap3.client.ui.constants.HasEnabled;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DropDownButton extends ButtonGroup implements HasText, HasEnabled {

    private final Button button = new Button("...");
    private final DropDownMenu menu = new DropDownMenu();

    public DropDownButton() {
        button.setToggle(Toggle.DROPDOWN);

        add(button);
        add(menu);
    }

    public DropDownButton(String text) {
        this();

        setText(text);
    }

    public Button getToggleButton() {
        return button;
    }

    public DropDownMenu getMenu() {
        return menu;
    }

    @Override
    public String getText() {
        return button.getText();
    }

    @Override
    public void setText(String text) {
        button.setText(text);
    }

    @Override
    public boolean isEnabled() {
        return button.isEnabled();
    }

    @Override
    public void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
    }

}
