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

import com.dianaui.universal.core.event.CarouselSlidEvent;
import com.dianaui.universal.core.event.CarouselSlidHandler;
import com.dianaui.universal.core.event.CarouselSlideEvent;
import com.dianaui.universal.core.event.CarouselSlideHandler;
import com.dianaui.universal.core.ui.constants.Attributes;
import com.dianaui.universal.core.ui.constants.Styles;
import com.dianaui.universal.core.ui.html.Div;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;

/**
 * @author Joshua Godi
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class Carousel extends Div {
    public static final String HOVER = "hover";
    private String pause = HOVER;
    public static final String CAROUSEL = "carousel";
    public static final String CYCLE = "cycle";
    public static final String PAUSE = "pause";
    public static final String PREV = "prev";
    public static final String NEXT = "next";
    // Bootstrap default values: http://getbootstrap.com/javascript/#carousel
    private int interval = 5000;
    private boolean wrap = true;

    public Carousel() {
        // Set the default styles
        setStyleName(Styles.CAROUSEL);
        addStyleName(Styles.SLIDE);

        // Set the default attribute
        getElement().setAttribute(Attributes.DATA_RIDE, CAROUSEL);
    }

    public void setInterval(final int interval) {
        this.interval = interval;
    }

    public void setPause(final String pause) {
        this.pause = pause;
    }

    public void setWrap(final boolean wrap) {
        this.wrap = wrap;
    }

    /**
     * Causes the carousel to cycle
     */
    public void cycleCarousel() {
        // TODO
        // fireMethod(getElement(), CYCLE);
    }

    /**
     * Causes the carousel to pause movement
     */
    public void pauseCarousel() {
        // TODO
        // fireMethod(getElement(), PAUSE);
    }

    /**
     * Causes the carousel to jump to that slide
     *
     * @param slideNumber slideNumber
     */
    public void jumpToSlide(final int slideNumber) {
        // TODO
        // fireMethod(getElement(), slideNumber);
    }

    /**
     * Causes the carousel to go back
     */
    public void goToPrev() {
        // TODO
        // fireMethod(getElement(), PREV);
    }

    /**
     * Causes the carousel to go to the next slide
     */
    public void goToNext() {
        // TODO
        // fireMethod(getElement(), NEXT);
    }

    public HandlerRegistration addSlideHandler(final CarouselSlideHandler carouselSlideHandler) {
        return addHandler(carouselSlideHandler, CarouselSlideEvent.getType());
    }

    public HandlerRegistration addSlidHandler(final CarouselSlidHandler slidHandler) {
        return addHandler(slidHandler, CarouselSlidEvent.getType());
    }

    /**
     * Fired when the carousel is starting to change slides
     *
     * @param evt event
     */
    private void onSlide(final Event evt) {
        fireEvent(new CarouselSlideEvent(this, evt));
    }

    /**
     * Fired when the carousel is finished changing slides
     *
     * @param evt event
     */
    private void onSlid(final Event evt) {
        fireEvent(new CarouselSlidEvent(this, evt));
    }

}
