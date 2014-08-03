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
import com.dianaui.universal.core.client.ui.constants.*;
import com.dianaui.universal.core.client.ui.html.Text;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.HasText;

/**
 * Mixin for Widgets that have text and an optional icon.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class IconTextMixin<T extends ComplexWidget & HasText & HasIcon & HasIconPosition> implements HasText, HasIcon,
        HasGlyphicon, HasIconPosition {

    private final T widget;
    private final Text text = new Text();
    private final Text separator = new Text(" ");
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

    public IconTextMixin(final T widget) {
        this.widget = widget;
    }

    public void addTextWidgetToParent() {
        widget.add(text);
    }

    @Override
    public String getText() {
        return text.getText();
    }

    @Override
    public void setText(final String text) {
        this.text.setText(text);
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return glyphicon == null ? null : glyphicon.getType();
    }

    @Override
    public void setGlyphicon(GlyphiconType iconType) {
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

        if (fontAwesomeIcon != null && fontAwesomeIcon.getSize() != iconSize) {
            render();
        }
    }

    @Override
    public IconFlip getIconFlip() {
        return iconFlip;
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        this.iconFlip = iconFlip;

        if (fontAwesomeIcon != null && fontAwesomeIcon.getFlip() != iconFlip) {
            render();
        }
    }

    @Override
    public IconRotate getIconRotate() {
        return iconRotate;
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        this.iconRotate = iconRotate;

        if (fontAwesomeIcon != null && fontAwesomeIcon.getRotate() != iconRotate) {
            render();
        }
    }

    @Override
    public boolean isIconBordered() {
        return iconBordered;
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        this.iconBordered = iconBordered;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isBorder() != iconBordered) {
            render();
        }
    }

    @Override
    public boolean isIconMuted() {
        return iconMuted;
    }

    @Override
    public void setIconMuted(boolean iconMuted) {
        this.iconMuted = iconMuted;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isMuted() != iconMuted) {
            render();
        }
    }

    @Override
    public boolean isIconLight() {
        return iconLight;
    }

    @Override
    public void setIconLight(boolean iconLight) {
        this.iconLight = iconLight;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isLight() != iconLight) {
            render();
        }
    }

    @Override
    public boolean isIconSpin() {
        return iconSpin;
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        this.iconSpin = iconSpin;

        if (fontAwesomeIcon != null && fontAwesomeIcon.isSpin() != iconSpin) {
            render();
        }
    }

    private void render() {
        if (glyphicon != null || fontAwesomeIcon != null) {
            // We defer to make sure the elements are available to manipulate their positions
            Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
                @Override
                public void execute() {
                    if (text.isAttached())
                        text.removeFromParent();

                    if (separator.isAttached())
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
                    }

                    // Since we are dealing with Icon/Text, we can insert them at the right position
                    // Helps on widgets like ButtonDropDown, where it has a caret added
                    int position = 0;

                    if (iconPosition == IconPosition.LEFT) {
                        widget.insert(glyphicon != null ? glyphicon : fontAwesomeIcon, position++);
                        widget.insert(separator, position++);
                    }

                    if (text.getText() != null && text.getText().length() > 0) {
                        widget.insert(text, position);
                    }

                    if (iconPosition == IconPosition.RIGHT) {
                        widget.insert(separator, position++);
                        widget.insert(glyphicon != null ? glyphicon : fontAwesomeIcon, position);
                    }
                }
            });
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
