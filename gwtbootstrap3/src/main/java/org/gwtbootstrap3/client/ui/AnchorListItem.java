package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GWT Widgets
 * %%
 * Copyright (C) 2014 GWT Widgets
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

import com.google.gwt.event.dom.client.ClickHandler;
import org.gwtbootstrap3.client.ui.base.AbstractAnchorListItem;
import org.gwtbootstrap3.client.ui.constants.GlyphiconType;
import org.gwtbootstrap3.client.ui.constants.IconType;

/**
 * Represents a list item with text contents which is used in multiple widgets.
 * <h3>Widgets using ListItem</h3>
 * <ul>
 * <li>{@link DropDownMenu}</li>
 * <li>{@link NavTabs}</li>
 * <li>{@link NavPills}</li>
 * <li>{@link Navbar}</li>
 * </ul>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see DropDownMenu
 * @see NavTabs
 * @see NavPills
 * @see Navbar
 */
public class AnchorListItem extends AbstractAnchorListItem implements com.google.gwt.user.client.ui.HasText {

    public AnchorListItem() {
    }

    public AnchorListItem(final String text) {
        this(text, false);
    }

    public AnchorListItem(final String text, final boolean html) {
        if (html) {
            setHTML(text);
        } else {
            setText(text);
        }
    }

    public AnchorListItem(final String text, final String href) {
        this(text, false, href);
    }

    public AnchorListItem(final String text, final boolean html, final String href) {
        this(text, html);
        setHref(href);
    }

    public AnchorListItem(final String text, final ClickHandler clickHandler) {
        this(text, false, clickHandler);
    }

    public AnchorListItem(final String text, final boolean html, final ClickHandler clickHandler) {
        this(text, html);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorListItem(final String text, final IconType iconType) {
        this(text, false, iconType);
    }

    public AnchorListItem(final String text, final boolean html, final IconType iconType) {
        this(text, html);
        setFontAwesomeIcon(iconType);
    }

    public AnchorListItem(final String text, final GlyphiconType iconType) {
        this(text, false, iconType);
    }

    public AnchorListItem(final String text, final boolean html, final GlyphiconType iconType) {
        this(text, html);
        setGlyphicon(iconType);
    }

    public AnchorListItem(final String text, final IconType iconType, final ClickHandler clickHandler) {
        this(text, false, iconType, clickHandler);
    }

    public AnchorListItem(final String text, final boolean html, final IconType iconType,
                          final ClickHandler clickHandler) {
        this(text, html, iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorListItem(final String text, final GlyphiconType iconType, final ClickHandler clickHandler) {
        this(text, false, iconType, clickHandler);
    }

    public AnchorListItem(final String text, final boolean html, final GlyphiconType iconType,
                          final ClickHandler clickHandler) {
        this(text, html, iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorListItem(final String text, final IconType iconType, final String href) {
        this(text, false, iconType, href);
    }

    public AnchorListItem(final String text, final boolean html, final IconType iconType, final String href) {
        this(text, html, iconType);
        setHref(href);
    }

    public AnchorListItem(final String text, final GlyphiconType iconType, final String href) {
        this(text, false, iconType, href);
    }

    public AnchorListItem(final String text, final boolean html, final GlyphiconType iconType, final String href) {
        this(text, html, iconType);
        setHref(href);
    }

    @Override
    public void setText(final String text) {
        anchor.setText(text);
    }

    public String getHTML() {
        return anchor.getHTML();
    }

    public void setHTML(final String html) {
        anchor.setHTML(html);
    }

    @Override
    public String getText() {
        return anchor.getText();
    }

}
