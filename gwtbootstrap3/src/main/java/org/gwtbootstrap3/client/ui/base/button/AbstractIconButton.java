package org.gwtbootstrap3.client.ui.base.button;

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

import com.google.gwt.user.client.ui.HasText;
import org.gwtbootstrap3.client.ui.HasIcon;
import org.gwtbootstrap3.client.ui.HasIconPosition;
import org.gwtbootstrap3.client.ui.base.mixin.IconTextMixin;
import org.gwtbootstrap3.client.ui.constants.*;

/**
 * Base class for buttons that can contain an icon.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see org.gwtbootstrap3.client.ui.FontAwesomeIcon
 */
public abstract class AbstractIconButton extends AbstractButton implements HasText, HasIcon, HasIconPosition {

    IconTextMixin<AbstractIconButton> iconTextMixin = new IconTextMixin<AbstractIconButton>(this);

    private final ButtonStateHandler buttonStateHandler = new ButtonStateHandler();
    private String loadingText;

    public class ButtonStateHandler {

        private String text;

        private ButtonStateHandler() {
        }

        public void loading() {
            this.text = getText();

            setEnabled(false);
            setText(loadingText);
        }

        public void reset() {
            setEnabled(true);
            setText(text);
        }

    }

    protected AbstractIconButton() {
    }

    protected AbstractIconButton(final ButtonType type) {
        super(type);
    }

    public void setLoadingText(final String loadingText) {
        this.loadingText = loadingText;
    }

    public ButtonStateHandler state() {
        return buttonStateHandler;
    }

    @Override
    public void setText(final String text) {
        iconTextMixin.setText(text);

        onChanged();
    }

    @Override
    public String getText() {
        return iconTextMixin.getText();
    }

    @Override
    public void setGlyphicon(GlyphiconType iconType) {
        iconTextMixin.setGlyphicon(iconType);

        onChanged();
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return iconTextMixin.getGlyphicon();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        iconTextMixin.setFontAwesomeIcon(iconType);

        onChanged();
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return iconTextMixin.getFontAwesomeIcon();
    }

    // TODO
    @Override
    public void clearIcon() {
        iconTextMixin.setFontAwesomeIcon(null);

        onChanged();
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        iconTextMixin.setIconPosition(iconPosition);

        onChanged();
    }

    @Override
    public IconPosition getIconPosition() {
        return iconTextMixin.getIconPosition();
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        iconTextMixin.setIconSize(iconSize);

        onChanged();
    }

    @Override
    public IconSize getIconSize() {
        return iconTextMixin.getIconSize();
    }

    @Override
    public void setIconFlip(final IconFlip iconFlip) {
        iconTextMixin.setIconFlip(iconFlip);

        onChanged();
    }

    @Override
    public IconFlip getIconFlip() {
        return iconTextMixin.getIconFlip();
    }

    @Override
    public void setIconRotate(final IconRotate iconRotate) {
        iconTextMixin.setIconRotate(iconRotate);

        onChanged();
    }

    @Override
    public IconRotate getIconRotate() {
        return iconTextMixin.getIconRotate();
    }

    @Override
    public void setIconBordered(final boolean iconBordered) {
        iconTextMixin.setIconBordered(iconBordered);

        onChanged();
    }

    @Override
    public boolean isIconBordered() {
        return iconTextMixin.isIconBordered();
    }

    @Override
    public void setIconMuted(final boolean iconMuted) {
        iconTextMixin.setIconMuted(iconMuted);

        onChanged();
    }

    @Override
    public boolean isIconMuted() {
        return iconTextMixin.isIconMuted();
    }

    @Override
    public void setIconLight(final boolean iconLight) {
        iconTextMixin.setIconLight(iconLight);

        onChanged();
    }

    @Override
    public boolean isIconLight() {
        return iconTextMixin.isIconLight();
    }

    @Override
    public void setIconSpin(final boolean iconSpin) {
        iconTextMixin.setIconSpin(iconSpin);

        onChanged();
    }

    @Override
    public boolean isIconSpin() {
        return iconTextMixin.isIconSpin();
    }

    protected void onChanged() {
    }

}
