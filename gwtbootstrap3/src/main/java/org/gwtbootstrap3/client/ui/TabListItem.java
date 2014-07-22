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
import com.google.gwt.user.client.Event;
import org.gwtbootstrap3.client.ui.constants.HasActive;
import org.gwtbootstrap3.client.ui.constants.HasTarget;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class TabListItem extends AnchorListItem implements HasTarget {

    private int toggleIndex = -1;

    public TabListItem() {
        this("");
    }

    public TabListItem(final String text) {
        super(text);
        setToggle(Toggle.TAB);
    }

    @Override
    public String getHref() {
        return getTarget();
    }

    /**
     * We override set href here because we want to ensure that projects with gwt places and gwtp
     * don't try to execute a place change event with it being clicked
     */
    @Override
    public void setHref(String href) {
        setTarget(href);
    }

    @Override
    public String getTarget() {
        return anchor.getTarget();
    }

    @Override
    public void setTarget(final String target) {
        anchor.setTarget(target);
    }

    public int getToggleIndex() {
        return toggleIndex;
    }

    public void setToogleIndex(final int toggleIndex) {
        this.toggleIndex = toggleIndex;
    }

    public void onBrowserEvent(final Event event) {
        super.onBrowserEvent(event);

        switch (DOM.eventGetType(event)) {
            case Event.ONCLICK:
                if (getParent() instanceof NavTabs) {
                    NavTabs tabs = (NavTabs) getParent();

                    uncheckTabs(tabs);

                    setActive(true);

                    tabs.getContent().selectTab(getToggleIndex());
                } else if (getParent() instanceof DropDownMenu &&
                        getParent().getParent() instanceof ListDropDown &&
                        getParent().getParent().getParent() instanceof NavTabs) {
                    NavTabs tabs = (NavTabs) getParent().getParent().getParent();

                    uncheckTabs(tabs);

                    ((ListDropDown) getParent().getParent()).setActive(true);

                    tabs.getContent().selectTab(getToggleIndex());
                }
                break;
        }
    }

    protected void onAttach() {
        super.onAttach();

        sinkEvents(Event.ONCLICK);
    }

    private void uncheckTabs(final NavTabs tabs) {
        for (int i = 0; i < tabs.getWidgetCount(); i++) {
            ((HasActive) tabs.getWidget(i)).setActive(false);
        }
    }

}
