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
package com.dianaui.universal.core.client.ui.base;

/**
 * Simple interface that will set/get the objects 'target' attribute
 *
 * @author Joshua Godi
 * @author Sven Jacobs
 * @see com.dianaui.universal.core.client.ui.constants.Attributes
 */
public interface HasTarget {

    public static final String BLANK = "_blank";

    /**
     * Get the target attribute of the object
     *
     * @return target attribute
     */
    String getTarget();

    /**
     * Set the target attribute of the object
     *
     * @param target target attribute
     */
    void setTarget(String target);

}
