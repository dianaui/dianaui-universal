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

import com.dianaui.universal.core.ui.constants.ValidationState;

/**
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public interface HasValidationState {

    /**
     * Gets the validation state of the widget
     *
     * @return widget's validation state
     */
    ValidationState getValidationState();

    /**
     * Sets the validation state on the widget
     *
     * @param state validation state
     */
    void setValidationState(ValidationState state);

    /**
     * Clear the validation state on the widget
     */
    void clearValidationState();

}
