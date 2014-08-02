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
import com.dianaui.universal.core.client.ui.base.mixin.ActiveMixin;
import com.dianaui.universal.core.client.ui.constants.Attributes;
import com.google.gwt.dom.client.Document;

/**
 * @author Joshua Godi
 */
public class CarouselIndicator extends ComplexWidget implements HasActive {

    private final ActiveMixin<CarouselIndicator> activeMixin = new ActiveMixin<CarouselIndicator>(this);

    public CarouselIndicator() {
        setElement(Document.get().createLIElement());
    }

    public void setDataSlideTo(final String dataSlideTo) {
        getElement().setAttribute(Attributes.DATA_SLIDE_TO, dataSlideTo);
    }

    @Override
    public boolean isActive() {
        return activeMixin.isActive();
    }

    @Override
    public void setActive(final boolean active) {
        activeMixin.setActive(active);
    }

}
