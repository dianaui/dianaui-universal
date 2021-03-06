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

import com.dianaui.universal.core.client.ui.base.ComplexWidget;
import com.dianaui.universal.core.client.ui.base.HasActive;
import com.dianaui.universal.core.client.ui.base.HasHref;
import com.dianaui.universal.core.client.ui.base.HasTargetHistoryToken;
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Span;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.History;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class LinkedGroupItem extends ComplexWidget implements HasClickHandlers, HasDoubleClickHandlers, HasHref,
        HasTargetHistoryToken, HasActive {

    private final Span span = new Span();

    private String targetHistoryToken;

    public LinkedGroupItem(final String href) {
        setElement(Document.get().createAnchorElement());
        setStyleName(Styles.LIST_GROUP_ITEM);
        setHref(href);
        add(span);
    }

    public LinkedGroupItem(final String text, final String href) {
        this(href);
        setText(text);
    }

    public LinkedGroupItem() {
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

    public String getText() {
        return span.getText();
    }

    public void setText(final String text) {
        span.setText(text);
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
    public boolean isActive() {
        return ActiveMixin.isActive(this);
    }

    @Override
    public void setActive(final boolean active) {
        ActiveMixin.setActive(this, active);
    }

}
