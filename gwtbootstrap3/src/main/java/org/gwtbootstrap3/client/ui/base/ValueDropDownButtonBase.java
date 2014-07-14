package org.gwtbootstrap3.client.ui.base;

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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.DropDownButton;

import java.util.List;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public abstract class ValueDropDownButtonBase<T> extends DropDownButton implements LeafValueEditor<T>, HasValue<T> {

    private T value;
    private List<T> list;
    private String defaultText = "";

    public ValueDropDownButtonBase() {

    }

    public ValueDropDownButtonBase(List<T> list) {
        setList(list);
    }

    public String getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(String defaultText) {
        this.defaultText = defaultText;

        if (value == null) {
            getToggleButton().setText(defaultText);
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        getMenu().clear();

        if (list != null) {
            this.list = list;

            for (final T item : list) {
                AnchorListItem link = new AnchorListItem();
                link.setText(getDisplayValue(item, link));

                link.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        setValue(item, true);

                        hide();
                    }
                });

                getMenu().add(link);
            }
        }
    }

    public String getDisplayValue(T object, AnchorListItem item) {
        return object.toString();
    }

    public void setVisibleValue(T value) {
        setText(value != null ? value.toString() : defaultText);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        setValue(value, true);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        this.value = value;

        setVisibleValue(value);

        if (fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

}
