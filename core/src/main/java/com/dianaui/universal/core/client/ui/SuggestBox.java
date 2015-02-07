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

import com.dianaui.universal.core.client.ui.base.*;
import com.dianaui.universal.core.client.ui.base.helper.StyleHelper;
import com.dianaui.universal.core.client.ui.base.mixin.EnabledMixin;
import com.dianaui.universal.core.client.ui.base.mixin.IdMixin;
import com.dianaui.universal.core.client.ui.constants.DeviceSize;
import com.dianaui.universal.core.client.ui.constants.InputSize;
import com.dianaui.universal.core.client.ui.constants.Styles;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

import java.util.Collection;
import java.util.List;

/**
 * Wrapper for a {@link com.google.gwt.user.client.ui.SuggestBox}.<br/>
 * <br/>
 * The default style is inherited from the {@link Styles#DROPDOWN_MENU}. Styling of the suggestions items need
 * a bit of css in order to be pleasing to the eye.
 * <p/>
 * <pre>
 *  .dropdown-menu .item {
 *      padding: 5px;
 *  }
 *
 *  .dropdown-menu .item-selected {
 *      background-color: #eee;
 *  }
 * </pre>
 *
 * @author Steven Jardine
 */
public class SuggestBox extends com.google.gwt.user.client.ui.SuggestBox implements HasId, HasResponsiveness, HasPlaceholder,
        HasAutoComplete, HasSize<InputSize>, HasEditorErrors<String> {

    static class CustomSuggestionDisplay extends DefaultSuggestionDisplay {

        private ResizeHandler popupResizeHandler = null;

        public CustomSuggestionDisplay() {
            super();
            final PopupPanel popup = getPopupPanel();
            popup.setStyleName(Styles.DROPDOWN_MENU);
            popup.getElement().getStyle().setDisplay(Display.BLOCK);
        }

        /**
         * Resize the popup panel to the size of the suggestBox and place it below the SuggestBox. This is not
         * ideal but works better in a mobile environment.
         *
         * @param box the box the SuggestBox.
         */
        private void resizePopup(final com.google.gwt.user.client.ui.SuggestBox box) {
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                @Override
                public void execute() {
                    Element e = box.getElement();
                    PopupPanel panel = getPopupPanel();
                    panel.setWidth((e.getAbsoluteRight() - e.getAbsoluteLeft() - 2) + Unit.PX.getType());
                    panel.setPopupPosition(e.getAbsoluteLeft(), e.getAbsoluteBottom());
                }
            });
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void showSuggestions(final com.google.gwt.user.client.ui.SuggestBox suggestBox,
                                       final Collection<? extends Suggestion> suggestions, final boolean isDisplayStringHTML,
                                       final boolean isAutoSelectEnabled, final SuggestionCallback callback) {
            super.showSuggestions(suggestBox, suggestions, isDisplayStringHTML, isAutoSelectEnabled, callback);
            resizePopup(suggestBox);
            if (popupResizeHandler == null) {
                popupResizeHandler = new ResizeHandler() {
                    private Timer timer = new Timer() {
                        public void run() {
                            resizePopup(suggestBox);
                        }
                    };

                    @Override
                    public void onResize(ResizeEvent event) {
                        timer.schedule(250);
                    }
                };
                Window.addResizeHandler(popupResizeHandler);
            }
        }

    }

    private ValueBoxBase.EditorErrorSupport errorSupport = new ValueBoxErrorSupport(this);

    /**
     * Constructor for {@link SuggestBox}. Creates a {@link MultiWordSuggestOracle} and {@link TextBox} to use
     * with this {@link SuggestBox}.
     */
    public SuggestBox() {
        this(new MultiWordSuggestOracle());
    }

    /**
     * Constructor for {@link SuggestBox}. Creates a {@link TextBox} to use with this {@link SuggestBox}.
     *
     * @param oracle the oracle for this <code>SuggestBox</code>
     */
    public SuggestBox(SuggestOracle oracle) {
        this(oracle, new TextBox());
    }

    /**
     * Constructor for {@link SuggestBox}. The text box will be removed from it's current location and wrapped
     * by the {@link SuggestBox}.
     *
     * @param oracle supplies suggestions based upon the current contents of the text widget
     * @param box    the text widget
     */
    public SuggestBox(SuggestOracle oracle, ValueBoxBase<String> box) {
        this(oracle, box, new CustomSuggestionDisplay());
    }

    /**
     * Constructor for {@link SuggestBox}. The text box will be removed from it's current location and wrapped
     * by the {@link SuggestBox}.
     *
     * @param oracle         supplies suggestions based upon the current contents of the text widget
     * @param box            the text widget
     * @param suggestDisplay the class used to display suggestions
     */
    public SuggestBox(SuggestOracle oracle, ValueBoxBase<String> box, SuggestionDisplay suggestDisplay) {
        super(oracle, box, suggestDisplay);
        setStyleName(Styles.FORM_CONTROL);
    }

    /**
     * Gets the error support.
     *
     * @return the adds the error support
     */
    public boolean getAddErrorSupport() {
        return errorSupport != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAutoComplete() {
        return getElement().getAttribute(AUTO_COMPLETE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return IdMixin.getId(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlaceholder() {
        return getElement().getAttribute(PLACEHOLDER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputSize getSize() {
        return InputSize.fromStyleName(getStyleName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return EnabledMixin.isEnabled(this);
    }

    /**
     * Sets the error support.
     *
     * @param addErrorSupport the new adds the error support
     */
    public void setAddErrorSupport(final boolean addErrorSupport) {
        if (!addErrorSupport) {
            errorSupport = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAutoComplete(final boolean autoComplete) {
        getElement().setAttribute(AUTO_COMPLETE, autoComplete ? ON : OFF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(final boolean enabled) {
        EnabledMixin.setEnabled(this, enabled);
    }

    /**
     * @param errorSupport the EditorErrorSupport to set.
     */
    public void setErrorSupport(final ValueBoxBase.EditorErrorSupport errorSupport) {
        this.errorSupport = errorSupport;
        addAttachHandler(errorSupport);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHiddenOn(final DeviceSize deviceSize) {
        StyleHelper.setHiddenOn(this, deviceSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        IdMixin.setId(this, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlaceholder(final String placeHolder) {
        getElement().setAttribute(PLACEHOLDER, placeHolder != null ? placeHolder : "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSize(final InputSize size) {
        StyleHelper.addUniqueEnumStyleName(this, InputSize.class, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
    }

    @Override
    public void showErrors(final List<EditorError> errors) {
        if (errorSupport != null) {
            errorSupport.showErrors(errors);
        }
    }

}
