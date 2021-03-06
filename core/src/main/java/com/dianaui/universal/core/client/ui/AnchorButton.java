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

import com.dianaui.universal.core.client.ui.base.HasHref;
import com.dianaui.universal.core.client.ui.base.button.AbstractToggleButton;
import com.dianaui.universal.core.client.ui.constants.ButtonType;
import com.dianaui.universal.core.client.ui.constants.GlyphiconType;
import com.dianaui.universal.core.client.ui.constants.IconType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Button based on {@code <a>} element.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see Button
 * @see AbstractToggleButton
 */
public class AnchorButton extends AbstractToggleButton implements HasHref {

    public AnchorButton() {
        this(ButtonType.DEFAULT);
    }

    public AnchorButton(final String text) {
        this();
        setText(text);
    }

    public AnchorButton(final String text, final String href) {
        this(text);
        setHref(href);
    }

    public AnchorButton(final String text, final ClickHandler clickHandler) {
        this(text);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorButton(final String text, final IconType iconType) {
        this(text);
        setFontAwesomeIcon(iconType);
    }

    public AnchorButton(final String text, final GlyphiconType iconType) {
        this(text);
        setGlyphicon(iconType);
    }

    public AnchorButton(final String text, final IconType iconType, final ClickHandler clickHandler) {
        this(text, iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorButton(final String text, final GlyphiconType iconType, final ClickHandler clickHandler) {
        this(text, iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorButton(final String text, final IconType iconType, final String href) {
        this(text, iconType);
        setHref(href);
    }

    public AnchorButton(final String text, final GlyphiconType iconType, final String href) {
        this(text, iconType);
        setHref(href);
    }

    public AnchorButton(final IconType iconType) {
        this();
        setFontAwesomeIcon(iconType);
    }

    public AnchorButton(final GlyphiconType iconType) {
        this();
        setGlyphicon(iconType);
    }

    public AnchorButton(final IconType iconType, final ClickHandler clickHandler) {
        this(iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorButton(final GlyphiconType iconType, final ClickHandler clickHandler) {
        this(iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorButton(final IconType iconType, final String href) {
        this(iconType);
        setHref(href);
    }

    public AnchorButton(final GlyphiconType iconType, final String href) {
        this(iconType);
        setHref(href);
    }

    public AnchorButton(final ButtonType type) {
        super(type);
        setHref(EMPTY_HREF);
    }

    @Override
    public String getHref() {
        return getAnchorElement().getHref();
    }

    @Override
    public void setHref(final String href) {
        if (href == null) {
            getAnchorElement().removeAttribute(HREF);
        } else {
            getAnchorElement().setHref(href);
        }
    }

    @Override
    public boolean isEnabled() {
        return getStyleName().contains(Styles.DISABLED);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        if (enabled) {
            removeStyleName(Styles.DISABLED);
        } else {
            addStyleName(Styles.DISABLED);
        }
    }

    @Override
    protected Element createElement() {
        return Document.get().createAnchorElement();
    }

    private AnchorElement getAnchorElement() {
        return AnchorElement.as(getElement());
    }

}
