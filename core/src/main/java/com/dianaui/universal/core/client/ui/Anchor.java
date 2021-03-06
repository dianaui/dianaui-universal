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

import com.dianaui.universal.core.client.ui.base.*;
import com.dianaui.universal.core.client.ui.base.mixin.EnabledMixin;
import com.dianaui.universal.core.client.ui.base.mixin.FocusableMixin;
import com.dianaui.universal.core.client.ui.base.mixin.IconTextMixin;
import com.dianaui.universal.core.client.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasHTML;

/**
 * Anchor {@code <a>} element with text and optional icon.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author Grant Slender
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Anchor extends ComplexWidget implements HasClickHandlers, HasDoubleClickHandlers, HasHref, HasToggle,
        HasTargetHistoryToken, HasHTML, HasIcon, HasIconPosition, Focusable, HasTarget, HasPull, HasEnabled {

    private final IconTextMixin<Anchor> iconTextMixin = new IconTextMixin<Anchor>(this);
    private String targetHistoryToken;
    private Toggle toggle;
    private boolean enabled;

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

        sinkEvents(Event.ONCLICK);
    }

    public Anchor(final IconType iconType) {
        this();
        setFontAwesomeIcon(iconType);
    }

    public Anchor(final GlyphiconType iconType) {
        this();
        setGlyphicon(iconType);
    }

    /**
     * Creates an anchor widget with the desired HREF and text
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
    public boolean isIconFixedWidth() {
        return iconTextMixin.isIconFixedWidth();
    }

    @Override
    public void setIconFixedWidth(final boolean iconFixedWidth) {
        iconTextMixin.setIconFixedWidth(iconFixedWidth);
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
        return FocusableMixin.getTabIndex(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTabIndex(final int index) {
        FocusableMixin.setTabIndex(this, index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAccessKey(final char key) {
        FocusableMixin.setAccessKey(this, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus(final boolean focused) {
        FocusableMixin.setFocus(this, focused);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHTML() {
        return iconTextMixin.getHTML();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHTML(String html) {
        iconTextMixin.setHTML(html);
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
    public void setTarget(final String target) {
        getAnchorElement().setTarget(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pull getPull() {
        return PullMixin.getPull(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPull(final Pull pull) {
        PullMixin.setPull(this, pull);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return EnabledMixin.isEnabled(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(final boolean enabled) {
        EnabledMixin.setEnabled(this, enabled);
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

    /**
     * We override this because the <a></a> tag doesn't support the disabled property. So on clicks and focus, if disabled then ignore
     *
     * @param event dom event
     */
    @Override
    public void onBrowserEvent(final Event event) {
        switch (DOM.eventGetType(event)) {
            case Event.ONDBLCLICK:
            case Event.ONFOCUS:
            case Event.ONCLICK:
                if (!isEnabled()) {
                    return;
                }
                if (getToggle() == Toggle.DROPDOWN && getParent() instanceof DropDown) {
                    ((DropDown) getParent()).toggle();
                    return;
                }
                break;
        }
        super.onBrowserEvent(event);
    }

    protected boolean isIconList() {
        return iconTextMixin.isIconList();
    }

    protected void setIconList(final boolean iconList) {
        iconTextMixin.setIconList(iconList);
    }

    private AnchorElement getAnchorElement() {
        return AnchorElement.as(getElement());
    }

}
