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
import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;

/**
 * Simple put, an icon.
 *
 * @author Sven Jacobs
 * @see IconType
 */
public class Icon extends ComplexWidget implements HasType<IconType> {

    public Icon() {
        setElement(Document.get().createElement(ElementTags.I));
        addStyleName(Styles.FONT_AWESOME_BASE);
    }

    @UiConstructor
    public Icon(final IconType type) {
        this();
        setType(type);
    }

    @Override
    public IconType getType() {
        return IconType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final IconType type) {
        StyleHelper.addUniqueEnumStyleName(this, IconType.class, type);
    }

    public boolean isLight() {
        return StyleHelper.containsStyle(Styles.ICON_LIGHT, getStyleName());
    }

    public void setLight(final boolean light) {
        StyleHelper.toggleStyleName(this, light, Styles.ICON_LIGHT);
    }

    public boolean isMuted() {
        return StyleHelper.containsStyle(Styles.ICON_MUTED, getStyleName());
    }

    public void setMuted(final boolean muted) {
        StyleHelper.toggleStyleName(this, muted, Styles.ICON_MUTED);
    }

    public boolean isBorder() {
        return StyleHelper.containsStyle(Styles.ICON_BORDER, getStyleName());
    }

    public void setBorder(final boolean border) {
        StyleHelper.toggleStyleName(this, border, Styles.ICON_BORDER);
    }

    public boolean isStackBase() {
        return StyleHelper.containsStyle(Styles.ICON_STACK_BASE, getStyleName());
    }

    public void setStackBase(final boolean stackBase) {
        StyleHelper.toggleStyleName(this, stackBase, Styles.ICON_STACK_BASE);
    }

    public boolean isFixedWidth() {
        return StyleHelper.containsStyle(Styles.ICON_FIXED_WIDTH, getStyleName());
    }

    public void setFixedWidth(final boolean fixedWidth) {
        StyleHelper.toggleStyleName(this, fixedWidth, Styles.ICON_FIXED_WIDTH);
    }

    public boolean isStackTop() {
        return StyleHelper.containsStyle(Styles.ICON_STACK_TOP, getStyleName());
    }

    public void setStackTop(final boolean stackTop) {
        StyleHelper.toggleStyleName(this, stackTop, Styles.ICON_STACK_TOP);
    }

    public void setSpin(final boolean spin) {
        StyleHelper.toggleStyleName(this, spin, Styles.ICON_SPIN);
    }

    public IconRotate getRotate() {
        return IconRotate.fromStyleName(getStyleName());
    }

    public void setRotate(final IconRotate iconRotate) {
        if (iconRotate == null) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconRotate.class, iconRotate);
    }

    public IconFlip getFlip() {
        return IconFlip.fromStyleName(getStyleName());
    }

    public void setFlip(final IconFlip iconFlip) {
        if (iconFlip == null) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconFlip.class, iconFlip);
    }

    public IconSize getSize() {
        return IconSize.fromStyleName(getStyleName());
    }

    public void setSize(final IconSize iconSize) {
        if (iconSize == null) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconSize.class, iconSize);
    }
}
