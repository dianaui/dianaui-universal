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

import com.dianaui.universal.core.client.ui.AnchorListItem;
import com.dianaui.universal.core.client.ui.base.AbstractListItem;
import com.dianaui.universal.core.client.ui.base.ComplexWidget;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget representing an Unordered List
 * ** Children must be of type ListItem
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 *     <b:UnorderedList>
 *         ... [ListItems]
 *     </b:UnorderedList>
 * }
 * </pre>
 *
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see com.dianaui.universal.core.client.ui.ListItem
 */
public class UnorderedList extends ComplexWidget {

    /**
     * Creates an empty list.
     */
    public UnorderedList() {
        setElement(Document.get().createULElement());
    }

    /**
     * Creates a list and adds the given widgets.
     *
     * @param widgets widgets to be added
     */
    public UnorderedList(final AbstractListItem... widgets) {
        this();

        // Add all the list items to the widget
        for (final AbstractListItem li : widgets) {
            add(li);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Widget child) {
        if (isListIcons()) {
            if (child instanceof AnchorListItem) {
                ((AnchorListItem) child).setIconList(true);
            }
        }
        super.add(child);
    }

    /**
     * Returns a boolean of whether or not the UnorderedList is unstyled
     *
     * @return true/false for unstyled or not
     */
    public boolean isUnstyled() {
        return StyleHelper.containsStyle(Styles.UNSTYLED, getStyleName());
    }

    /**
     * Sets the UnorderList to be unstyled
     *
     * @param unstyled boolean true/false to make unstyled
     */
    public void setUnstyled(final boolean unstyled) {
        setStyleName(Styles.UNSTYLED, unstyled);
    }

    /**
     * Returns a boolean of whether or not the UnorderedList in inline
     *
     * @return true/false for inline or not
     */
    public boolean isInline() {
        return StyleHelper.containsStyle(Styles.LIST_INLINE, getStyleName());
    }

    /**
     * Sets the UnorderedList to appear inline rather then stacked
     *
     * @param inline true/false for inline or not
     */
    public void setInline(final boolean inline) {
        StyleHelper.toggleStyleName(this, inline, Styles.LIST_INLINE);
    }

    public boolean isListIcons() {
        return StyleHelper.containsStyle(Styles.FONT_AWESOME_UL, getStyleName());
    }

    public void setListIcons(final boolean listIcons) {
        StyleHelper.toggleStyleName(this, listIcons, Styles.FONT_AWESOME_UL);

        for (Widget child : getChildren()) {
            if (child instanceof AnchorListItem) {
                ((AnchorListItem) child).setIconList(true);
            }
        }
    }

}
