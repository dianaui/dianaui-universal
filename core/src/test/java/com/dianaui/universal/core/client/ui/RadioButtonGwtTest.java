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

import com.dianaui.universal.core.client.ui.constants.*;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class RadioButtonGwtTest extends TestCore {

    static String getHtml(String content, String styles, String attributes) {
        styles = styles != null ? " " + styles : "";
        attributes = attributes != null ? " " + attributes : "";
        content = content != null ? content : "";
        return "<div class=\"btn" + styles + "\"" + attributes + "><input type=\"radio\" value=\"on\" name=\"test\" tabindex=\"0\"></input>" + content + "</div>";
    }

    public void testDefaults() {
        RadioButton button = new RadioButton("test");
        assertEquals(getHtml(null, ButtonType.DEFAULT.getCssName(), null), button.getElement().toString());
        assertNull(button.getHTML());
        assertNull(button.getText());
        assertNull(button.getToggle());
        assertEquals(ButtonType.DEFAULT, button.getType());
        assertEquals(ButtonSize.DEFAULT, button.getSize());
        assertEquals(0, button.getWidgetCount());
        assertNull(button.getFontAwesomeIcon());
        assertNull(button.getGlyphicon());
        hasStyle(Styles.BTN, button);

        button.setFontAwesomeIcon(IconType.ANCHOR);
        button.setHTML("<strong>anchor</strong>");
        assertEquals(getHtml("<i class=\"fa fa-anchor\"></i> <span><strong>anchor</strong></span>", "btn-default", null),
                button.getElement().toString());

        button.setIconPosition(IconPosition.RIGHT);
        assertEquals(getHtml("<span><strong>anchor</strong></span> <i class=\"fa fa-anchor\"></i>", "btn-default", null),
                button.getElement().toString());

        button.setIconPosition(IconPosition.LEFT);
        assertEquals(getHtml("<i class=\"fa fa-anchor\"></i> <span><strong>anchor</strong></span>", "btn-default", null),
                button.getElement().toString());

        button.setFontAwesomeIcon(IconType.LIST);
        button.setText("list");
        button.setType(ButtonType.DANGER);
        button.setSize(ButtonSize.LARGE);
        assertEquals(getHtml("<i class=\"fa fa-list\"></i> list", "btn-danger btn-lg", null),
                button.getElement().toString());
    }

}
