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

import com.dianaui.universal.core.client.event.HiddenEvent;
import com.dianaui.universal.core.client.event.HideEvent;
import com.dianaui.universal.core.client.event.ShowEvent;
import com.dianaui.universal.core.client.event.ShownEvent;
import com.dianaui.universal.core.client.ui.base.HasResponsiveness;
import com.dianaui.universal.core.client.ui.base.HasType;
import com.dianaui.universal.core.client.ui.base.button.CloseButton;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.constants.AlertType;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.dianaui.universal.core.client.ui.html.Div;
import com.dianaui.universal.core.client.ui.html.Text;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Alert block.
 * Use {@link #setDismissable(boolean)} to add a close ("x") button.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Alert extends Div implements HasWidgets, HasText, HasType<AlertType>, HasResponsiveness {

    protected static int DEFAULT_TRANSITION_MS = 150;
    protected int transitionMs = DEFAULT_TRANSITION_MS;

    private final Text text = new Text();
    protected CloseButton closeButton;

    /**
     * Builds a default alert
     */
    public Alert() {
        setStyleName(Styles.ALERT);
        addStyleName(Styles.IN);
        setType(AlertType.WARNING);
    }

    /**
     * Builds a default alert with the desired text
     *
     * @param text text for the alert
     */
    public Alert(final String text) {
        this();
        setText(text);
    }

    /**
     * Builds an alert with the desired text and type
     *
     * @param text text for the alert
     * @param type type for the alert
     */
    public Alert(final String text, final AlertType type) {
        this(text);
        setType(type);
    }

    public Alert(final SafeHtml safeHtml) {
        this(safeHtml.asString());
    }

    public Alert(final SafeHtml safeHtml, final AlertType type) {
        this(safeHtml.asString(), type);
    }

    @Override
    public AlertType getType() {
        return AlertType.fromStyleName(getStyleName());
    }

    /**
     * Sets alert type.
     *
     * @param type Alert type
     * @see AlertType
     */
    @Override
    public void setType(final AlertType type) {
        StyleHelper.addUniqueEnumStyleName(this, AlertType.class, type);
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

    public boolean isDismissable() {
        return closeButton != null;
    }

    /**
     * Adds a close button to the alert
     *
     * @param dismissable Adds close button when {@code true}
     */
    public void setDismissable(final boolean dismissable) {
        if (dismissable) {
            closeButton = new CloseButton();
            insert(closeButton, (Element) getElement(), 0, true);
            addStyleName(Styles.ALERT_DISMISSABLE);
        } else {
            closeButton.removeFromParent();
            closeButton = null;
            removeStyleName(Styles.ALERT_DISMISSABLE);
        }
    }

    /**
     * If set Alert will fade in/out.
     *
     * @param fade If {@code true} alert will fade in/out
     */
    public void setFade(final boolean fade) {
        if (fade) {
            addStyleName(Styles.FADE);
            transitionMs = DEFAULT_TRANSITION_MS;
        } else {
            removeStyleName(Styles.FADE);
            transitionMs = 0;
        }
    }

    public void show() {
        if (!isViewing()) {
            fireEvent(new ShowEvent());

            addStyleName(Styles.IN);

            new Timer() {
                @Override
                public void run() {
                    Alert.this.fireEvent(new ShownEvent());
                }
            }.schedule(transitionMs);
        }
    }

    public void hide() {
        if (isViewing()) {
            fireEvent(new HideEvent());

            removeStyleName(Styles.IN);

            new Timer() {
                @Override
                public void run() {
                    Alert.this.fireEvent(new HiddenEvent());
                }
            }.schedule(transitionMs);
        }
    }

    public boolean isViewing() {
        return isAttached() && StyleHelper.containsStyle(getStyleName(), Styles.IN);
    }

    public void toggle() {
        if (isViewing()) {
            hide();
        } else {
            show();
        }
    }

    /**
     * Closes alert.
     */
    public void close() {
        hide();

        new Timer() {
            @Override
            public void run() {
                removeFromParent();
            }
        }.schedule(transitionMs);
    }

}
