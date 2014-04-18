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

import com.google.gwt.user.client.DOM;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Attributes;
import org.gwtbootstrap3.client.ui.constants.GlyphiconType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class CarouselControl extends ComplexWidget {

    private final Glyphicon glyphicon = new Glyphicon();
    private final FontAwesomeIcon fontAwesomeIcon = new FontAwesomeIcon();

    public CarouselControl() {
        setElement(DOM.createAnchor());
        setStyleName(Styles.CAROUSEL_CONTROL);

        add(fontAwesomeIcon);
    }

    public void setGlyphicon(final GlyphiconType iconType) {
        if (fontAwesomeIcon.isAttached()) {
            fontAwesomeIcon.removeFromParent();
        }

        glyphicon.setType(iconType);

        add(glyphicon);
    }

    public void setFontAwesomeIcon(final IconType iconType) {
        if (glyphicon.isAttached()) {
            glyphicon.removeFromParent();
        }

        fontAwesomeIcon.setType(iconType);

        add(fontAwesomeIcon);
    }

    public void setPrev(final boolean prev) {
        getElement().removeAttribute(Attributes.DATA_SLIDE);
        getElement().setAttribute(Attributes.DATA_SLIDE, Carousel.PREV);
        StyleHelper.toggleStyleName(this, prev, Styles.LEFT);
        fontAwesomeIcon.addStyleName(Styles.ICON_PREV);
    }

    public void setNext(final boolean next) {
        getElement().removeAttribute(Attributes.DATA_SLIDE);
        getElement().setAttribute(Attributes.DATA_SLIDE, Carousel.NEXT);
        StyleHelper.toggleStyleName(this, next, Styles.RIGHT);
        fontAwesomeIcon.addStyleName(Styles.ICON_NEXT);
    }

}
