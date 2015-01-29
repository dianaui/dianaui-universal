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
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import org.junit.Assert;
import org.junit.Test;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
@GwtModule("com.dianaui.universal.core.DianaUICore")
public class CheckBoxButtonGwtTest extends GwtTest {

    static String getHtml(String content, String styles, String attributes) {
        styles = styles != null ? " " + styles : "";
        attributes = attributes != null ? " " + attributes : "";
        content = content != null ? content : "";
        return "<div class=\"btn" + styles + "\"" + attributes + "><input type=\"checkbox\" value=\"on\"></input>" + content + "</div>";
    }

    @Test
    public void defaults() {
        CheckBoxButton button = new CheckBoxButton();
        Assert.assertEquals(getHtml(null, ButtonType.DEFAULT.getCssName(), null), button.getElement().toString());
        Assert.assertNull(button.getHTML());
        Assert.assertNull(button.getText());
        Assert.assertNull(button.getToggle());
        Assert.assertEquals(ButtonType.DEFAULT, button.getType());
        Assert.assertEquals(ButtonSize.DEFAULT, button.getSize());
        Assert.assertEquals(0, button.getWidgetCount());
        Assert.assertNull(button.getFontAwesomeIcon());
        Assert.assertNull(button.getGlyphicon());
        assertThat(button).hasStyle(Styles.BTN);

        button.setFontAwesomeIcon(IconType.ANCHOR);
        button.setHTML("<strong>anchor</strong>");
        Assert.assertEquals(getHtml("<i class=\"fa fa-anchor\"></i> <span><strong>anchor</strong></span>", "btn-default", null),
                button.getElement().toString());

        button.setIconPosition(IconPosition.RIGHT);
        Assert.assertEquals(getHtml("<span><strong>anchor</strong></span> <i class=\"fa fa-anchor\"></i>", "btn-default", null),
                button.getElement().toString());

        button.setIconPosition(IconPosition.LEFT);
        Assert.assertEquals(getHtml("<i class=\"fa fa-anchor\"></i> <span><strong>anchor</strong></span>", "btn-default", null),
                button.getElement().toString());

        button.setFontAwesomeIcon(IconType.LIST);
        button.setText("list");
        button.setType(ButtonType.DANGER);
        button.setSize(ButtonSize.LARGE);
        Assert.assertEquals(getHtml("<i class=\"fa fa-list\"></i> list", "btn-danger btn-lg", null),
                button.getElement().toString());
    }

}
