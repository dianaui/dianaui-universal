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
package com.dianaui.universal.core.ui.base;

import com.dianaui.universal.core.ui.Anchor;
import com.dianaui.universal.core.ui.constants.*;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for list items that contain an {@link com.dianaui.universal.core.ui.Anchor} link.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public abstract class AbstractAnchorListItem extends AbstractListItem implements HasLink, HasTargetHistoryToken,
        HasHref, HasClickHandlers, Focusable, HasToggle, HasTarget, HasIcon, HasIconPosition {

    protected final Anchor anchor;

    protected AbstractAnchorListItem() {
        anchor = new Anchor();
        add(anchor, (Element) getElement());
    }

    public Anchor getAnchor() {
        return anchor;
    }

    @Override
    public void add(final Widget child) {
        anchor.add(child);
    }

    @Override
    public String getHref() {
        return anchor.getHref();
    }

    @Override
    public void setHref(final String href) {
        anchor.setHref(href);
    }

    @Override
    public String getTargetHistoryToken() {
        return anchor.getTargetHistoryToken();
    }

    @Override
    public void setTargetHistoryToken(final String targetHistoryToken) {
        anchor.setTargetHistoryToken(targetHistoryToken);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return anchor.addClickHandler(handler);
    }

    @Override
    public int getTabIndex() {
        return anchor.getTabIndex();
    }

    @Override
    public void setTabIndex(final int index) {
        anchor.setTabIndex(index);
    }

    @Override
    public void setAccessKey(final char key) {
        anchor.setAccessKey(key);
    }

    @Override
    public void setFocus(final boolean focused) {
        anchor.setFocus(focused);
    }

    @Override
    public Toggle getToggle() {
        return anchor.getToggle();
    }

    @Override
    public void setToggle(final Toggle toggle) {
        anchor.setToggle(toggle);
    }

    @Override
    public String getTarget() {
        return anchor.getTarget();
    }

    @Override
    public void setTarget(final String target) {
        anchor.setTarget(target);
    }

    public GlyphiconType getGlyphicon() {
        return anchor.getGlyphicon();
    }

    public void setGlyphicon(final GlyphiconType iconType) {
        anchor.setGlyphicon(iconType);
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return anchor.getFontAwesomeIcon();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        anchor.setFontAwesomeIcon(iconType);
    }

    @Override
    public IconPosition getIconPosition() {
        return anchor.getIconPosition();
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        anchor.setIconPosition(iconPosition);
    }

    @Override
    public IconSize getIconSize() {
        return anchor.getIconSize();
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        anchor.setIconSize(iconSize);
    }

    @Override
    public IconFlip getIconFlip() {
        return anchor.getIconFlip();
    }

    @Override
    public void setIconFlip(final IconFlip iconFlip) {
        anchor.setIconFlip(iconFlip);
    }

    @Override
    public IconRotate getIconRotate() {
        return anchor.getIconRotate();
    }

    @Override
    public void setIconRotate(final IconRotate iconRotate) {
        anchor.setIconRotate(iconRotate);
    }

    @Override
    public boolean isIconBordered() {
        return anchor.isIconBordered();
    }

    @Override
    public void setIconBordered(final boolean iconBordered) {
        anchor.setIconBordered(iconBordered);
    }

    @Override
    public boolean isIconMuted() {
        return anchor.isIconMuted();
    }

    @Override
    public void setIconMuted(final boolean iconMuted) {
        anchor.setIconMuted(iconMuted);
    }

    @Override
    public boolean isIconLight() {
        return anchor.isIconLight();
    }

    @Override
    public void setIconLight(final boolean iconLight) {
        anchor.setIconLight(iconLight);
    }

    @Override
    public boolean isIconSpin() {
        return anchor.isIconSpin();
    }

    @Override
    public void setIconSpin(final boolean iconSpin) {
        anchor.setIconSpin(iconSpin);
    }

    @Override
    public void clearIcon() {
        anchor.clearIcon();
    }

}