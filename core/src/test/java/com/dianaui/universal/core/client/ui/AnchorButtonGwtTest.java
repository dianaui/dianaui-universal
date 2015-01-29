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

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class AnchorButtonGwtTest extends TestCore {

    static String TEST_TEXT = "Test";
    static String BOLD_TEXT = "bold";
    static String BOLD_HTML = "<strong>bold</strong>";
    static String ITALIC_HTML = "<em>italic</em>";
    static String FA_ANCHOR_HTML = "<i class=\"fa fa-anchor\"></i>";

    static String getHtml(String content) {
        return "<a class=\"btn btn-default\" href=\"javascript:;\">" + (content != null ? content : "") + "</a>";
    }

    public void testButtonTextAndHtml() {
        AnchorButton button = new AnchorButton();

        assertEquals(getHtml(null), button.getElement().toString());
        assertNull(button.getText());
        assertNull(button.getHTML());

        button.setText(TEST_TEXT);
        assertEquals(TEST_TEXT, button.getText());
        assertNull(button.getHTML());
        assertEquals(getHtml(TEST_TEXT), button.getElement().toString());

        button.setHTML(BOLD_HTML);
        assertEquals(BOLD_HTML, button.getHTML());
        assertEquals(BOLD_TEXT, button.getText());
        assertEquals(getHtml("<span>" + BOLD_HTML + "</span>"), button.getElement().toString());

        button.setHTML(null);
        assertEquals("", button.getHTML());
        assertEquals("", button.getText());
        assertEquals(getHtml("<span></span>"), button.getElement().toString());

        button.setHTML(ITALIC_HTML);
        button.setText(null);
        assertEquals(null, button.getHTML());
        assertEquals(null, button.getText());
        assertEquals(getHtml(null), button.getElement().toString());
    }

    public void testButtonIcon() {
        AnchorButton button = new AnchorButton();

        button.setText(TEST_TEXT);
        button.setFontAwesomeIcon(IconType.ANCHOR);
        assertEquals(getHtml(FA_ANCHOR_HTML + " " + TEST_TEXT), button.getElement().toString());

        button.setText(null);
        assertEquals(getHtml(FA_ANCHOR_HTML), button.getElement().toString());

        button.setFontAwesomeIcon(null);
        assertEquals(getHtml(null), button.getElement().toString());

        button.setFontAwesomeIcon(IconType.ANCHOR);
        button.setText(TEST_TEXT);
        assertEquals(getHtml(FA_ANCHOR_HTML + " " + TEST_TEXT), button.getElement().toString());

        button.setIconPosition(IconPosition.RIGHT);
        assertEquals(getHtml(TEST_TEXT + " " + FA_ANCHOR_HTML), button.getElement().toString());
    }

}
