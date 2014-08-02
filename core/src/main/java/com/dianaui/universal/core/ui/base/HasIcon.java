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
package com.dianaui.universal.core.ui.base;

import com.dianaui.universal.core.ui.constants.IconFlip;
import com.dianaui.universal.core.ui.constants.IconRotate;
import com.dianaui.universal.core.ui.constants.IconSize;
import com.dianaui.universal.core.ui.constants.IconType;

/**
 * Interface for all the properties of Icons
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public interface HasIcon extends HasGlyphicon {

    IconType getFontAwesomeIcon();

    void setFontAwesomeIcon(IconType iconType);

    void clearIcon();

    IconSize getIconSize();

    void setIconSize(IconSize iconSize);

    IconFlip getIconFlip();

    void setIconFlip(IconFlip iconFlip);

    IconRotate getIconRotate();

    void setIconRotate(IconRotate iconRotate);

    boolean isIconBordered();

    void setIconBordered(boolean iconBordered);

    boolean isIconMuted();

    void setIconMuted(boolean iconMuted);

    boolean isIconLight();

    void setIconLight(boolean iconLight);

    boolean isIconSpin();

    void setIconSpin(boolean iconSpin);

}
