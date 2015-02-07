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

import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.FormType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.NamedFrame;

public class FormPanel extends com.google.gwt.user.client.ui.FormPanel implements HasType<FormType>, HasResponsiveness {

    public FormPanel() {
        super();
    }

    public FormPanel(Element element, boolean createIFrame) {
        super(element, createIFrame);
    }

    public FormPanel(Element element) {
        super(element);
    }

    public FormPanel(NamedFrame frameTarget) {
        super(frameTarget);
    }

    public FormPanel(String target) {
        super(target);
    }

    @Override
    public void setType(final FormType type) {
        StyleHelper.addUniqueEnumStyleName(this, FormType.class, type);
    }

    @Override
    public FormType getType() {
        return FormType.fromStyleName(getStyleName());
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

}
