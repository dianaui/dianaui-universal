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

import com.dianaui.universal.core.ui.constants.DeviceSize;

/**
 * Interface to set the visibility and hidden properties of elements
 * Example:
 * hiddenOn="SM MD"
 * hiddenOn="SM,MD"
 * visibleOn="SM LG"
 * visibleOn="MD,LG"
 * hiddenOn="LG"
 *
 * @author Joshua Godi
 */
public interface HasResponsiveness {
    /**
     * Sets the devices that the element is visible on
     *
     * @param deviceSize device size
     * @see com.dianaui.universal.core.ui.constants.DeviceSize
     */
    void setVisibleOn(final DeviceSize deviceSize);

    /**
     * Sets the devices that the element is hidden on
     *
     * @param deviceSize device sizes
     * @see com.dianaui.universal.core.ui.constants.DeviceSize
     */
    void setHiddenOn(final DeviceSize deviceSize);
}
