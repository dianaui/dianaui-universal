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

import com.dianaui.universal.core.ui.constants.Styles;
import com.dianaui.universal.core.ui.html.Div;

/**
 * Widget representing the Bootstrap Thumbnail for use with other components
 * <a href="http://getbootstrap.com/components/#thumbnails">Bootstrap Documentation</a> (Custom Content section)
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 * <b:ThumbnailPanel>
 *    Image
 *    <b:Heading size="...">Label</b:Heading>
 *    <b:Paragraph>Content</b:Paragraph>
 * </b:ThumbnailPanel>
 * }
 * </pre>
 *
 * @author Joshua Godi
 */
public class ThumbnailPanel extends Div {

    /**
     * Creates the ThumbnailPanel with the default styles
     */
    public ThumbnailPanel() {
        addStyleName(Styles.THUMBNAIL);
    }
}
