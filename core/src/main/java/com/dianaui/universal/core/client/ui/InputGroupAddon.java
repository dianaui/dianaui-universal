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

import com.dianaui.universal.core.client.ui.base.AbstractInputGroupAddon;
import com.dianaui.universal.core.client.ui.base.HasIcon;
import com.dianaui.universal.core.client.ui.base.HasIconPosition;
import com.dianaui.universal.core.client.ui.base.mixin.IconTextMixin;
import com.dianaui.universal.core.client.ui.constants.*;
import com.google.gwt.user.client.ui.HasHTML;

/**
 * Prepends or appends texts to input fields.
 * For prepending/appending {@link Button} see {@link InputGroupButton}.
 * Needs to be encapsulated in an {@link InputGroup}.
 * <h3>UiBinder example</h3>
 * <pre>{@code
 *     <b:InputGroup>
 *         <b:InputGroupAddon>Prepend something</b:InputGroupAddon>
 *         <b:TextBox/>
 *         <b:InputGroupAddon>Append something</b:InputGroupAddon>
 *     </b:InputGroup>
 * }</pre>
 * Also see Bootstrap <a href="http://getbootstrap.com/components/#input-groups">documentation</a>.
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see InputGroup
 * @see InputGroupButton
 */
public class InputGroupAddon extends AbstractInputGroupAddon implements HasHTML, HasIcon, HasIconPosition {

    private final IconTextMixin<InputGroupAddon> iconTextMixin = new IconTextMixin<InputGroupAddon>(this);

    public InputGroupAddon() {
        super(Styles.INPUT_GROUP_ADDON);
    }

    @Override
    public String getText() {
        return iconTextMixin.getText();
    }

    @Override
    public void setText(final String text) {
        iconTextMixin.setText(text);
    }

    @Override
    public String getHTML() {
        return iconTextMixin.getHTML();
    }

    @Override
    public void setHTML(String html) {
        iconTextMixin.setHTML(html);
    }

    @Override
    public GlyphiconType getGlyphicon() {
        return iconTextMixin.getGlyphicon();
    }

    @Override
    public void setGlyphicon(GlyphiconType iconType) {
        iconTextMixin.setGlyphicon(iconType);
    }

    @Override
    public IconType getFontAwesomeIcon() {
        return iconTextMixin.getFontAwesomeIcon();
    }

    @Override
    public void setFontAwesomeIcon(final IconType iconType) {
        iconTextMixin.setFontAwesomeIcon(iconType);
    }

    @Override
    public void clearIcon() {
        iconTextMixin.clearIcon();
    }

    @Override
    public IconPosition getIconPosition() {
        return iconTextMixin.getIconPosition();
    }

    @Override
    public void setIconPosition(final IconPosition iconPosition) {
        iconTextMixin.setIconPosition(iconPosition);
    }

    @Override
    public IconSize getIconSize() {
        return iconTextMixin.getIconSize();
    }

    @Override
    public void setIconSize(final IconSize iconSize) {
        iconTextMixin.setIconSize(iconSize);
    }

    @Override
    public IconFlip getIconFlip() {
        return iconTextMixin.getIconFlip();
    }

    @Override
    public void setIconFlip(final IconFlip iconFlip) {
        iconTextMixin.setIconFlip(iconFlip);
    }

    @Override
    public IconRotate getIconRotate() {
        return iconTextMixin.getIconRotate();
    }

    @Override
    public void setIconRotate(final IconRotate iconRotate) {
        iconTextMixin.setIconRotate(iconRotate);
    }

    @Override
    public boolean isIconBordered() {
        return iconTextMixin.isIconBordered();
    }

    @Override
    public void setIconBordered(final boolean iconBordered) {
        iconTextMixin.setIconBordered(iconBordered);
    }

    @Override
    public boolean isIconMuted() {
        return iconTextMixin.isIconMuted();
    }

    @Override
    public void setIconMuted(final boolean iconMuted) {
        iconTextMixin.setIconMuted(iconMuted);
    }

    @Override
    public boolean isIconLight() {
        return iconTextMixin.isIconLight();
    }

    @Override
    public void setIconLight(final boolean iconLight) {
        iconTextMixin.setIconLight(iconLight);
    }

    @Override
    public boolean isIconSpin() {
        return iconTextMixin.isIconSpin();
    }

    @Override
    public void setIconSpin(final boolean iconSpin) {
        iconTextMixin.setIconSpin(iconSpin);
    }

    @Override
    public boolean isIconFixedWidth() {
        return iconTextMixin.isIconFixedWidth();
    }

    @Override
    public void setIconFixedWidth(final boolean iconFixedWidth) {
        iconTextMixin.setIconFixedWidth(iconFixedWidth);
    }

}
