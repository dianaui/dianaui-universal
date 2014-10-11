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

import com.dianaui.universal.core.client.ui.base.HasSubText;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Div;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Page header with optional subtext
 * <p/>
 * <h3>UiBinder example</h3>
 * <p/>
 * <pre>
 * {@code
 *     <b:PageHeader subText="Some subtext">Page header title</b:PageHeader>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class PanelHeader extends Div implements HasWidgets, HasText, HasSubText {

    private String heading;
    private String subText;

    public PanelHeader() {
        setStyleName(Styles.PANEL_HEADING);
    }

    public PanelHeader(final String text) {
        this();
        setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return heading;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(final String text) {
        heading = text;
        render();
    }

    @Override
    public String getSubText() {
        return subText;
    }

    @Override
    public void setSubText(final String subText) {
        this.subText = subText;
        render();
    }

    private void render() {
        final SafeHtmlBuilder builder = new SafeHtmlBuilder();

        builder.appendHtmlConstant("<h1>");
        builder.appendEscaped(heading == null ? "" : heading);

        if (subText != null && !subText.isEmpty()) {
            builder.appendEscaped(" ");
            builder.appendHtmlConstant("<small>");
            builder.appendEscaped(subText);
            builder.appendHtmlConstant("</small>");
        }

        builder.appendHtmlConstant("</h1>");

        getElement().setInnerSafeHtml(builder.toSafeHtml());
    }

}
