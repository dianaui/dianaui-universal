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
package com.dianaui.universal.core.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * Simple text node.
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 * <b:Text>
 *    ...
 * </b:Text>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 */
public class Text extends Widget implements HasText {

    private final com.google.gwt.dom.client.Text text;
    private boolean isAttached;

    /**
     * Creates the default text node with empty text
     */
    public Text() {
        this("");
    }

    /**
     * Creates a text node with the desired text
     *
     * @param txt String text to display
     */
    public Text(final String txt) {
        text = Document.get().createTextNode(txt);
        setElement(text.<Element>cast());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return text.getData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setText(final String txt) {
        text.setData(txt);
    }

    @Override
    public boolean isAttached() {
        return isAttached;
    }

    @Override
    protected void onAttach() {
        if (isAttached()) {
            throw new IllegalStateException("Text is already attached!");
        }
        isAttached = true;
        onLoad();
        AttachEvent.fire(this, isAttached);
    }

    @Override
    protected void onDetach() {
        if (!isAttached()) {
            throw new IllegalStateException("Text is not attached!");
        }
        isAttached = false;
        AttachEvent.fire(this, false);
    }

}
