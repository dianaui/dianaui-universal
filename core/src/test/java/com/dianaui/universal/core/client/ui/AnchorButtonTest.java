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

import com.dianaui.universal.core.client.ui.constants.IconPosition;
import com.dianaui.universal.core.client.ui.constants.IconType;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
@GwtModule("com.dianaui.universal.core.DianaUICore")
public class AnchorButtonTest extends GwtTest {

    static String TEST_TEXT = "Test";
    static String BOLD_TEXT = "bold";
    static String BOLD_HTML = "<strong>bold</strong>";
    static String ITALIC_HTML = "<em>italic</em>";
    static String FA_ANCHOR_HTML = "<i class=\"fa fa-anchor\"></i>";

    static String getHtml(String content) {
        return "<a class=\"btn btn-default\" href=\"javascript:;\">" + (content != null ? content : "") + "</a>";
    }

    @Test
    public void buttonTextAndHtml() {
        AnchorButton button = new AnchorButton();

        Assert.assertEquals(getHtml(null), button.getElement().toString());
        Assert.assertNull(button.getText());
        Assert.assertNull(button.getHTML());

        button.setText(TEST_TEXT);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(TEST_TEXT, button.getText());
        Assert.assertNull(button.getHTML());
        Assert.assertEquals(getHtml(TEST_TEXT), button.getElement().toString());

        button.setHTML(BOLD_HTML);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(BOLD_HTML, button.getHTML());
        Assert.assertEquals(BOLD_TEXT, button.getText());
        Assert.assertEquals(getHtml("<span>" + BOLD_HTML + "</span>"), button.getElement().toString());

        button.setHTML(null);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals("", button.getHTML());
        Assert.assertEquals("", button.getText());
        Assert.assertEquals(getHtml("<span></span>"), button.getElement().toString());

        button.setHTML(ITALIC_HTML);
        button.setText(null);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(null, button.getHTML());
        Assert.assertEquals(null, button.getText());
        Assert.assertEquals(getHtml(null), button.getElement().toString());
    }

    @Test
    public void buttonIcon() {
        AnchorButton button = new AnchorButton();

        button.setText(TEST_TEXT);
        button.setFontAwesomeIcon(IconType.ANCHOR);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(getHtml(FA_ANCHOR_HTML + " " + TEST_TEXT), button.getElement().toString());

        button.setText(null);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(getHtml(FA_ANCHOR_HTML), button.getElement().toString());

        button.setFontAwesomeIcon(null);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(getHtml(null), button.getElement().toString());

        button.setFontAwesomeIcon(IconType.ANCHOR);
        button.setText(TEST_TEXT);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(getHtml(FA_ANCHOR_HTML + " " + TEST_TEXT), button.getElement().toString());

        button.setIconPosition(IconPosition.RIGHT);
        getBrowserSimulator().fireLoopEnd();
        Assert.assertEquals(getHtml(TEST_TEXT + " " + FA_ANCHOR_HTML), button.getElement().toString());
    }

}
