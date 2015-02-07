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

import com.dianaui.universal.core.client.ui.base.AbstractTextWidget;
import com.dianaui.universal.core.client.ui.constants.ElementTags;
import com.dianaui.universal.core.client.ui.constants.IconType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

/**
 * @author Joshua Godi
 */
public class HelpBlock extends AbstractTextWidget {

    public HelpBlock() {
        super(Document.get().createSpanElement());
        setStyleName(Styles.HELP_BLOCK);
    }

    private IconType iconType = null;

    public void setIconType(IconType type) {
        iconType = type;
    }

    public IconType getIconType() {
        return iconType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(String text) {
        super.setText(text);
        if (iconType != null && text != null && !text.equals("")) {
            Element e = Document.get().createElement(ElementTags.I);
            e.addClassName(Styles.FONT_AWESOME_BASE);
            e.addClassName(iconType.getCssName());
            e.getStyle().setPaddingRight(5, Style.Unit.PX);
            getElement().insertFirst(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return super.getText();
    }

}
