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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class SimplePagination extends Pagination implements HasValue<Integer> {

    private int pagesCount;
    private Integer value;

    public void init(final int pagesCount, final int activePage, final boolean hasPrevious, final boolean hasNext) {
        clear();
        previousLink = null;
        nextLink = null;

        if (pagesCount == 0) {
            return;
        }

        if (pagesCount < activePage || activePage < 0) {
            throw new RuntimeException("Active page is wrong: " + activePage);
        }

        if (hasPrevious) {
            addPreviousLink();

            previousLink.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    onPreviousClicked();
                }
            });
        }

        for (int i = 0; i < pagesCount; i++) {
            final int pageIndex = i;
            final int display = i + 1;
            final AnchorListItem page = new AnchorListItem(String.valueOf(display));
            page.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    onPageClicked(pageIndex);
                }
            });

            if (i == activePage) {
                page.setActive(true);
            }

            add(page);
        }

        if (hasNext) {
            addNextLink();

            nextLink.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    onNextClicked();
                }
            });
        }

        // update values
        this.pagesCount = pagesCount;
        value = activePage;
    }

    private void onPageClicked(int pageIndex) {
        setValue(pageIndex, true);
    }

    private void onNextClicked() {
        if (value + 1 < pagesCount) {
            setValue(value + 1, true);
        }
    }

    private void onPreviousClicked() {
        if (value - 1 > -1) {
            setValue(value - 1, true);
        }
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        setValue(value, false);
    }

    @Override
    public void setValue(Integer value, boolean fireEvents) {
        if (pagesCount < value || value < 0) {
            throw new RuntimeException("New page is wrong: " + value);
        }

        if (value.equals(this.value)) {
            return;
        }

        int index = previousLink != null ? value + 1 : value;

        for (int i = 0; i < getWidgetCount(); i++) {
            ((AnchorListItem) getWidget(i)).setActive(i == index ? true : false);
        }

        if (previousLink != null)
            previousLink.setEnabled(index == 1 ? false : true);

        if (nextLink != null)
            nextLink.setEnabled(index == pagesCount ? false : true);

        this.value = value;

        if (fireEvents) {
            ValueChangeEvent.fire(this, value);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {
        return addHandler(handler, ValueChangeEvent.getType());
    }

}
