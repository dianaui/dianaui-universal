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
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;
import org.junit.Assert;
import org.junit.Test;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
@GwtModule("com.dianaui.universal.core.DianaUICore")
public class AlertGwtTest extends GwtTest {

    static String getHtml(String content, String styles, String attributes) {
        styles = styles != null ? " " + styles : "";
        attributes = attributes != null ? " " + attributes : "";
        content = content != null ? content : "";
        return "<div class=\"alert in" + styles + "\"" + attributes + ">" + content + "</div>";
    }

    @Test
    public void defaults() {
        Alert alert = new Alert();
        Assert.assertEquals(getHtml(null, "alert-warning", null), alert.getElement().toString());
        Assert.assertEquals("", alert.getText());
        Assert.assertFalse(alert.isDismissable());
        Assert.assertEquals(AlertType.WARNING, alert.getType());
        Assert.assertEquals(0, alert.getWidgetCount());
        assertThat(alert).hasStyle(Styles.ALERT);
        assertThat(alert).hasStyle(AlertType.WARNING.getCssName());

        alert.setDismissable(true);
        Assert.assertEquals(getHtml("<button type=\"button\" class=\"close\">×</button>", "alert-warning alert-dismissable", null), alert.getElement().toString());

        alert.setDismissable(false);
        alert.setType(AlertType.INFO);
        Assert.assertEquals(AlertType.INFO, alert.getType());
        Assert.assertEquals(getHtml(null, "alert-info", null), alert.getElement().toString());
    }

    @Test
    public void constructors() {
        Alert alert = new Alert("Test");
        Assert.assertEquals(getHtml("Test", "alert-warning", null), alert.getElement().toString());

        alert = new Alert("Test", AlertType.SUCCESS);
        Assert.assertEquals(getHtml("Test", "alert-success", null), alert.getElement().toString());

        alert = new Alert(new SafeHtml() {
            @Override
            public String asString() {
                return "<strong>Test</strong>";
            }
        }, AlertType.SUCCESS);
        Assert.assertEquals(getHtml("<strong>Test</strong>", "alert-success", null), alert.getElement().toString());
    }

}
