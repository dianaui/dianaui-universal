package com.dianaui.universal.core.client.ui.constants;

import com.dianaui.universal.core.client.ui.base.helper.EnumHelper;
import com.google.gwt.dom.client.Style;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public enum ButtonGroupSize implements Size, Style.HasCssName {
    LARGE("btn-group-lg"),
    DEFAULT(""),
    SMALL("btn-group-sm"),
    EXTRA_SMALL("btn-group-xs");

    private final String cssClass;

    private ButtonGroupSize(final String cssClass) {
        this.cssClass = cssClass;
    }

    public static ButtonGroupSize fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, ButtonGroupSize.class, DEFAULT);
    }

    @Override
    public String getCssName() {
        return cssClass;
    }
}
