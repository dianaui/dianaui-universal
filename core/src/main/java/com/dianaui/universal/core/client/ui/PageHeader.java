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

import com.dianaui.universal.core.client.ui.constants.HeadingSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Div;
import com.google.gwt.user.client.ui.HasHTML;

/**
 * Page header with optional subtext
 * <h3>UiBinder example</h3>
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
public class PageHeader extends Div implements HasHTML {

    private Heading heading = new Heading(HeadingSize.H1);

    public PageHeader() {
        setStyleName(Styles.PAGE_HEADER);
        add(heading);
    }

    /**
     * Creates a Heading with the passed in size and text.
     *
     * @param text text for the heading
     */
    public PageHeader(final String text) {
        this();
        setText(text);
    }

    /**
     * Creates a Heading with the passed in size and text.
     *
     * @param text    text for the heading
     * @param subtext subtext for the heading
     */
    public PageHeader(final String text, final String subtext) {
        this(text);
        setSubText(subtext);
    }

    public Heading getHeading() {
        return heading;
    }

    /**
     * @return subtext of the heading
     */
    public String getSubText() {
        return heading.getText();
    }

    /**
     * @param subtext the subtext of the heading
     */
    public void setSubText(final String subtext) {
        heading.setSubText(subtext);
    }

    @Override
    public String getText() {
        return heading.getText();
    }

    @Override
    public void setText(final String text) {
        heading.setText(text);
    }

    @Override
    public String getHTML() {
        return heading.getHTML();
    }

    @Override
    public void setHTML(final String html) {
        heading.setHTML(html);
    }

}
