package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.base.HasFormValue;
import org.gwtbootstrap3.client.ui.base.HasId;
import org.gwtbootstrap3.client.ui.base.HasPull;
import org.gwtbootstrap3.client.ui.base.HasResponsiveness;
import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.base.mixin.EnabledMixin;
import org.gwtbootstrap3.client.ui.base.mixin.IdMixin;
import org.gwtbootstrap3.client.ui.base.mixin.PullMixin;
import org.gwtbootstrap3.client.ui.constants.DeviceSize;
import org.gwtbootstrap3.client.ui.constants.Pull;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A simple radio button widget, with no label.
 */
public class SimpleRadioButton extends com.google.gwt.user.client.ui.SimpleRadioButton implements HasResponsiveness,
        HasId, HasPull, HasFormValue {

    /**
     * Creates a SimpleRadioButton widget that wraps an existing &lt;input
     * type='radio'&gt; element.
     * 
     * This element must already be attached to the document. If the element is
     * removed from the document, you must call
     * {@link RootPanel#detachNow(Widget)}.
     * 
     * @param element
     *            the element to be wrapped
     */
    public static SimpleRadioButton wrap(Element element) {
        // Assert that the element is attached.
        assert Document.get().getBody().isOrHasChild(element);

        SimpleRadioButton radioButton = new SimpleRadioButton(InputElement.as(element));

        // Mark it attached and remember it for cleanup.
        radioButton.onAttach();
        RootPanel.detachOnWindowClose(radioButton);

        return radioButton;
    }

    private final IdMixin<SimpleRadioButton> idMixin = new IdMixin<SimpleRadioButton>(this);
    private final PullMixin<SimpleRadioButton> pullMixin = new PullMixin<SimpleRadioButton>(this);
    private final EnabledMixin<SimpleRadioButton> enabledMixin = new EnabledMixin<SimpleRadioButton>(this);

    /**
     * Creates a new radio associated with a particular group name. All radio
     * buttons associated with the same group name belong to a
     * mutually-exclusive set.
     * 
     * Radio buttons are grouped by their name attribute, so changing their name
     * using the setName() method will also change their associated group.
     * 
     * @param name
     *            the group name with which to associate the radio button
     */
    @UiConstructor
    public SimpleRadioButton(String name) {
        super(Document.get().createRadioInputElement(name));
    }

    /**
     * This constructor may be used by subclasses to explicitly use an existing
     * element. This element must be an &lt;input&gt; element whose type is
     * 'radio'.
     * 
     * @param element
     *            the element to be used
     */
    protected SimpleRadioButton(InputElement element) {
        super(element);
    }

    @Override
    public void setEnabled(boolean enabled) {
        enabledMixin.setEnabled(enabled);
    }

    @Override
    public boolean isEnabled() {
        return enabledMixin.isEnabled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        idMixin.setId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return idMixin.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisibleOn(final DeviceSize deviceSize) {
        StyleHelper.setVisibleOn(this, deviceSize);
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
    public void setPull(final Pull pull) {
        pullMixin.setPull(pull);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pull getPull() {
        return pullMixin.getPull();
    }

}
