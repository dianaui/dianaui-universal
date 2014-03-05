package org.gwtbootstrap3.client.ui.base;

import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.HasValue;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class RichTextArea extends com.google.gwt.user.client.ui.RichTextArea implements
        HasValueChangeHandlers<String>, HasValue<String>, TakesValue<String>, LeafValueEditor<String> {

    private boolean valueChangeHandlerInitialized;

    public RichTextArea() {
        addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                removeStyleName("focus");
            }
        });

        addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                addStyleName("focus");
            }
        });
    }

    public void injectStyle(String css) {
        IFrameElement iframe = IFrameElement.as(getElement());

        StyleElement style = iframe.getContentDocument().createStyleElement();
        style.setPropertyString("language", "text/css");
        style.setType("text/css");

        setStyleContent(style, css);

        iframe.getContentDocument().getElementsByTagName("head").getItem(0).appendChild(style);
    }

    @Override
    public String getValue() {
        return getHTML();
    }

    @Override
    public void setValue(String value) {
        setHTML(value);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        setHTML(value);
        if (fireEvents) {
            ValueChangeEvent.fireIfNotEqual(this, getHTML(), value);
        }
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<String> handler) {
        // Initialization code
        if (!valueChangeHandlerInitialized) {
            valueChangeHandlerInitialized = true;
            addChangeHandler(new ChangeHandler() {
                public void onChange(ChangeEvent event) {
                    ValueChangeEvent.fire(RichTextArea.this, getValue());
                }
            });
        }
        return addHandler(handler, ValueChangeEvent.getType());
    }

    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return addDomHandler(handler, ChangeEvent.getType());
    }

    private static native void setStyleContent(StyleElement element, String css) /*-{
        if (element.styleSheet) {
            element.styleSheet.cssText = css;
        } else {
            element.appendChild(document.createTextNode(css));
        }
    }-*/;

}