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

import com.dianaui.universal.core.client.ui.base.ComplexWidget;
import com.dianaui.universal.core.client.ui.base.HasAlignment;
import com.dianaui.universal.core.client.ui.base.HasEmphasis;
import com.dianaui.universal.core.client.ui.base.HasSubText;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.Alignment;
import com.dianaui.universal.core.client.ui.constants.Emphasis;
import com.dianaui.universal.core.client.ui.constants.HeadingSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Small;
import com.dianaui.universal.core.client.ui.html.Text;
import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Represents a Heading tag, has an optional subtext.
 * <h3>Bootstrap's Documentation</h3>
 * <a href="http://getbootstrap.com/css/#type">Typography</a>
 * <h3>Usage in UiBinder</h3>
 * <pre>
 * {@code
 * <b:Heading size="H1">
 *     <b:Text text="Heading"/>
 *     <b:Small text=" subtext"/>
 * </b:Heading>
 *
 * <b:Heading size="H1" text="Heading Text" subtext="Subtext Text"/>
 * <b:Heading size="H1" subText="Subtext Text" text="Heading Text"/>
 *
 * <b:Heading size="H1">
 *     <b:Icon type="..."/>
 *     <b:Text text="Heading with icon"/>
 * </b:Heading>
 *
 * <b:Heading size="H1">
 *     <b:Icon type="..."/>
 *     <b:Text text="Heading with icon"/>
 *     <b:Small text=" subText"/>
 * </b:Heading>
 * }
 * </pre>
 * <h3>Usage in Java</h3>
 * <pre>
 * Heading h1 = new Heading(1, "Heading Text");
 * h1.setSubText("Subtext Text); // optional
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Heading extends ComplexWidget implements HasWidgets, HasText, HasHTML, HasEmphasis, HasAlignment,
        HasSubText {

    private final Text text = new Text();
    private Small subText;

    /**
     * Creates a Heading with the passed in size.
     *
     * @param size size of the heading
     */
    @UiConstructor
    public Heading(final HeadingSize size) {
        setElement(Document.get().createHElement(size.getHeadingSize()));
    }

    /**
     * Creates a Heading with the passed in size and text.
     *
     * @param size size of the heading
     * @param text text for the heading
     */
    public Heading(final HeadingSize size, final String text) {
        this(size);
        setText(text);
    }

    /**
     * Creates a Heading with the passed in size and text.
     *
     * @param size    size of the heading
     * @param text    text for the heading
     * @param subText subtext for the heading
     */
    public Heading(final HeadingSize size, final String text, final String subText) {
        this(size, text);
        setSubText(subText);
    }

    /**
     * Returns the subtext of the heading.
     *
     * @return subtext of the heading
     */
    public String getSubText() {
        if (subText != null)
            return subText.getText();
        return null;
    }

    /**
     * Sets the subtext for the heading (wrapped in a Small tag).
     * When using the setter for this, the subtext will be added after the text
     *
     * @param subText the subtext of the heading
     */
    public void setSubText(final String text) {
        if (text == null) {
            if (subText != null) {
                subText.removeFromParent();
                subText = null;
            }
            return;
        }

        if (subText == null)
            subText = new Small();

        // Force a space between the heading and the subtext
        subText.setText(" " + text);

        if (!subText.isAttached())
            insert(subText, this.text.isAttached() ? 1 : 0);
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

        if (!this.text.isAttached())
            insert(this.text, 0);
    }

    @Override
    public String getHTML() {
        return getElement().getInnerHTML();
    }

    @Override
    public void setHTML(String html) {
        getElement().setInnerHTML(html);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Emphasis getEmphasis() {
        return Emphasis.fromStyleName(getStyleName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEmphasis(final Emphasis emphasis) {
        StyleHelper.addUniqueEnumStyleName(this, Emphasis.class, emphasis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alignment getAlignment() {
        return Alignment.fromStyleName(getStyleName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlignment(final Alignment alignment) {
        StyleHelper.addUniqueEnumStyleName(this, Alignment.class, alignment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onAttach() {
        super.onAttach();

        // Adding styles to the heading depending on the parent
        if (getParent() != null) {
            if (getParent() instanceof LinkedGroupItem) {
                addStyleName(Styles.LIST_GROUP_ITEM_HEADING);
            } else if (getParent() instanceof PanelHeader) {
                addStyleName(Styles.PANEL_TITLE);
            } else if (getParent() instanceof MediaBody) {
                addStyleName(Styles.MEDIA_HEADING);
            }
        }
    }

}
