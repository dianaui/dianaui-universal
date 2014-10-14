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
package com.dianaui.universal.core.client.ui.base.mixin;

import com.dianaui.universal.core.client.ui.FontAwesomeIcon;
import com.dianaui.universal.core.client.ui.Glyphicon;
import com.dianaui.universal.core.client.ui.base.ComplexWidget;
import com.dianaui.universal.core.client.ui.base.HasGlyphicon;
import com.dianaui.universal.core.client.ui.base.HasIcon;
import com.dianaui.universal.core.client.ui.base.HasIconPosition;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.*;
import com.dianaui.universal.core.client.ui.html.Span;
import com.dianaui.universal.core.client.ui.html.Text;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Mixin for Widgets that have text and an optional icon.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class IconTextMixin<T extends ComplexWidget & HasText & HasHTML & HasIcon & HasIconPosition> implements HasText,
        HasHTML, HasIcon, HasGlyphicon, HasIconPosition {

    private final T widget;
    private final Text separator = new Text(" ");
    private Widget text;
    private Glyphicon glyphicon;
    private FontAwesomeIcon fontAwesomeIcon;
    private IconPosition iconPosition = IconPosition.LEFT;
    private IconSize iconSize = IconSize.NONE;
    private IconFlip iconFlip = IconFlip.NONE;
    private IconRotate iconRotate = IconRotate.NONE;
    private boolean iconMuted = false;
    private boolean iconSpin = false;
    private boolean iconBordered = false;
    private boolean iconLight = false;
    private boolean iconFixedWidth = false;
    private boolean iconList = false;

    public IconTextMixin(final T widget) {
        this.widget = widget;
    }

    public void addTextWidgetToParent() {
        widget.add(text);
    }

    @Override
    public String getText() {
        if (text instanceof Span) {
            return ((Span) text).getText();
        }
        return text instanceof Text ? ((Text) text).getText() : null;
    }

    @Override
    public void setText(final String text) {
        if (this.text != null) {
            this.text.removeFromParent();
        }
        if (text == null) {
            this.text = null;
        } else {
            if (!(this.text instanceof Text))
                this.text = new Text();

            ((Text) this.text).setText(text);
        }

        render();
    }

    @Override
    public String getHTML() {
        return text instanceof Span ? ((Span) text).getHTML() : null;
    }

    @Override
    public void setHTML(final String html) {
        if (text != null)
            text.removeFromParent();
        if (!(text instanceof Span))
            text = new Span();

        ((Span) this.text).setHTML(html);

        render();
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return glyphicon == null ? null : glyphicon.getType();
    }

    @Override
    public void setGlyphicon(GlyphiconType iconType) {
        if (iconType == null) {
            clearIcon();
            return;
        }

        removeFontAwesomeIcon();

        if (glyphicon == null)
            glyphicon = new Glyphicon();

        glyphicon.setType(iconType);

        render();
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return fontAwesomeIcon == null ? null : fontAwesomeIcon.getType();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        if (iconType == null) {
            clearIcon();
            return;
        }

        removeGlyphicon();

        if (fontAwesomeIcon == null)
            fontAwesomeIcon = new FontAwesomeIcon();

        fontAwesomeIcon.setType(iconType);

        render();
    }

    @Override
    public void clearIcon() {
        removeGlyphicon();
        removeFontAwesomeIcon();
    }

    @Override
    public IconPosition getIconPosition() {
        return iconPosition;
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        if (this.iconPosition != iconPosition) {
            this.iconPosition = iconPosition;

            render();
        }
    }

    @Override
    public IconSize getIconSize() {
        return iconSize;
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        this.iconSize = iconSize;

        if (fontAwesomeIcon != null && fontAwesomeIcon.getSize() != iconSize)
            render();
    }

    @Override
    public IconFlip getIconFlip() {
        return iconFlip;
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        this.iconFlip = iconFlip;

        if (fontAwesomeIcon != null && fontAwesomeIcon.getFlip() != iconFlip)
            render();
    }

    @Override
    public IconRotate getIconRotate() {
        return iconRotate;
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        this.iconRotate = iconRotate;

        if (fontAwesomeIcon != null && fontAwesomeIcon.getRotate() != iconRotate)
            render();
    }

    @Override
    public boolean isIconBordered() {
        return iconBordered;
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        this.iconBordered = iconBordered;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isBorder() != iconBordered)
            render();
    }

    @Override
    public boolean isIconMuted() {
        return iconMuted;
    }

    @Override
    public void setIconMuted(boolean iconMuted) {
        this.iconMuted = iconMuted;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isMuted() != iconMuted)
            render();
    }

    @Override
    public boolean isIconLight() {
        return iconLight;
    }

    @Override
    public void setIconLight(boolean iconLight) {
        this.iconLight = iconLight;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isLight() != iconLight)
            render();
    }

    @Override
    public boolean isIconSpin() {
        return iconSpin;
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        this.iconSpin = iconSpin;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isSpin() != iconSpin)
            render();
    }

    @Override
    public boolean isIconFixedWidth() {
        return iconFixedWidth;
    }

    @Override
    public void setIconFixedWidth(final boolean iconFixedWidth) {
        this.iconFixedWidth = iconFixedWidth;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isSpin() != iconSpin)
            render();
    }

    public boolean isIconList() {
        return iconList;
    }

    public void setIconList(final boolean iconList) {
        this.iconList = iconList;

        if (fontAwesomeIcon != null &&
                StyleHelper.containsStyle(fontAwesomeIcon.getStyleName(), Styles.FONT_AWESOME_LI) != iconList) {
            render();
        }
    }

    private void render() {
        if (text != null)
            text.removeFromParent();

        if (separator != null)
            separator.removeFromParent();

        if (fontAwesomeIcon != null)
            fontAwesomeIcon.removeFromParent();

        if (glyphicon != null)
            glyphicon.removeFromParent();

        if (fontAwesomeIcon != null) {
            fontAwesomeIcon.setSize(iconSize);
            fontAwesomeIcon.setFlip(iconFlip);
            fontAwesomeIcon.setRotate(iconRotate);
            fontAwesomeIcon.setMuted(iconMuted);
            fontAwesomeIcon.setSpin(iconSpin);
            fontAwesomeIcon.setBorder(iconBordered);
            fontAwesomeIcon.setLight(iconLight);
            fontAwesomeIcon.setFixedWidth(iconFixedWidth);
            StyleHelper.toggleStyleName(fontAwesomeIcon, iconList, Styles.FONT_AWESOME_LI);
        }

        // Since we are dealing with Icon/Text, we can insert them at the right position
        // Helps on widgets like ButtonDropDown, where it has a caret added
        int position = widget.getWidgetCount();

        if (iconPosition == IconPosition.LEFT && (glyphicon != null || fontAwesomeIcon != null)) {
            widget.insert(glyphicon != null ? glyphicon : fontAwesomeIcon, position++);
            if (text != null)
                widget.insert(separator, position++);
        }

        if (text != null)
            widget.insert(text, position++);

        if (iconPosition == IconPosition.RIGHT && (glyphicon != null || fontAwesomeIcon != null)) {
            if (text != null)
                widget.insert(separator, position++);
            widget.insert(glyphicon != null ? glyphicon : fontAwesomeIcon, position);
        }
    }

    private void removeFontAwesomeIcon() {
        if (fontAwesomeIcon != null)
            fontAwesomeIcon.removeFromParent();
        fontAwesomeIcon = null;
    }

    private void removeGlyphicon() {
        if (glyphicon != null)
            glyphicon.removeFromParent();
        glyphicon = null;
    }

}
