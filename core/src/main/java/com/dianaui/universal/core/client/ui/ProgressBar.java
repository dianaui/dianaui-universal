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
package com.dianaui.universal.core.client.ui;

import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.ProgressBarType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Div;
import com.dianaui.universal.core.client.ui.html.Span;
import com.google.gwt.dom.client.Style;

/**
 * @author Joshua Godi
 */
public class ProgressBar extends Div implements HasType<ProgressBarType> {
    private final Span span = new Span();

    public ProgressBar() {
        // Default style
        setStyleName(Styles.PROGRESS_BAR);

        // Progress text
        add(span);
    }

    public void setSrOnly(final boolean srOnly) {
        span.setStyleName(Styles.SR_ONLY, srOnly);
    }

    public String getText() {
        return span.getText();
    }

    public void setText(final String text) {
        span.setText(text);
    }

    public double getPercent() {
        final String width = getElement().getStyle().getWidth();
        return width == null ? 0 : Double.valueOf(width.substring(0, width.indexOf("%")));
    }

    public void setPercent(final double percent) {
        getElement().getStyle().setWidth(percent, Style.Unit.PCT);
    }

    @Override
    public ProgressBarType getType() {
        return ProgressBarType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final ProgressBarType type) {
        StyleHelper.addUniqueEnumStyleName(this, ProgressBarType.class, type);
    }
}
