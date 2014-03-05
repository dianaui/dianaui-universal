package org.gwtbootstrap3.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.InitializeEvent;
import com.google.gwt.event.logical.shared.InitializeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
public class RichTextToolbar extends Composite {

    interface Binder extends UiBinder<Widget, RichTextToolbar> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    /**
     * We use an inner EventHandler class to avoid exposing event methods on the
     * RichTextToolbar itself.
     */
    private class EventHandler implements ClickHandler, ChangeHandler,
            KeyUpHandler {

        public void onChange(ChangeEvent event) {
            Widget sender = (Widget) event.getSource();

      /* TODO
      if (sender == backColors) {
        basic.setBackColor(backColors.getValue(backColors.getSelectedIndex()));
        backColors.setSelectedIndex(0);
      } else if (sender == foreColors) {
        basic.setForeColor(foreColors.getValue(foreColors.getSelectedIndex()));
        foreColors.setSelectedIndex(0);
      } else if (sender == fonts) {
        basic.setFontName(fonts.getValue(fonts.getSelectedIndex()));
        fonts.setSelectedIndex(0);
      } else if (sender == fontSizes) {
        basic.setFontSize(fontSizesConstants[fontSizes.getSelectedIndex() - 1]);
        fontSizes.setSelectedIndex(0);
      }
      */
        }

        public void onClick(ClickEvent event) {
            Widget sender = (Widget) event.getSource();

            if (sender == boldButton) {
                richText.getFormatter().toggleBold();
            } else if (sender == italicButton) {
                richText.getFormatter().toggleItalic();
            } else if (sender == underlineButton) {
                richText.getFormatter().toggleUnderline();
            } else if (sender == subscriptButton) {
                richText.getFormatter().toggleSubscript();
            } else if (sender == superscriptButton) {
                richText.getFormatter().toggleSuperscript();
            } else if (sender == strikethroughButton) {
                richText.getFormatter().toggleStrikethrough();
            } else if (sender == indentButton) {
                richText.getFormatter().rightIndent();
            } else if (sender == outdentButton) {
                richText.getFormatter().leftIndent();
            } else if (sender == justifyLeftButton) {
                richText.getFormatter().setJustification(org.gwtbootstrap3.client.ui.base.RichTextArea.Justification.LEFT);
            } else if (sender == justifyCenterButton) {
                richText.getFormatter().setJustification(org.gwtbootstrap3.client.ui.base.RichTextArea.Justification.CENTER);
            } else if (sender == justifyRightButton) {
                richText.getFormatter().setJustification(org.gwtbootstrap3.client.ui.base.RichTextArea.Justification.RIGHT);
            } else if (sender == imageButton) {
                String url = Window.prompt("Enter an image URL:", "http://");
                if (url != null) {
                    richText.getFormatter().insertImage(url);
                }
            } else if (sender == linkButton) {
                String url = Window.prompt("Enter a link URL:", "http://");
                if (url != null) {
                    richText.getFormatter().createLink(url);
                }
            } else if (sender == unlinkButton) {
                richText.getFormatter().removeLink();
            } else if (sender == hrButton) {
                richText.getFormatter().insertHorizontalRule();
            } else if (sender == olButton) {
                richText.getFormatter().insertOrderedList();
            } else if (sender == ulButton) {
                richText.getFormatter().insertUnorderedList();
            } else if (sender == removeFormatButton) {
                richText.getFormatter().removeFormat();
            } else if (sender == undoButton) {
                richText.getFormatter().undo();
            } else if (sender == redoButton) {
                richText.getFormatter().redo();
            } else if (sender == richText) {
                // We use the RichTextArea's onKeyUp event to update the toolbar status.
                // This will catch any cases where the user moves the cursur using the
                // keyboard, or uses one of the browser's built-in keyboard shortcuts.
                updateStatus();
            }
        }

        public void onKeyUp(KeyUpEvent event) {
            Widget sender = (Widget) event.getSource();
            if (sender == richText) {
                // We use the RichTextArea's onKeyUp event to update the toolbar status.
                // This will catch any cases where the user moves the cursur using the
                // keyboard, or uses one of the browser's built-in keyboard shortcuts.
                updateStatus();
            }

            if (richText.getHTML().trim().equals("") || richText.getHTML().trim().equals("<br>")) {
                IFrameElement.as(richText.getElement()).getContentDocument().getBody().setInnerHTML("<div>&nbsp;</div>");

                setSelectionRange(richText.getElement(), 0, 1);
            }
        }
    }

    private org.gwtbootstrap3.client.ui.base.RichTextArea richText;

    @UiField
    Button boldButton;

    @UiField
    Button italicButton;

    @UiField
    Button underlineButton;

    @UiField
    Button subscriptButton;

    @UiField
    Button superscriptButton;

    @UiField
    Button strikethroughButton;

    @UiField
    Button indentButton;

    @UiField
    Button outdentButton;

    @UiField
    Button justifyLeftButton;

    @UiField
    Button justifyCenterButton;

    @UiField
    Button justifyRightButton;

    @UiField
    Button hrButton;

    @UiField
    Button olButton;

    @UiField
    Button ulButton;

    @UiField
    Button imageButton;

    @UiField
    Button linkButton;

    @UiField
    Button unlinkButton;

    @UiField
    Button removeFormatButton;

    @UiField
    Button undoButton;

    @UiField
    Button redoButton;


    /**
     * Creates a new toolbar that drives the given rich text area.
     *
     * @param richText the rich text area to be controlled
     */
    public RichTextToolbar(final org.gwtbootstrap3.client.ui.base.RichTextArea richText) {
        initWidget(binder.createAndBindUi(this));

        richText.addInitializeHandler(new InitializeHandler() {
            @Override
            public void onInitialize(InitializeEvent event) {
                IFrameElement iframe = IFrameElement.as(richText.getElement());

                iframe.getContentDocument().getBody().getStyle().setMargin(0, Style.Unit.PX);
                iframe.getContentDocument().getBody().getStyle().setPadding(0, Style.Unit.PX);
            }
        });

        this.richText = richText;

        richText.setHTML("<div></div>");

        EventHandler handler = new EventHandler();

        boldButton.addClickHandler(handler);
        italicButton.addClickHandler(handler);
        underlineButton.addClickHandler(handler);
        subscriptButton.addClickHandler(handler);
        superscriptButton.addClickHandler(handler);
        justifyLeftButton.addClickHandler(handler);
        justifyCenterButton.addClickHandler(handler);
        justifyRightButton.addClickHandler(handler);
        strikethroughButton.addClickHandler(handler);
        indentButton.addClickHandler(handler);
        outdentButton.addClickHandler(handler);
        hrButton.addClickHandler(handler);
        olButton.addClickHandler(handler);
        ulButton.addClickHandler(handler);
        imageButton.addClickHandler(handler);
        linkButton.addClickHandler(handler);
        unlinkButton.addClickHandler(handler);
        removeFormatButton.addClickHandler(handler);
        undoButton.addClickHandler(handler);
        redoButton.addClickHandler(handler);

        // We only use these handlers for updating status, so don't hook them up
        // unless at least basic editing is supported.
        richText.addKeyUpHandler(handler);
        richText.addClickHandler(handler);
    }

    private void updateStatus() {
        boldButton.setActive(richText.getFormatter().isBold());
        italicButton.setActive(richText.getFormatter().isItalic());
        underlineButton.setActive(richText.getFormatter().isUnderlined());
        subscriptButton.setActive(richText.getFormatter().isSubscript());
        superscriptButton.setActive(richText.getFormatter().isSuperscript());
        strikethroughButton.setActive(richText.getFormatter().isStrikethrough());
    }

    public native void setSelectionRange(Element elem, int pos, int length) /*-{
        try {
            var selection = null, range2 = null;
            var iframeWindow = elem.contentWindow;
            var iframeDocument = iframeWindow.document;

            selection = iframeWindow.getSelection();
            range2 = selection.getRangeAt(0);

            //create new range
            var range = iframeDocument.createRange();
            range.setStart(selection.anchorNode, pos);
            range.setEnd(selection.anchorNode, length);

            //remove the old range and add the newly created range
            if (selection.removeRange) { // Firefox, Opera, IE after version 9
                selection.removeRange(range2);
            } else {
                if (selection.removeAllRanges) { // Safari, Google Chrome
                    selection.removeAllRanges();
                }
            }
            selection.addRange(range);
        } catch (e) {
            $wnd.alert(e);
        }
    }-*/;

}