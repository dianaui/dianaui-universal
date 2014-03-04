package org.gwtbootstrap3.client.ui;

import org.gwtbootstrap3.client.ui.base.helper.StyleHelper;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * Created by BeaVe on 3/4/14.
 */
public class DropDownButton extends ButtonGroup implements HasText {

    private final Button button = new Button("...");
    private final DropDownMenu menu = new DropDownMenu();

    public DropDownButton() {
        button.setToggle(Toggle.DROPDOWN);

        add(button);
        add(menu);
    }

    public DropDownButton(String text) {
        this();

        setText(text);
    }

    @Override
    public void setText(String text) {
        button.setText(text);
    }

    @Override
    public String getText() {
        return button.getText();
    }

    public DropDownMenu getMenu() {
        return menu;
    }

    public void toggle() {
        if (isOpen()) {
            hide();
        } else {
            show();
        }
    }

    public void show() {
        addStyleName(Styles.OPEN);
    }

    public void hide() {
        removeStyleName(Styles.OPEN);
    }

    public boolean isOpen() {
        return StyleHelper.containsStyle(getStyleName(), Styles.OPEN);
    }

}
