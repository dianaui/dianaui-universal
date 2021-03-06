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

import com.dianaui.universal.core.client.ui.base.IsClosable;
import com.dianaui.universal.core.client.ui.base.ModalComponent;
import com.dianaui.universal.core.client.ui.base.button.CloseButton;
import com.dianaui.universal.core.client.ui.constants.HeadingSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * @author Sven Jacobs
 * @author Joshua Godi
 * @see Modal
 */
public class ModalHeader extends FlowPanel implements ModalComponent, IsClosable {

    private final Heading heading = new Heading(HeadingSize.H4);
    private CloseButton closeButton = null;

    public ModalHeader() {
        setStyleName(Styles.MODAL_HEADER);

        heading.setStyleName(Styles.MODAL_TITLE);
    }

    @Override
    public void setTitle(final String title) {
        heading.setText(title);

        if (heading.getParent() == null) {
            add(heading);
        }
    }

    public CloseButton getCloseButton() {
        return closeButton;
    }

    @Override
    public boolean isClosable() {
        return closeButton != null && closeButton.getParent() != null;
    }

    @Override
    public void setClosable(final boolean closable) {
        if (closable) {
            closeButton = closeButton == null ? new CloseButton() : closeButton;
            insert(closeButton, (Element) getElement(), 0, true);
        } else {
            closeButton.removeFromParent();
            closeButton = null;
        }
    }

}
