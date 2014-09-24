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
package com.dianaui.universal.core.client.ui.constants;

/**
 * @author Sven Jacobs
 */
public enum ModalBackdrop {
    /**
     * Shows backdrop (greyed out background).
     */
    TRUE("true"),

    /**
     * Don't show backdrop.
     */
    FALSE("false"),

    /**
     * Show a static backdrop (modal won't be closed when user clicks on backdrop).
     */
    STATIC("static");

    private final String backdrop;

    private ModalBackdrop(final String backdrop) {
        this.backdrop = backdrop;
    }

    public String getBackdrop() {
        return backdrop;
    }
}