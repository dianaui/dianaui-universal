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

import com.dianaui.universal.core.client.ui.base.HasPull;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Pull;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Sven Jacobs
 */
public class PullMixin<T extends UIObject & HasPull> extends AbstractMixin implements HasPull {

    public PullMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public Pull getPull() {
        return Pull.fromStyleName(uiObject.getStyleName());
    }

    @Override
    public void setPull(final Pull pull) {
        StyleHelper.addUniqueEnumStyleName(uiObject, Pull.class, pull);
    }
}
