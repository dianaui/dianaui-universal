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

import com.dianaui.universal.core.ui.base.AbstractSuggestBox;
import com.dianaui.universal.core.ui.base.ValueBoxBase;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle;

/**
 * Inspired by original GWT {@link com.google.gwt.user.client.ui.SuggestBox}
 *
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class TextSuggestBox extends AbstractSuggestBox<String> {

    /**
     * Constructor for {@link TextSuggestBox}. Creates a
     * {@link com.google.gwt.user.client.ui.MultiWordSuggestOracle} and {@link TextBox} to use with this
     * {@link com.google.gwt.user.client.ui.SuggestBox}.
     */
    public TextSuggestBox() {
        this(new MultiWordSuggestOracle());
    }

    public TextSuggestBox(SuggestOracle oracle) {
        super(oracle);
    }

    public TextSuggestBox(SuggestOracle oracle, ValueBoxBase<String> box) {
        super(oracle, box);
    }

    public TextSuggestBox(SuggestOracle oracle, ValueBoxBase<String> box, SuggestionDisplay suggestDisplay) {
        super(oracle, box, suggestDisplay);
    }

    @Override
    public String getValue() {
        return box.getValue();
    }

    @Override
    protected void setValue(SuggestOracle.Suggestion newValue) {
        box.setValue(newValue.getReplacementString());
    }

    @Override
    public void setValue(String newValue) {
        box.setValue(newValue);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        box.setValue(value, fireEvents);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return box.addHandler(handler, ValueChangeEvent.getType());
    }

}
