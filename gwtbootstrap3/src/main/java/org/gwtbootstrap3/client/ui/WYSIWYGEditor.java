package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GWT Widgets
 * %%
 * Copyright (C) 2014 GWT Widgets
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

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasValue;
import org.gwtbootstrap3.client.ui.base.RichTextArea;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.base.HasSize;
import org.gwtbootstrap3.client.ui.html.Div;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class WYSIWYGEditor extends Div implements HasValueChangeHandlers<String>, HasValue<String>,
        TakesValue<String>, LeafValueEditor<String>, HasSize<ButtonSize> {

    private RichTextToolbar toolbar;
    private RichTextArea textArea;

    public WYSIWYGEditor() {
        textArea = new RichTextArea();
        toolbar = new RichTextToolbar(textArea);

        add(toolbar);
        add(textArea);
    }

    public RichTextToolbar getToolbar() {
        return toolbar;
    }

    public RichTextArea getTextArea() {
        return textArea;
    }

    @Override
    public String getValue() {
        return textArea.getValue();
    }

    @Override
    public void setValue(String value) {
        textArea.setValue(value);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        textArea.setValue(value, fireEvents);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler) {
        return textArea.addValueChangeHandler(handler);
    }

    @Override
    public ButtonSize getSize() {
        return toolbar.boldButton.getSize();
    }

    @Override
    public void setSize(ButtonSize size) {
        toolbar.boldButton.setSize(size);
        toolbar.italicButton.setSize(size);
        toolbar.underlineButton.setSize(size);
        toolbar.subscriptButton.setSize(size);
        toolbar.superscriptButton.setSize(size);
        toolbar.strikethroughButton.setSize(size);
        toolbar.indentButton.setSize(size);
        toolbar.outdentButton.setSize(size);
        toolbar.justifyLeftButton.setSize(size);
        toolbar.justifyCenterButton.setSize(size);
        toolbar.justifyRightButton.setSize(size);
        toolbar.hrButton.setSize(size);
        toolbar.olButton.setSize(size);
        toolbar.ulButton.setSize(size);
        toolbar.imageButton.setSize(size);
        toolbar.linkButton.setSize(size);
        toolbar.unlinkButton.setSize(size);
        toolbar.removeFormatButton.setSize(size);
        toolbar.undoButton.setSize(size);
        toolbar.redoButton.setSize(size);
    }
}
