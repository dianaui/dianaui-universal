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
import com.dianaui.universal.core.client.ui.html.Div;
import com.dianaui.universal.core.client.ui.html.Text;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Page header with optional subtext
 * <p/>
 * <h3>UiBinder example</h3>
 * <p/>
 * <pre>
 * {@code
 *     <b:PanelHeader>Panel header title</b:PageHeader>
 * }
 * </pre>
 * <pre>
 * {@code
 *     <b:PanelHeader>
 *         <b:Heading size="H1" text="Heading Text" subText="Subtext Text"/>
 *     </b:PageHeader>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see Heading
 */
public class PanelHeader extends Div implements HasWidgets, HasText {

    private Text text;

    public PanelHeader() {
        setStyleName(Styles.PANEL_HEADING);
    }

    public PanelHeader(final String text) {
        this();
        setText(text);
    }

    public PanelHeader(final SafeHtml safeHtml) {
        this(safeHtml.asString());
    }

    public void setHtml(final SafeHtml safeHtml) {
        setText(safeHtml.asString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        if (text != null)
            return text.getText();
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(final String text) {
        if (this.text == null)
            this.text = new Text();
        this.text.setText(text);
        insert(this.text, 0);
    }

}
