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
import com.dianaui.universal.core.client.ui.base.HasGlyphicon;
import com.dianaui.universal.core.client.ui.base.HasIcon;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.dom.client.Document;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class FormControlFeedback extends ComplexWidget implements HasIcon, HasGlyphicon {

    private Glyphicon glyphicon = null;
    private FontAwesomeIcon fontAwesomeIcon = null;
    private IconSize iconSize = IconSize.NONE;
    private IconFlip iconFlip = IconFlip.NONE;
    private IconRotate iconRotate = IconRotate.NONE;
    private boolean iconMuted = false;
    private boolean iconSpin = false;
    private boolean iconBordered = false;
    private boolean iconLight = false;
    private boolean iconFixedWidth = false;

    public FormControlFeedback() {
        setElement(Document.get().createSpanElement());
        setStyleName(Styles.FORM_CONTROL_FEEDBACK);
    }

    public void cleanGlyphicon() {
        StyleHelper.removeStyleNameStartsWith(this, Styles.GLYPHICON_BASE);
    }

    public void clearIcon() {
        StyleHelper.removeStyleNameStartsWith(this, Styles.FONT_AWESOME_BASE);
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return glyphicon != null ? glyphicon.getType() : null;
    }

    @Override
    public void setGlyphicon(GlyphiconType iconType) {
        if (glyphicon == null) {
            glyphicon = new Glyphicon(getElement());
        }

        glyphicon.setType(iconType);
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return fontAwesomeIcon != null ? fontAwesomeIcon.getType() : null;
    }

    @Override
    public void setFontAwesomeIcon(IconType iconType) {
        if (fontAwesomeIcon == null) {
            fontAwesomeIcon = new FontAwesomeIcon(getElement());
            fontAwesomeIcon.setMuted(iconMuted);
            fontAwesomeIcon.setSize(iconSize);
            fontAwesomeIcon.setFlip(iconFlip);
            fontAwesomeIcon.setRotate(iconRotate);
            fontAwesomeIcon.setMuted(iconMuted);
            fontAwesomeIcon.setSpin(iconSpin);
            fontAwesomeIcon.setBorder(iconBordered);
            fontAwesomeIcon.setLight(iconLight);
            fontAwesomeIcon.setFixedWidth(iconFixedWidth);
        }

        fontAwesomeIcon.setType(iconType);
    }

    @Override
    public IconSize getIconSize() {
        return iconSize;
    }

    @Override
    public void setIconSize(IconSize iconSize) {
        this.iconSize = iconSize;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setSize(iconSize);
        }
    }

    @Override
    public IconFlip getIconFlip() {
        return iconFlip;
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        this.iconFlip = iconFlip;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setFlip(iconFlip);
        }
    }

    @Override
    public IconRotate getIconRotate() {
        return iconRotate;
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        this.iconRotate = iconRotate;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setRotate(iconRotate);
        }
    }

    @Override
    public boolean isIconBordered() {
        return iconBordered;
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        this.iconBordered = iconBordered;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setBorder(iconBordered);
        }
    }

    @Override
    public boolean isIconMuted() {
        return iconMuted;
    }

    @Override
    public void setIconMuted(boolean iconMuted) {
        this.iconMuted = iconMuted;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setMuted(iconMuted);
        }
    }

    @Override
    public boolean isIconLight() {
        return iconLight;
    }

    @Override
    public void setIconLight(boolean iconLight) {
        this.iconLight = iconLight;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setLight(iconLight);
        }
    }

    @Override
    public boolean isIconSpin() {
        return iconSpin;
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        this.iconSpin = iconSpin;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setSpin(iconSpin);
        }
    }

    @Override
    public boolean isIconFixedWidth() {
        return iconFixedWidth;
    }

    public void setIconFixedWidth(final boolean iconFixedWidth) {
        this.iconFixedWidth = iconFixedWidth;

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setFixedWidth(iconFixedWidth);
        }
    }

}
