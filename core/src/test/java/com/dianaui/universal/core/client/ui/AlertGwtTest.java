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

import com.dianaui.universal.core.client.ui.constants.AlertType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class AlertGwtTest extends TestCore {

    static String getHtml(String content, String styles, String attributes) {
        styles = styles != null ? " " + styles : "";
        attributes = attributes != null ? " " + attributes : "";
        content = content != null ? content : "";
        return "<div class=\"alert in" + styles + "\"" + attributes + ">" + content + "</div>";
    }

    public void testDefaults() {
        Alert alert = new Alert();
        assertEquals(getHtml(null, "alert-warning", null), alert.getElement().toString());
        assertEquals("", alert.getText());
        assertFalse(alert.isDismissable());
        assertEquals(AlertType.WARNING, alert.getType());
        assertEquals(0, alert.getWidgetCount());
        hasStyle(Styles.ALERT, alert);
        hasStyle(AlertType.WARNING.getCssName(), alert);

        alert.setDismissable(true);
        assertEquals(getHtml("<button type=\"button\" class=\"close\">×</button>", "alert-warning alert-dismissable", null), alert.getElement().toString());

        alert.setDismissable(false);
        alert.setType(AlertType.INFO);
        assertEquals(AlertType.INFO, alert.getType());
        assertEquals(getHtml(null, "alert-info", null), alert.getElement().toString());
    }

    public void testConstructors() {
        Alert alert = new Alert("Test");
        assertEquals(getHtml("Test", "alert-warning", null), alert.getElement().toString());

        alert = new Alert("Test", AlertType.SUCCESS);
        assertEquals(getHtml("Test", "alert-success", null), alert.getElement().toString());

        alert = new Alert(new SafeHtml() {
            @Override
            public String asString() {
                return "<strong>Test</strong>";
            }
        }, AlertType.SUCCESS);
        assertEquals(getHtml("<strong>Test</strong>", "alert-success", null), alert.getElement().toString());
    }

}
