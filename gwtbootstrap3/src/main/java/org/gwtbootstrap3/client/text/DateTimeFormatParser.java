package org.gwtbootstrap3.client.text;

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

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.text.shared.Parser;

import java.text.ParseException;
import java.util.Date;

/**
 * A localized parser based on {@link DateTimeFormat.PredefinedFormat#DATE_SHORT}.
 *
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class DateTimeFormatParser implements Parser<Date> {

    private final DateTimeFormat format;

    /**
     * Create an instance using {@link DateTimeFormat.PredefinedFormat#DATE_SHORT}.
     */
    public DateTimeFormatParser() {
        this(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));
    }

    /**
     * Create an instance with the given format.
     *
     * @param format date time format
     */
    public DateTimeFormatParser(DateTimeFormat format) {
        this.format = format;
    }

    public Date parse(CharSequence object) throws ParseException {
        if ("".equals(object.toString())) {
            return null;
        }

        try {
            return format.parse(object.toString());
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

}
