package com.dianaui.universal.core.client.ui;

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
public class ButtonGroupTest extends GwtTest {

    @Test
    public void clickOnCheckBox() {
        ButtonGroup group = new ButtonGroup();
        CheckBoxButton button1 = new CheckBoxButton("left");
        CheckBoxButton button2 = new CheckBoxButton("right");

        group.add(button1);
        group.add(button2);

        RootPanel.get().add(group);

        try {
            Assert.assertFalse(button1.getValue());
            Assert.assertFalse(button2.getValue());
            assertThat(button1).doesNotHaveStyle(Styles.ACTIVE);
            assertThat(button2).doesNotHaveStyle(Styles.ACTIVE);

            Browser.click(button2);

            Assert.assertFalse(button1.getValue());
            Assert.assertTrue(button2.getValue());
            assertThat(button1).doesNotHaveStyle(Styles.ACTIVE);
            assertThat(button2).hasStyle(Styles.ACTIVE);

            Browser.click(button1);
            Browser.click(button2);

            Assert.assertTrue(button1.getValue());
            Assert.assertFalse(button2.getValue());
            assertThat(button1).hasStyle(Styles.ACTIVE);
            assertThat(button2).doesNotHaveStyle(Styles.ACTIVE);
        } finally {
            group.removeFromParent();
        }
    }

    @Test
    public void clickOnRadio() {
        ButtonGroup group = new ButtonGroup();
        RadioButton button1 = new RadioButton("test");
        RadioButton button2 = new RadioButton("test");

        group.add(button1);
        group.add(button2);

        RootPanel.get().add(group);

        try {
            Assert.assertFalse(button1.getValue());
            Assert.assertFalse(button2.getValue());
            assertThat(button1).doesNotHaveStyle(Styles.ACTIVE);
            assertThat(button2).doesNotHaveStyle(Styles.ACTIVE);

            Browser.click(button2);

            Assert.assertFalse(button1.getValue());
            Assert.assertTrue(button2.getValue());
            assertThat(button1).doesNotHaveStyle(Styles.ACTIVE);
            assertThat(button2).hasStyle(Styles.ACTIVE);
        } finally {
            group.removeFromParent();
        }
    }

}
