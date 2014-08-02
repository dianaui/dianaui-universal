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
package com.dianaui.universal.core.ui;

import com.dianaui.universal.core.ui.base.*;
import com.dianaui.universal.core.ui.base.mixin.FocusableMixin;
import com.dianaui.universal.core.ui.base.mixin.ToggleMixin;
import com.dianaui.universal.core.ui.constants.Toggle;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Focusable;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class ThumbnailLink extends ComplexWidget implements HasClickHandlers, HasDoubleClickHandlers, HasHref,
        HasToggle, HasTargetHistoryToken, Focusable, HasTarget {

    private final ToggleMixin<ThumbnailLink> toggleMixin = new ToggleMixin<ThumbnailLink>(this);
    private FocusableMixin<ThumbnailLink> focusableMixin;
    private String targetHistoryToken;

    public ThumbnailLink(final String href) {
        setElement(Document.get().createAnchorElement());
        setHref(href);
    }

    public ThumbnailLink(final String text, final String href) {
        this(href);
    }

    public ThumbnailLink() {
        this(EMPTY_HREF);
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());
    }

    @Override
    public String getHref() {
        return AnchorElement.as(getElement()).getHref();
    }

    @Override
    public void setHref(final String href) {
        AnchorElement.as(getElement()).setHref(href);
    }

    @Override
    public String getTargetHistoryToken() {
        return targetHistoryToken;
    }

    @Override
    public void setTargetHistoryToken(final String targetHistoryToken) {
        this.targetHistoryToken = targetHistoryToken;
        final String hash = History.encodeHistoryToken(targetHistoryToken);
        setHref("#" + hash);
    }

    @Override
    public Toggle getToggle() {
        return toggleMixin.getToggle();
    }

    @Override
    public void setToggle(final Toggle toggle) {
        toggleMixin.setToggle(toggle);
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

    private FocusableMixin getFocusableMixin() {
        if (focusableMixin == null) {
            focusableMixin = new FocusableMixin<ThumbnailLink>(this);
        }
        return focusableMixin;
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

    private AnchorElement getAnchorElement() {
        return AnchorElement.as(getElement());
    }

}
