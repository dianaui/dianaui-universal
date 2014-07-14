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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.modal.ModalContent;
import org.gwtbootstrap3.client.ui.base.modal.ModalDialog;
import org.gwtbootstrap3.client.ui.base.modal.ModalWithBackdrop;
import org.gwtbootstrap3.client.ui.constants.Attributes;

/**
 * Modal dialog.
 * <h3>UiBinder example</h3>
 * <pre>
 * {@code
 *     <b:Modal title="Important information" b:id="modal1">
 *         <b:ModalBody>
 *             <g:HTML>Lorem ipsum...</g:HTML>
 *         </b:ModalBody>
 *         <b:ModalFooter>
 *             <b:Button type="PRIMARY">Do something</b:Button>
 *             <b:Button type="DANGER" dismiss="MODAL">Close</b:Button>
 *         </b:ModalFooter>
 *     </b:Modal>
 *     <b:Button target="#modal1" toggle="MODAL">Show modal</b:Button>
 * }
 * </pre>
 * It's also possible to specify a custom modal header:
 * <pre>
 * {@code
 *     <b:Modal>
 *         <b:ModalHeader>
 *             <g:HTML>
 *                 <h4>Custom header</h4>
 *             </g:HTML>
 *         </b:ModalHeader>
 *         ...
 *     </b:Modal>
 * }
 * </pre>
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 * @see ModalHeader
 * @see ModalBody
 * @see ModalFooter
 * @see org.gwtbootstrap3.client.shared.event.ShowEvent
 * @see org.gwtbootstrap3.client.shared.event.ShownEvent
 * @see org.gwtbootstrap3.client.shared.event.HideEvent
 * @see org.gwtbootstrap3.client.shared.event.HiddenEvent
 */
public class Modal extends ModalWithBackdrop implements IsClosable, HasResponsiveness {

    private final ModalContent content = new ModalContent();
    private final ModalDialog dialog = new ModalDialog();
    private ModalHeader header = new ModalHeader();

    public Modal() {
        content.add(header);
        dialog.add(content);

        add(dialog);
    }

    // TODO
    public void setKeyboard(final boolean keyboard) {
        getElement().setAttribute(Attributes.DATA_KEYBOARD, Boolean.toString(keyboard));
    }

    @Override
    public void setWidth(String width) {
        dialog.setWidth(width);
    }

    @Override
    public boolean isClosable() {
        return header.isClosable();
    }

    @Override
    public void setClosable(final boolean closable) {
        header.setClosable(closable);

        if (closable) {
            header.getCloseButton().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    hide();
                }
            });
        }
    }

    @Override
    public void setVisibleOn(final String deviceSizeString) {
        StyleHelper.setVisibleOn(this, deviceSizeString);
    }

    @Override
    public void setHiddenOn(final String deviceSizeString) {
        StyleHelper.setHiddenOn(this, deviceSizeString);
    }

    @Override
    public void add(final Widget w) {
        // User can supply own ModalHeader
        if (w instanceof ModalHeader) {
            header.removeFromParent();
            header = (ModalHeader) w;
        }

        if (w instanceof ModalComponent) {
            content.add(w);
        } else {
            super.add(w);
        }
    }

    @Override
    public void setTitle(final String title) {
        header.setTitle(title);
    }

}
