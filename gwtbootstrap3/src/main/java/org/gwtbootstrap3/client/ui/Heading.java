package org.gwtbootstrap3.client.ui;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.HasAlignment;
import org.gwtbootstrap3.client.ui.base.HasEmphasis;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.*;
import org.gwtbootstrap3.client.ui.html.Small;
import org.gwtbootstrap3.client.ui.html.Text;

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
 * <b:Heading size="H1" subtext="Subtext Text" text="Heading Text"/>
 *
 * <b:Heading size="H1">
 *     <b:Icon type="..."/>
 *     <b:Text text="Heading with icon"/>
 * </b:Heading>
 *
 * <b:Heading size="H1">
 *     <b:Icon type="..."/>
 *     <b:Text text="Heading with icon"/>
 *     <b:Small text=" subtext"/>
 * </b:Heading>
 * }
 * </pre>
 * <h3>Usage in Java</h3>
 * <pre>
 * Heading h1 = new Heading(1, "Heading Text");
 * h1.setSubtext("Subtext Text); // optional
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 */
public class Heading extends ComplexWidget implements HasWidgets, HasText, HasEmphasis, HasAlignment {

    private final Small subtext = new Small();
    private final Text text = new Text();

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
     * @param subtext subtext for the heading
     */
    public Heading(final HeadingSize size, final String text, final String subtext) {
        this(size, text);
        setSubtext(subtext);
    }

    /**
     * Returns the subtext of the heading.
     *
     * @return subtext of the heading
     */
    public String getSubtext() {
        return subtext.getText();
    }

    /**
     * Sets the subtext for the heading (wrapped in a Small tag).
     * When using the setter for this, the subtext will be added after the text
     *
     * @param subtext the subtext of the heading
     */
    public void setSubtext(final String subtext) {
        // Force a space between the heading and the subtext
        this.subtext.setText(" " + subtext);
        add(this.subtext);
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
