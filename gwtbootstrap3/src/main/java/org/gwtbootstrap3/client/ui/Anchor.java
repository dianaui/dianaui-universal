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

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasHTML;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.mixin.FocusableMixin;
import org.gwtbootstrap3.client.ui.base.mixin.IconTextMixin;
import org.gwtbootstrap3.client.ui.base.mixin.ParentMixin;
import org.gwtbootstrap3.client.ui.constants.*;

/**
 * Anchor {@code <a>} element with text and optional icon.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author Grant Slender
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Anchor extends ComplexWidget implements HasClickHandlers, HasDoubleClickHandlers, HasHref, HasToggle,
        HasParent, HasTargetHistoryToken, HasHTML, HasIcon, HasIconPosition, HasTabIndex, Focusable, HasTarget {

    private final ParentMixin<Anchor> parentMixin = new ParentMixin<Anchor>(this);
    private final IconTextMixin<Anchor> iconTextMixin = new IconTextMixin<Anchor>(this);
    private FocusableMixin focusableMixin;
    private String targetHistoryToken;
    private Toggle toggle;

    public Anchor() {
        this(EMPTY_HREF);
    }

    public Anchor(final String href) {
        setElement(Document.get().createAnchorElement());
        setHref(href);
        iconTextMixin.addTextWidgetToParent();
    }

    public Anchor(final String text, final String href) {
        this(href);
        setText(text);
    }

    public Anchor(final String text, final ClickHandler clickHandler) {
        this();
        setText(text);
        setHref(null);
        addClickHandler(clickHandler);
    }

    public Anchor(final String text, final IconType iconType) {
        this();
        setText(text);
        setFontAwesomeIcon(iconType);
    }

    public Anchor(final String text, final GlyphiconType iconType) {
        this();
        setText(text);
        setGlyphicon(iconType);
    }

    public Anchor(final String text, final IconType iconType, final ClickHandler clickHandler) {
        this(text, clickHandler);
        setFontAwesomeIcon(iconType);
    }

    public Anchor(final String text, final GlyphiconType iconType, final ClickHandler clickHandler) {
        this(text, clickHandler);
        setGlyphicon(iconType);
    }

    public Anchor(final String text, final IconType iconType, final String href) {
        this(text, iconType);
        setHref(href);
    }

    public Anchor(final String text, final GlyphiconType iconType, final String href) {
        this(text, iconType);
        setHref(href);
    }

    @Override
    public void setText(final String text) {
        iconTextMixin.setText(text);
    }

    @Override
    public String getText() {
        return iconTextMixin.getText();
    }

    @Override
    public void setGlyphicon(final GlyphiconType iconType) {
        iconTextMixin.setGlyphicon(iconType);
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return iconTextMixin.getGlyphicon();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        iconTextMixin.setFontAwesomeIcon(iconType);
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return iconTextMixin.getFontAwesomeIcon();
    }

    @Override
    public void clearIcon() {
        iconTextMixin.setFontAwesomeIcon(null);
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        iconTextMixin.setIconPosition(iconPosition);
    }

    @Override
    public IconPosition getIconPosition() {
        return iconTextMixin.getIconPosition();
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        iconTextMixin.setIconSize(iconSize);
    }

    @Override
    public IconSize getIconSize() {
        return iconTextMixin.getIconSize();
    }

    @Override
    public void setIconFlip(final IconFlip iconFlip) {
        iconTextMixin.setIconFlip(iconFlip);
    }

    @Override
    public IconFlip getIconFlip() {
        return iconTextMixin.getIconFlip();
    }

    @Override
    public void setIconRotate(final IconRotate iconRotate) {
        iconTextMixin.setIconRotate(iconRotate);
    }

    @Override
    public IconRotate getIconRotate() {
        return iconTextMixin.getIconRotate();
    }

    @Override
    public void setIconBordered(final boolean iconBordered) {
        iconTextMixin.setIconBordered(iconBordered);
    }

    @Override
    public boolean isIconBordered() {
        return iconTextMixin.isIconBordered();
    }

    @Override
    public void setIconMuted(final boolean iconMuted) {
        iconTextMixin.setIconMuted(iconMuted);
    }

    @Override
    public boolean isIconMuted() {
        return iconTextMixin.isIconMuted();
    }

    @Override
    public void setIconLight(final boolean iconLight) {
        iconTextMixin.setIconLight(iconLight);
    }

    @Override
    public boolean isIconLight() {
        return iconTextMixin.isIconLight();
    }

    @Override
    public void setIconSpin(final boolean iconSpin) {
        iconTextMixin.setIconSpin(iconSpin);
    }

    @Override
    public boolean isIconSpin() {
        return iconTextMixin.isIconSpin();
    }

    @Override
    public void setHref(final String href) {
        if (href == null) {
            getElement().removeAttribute(HREF);
        } else {
            getAnchorElement().setHref(href);
        }
    }

    @Override
    public String getHref() {
        return AnchorElement.as(getElement()).getHref();
    }

    @Override
    public void setTargetHistoryToken(final String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        final String hash = History.encodeHistoryToken(targetHistoryToken);
        setHref("#" + hash);
    }

    @Override
    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    @Override
    public void setDataParent(final String href) {
        parentMixin.setDataParent(href);
    }

    @Override
    public String getDataParent() {
        return parentMixin.getDataParent();
    }

    @Override
    public void setToggle(final Toggle toggle) {
        this.toggle = toggle;
    }

    @Override
    public Toggle getToggle() {
        return toggle;
    }

    @Override
    public int getTabIndex() {
        return getFocusableMixin().getTabIndex();
    }

    @Override
    public void setTabIndex(final int index) {
        getFocusableMixin().setTabIndex(index);
    }

    @Override
    public void setAccessKey(final char key) {
        getFocusableMixin().setAccessKey(key);
    }

    @Override
    public void setFocus(final boolean focused) {
        getFocusableMixin().setFocus(focused);
    }

    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setHTML(String html) {
        getElement().setInnerHTML(html);
    }

    @Override
    public void setTarget(String target) {
        getAnchorElement().setTarget(target);
    }

    @Override
    public String getTarget() {
        return getAnchorElement().getTarget();
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());
    }

    private FocusableMixin getFocusableMixin() {
        if (focusableMixin == null) {
            focusableMixin = new FocusableMixin(getAnchorElement());
        }
        return focusableMixin;
    }

    private AnchorElement getAnchorElement() {
        return AnchorElement.as(getElement());
    }

}
