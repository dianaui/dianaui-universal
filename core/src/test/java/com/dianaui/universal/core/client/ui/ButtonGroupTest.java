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

import com.dianaui.universal.core.client.ui.constants.ButtonGroupSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.constants.Toggle;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Event;
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

    // TODO toggle attr helper
    String DATA_TOGGLE_BUTTONS = "data-toggle=\"" + Toggle.BUTTONS.getToggle() + "\"";

    static String getHtml(String content, String styles, String attributes) {
        return "<div class=\"" + Styles.BTN_GROUP + (styles != null ? " " + styles : "") + "\"" +
                (attributes != null ? " " + attributes : "") + ">" + (content != null ? content : "") + "</div>";
    }

    @Test
    public void defaults() {
        ButtonGroup group = new ButtonGroup();
        assertThat(group).hasStyle(Styles.BTN_GROUP);
        Assert.assertFalse(group.isOpen());
        Assert.assertFalse(group.isJustified());
        Assert.assertEquals(getHtml(null, null, null), group.getElement().toString());

        group.setJustified(true);
        Assert.assertEquals(getHtml(null, Styles.BTN_GROUP_JUSTIFIED, null), group.getElement().toString());

        group.setJustified(false);
        group.setToggle(Toggle.BUTTONS);
        Assert.assertEquals(getHtml(null, null, DATA_TOGGLE_BUTTONS), group.getElement().toString());

        group.setToggle(null);
        group.setSize(ButtonGroupSize.EXTRA_SMALL);
        Assert.assertEquals(getHtml(null, ButtonGroupSize.EXTRA_SMALL.getCssName(), null), group.getElement().toString());

        group.setSize(ButtonGroupSize.DEFAULT);
        group.setDropUp(true);
        Assert.assertEquals(getHtml(null, Styles.DROP_UP, null), group.getElement().toString());

        group.setDropUp(false);
        group.show();
        Assert.assertEquals(getHtml(null, Styles.OPEN, null), group.getElement().toString());
        Assert.assertTrue(group.isOpen());

        group.hide();
        Assert.assertEquals(getHtml(null, null, null), group.getElement().toString());
    }

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

            // emulate click event on radio button2
            button1.onBrowserEvent((Event) Document.get().createClickEvent(0, 0, 0, 0, 0, false, false, false, false));
            button2.setValue(false, false);

            Assert.assertTrue(button1.getValue());
            Assert.assertFalse(button2.getValue());
            assertThat(button1).hasStyle(Styles.ACTIVE);
            assertThat(button2).doesNotHaveStyle(Styles.ACTIVE);
        } finally {
            group.removeFromParent();
        }
    }

}
