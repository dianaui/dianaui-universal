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
import org.gwtbootstrap3.client.ui.constants.*;

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
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see DropDownMenu
 * @see NavTabs
 * @see NavPills
 * @see Navbar
 */
public class AnchorListItem extends AbstractAnchorListItem implements HasIcon, HasIconPosition {

    public AnchorListItem() {
    }

    public AnchorListItem(final String text) {
        this(text, false);
    }

    public AnchorListItem(final String text, boolean html) {
        if (html) {
            setHTML(text);
        } else {
            setText(text);
        }
    }

    public AnchorListItem(final String text, final String href) {
        this(text, false, href);
    }

    public AnchorListItem(final String text, boolean html, final String href) {
        this(text, html);
        setHref(href);
    }

    public AnchorListItem(final String text, final ClickHandler clickHandler) {
        this(text, false, clickHandler);
    }

    public AnchorListItem(final String text, boolean html, final ClickHandler clickHandler) {
        this(text, html);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorListItem(final String text, final IconType iconType) {
        this(text, false, iconType);
    }

    public AnchorListItem(final String text, boolean html, final IconType iconType) {
        this(text, html);
        setFontAwesomeIcon(iconType);
    }

    public AnchorListItem(final String text, final GlyphiconType iconType) {
        this(text, false, iconType);
    }

    public AnchorListItem(final String text, boolean html, final GlyphiconType iconType) {
        this(text, html);
        setGlyphicon(iconType);
    }

    public AnchorListItem(final String text, final IconType iconType, final ClickHandler clickHandler) {
        this(text, false, iconType, clickHandler);
    }

    public AnchorListItem(final String text, boolean html, final IconType iconType, final ClickHandler clickHandler) {
        this(text, html, iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorListItem(final String text, final GlyphiconType iconType, final ClickHandler clickHandler) {
        this(text, false, iconType, clickHandler);
    }

    public AnchorListItem(final String text, boolean html, final GlyphiconType iconType, final ClickHandler clickHandler) {
        this(text, html, iconType);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public AnchorListItem(final String text, final IconType iconType, final String href) {
        this(text, false, iconType, href);
    }

    public AnchorListItem(final String text, boolean html, final IconType iconType, final String href) {
        this(text, html, iconType);
        setHref(href);
    }

    public AnchorListItem(final String text, final GlyphiconType iconType, final String href) {
        this(text, false, iconType, href);
    }

    public AnchorListItem(final String text, boolean html, final GlyphiconType iconType, final String href) {
        this(text, html, iconType);
        setHref(href);
    }

    public void setText(final String text) {
        anchor.setText(text);
    }

    public String getText() {
        return anchor.getText();
    }

    public String getHTML() {
        return anchor.getHTML();
    }

    public void setHTML(String html) {
        anchor.setHTML(html);
    }

    @Override
    public void setGlyphicon(final GlyphiconType iconType) {
        anchor.setGlyphicon(iconType);
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return anchor.getGlyphicon();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        anchor.setFontAwesomeIcon(iconType);
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return anchor.getFontAwesomeIcon();
    }

    @Override
    public void clearIcon() {
        anchor.setFontAwesomeIcon(null);
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        anchor.setIconPosition(iconPosition);
    }

    @Override
    public IconPosition getIconPosition() {
        return anchor.getIconPosition();
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        anchor.setIconSize(iconSize);
    }

    @Override
    public IconSize getIconSize() {
        return anchor.getIconSize();
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        anchor.setIconFlip(iconFlip);
    }

    @Override
    public IconFlip getIconFlip() {
        return anchor.getIconFlip();
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        anchor.setIconRotate(iconRotate);
    }

    @Override
    public IconRotate getIconRotate() {
        return anchor.getIconRotate();
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        anchor.setIconBordered(iconBordered);
    }

    @Override
    public boolean isIconBordered() {
        return anchor.isIconBordered();
    }

    @Override
    public void setIconMuted(boolean iconMuted) {
        anchor.setIconMuted(iconMuted);
    }

    @Override
    public boolean isIconMuted() {
        return anchor.isIconMuted();
    }

    @Override
    public void setIconLight(boolean iconLight) {
        anchor.setIconLight(iconLight);
    }

    @Override
    public boolean isIconLight() {
        return anchor.isIconLight();
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        anchor.setIconSpin(iconSpin);
    }

    @Override
    public boolean isIconSpin() {
        return anchor.isIconSpin();
    }

}
