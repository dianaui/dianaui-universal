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
package com.dianaui.universal.core.client.ui.gwt;

import com.dianaui.universal.core.client.ui.base.HasFormValue;
import com.dianaui.universal.core.client.ui.base.IsWidget;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.EnabledMixin;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.Pull;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A simple checkbox widget, with no label.
 *
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class SimpleCheckBox extends com.google.gwt.user.client.ui.SimpleCheckBox implements IsWidget, HasFormValue {

    /**
     * Creates a new simple checkbox.
     */
    public SimpleCheckBox() {
        super(Document.get().createCheckInputElement());
    }

    /**
     * This constructor may be used by subclasses to explicitly use an existing
     * element. This element must be an &lt;input&gt; element whose type is
     * 'checkbox'.
     *
     * @param element the element to be used
     */
    protected SimpleCheckBox(InputElement element) {
        super(element);
    }

    /**
     * Creates a SimpleCheckBox widget that wraps an existing &lt;input
     * type='checkbox'&gt; element.
     * This element must already be attached to the document. If the element is
     * removed from the document, you must call
     * {@link RootPanel#detachNow(com.google.gwt.user.client.ui.Widget)}.
     *
     * @param element the element to be wrapped
     */
    public static SimpleCheckBox wrap(Element element) {
        // Assert that the element is attached.
        assert Document.get().getBody().isOrHasChild(element);

        SimpleCheckBox checkBox = new SimpleCheckBox(InputElement.as(element));

        // Mark it attached and remember it for cleanup.
        checkBox.onAttach();
        RootPanel.detachOnWindowClose(checkBox);

        return checkBox;
    }

    @Override
    public boolean isEnabled() {
        return EnabledMixin.isEnabled(this);
    }

    @Override
    public void setEnabled(boolean enabled) {
        EnabledMixin.setEnabled(this, enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
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

}
