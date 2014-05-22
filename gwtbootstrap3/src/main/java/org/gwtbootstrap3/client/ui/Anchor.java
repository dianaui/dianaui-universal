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
import org.gwtbootstrap3.client.ui.base.mixin.PullMixin;
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
        HasParent, HasTargetHistoryToken, HasHTML, HasIcon, HasIconPosition, Focusable, HasTarget, HasPull {

    private final PullMixin<Anchor> pullMixin = new PullMixin<Anchor>(this);
    private final ParentMixin<Anchor> parentMixin = new ParentMixin<Anchor>(this);
    private final IconTextMixin<Anchor> iconTextMixin = new IconTextMixin<Anchor>(this);
    private FocusableMixin<Anchor> focusableMixin;
    private String targetHistoryToken;
    private Toggle toggle;

    /**
     * Creates a default anchor with a default href
     */
    public Anchor() {
        this(EMPTY_HREF);
    }

    /**
     * Creates an anchor widget with the desired HREF
     *
     * @param href href for the anchor
     */
    public Anchor(final String href) {
        setElement(Document.get().createAnchorElement());
        setHref(href);
        iconTextMixin.addTextWidgetToParent();
    }

    /**
     * Creates an achor widget with the desired HREF and text
     *
     * @param text text for the anchor
     * @param href href for the anchor
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return iconTextMixin.getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(final String text) {
        iconTextMixin.setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GlyphiconType getGlyphicon() {
        return iconTextMixin.getGlyphicon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGlyphicon(final GlyphiconType iconType) {
        iconTextMixin.setGlyphicon(iconType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IconType getFontAwesomeIcon() {
        return iconTextMixin.getFontAwesomeIcon();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        iconTextMixin.setFontAwesomeIcon(iconType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearIcon() {
        iconTextMixin.setFontAwesomeIcon(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IconPosition getIconPosition() {
        return iconTextMixin.getIconPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        iconTextMixin.setIconPosition(iconPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IconSize getIconSize() {
        return iconTextMixin.getIconSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconSize(final IconSize iconSize) {
        iconTextMixin.setIconSize(iconSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IconFlip getIconFlip() {
        return iconTextMixin.getIconFlip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconFlip(final IconFlip iconFlip) {
        iconTextMixin.setIconFlip(iconFlip);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IconRotate getIconRotate() {
        return iconTextMixin.getIconRotate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconRotate(final IconRotate iconRotate) {
        iconTextMixin.setIconRotate(iconRotate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIconBordered() {
        return iconTextMixin.isIconBordered();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconBordered(final boolean iconBordered) {
        iconTextMixin.setIconBordered(iconBordered);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIconMuted() {
        return iconTextMixin.isIconMuted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconMuted(final boolean iconMuted) {
        iconTextMixin.setIconMuted(iconMuted);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIconLight() {
        return iconTextMixin.isIconLight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconLight(final boolean iconLight) {
        iconTextMixin.setIconLight(iconLight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIconSpin() {
        return iconTextMixin.isIconSpin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIconSpin(final boolean iconSpin) {
        iconTextMixin.setIconSpin(iconSpin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHref() {
        return AnchorElement.as(getElement()).getHref();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHref(final String href) {
        if (href == null) {
            getElement().removeAttribute(HREF);
        } else {
            getAnchorElement().setHref(href);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTargetHistoryToken(final String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        final String hash = History.encodeHistoryToken(targetHistoryToken);
        setHref("#" + hash);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDataParent() {
        return parentMixin.getDataParent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDataParent(final String href) {
        parentMixin.setDataParent(href);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Toggle getToggle() {
        return toggle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setToggle(final Toggle toggle) {
        this.toggle = toggle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTabIndex() {
        return getFocusableMixin().getTabIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTabIndex(final int index) {
        getFocusableMixin().setTabIndex(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccessKey(final char key) {
        getFocusableMixin().setAccessKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus(final boolean focused) {
        getFocusableMixin().setFocus(focused);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHTML(String html) {
        getElement().setInnerHTML(html);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTarget() {
        return getAnchorElement().getTarget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTarget(String target) {
        getAnchorElement().setTarget(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPull(final Pull pull) {
        pullMixin.setPull(pull);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pull getPull() {
        return pullMixin.getPull();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onAttach() {
        super.onAttach();

        // Adding styles to the heading depending on the parent
        if (getParent() != null) {
            if (getParent() instanceof Alert) {
                addStyleName(Styles.ALERT_LINK);
            }
        }
    }

    private FocusableMixin getFocusableMixin() {
        if (focusableMixin == null) {
            focusableMixin = new FocusableMixin<Anchor>(this);
        }
        return focusableMixin;
    }

    private AnchorElement getAnchorElement() {
        return AnchorElement.as(getElement());
    }

}
