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

import com.dianaui.universal.core.client.ui.base.HasIcon;
import com.dianaui.universal.core.client.ui.base.HasIconPosition;
import com.dianaui.universal.core.client.ui.base.HasSize;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * A ButtonCell with Bootstrap Style, supporting Icons and icon types.
 *
 * @author Carlos A Becker
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see Button
 */
public class ButtonCell extends com.google.gwt.cell.client.ButtonCell implements HasIcon, HasIconPosition,
        HasSize<ButtonSize> {

    private static final String separator = " ";
    private ButtonType type = null;
    private ButtonSize size = null;
    private IconType fontAwesomeIcon = null;
    private GlyphiconType glyphicon = null;
    private IconSize iconSize = null;
    private IconFlip iconFlip = null;
    private IconRotate iconRotate = null;
    private boolean iconBordered = false;
    private boolean iconMuted = false;
    private boolean iconLight = false;
    private boolean iconSpin = false;
    private boolean iconFixedWidth = false;
    private IconPosition iconPosition = IconPosition.LEFT;

    public ButtonCell() {
    }

    public ButtonCell(ButtonType type) {
        setType(type);
    }

    public ButtonCell(IconType icon) {
        setFontAwesomeIcon(icon);
    }

    public ButtonCell(GlyphiconType icon) {
        setGlyphicon(icon);
    }

    public ButtonCell(ButtonSize size) {
        setSize(size);
    }

    public ButtonCell(IconType icon, ButtonType type) {
        setFontAwesomeIcon(icon);
        setType(type);
    }

    public ButtonCell(IconType icon, ButtonSize size) {
        setFontAwesomeIcon(icon);
        setSize(size);
    }

    public ButtonCell(ButtonType type, ButtonSize size) {
        setType(type);
        setSize(size);
    }

    public ButtonCell(IconType icon, ButtonType type, ButtonSize size) {
        setFontAwesomeIcon(icon);
        setType(type);
        setSize(size);
    }

    @Override
    public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
        sb.appendHtmlConstant("<button type=\"button\" class=\"btn "
                + (type != null ? type.getCssName() : "") + (size != null ? " " + size.getCssName() : "") + "\" tabindex=\"-1\">");
        if (data != null) {
            String iconHtml = "";

            if (fontAwesomeIcon != null || glyphicon != null) {
                String iconClasses = fontAwesomeIcon != null ? Styles.FONT_AWESOME_BASE + " " +
                        fontAwesomeIcon.getCssName() : Styles.GLYPHICON_BASE + " " + glyphicon.getCssName();

                if (fontAwesomeIcon != null) {
                    if (iconSize != null)
                        iconClasses += iconSize.getCssName() + " ";
                    if (iconFlip != null)
                        iconClasses += iconFlip.getCssName() + " ";
                    if (iconRotate != null)
                        iconClasses += iconRotate.getCssName() + " ";
                    if (iconBordered)
                        iconClasses += Styles.ICON_BORDER + " ";
                    if (iconMuted)
                        iconClasses += Styles.ICON_MUTED + " ";
                    if (iconLight)
                        iconClasses += Styles.ICON_LIGHT + " ";
                    if (iconSpin)
                        iconClasses += Styles.ICON_SPIN + " ";
                    if (iconFixedWidth)
                        iconClasses += Styles.ICON_FIXED_WIDTH;
                }

                iconHtml = "<i class=\"" + iconClasses + "\"></i> ";
            }

            if (iconPosition == IconPosition.LEFT) {
                sb.appendHtmlConstant(iconHtml);
                sb.appendEscapedLines(separator);
            }

            sb.append(data);

            if (iconPosition == IconPosition.RIGHT) {
                sb.appendEscapedLines(separator);
                sb.appendHtmlConstant(iconHtml);
            }
        }
        sb.appendHtmlConstant("</button>");
    }

    public ButtonType getType() {
        return type;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }

    @Override
    public ButtonSize getSize() {
        return size;
    }

    @Override
    public void setSize(ButtonSize size) {
        this.size = size;
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return fontAwesomeIcon;
    }

    @Override
    public void setFontAwesomeIcon(IconType iconType) {
        fontAwesomeIcon = iconType;
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return glyphicon;
    }

    @Override
    public void setGlyphicon(GlyphiconType iconType) {
        glyphicon = iconType;
    }

    @Override
    public void clearIcon() {
        fontAwesomeIcon = null;
        glyphicon = null;
    }

    @Override
    public IconSize getIconSize() {
        return iconSize;
    }

    @Override
    public void setIconSize(IconSize iconSize) {
        this.iconSize = iconSize;
    }

    @Override
    public IconFlip getIconFlip() {
        return iconFlip;
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        this.iconFlip = iconFlip;
    }

    @Override
    public IconRotate getIconRotate() {
        return iconRotate;
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        this.iconRotate = iconRotate;
    }

    @Override
    public boolean isIconBordered() {
        return iconBordered;
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        this.iconBordered = iconBordered;
    }

    @Override
    public boolean isIconMuted() {
        return iconMuted;
    }

    @Override
    public void setIconMuted(boolean iconMuted) {
        this.iconMuted = iconMuted;
    }

    @Override
    public boolean isIconLight() {
        return iconLight;
    }

    @Override
    public void setIconLight(boolean iconLight) {
        this.iconLight = iconLight;
    }

    @Override
    public boolean isIconSpin() {
        return iconSpin;
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        this.iconSpin = iconSpin;
    }

    @Override
    public boolean isIconFixedWidth() {
        return iconFixedWidth;
    }

    @Override
    public void setIconFixedWidth(boolean iconFixedWidth) {
        this.iconFixedWidth = iconFixedWidth;
    }

    @Override
    public IconPosition getIconPosition() {
        return iconPosition;
    }

    @Override
    public void setIconPosition(IconPosition iconPosition) {
        this.iconPosition = iconPosition;
    }

}
