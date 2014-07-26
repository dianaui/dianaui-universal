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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiConstructor;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.HasType;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.*;

/**
 * Simple put, an icon.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see org.gwtbootstrap3.client.ui.constants.IconType
 */
public class FontAwesomeIcon extends ComplexWidget implements HasType<IconType> {

    public FontAwesomeIcon() {
        this(Document.get().createElement(ElementTags.I));
    }

    protected FontAwesomeIcon(Element element) {
        setElement(element);
        addStyleName(Styles.FONT_AWESOME_BASE);
    }

    @UiConstructor
    public FontAwesomeIcon(final IconType type) {
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

    public void setLight(final boolean light) {
        StyleHelper.toggleStyleName(this, light, Styles.ICON_LIGHT);
    }

    public void setMuted(final boolean muted) {
        StyleHelper.toggleStyleName(this, muted, Styles.ICON_MUTED);
    }

    public void setBorder(final boolean border) {
        StyleHelper.toggleStyleName(this, border, Styles.ICON_BORDER);
    }

    public void setStackBase(final boolean stackBase) {
        StyleHelper.toggleStyleName(this, stackBase, Styles.ICON_STACK_BASE);
    }

    public void setFixedWidth(final boolean fixedWidth) {
        StyleHelper.toggleStyleName(this, fixedWidth, Styles.ICON_FIXED_WIDTH);
    }

    public void setStackTop(final boolean stackTop) {
        StyleHelper.toggleStyleName(this, stackTop, Styles.ICON_STACK_TOP);
    }

    public void setSpin(final boolean spin) {
        StyleHelper.toggleStyleName(this, spin, Styles.ICON_SPIN);
    }

    public void setRotate(final IconRotate iconRotate) {
        if (iconRotate == null || iconRotate == IconRotate.NONE) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconRotate.class, iconRotate);
    }

    public void setFlip(final IconFlip iconFlip) {
        if (iconFlip == null || iconFlip == IconFlip.NONE) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconFlip.class, iconFlip);
    }

    public void setSize(final IconSize iconSize) {
        if (iconSize == null || iconSize == IconSize.NONE) {
            return;
        }

        StyleHelper.addUniqueEnumStyleName(this, IconSize.class, iconSize);
    }
}
