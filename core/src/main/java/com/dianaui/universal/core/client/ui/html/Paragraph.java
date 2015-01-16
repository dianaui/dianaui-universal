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
package com.dianaui.universal.core.client.ui.html;

import com.dianaui.universal.core.client.ui.base.HasAlignment;
import com.dianaui.universal.core.client.ui.base.HasEmphasis;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.HTMLMixin;
import com.dianaui.universal.core.client.ui.base.mixin.TextMixin;
import com.dianaui.universal.core.client.ui.constants.Alignment;
import com.dianaui.universal.core.client.ui.constants.Emphasis;
import com.dianaui.universal.core.client.ui.gwt.HTMLPanel;
import com.google.gwt.dom.client.ParagraphElement;

/**
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Paragraph extends HTMLPanel implements HasAlignment, HasEmphasis {

    public Paragraph() {
        super(ParagraphElement.TAG, "");
    }

    public Paragraph(final String html) {
        super(ParagraphElement.TAG, html);
    }

    public String getText() {
        return TextMixin.getText(this);
    }

    public void setText(final String text) {
        TextMixin.setText(this, text);
    }

    public String getHTML() {
        return HTMLMixin.getHTML(this);
    }

    public void setHTML(final String html) {
        HTMLMixin.setHTML(this, html);
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
