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
package com.dianaui.universal.core.ui;

import com.dianaui.universal.core.ui.base.ComplexWidget;
import com.dianaui.universal.core.ui.base.HasType;
import com.dianaui.universal.core.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.ui.constants.ListGroupItemType;
import com.dianaui.universal.core.ui.constants.Styles;
import com.dianaui.universal.core.ui.html.Span;
import com.google.gwt.dom.client.Document;

/**
 * @author Joshua Godi
 */
public class ListGroupItem extends ComplexWidget implements HasType<ListGroupItemType> {
    private final Span span = new Span();

    public ListGroupItem() {
        setElement(Document.get().createLIElement());
        setStyleName(Styles.LIST_GROUP_ITEM);

        add(span);
    }

    public String getText() {
        return span.getText();
    }

    public void setText(final String text) {
        span.setText(text);
    }

    @Override
    public ListGroupItemType getType() {
        return ListGroupItemType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final ListGroupItemType type) {
        StyleHelper.addUniqueEnumStyleName(this, ListGroupItemType.class, type);
    }
}
