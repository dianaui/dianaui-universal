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

import com.dianaui.universal.core.client.ui.base.*;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.base.mixin.PullMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.ImageType;
import com.dianaui.universal.core.client.ui.constants.Pull;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Image extends com.google.gwt.user.client.ui.Image implements HasType<ImageType>, HasResponsiveness,
        HasPull, HasCenterBlock, HasId {

    public Image() {
        super();
        setStyleName("");
    }

    public Image(final ImageResource resource) {
        super(resource);
        setStyleName("");
    }

    public Image(final SafeUri url, final int left, final int top, final int width, final int height) {
        super(url, left, top, width, height);
        setStyleName("");
    }

    public Image(final SafeUri url) {
        super(url);
        setStyleName("");
    }

    public Image(final String url, final int left, final int top, final int width, final int height) {
        super(url, left, top, width, height);
        setStyleName("");
    }

    public Image(final String url) {
        super(url);
        setStyleName("");
    }

    @Override
    public ImageType getType() {
        return ImageType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final ImageType type) {
        StyleHelper.addEnumStyleName(this, type);
    }

    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    public void setResponsive(final boolean responsive) {
        StyleHelper.toggleStyleName(this, responsive, Styles.IMG_RESPONSIVE);
    }

    public void setAsMediaObject(final boolean asMediaObject) {
        StyleHelper.toggleStyleName(this, asMediaObject, Styles.MEDIA_OBJECT);
    }

    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    @Override
    public Pull getPull() {
        return PullMixin.getPull(this);
    }

    @Override
    public void setPull(final Pull pull) {
        PullMixin.setPull(this, pull);
    }

    @Override
    public boolean isCenterBlock() {
        return StyleHelper.containsStyle(getStyleName(), Styles.CENTER_BLOCK);
    }

    @Override
    public void setCenterBlock(boolean centerBlock) {
        StyleHelper.toggleStyleName(this, centerBlock, Styles.CENTER_BLOCK);
    }

}
