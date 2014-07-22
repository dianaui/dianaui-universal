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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiConstructor;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.ElementTags;
import org.gwtbootstrap3.client.ui.constants.GlyphiconType;
import org.gwtbootstrap3.client.ui.constants.HasType;
import org.gwtbootstrap3.client.ui.constants.Styles;

/**
 * Simple put, an icon.
 *
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see org.gwtbootstrap3.client.ui.constants.IconType
 */
public class Glyphicon extends ComplexWidget implements HasType<GlyphiconType> {

    public Glyphicon() {
        this(Document.get().createElement(ElementTags.I));
    }

    protected Glyphicon(Element element) {
        setElement(element);
        addStyleName(Styles.GLYPHICON_BASE);
    }

    @UiConstructor
    public Glyphicon(final GlyphiconType type) {
        this();
        setType(type);
    }

    @Override
    public GlyphiconType getType() {
        return GlyphiconType.fromStyleName(getStyleName());
    }

    @Override
    public void setType(final GlyphiconType type) {
        StyleHelper.addUniqueEnumStyleName(this, GlyphiconType.class, type);
    }

}
