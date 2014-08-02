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
package com.dianaui.universal.core.ui.html;

import com.dianaui.universal.core.ui.base.HasAlignment;
import com.dianaui.universal.core.ui.base.HasEmphasis;
import com.dianaui.universal.core.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.ui.base.mixin.HTMLMixin;
import com.dianaui.universal.core.ui.constants.Alignment;
import com.dianaui.universal.core.ui.constants.Emphasis;
import com.dianaui.universal.core.ui.gwt.HTMLPanel;
import com.google.gwt.dom.client.ParagraphElement;

/**
 * @author Sven Jacobs
 */
public class Paragraph extends HTMLPanel implements HasAlignment, HasEmphasis {

    private final HTMLMixin<Paragraph> textMixin = new HTMLMixin<Paragraph>(this);

    public Paragraph() {
        super(ParagraphElement.TAG, "");
    }

    public Paragraph(final String html) {
        this();
        setHTML(html);
    }

    public String getText() {
        return textMixin.getText();
    }

    public void setText(final String text) {
        textMixin.setText(text);
    }

    public String getHTML() {
        return textMixin.getHTML();
    }

    public void setHTML(final String html) {
        textMixin.setHTML(html);
    }

    @Override
    public Alignment getAlignment() {
        return Alignment.fromStyleName(getStyleName());
    }

    @Override
    public void setAlignment(final Alignment alignment) {
        StyleHelper.addUniqueEnumStyleName(this, Alignment.class, alignment);
    }

    @Override
    public Emphasis getEmphasis() {
        return Emphasis.fromStyleName(getStyleName());
    }

    @Override
    public void setEmphasis(final Emphasis emphasis) {
        StyleHelper.addUniqueEnumStyleName(this, Emphasis.class, emphasis);
    }

}
