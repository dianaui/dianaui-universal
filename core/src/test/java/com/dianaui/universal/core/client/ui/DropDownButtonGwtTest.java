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
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import com.googlecode.gwt.test.utils.events.Browser;
import org.junit.Assert;
import org.junit.Test;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
@GwtModule("com.dianaui.universal.core.DianaUICore")
public class DropDownButtonGwtTest extends GwtTest {

    @Test
    public void buttonText() {
        DropDownButton button = new DropDownButton();
        getBrowserSimulator().fireLoopEnd();
        Assert.assertTrue(button.getToggleButton().getText().equals("..."));

        button.setText("new");
        getBrowserSimulator().fireLoopEnd();
        Assert.assertTrue(button.getToggleButton().getText().equals("new"));

        button = new DropDownButton("Test");
        getBrowserSimulator().fireLoopEnd();
        Assert.assertTrue(button.getToggleButton().getText().equals("Test"));
    }

    @Test
    public void showMenuOnClick() {
        DropDownButton button = new DropDownButton();
        RootPanel.get().add(button);

        assertThat(button).isAttached();
        assertThat(button).doesNotHaveStyle(Styles.OPEN);

        Browser.click(button.getToggleButton());
        assertThat(button).hasStyle(Styles.OPEN);

        Browser.click(button.getToggleButton());
        assertThat(button).doesNotHaveStyle(Styles.OPEN);

        button.removeFromParent();
    }

}
