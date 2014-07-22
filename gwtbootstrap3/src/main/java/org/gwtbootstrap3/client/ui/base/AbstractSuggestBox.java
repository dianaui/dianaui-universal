package org.gwtbootstrap3.client.ui.base;

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

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.DropDownMenu;
import org.gwtbootstrap3.client.ui.constants.HasEnabled;
import org.gwtbootstrap3.client.ui.constants.HasPlaceholder;

import java.util.Collection;

/**
 * Inspired by original GWT {@link com.google.gwt.user.client.ui.SuggestBox}
 *
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public abstract class AbstractSuggestBox<T> extends Composite implements LeafValueEditor<T>, HasValue<T>,
        HasSelectionHandlers<SuggestOracle.Suggestion>, HasText, HasEnabled, Focusable, HasAllFocusHandlers,
        HasPlaceholder {

    protected final SuggestionDisplay display;
    protected final ValueBoxBase<String> box;
    private final SuggestBox.SuggestionCallback suggestionCallback = new SuggestBox.SuggestionCallback() {
        public void onSuggestionSelected(SuggestOracle.Suggestion suggestion) {
            box.setFocus(true);
            setNewSelection(suggestion);
        }
    };
    private final SuggestOracle.Callback callback = new SuggestOracle.Callback() {
        public void onSuggestionsReady(SuggestOracle.Request request, SuggestOracle.Response response) {
            // If disabled while request was in-flight, drop it
            if (!isEnabled()) {
                return;
            }

            display.setMoreSuggestions(response.hasMoreSuggestions(), response.getMoreSuggestionsCount());
            display.showSuggestions(AbstractSuggestBox.this, response.getSuggestions(),
                    oracle.isDisplayStringHTML(), isAutoSelectEnabled(),
                    suggestionCallback);
        }
    };
    private int limit = 20;
    private boolean selectsFirstItem = true;
    private SuggestOracle oracle;
    private String currentText;

    /**
     * Constructor for {@link SuggestBox}. Creates a {@link org.gwtbootstrap3.client.ui.TextBox} to use with
     * this {@link SuggestBox}.
     *
     * @param oracle the oracle for this <code>SuggestBox</code>
     */
    public AbstractSuggestBox(SuggestOracle oracle) {
        this(oracle, new org.gwtbootstrap3.client.ui.TextBox());
    }

    /**
     * Constructor for {@link SuggestBox}. The text box will be removed from it's
     * current location and wrapped by the {@link SuggestBox}.
     *
     * @param oracle supplies suggestions based upon the current contents of the
     *               text widget
     * @param box    the text widget
     */
    public AbstractSuggestBox(SuggestOracle oracle, ValueBoxBase<String> box) {
        this(oracle, box, new DefaultSuggestionDisplay());
    }

    /**
     * Constructor for {@link SuggestBox}. The text box will be removed from it's
     * current location and wrapped by the {@link SuggestBox}.
     *
     * @param oracle         supplies suggestions based upon the current contents of the
     *                       text widget
     * @param box            the text widget
     * @param suggestDisplay the class used to display suggestions
     */
    public AbstractSuggestBox(SuggestOracle oracle, ValueBoxBase<String> box, SuggestionDisplay suggestDisplay) {
        this.box = box;
        this.display = suggestDisplay;
        initWidget(box);

        addEventsToTextBox();

        setOracle(oracle);
    }

    /**
     * Get the {@link SuggestionDisplay} used to display suggestions.
     *
     * @return the {@link SuggestionDisplay}
     */
    public SuggestionDisplay getSuggestionDisplay() {
        return display;
    }

    /**
     * Gets the suggest box's {@link com.google.gwt.user.client.ui.SuggestOracle}.
     *
     * @return the {@link SuggestOracle}
     */
    public SuggestOracle getSuggestOracle() {
        return oracle;
    }

    /**
     * Gets the limit for the number of suggestions that should be displayed for
     * this box. It is up to the current {@link SuggestOracle} to enforce this
     * limit.
     *
     * @return the limit for the number of suggestions
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit to the number of suggestions the oracle should provide. It
     * is up to the oracle to enforce this limit.
     *
     * @param limit the limit to the number of suggestions provided
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Returns whether or not the first suggestion will be automatically selected.
     * This behavior is on by default.
     *
     * @return true if the first suggestion will be automatically selected
     */
    public boolean isAutoSelectEnabled() {
        return selectsFirstItem;
    }

    /**
     * Turns on or off the behavior that automatically selects the first suggested
     * item. This behavior is on by default.
     *
     * @param selectsFirstItem Whether or not to automatically select the first
     *                         suggestion
     */
    public void setAutoSelectEnabled(boolean selectsFirstItem) {
        this.selectsFirstItem = selectsFirstItem;
    }

    /**
     * Gets whether this widget is enabled.
     *
     * @return <code>true</code> if the widget is enabled
     */
    @Override
    public boolean isEnabled() {
        return box.isEnabled();
    }

    /**
     * Sets whether this widget is enabled.
     *
     * @param enabled <code>true</code> to enable the widget, <code>false</code>
     *                to disable it
     */
    @Override
    public void setEnabled(boolean enabled) {
        box.setEnabled(enabled);
        if (!enabled) {
            display.hideSuggestions();
        }
    }

    protected abstract void setValue(SuggestOracle.Suggestion newValue);

    @Override
    public String getText() {
        return box.getText();
    }

    @Override
    public void setText(String text) {
        box.setText(text);
    }

    @Override
    public int getTabIndex() {
        return box.getTabIndex();
    }

    @Override
    public void setTabIndex(int index) {
        box.setTabIndex(index);
    }

    @Override
    public void setAccessKey(char key) {
        box.setAccessKey(key);
    }

    public void setFocus(boolean focused) {
        box.setFocus(focused);
    }

    @Override
    public String getPlaceholder() {
        return box.getPlaceholder();
    }

    @Override
    public void setPlaceholder(String placeholder) {
        box.setPlaceholder(placeholder);
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return box.addBlurHandler(handler);
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return box.addFocusHandler(handler);
    }

    @Override
    public HandlerRegistration addSelectionHandler(SelectionHandler<SuggestOracle.Suggestion> handler) {
        return addHandler(handler, SelectionEvent.getType());
    }

    void showSuggestions(String query) {
        if (query.length() == 0) {
            oracle.requestDefaultSuggestions(new SuggestOracle.Request(null, limit), callback);
        } else {
            oracle.requestSuggestions(new SuggestOracle.Request(query, limit), callback);
        }
    }

    /**
     * Set the new suggestion in the text box.
     *
     * @param curSuggestion the new suggestion
     */
    protected void setNewSelection(SuggestOracle.Suggestion curSuggestion) {
        assert curSuggestion != null : "suggestion cannot be null";
        currentText = curSuggestion.getReplacementString();
        setText(currentText);
        setValue(curSuggestion);
        display.hideSuggestions();
        fireSuggestionEvent(curSuggestion);
    }

    @Override
    protected void onDetach() {
        super.onDetach();

        display.hideSuggestions();
    }

    private void refreshSuggestions() {
        // Get the raw text.
        String text = getText();
        if (text.equals(currentText)) {
            return;
        } else {
            currentText = text;
        }
        showSuggestions(text);
    }

    private void addEventsToTextBox() {
        class TextBoxEvents implements KeyDownHandler, KeyUpHandler, ValueChangeHandler<String> {

            public void onKeyDown(KeyDownEvent event) {
                switch (event.getNativeKeyCode()) {
                    case KeyCodes.KEY_DOWN:
                        display.moveSelectionDown();
                        break;
                    case KeyCodes.KEY_UP:
                        display.moveSelectionUp();
                        break;
                    case KeyCodes.KEY_ENTER:
                    case KeyCodes.KEY_TAB:
                        SuggestOracle.Suggestion suggestion = display.getCurrentSelection();
                        if (suggestion == null) {
                            display.hideSuggestions();
                        } else {
                            setNewSelection(suggestion);
                        }
                        break;
                }
            }

            public void onKeyUp(KeyUpEvent event) {
                // After every user key input, refresh the popup's suggestions.
                refreshSuggestions();
            }

            public void onValueChange(ValueChangeEvent<String> event) {
                delegateEvent(AbstractSuggestBox.this, event);
            }
        }

        TextBoxEvents events = new TextBoxEvents();
        box.addKeyDownHandler(events);
        box.addKeyUpHandler(events);
        box.addValueChangeHandler(events);
    }

    private void fireSuggestionEvent(SuggestOracle.Suggestion selectedSuggestion) {
        SelectionEvent.fire(this, selectedSuggestion);
    }

    /**
     * Sets the suggestion oracle used to create suggestions.
     *
     * @param oracle the oracle
     */
    private void setOracle(SuggestOracle oracle) {
        this.oracle = oracle;
    }

    /**
     * Used to display suggestions to the user.
     */
    public abstract static class SuggestionDisplay implements IsWidget {

        /**
         * Get the currently selected {@link com.google.gwt.user.client.ui.SuggestOracle.Suggestion} in the display.
         *
         * @return the current suggestion, or null if none selected
         */
        protected abstract SuggestOracle.Suggestion getCurrentSelection();

        /**
         * Hide the list of suggestions from view.
         */
        protected abstract void hideSuggestions();

        /**
         * Highlight the suggestion directly below the current selection in the
         * list.
         */
        protected abstract void moveSelectionDown();

        /**
         * Highlight the suggestion directly above the current selection in the
         * list.
         */
        protected abstract void moveSelectionUp();

        /**
         * Accepts information about whether there were more suggestions matching
         * than were provided to {@link #showSuggestions}.
         *
         * @param hasMoreSuggestions true if more matches were available
         * @param numMoreSuggestions number of more matches available. If the
         *                           specific number is unknown, 0 will be passed.
         */
        protected void setMoreSuggestions(boolean hasMoreSuggestions,
                                          int numMoreSuggestions) {
            // Subclasses may optionally implement.
        }

        /**
         * Update the list of visible suggestions.
         * Use care when using isDisplayStringHtml; it is an easy way to expose
         * script-based security problems.
         *
         * @param suggestBox          the suggest box where the suggestions originated
         * @param suggestions         the suggestions to show
         * @param isDisplayStringHTML should the suggestions be displayed as HTML
         * @param isAutoSelectEnabled if true, the first item should be selected
         *                            automatically
         * @param callback            the callback used when the user makes a suggestion
         */
        protected abstract void showSuggestions(AbstractSuggestBox suggestBox,
                                                Collection<? extends SuggestOracle.Suggestion> suggestions,
                                                boolean isDisplayStringHTML, boolean isAutoSelectEnabled,
                                                SuggestBox.SuggestionCallback callback);

    }

    public static class DefaultSuggestionDisplay extends SuggestionDisplay {

        private DropDownMenu menu;

        private boolean hideWhenEmpty = true;

        /**
         * Construct a new {@link DefaultSuggestionDisplay}.
         */
        public DefaultSuggestionDisplay() {
            menu = new DropDownMenu();
        }

        /**
         * Check whether or not the suggestion list is hidden when there are no
         * suggestions to display.
         *
         * @return true if hidden when empty, false if not
         */
        public boolean isSuggestionListHiddenWhenEmpty() {
            return hideWhenEmpty;
        }

        /**
         * Set whether or not the suggestion list should be hidden when there are
         * no suggestions to display. Defaults to true.
         *
         * @param hideWhenEmpty true to hide when empty, false not to
         */
        public void setSuggestionListHiddenWhenEmpty(boolean hideWhenEmpty) {
            this.hideWhenEmpty = hideWhenEmpty;
        }

        /**
         * Check whether or not the list of suggestions is being shown.
         *
         * @return true if the suggestions are visible, false if not
         */
        public boolean isSuggestionListShowing() {
            return menu.isShowing();
        }

        @Override
        public void hideSuggestions() {
            menu.hide();
        }

        @Override
        public void moveSelectionDown() {
            // Make sure that the menu is actually showing. These keystrokes
            // are only relevant when choosing a suggestion.
            if (isSuggestionListShowing()) {
                // If nothing is selected, getSelectedItemIndex will return -1 and we
                // will select index 0 (the first item) by default.
                menu.selectItem(menu.getSelectedItemIndex() + 1);
            }
        }

        @Override
        public void moveSelectionUp() {
            // Make sure that the menu is actually showing. These keystrokes
            // are only relevant when choosing a suggestion.
            if (isSuggestionListShowing()) {
                // if nothing is selected, then we should select the last suggestion by
                // default. This is because, in some cases, the suggestions menu will
                // appear above the text box rather than below it (for example, if the
                // text box is at the bottom of the window and the suggestions will not
                // fit below the text box). In this case, users would expect to be able
                // to use the up arrow to navigate to the suggestions.
                if (menu.getSelectedItemIndex() == -1) {
                    menu.selectItem(menu.getWidgetCount() - 1);
                } else {
                    menu.selectItem(menu.getSelectedItemIndex() - 1);
                }
            }
        }

        @Override
        public void showSuggestions(final AbstractSuggestBox suggestBox,
                                    Collection<? extends SuggestOracle.Suggestion> suggestions,
                                    boolean isDisplayStringHTML, boolean isAutoSelectEnabled,
                                    SuggestBox.SuggestionCallback callback) {
            // Hide the popup if there are no suggestions to display.
            boolean anySuggestions = (suggestions != null && suggestions.size() > 0);
            if (!anySuggestions && hideWhenEmpty) {
                hideSuggestions();
                return;
            }

            menu.clear();

            for (final SuggestOracle.Suggestion suggestion : suggestions) {
                final SuggestionMenuItem item = new SuggestionMenuItem(suggestion, isDisplayStringHTML);

                item.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        menu.hide();

                        suggestBox.setValue(suggestion);
                    }
                });

                menu.add(item);
            }

            menu.show(suggestBox);
        }

        @Override
        public Widget asWidget() {
            return menu;
        }

        @Override
        protected SuggestOracle.Suggestion getCurrentSelection() {
            if (!isSuggestionListShowing()) {
                return null;
            }
            AbstractListItem item = menu.getSelectedItem();
            return item == null ? null : ((SuggestionMenuItem) item).getSuggestion();
        }

    }

    /**
     * Class for menu items in a SuggestionMenu. A SuggestionMenuItem differs from
     * a MenuItem in that each item is backed by a Suggestion object. The text of
     * each menu item is derived from the display string of a Suggestion object,
     * and each item stores a reference to its Suggestion object.
     */
    private static class SuggestionMenuItem extends AnchorListItem {

        private SuggestOracle.Suggestion suggestion;

        public SuggestionMenuItem(SuggestOracle.Suggestion suggestion, boolean asHTML) {
            super(suggestion.getDisplayString(), asHTML);

            setSuggestion(suggestion);
        }

        public SuggestOracle.Suggestion getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestOracle.Suggestion suggestion) {
            this.suggestion = suggestion;
        }

    }

}
