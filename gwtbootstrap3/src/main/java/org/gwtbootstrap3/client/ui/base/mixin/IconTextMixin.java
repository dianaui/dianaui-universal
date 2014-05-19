package org.gwtbootstrap3.client.ui.base.mixin;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.HasText;
import org.gwtbootstrap3.client.ui.FontAwesomeIcon;
import org.gwtbootstrap3.client.ui.Glyphicon;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.constants.*;
import org.gwtbootstrap3.client.ui.html.Text;

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
        render(new Glyphicon(iconType));
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return fontAwesomeIcon == null ? null : fontAwesomeIcon.getType();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        render(new FontAwesomeIcon(iconType));
    }

    @Override
    public void clearIcon() {
        fontAwesomeIcon = null;
    }

    @Override
    public IconPosition getIconPosition() {
        return iconPosition;
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        this.iconPosition = iconPosition;
        render(fontAwesomeIcon);
    }

    @Override
    public IconSize getIconSize() {
        return iconSize;
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        this.iconSize = iconSize;
        render(fontAwesomeIcon);
    }

    @Override
    public IconFlip getIconFlip() {
        return iconFlip;
    }

    @Override
    public void setIconFlip(IconFlip iconFlip) {
        this.iconFlip = iconFlip;
        render(fontAwesomeIcon);
    }

    @Override
    public IconRotate getIconRotate() {
        return iconRotate;
    }

    @Override
    public void setIconRotate(IconRotate iconRotate) {
        this.iconRotate = iconRotate;
        render(fontAwesomeIcon);
    }

    @Override
    public boolean isIconBordered() {
        return iconBordered;
    }

    @Override
    public void setIconBordered(boolean iconBordered) {
        this.iconBordered = iconBordered;
        render(fontAwesomeIcon);
    }

    @Override
    public boolean isIconMuted() {
        return iconMuted;
    }

    @Override
    public void setIconMuted(boolean iconMuted) {
        this.iconMuted = iconMuted;
        render(fontAwesomeIcon);
    }

    @Override
    public boolean isIconLight() {
        return iconLight;
    }

    @Override
    public void setIconLight(boolean iconLight) {
        this.iconLight = iconLight;
        render(fontAwesomeIcon);
    }

    @Override
    public boolean isIconSpin() {
        return iconSpin;
    }

    @Override
    public void setIconSpin(boolean iconSpin) {
        this.iconSpin = iconSpin;
        render(fontAwesomeIcon);
    }

    private void render(final FontAwesomeIcon newIcon) {
        // We defer to make sure the elements are available to manipulate their positions
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                text.removeFromParent();
                separator.removeFromParent();

                if (fontAwesomeIcon != null) {
                    fontAwesomeIcon.removeFromParent();
                }

                fontAwesomeIcon = newIcon;
                fontAwesomeIcon.setSize(iconSize);
                fontAwesomeIcon.setFlip(iconFlip);
                fontAwesomeIcon.setRotate(iconRotate);
                fontAwesomeIcon.setMuted(iconMuted);
                fontAwesomeIcon.setSpin(iconSpin);
                fontAwesomeIcon.setBorder(iconBordered);
                fontAwesomeIcon.setLight(iconLight);

                // Since we are dealing with Icon/Text, we can insert them at the right position
                // Helps on widgets like ButtonDropDown, where it has a caret added
                int position = 0;

                if (iconPosition == IconPosition.LEFT) {
                    widget.insert(fontAwesomeIcon, position++);
                    widget.insert(separator, position++);
                }

                widget.insert(text, position++);

                if (iconPosition == IconPosition.RIGHT) {
                    widget.insert(separator, position++);
                    widget.insert(fontAwesomeIcon, position);
                }
            }
        });
    }

    private void render(final Glyphicon newIcon) {
        // We defer to make sure the elements are available to manipulate their positions
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                text.removeFromParent();
                separator.removeFromParent();

                if (glyphicon != null) {
                    glyphicon.removeFromParent();
                }

                glyphicon = newIcon;

                // Since we are dealing with Icon/Text, we can insert them at the right position
                // Helps on widgets like ButtonDropDown, where it has a caret added
                int position = 0;

                if (iconPosition == IconPosition.LEFT) {
                    widget.insert(glyphicon, position++);
                    widget.insert(separator, position++);
                }

                widget.insert(text, position++);

                if (iconPosition == IconPosition.RIGHT) {
                    widget.insert(separator, position++);
                    widget.insert(glyphicon, position);
                }
            }
        });
    }

}