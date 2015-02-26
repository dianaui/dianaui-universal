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

import com.dianaui.universal.core.client.ui.base.HasPaginationSize;
import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.IconType;
import com.dianaui.universal.core.client.ui.constants.PaginationSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.UnorderedList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.SimplePager;

/**
 * Support for Bootstrap pagination (http://getbootstrap.com/components/#pagination)
 *
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Pagination extends UnorderedList implements HasResponsiveness, HasPaginationSize {

    protected AnchorListItem previousLink;
    protected AnchorListItem nextLink;

    public Pagination() {
        setStyleName(Styles.PAGINATION);
    }

    public Pagination(final PaginationSize paginationSize) {
        this();
        setPaginationSize(paginationSize);
    }

    @Override
    public PaginationSize getPaginationSize() {
        return PaginationSize.fromStyleName(getStyleName());
    }

    @Override
    public void setPaginationSize(final PaginationSize paginationSize) {
        StyleHelper.addUniqueEnumStyleName(this, PaginationSize.class, paginationSize);
    }

    public AnchorListItem getPreviousLink() {
        return previousLink;
    }

    public AnchorListItem getNextLink() {
        return nextLink;
    }

    public AnchorListItem addPreviousLink() {
        if (previousLink == null) {
            previousLink = new AnchorListItem();
            previousLink.setFontAwesomeIcon(IconType.ANGLE_DOUBLE_LEFT);
            insert(previousLink, 0);
        }
        return previousLink;
    }

    public AnchorListItem addNextLink() {
        if (nextLink == null) {
            nextLink = new AnchorListItem();
            nextLink.setFontAwesomeIcon(IconType.ANGLE_DOUBLE_RIGHT);
            add(nextLink);
        }
        return nextLink;
    }

    /**
     * This will help to rebuild the Pagination based on the data inside the SimplePager passed in.
     * Make sure to all this after adding/remove data from any of the grid to ensure that this stays
     * current with the SimplePager.
     * ex.
     * dataProvider.getList().addAll(newData);
     * pagination.rebuild(mySimplePager);
     *
     * @param pager the SimplePager of the CellTable/DataGrid
     */
    public void rebuild(final SimplePager pager) {
        clear();
        previousLink = null;
        nextLink = null;

        if (pager.getPageCount() == 0) {
            return;
        }

        final AnchorListItem prev = addPreviousLink();
        prev.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pager.previousPage();
            }
        });
        prev.setEnabled(pager.hasPreviousPage());

        for (int i = 0; i < pager.getPageCount(); i++) {
            final int display = i + 1;
            final AnchorListItem page = new AnchorListItem(String.valueOf(display));
            page.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(final ClickEvent event) {
                    pager.setPage(display - 1);
                }
            });

            if (i == pager.getPage()) {
                page.setActive(true);
            }

            add(page);
        }

        final AnchorListItem next = addNextLink();
        next.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                pager.nextPage();
            }
        });
        next.setEnabled(pager.hasNextPage());
    }

}
