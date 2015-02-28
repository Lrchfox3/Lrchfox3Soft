/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.botones;

/**
 * The definitions of various button style. This is used by <code>JideButton</code> and <code>JideSplitButton</code>.
 */
public interface ButtonStyle {
    public static final String BUTTON_STYLE_PROPERTY = "buttonStyle";

    static final int TOOLBAR_STYLE = 0;
    static final int TOOLBOX_STYLE = 1;
    static final int FLAT_STYLE = 2;
    static final int HYPERLINK_STYLE = 3;

    // we used the same definition as Mac OS X.
    // http://developer.apple.com/technotes/tn2007/tn2196.html#JBUTTON_BUTTONTYPE
    public static final String CLIENT_PROPERTY_SEGMENT_POSITION = "JButton.segmentPosition";
    public static final String SEGMENT_POSITION_FIRST = "first";
    public static final String SEGMENT_POSITION_MIDDLE = "middle";
    public static final String SEGMENT_POSITION_LAST = "last";
    public static final String SEGMENT_POSITION_ONLY = "only";

    /**
     * Gets the button style.
     *
     * @return the button style.
     */
    int getButtonStyle();

    /**
     * Sets the button style.
     *
     * @param buttonStyle the button style.
     */
    void setButtonStyle(int buttonStyle);
}