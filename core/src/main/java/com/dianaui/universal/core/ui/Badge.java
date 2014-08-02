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
import com.dianaui.universal.core.ui.constants.Styles;
import com.dianaui.universal.core.ui.html.Text;
import com.google.gwt.dom.client.Document;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.ui.client.adapters.HasTextEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Badge for highlighting new or unread items.
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 *     <b:Badge>42</b:Badge>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Badge extends ComplexWidget implements HasWidgets, HasText, IsEditor<LeafValueEditor<String>>,
        HasClickHandlers {

    private final Text text = new Text();

    private LeafValueEditor<String> editor;

    public Badge() {
        setElement(Document.get().createSpanElement());
        setStyleName(Styles.BADGE);
    }

    public Badge(final String text) {
        this();
        setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return text.getText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(final String text) {
        this.text.setText(text);
        insert(this.text, 0);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public LeafValueEditor<String> asEditor() {
        if (editor == null) {
            editor = HasTextEditor.of(this);
        }
        return editor;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler clickHandler) {
        return addDomHandler(clickHandler, ClickEvent.getType());
    }

}
