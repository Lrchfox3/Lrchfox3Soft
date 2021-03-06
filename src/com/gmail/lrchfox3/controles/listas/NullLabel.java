/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.listas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.ColorUIResource;

/**
 * This is part of the null-components. A null component doesn't have foreground, background or font value set. In the
 * other words, the foreground, background and font value of null-component are null. But this doesn't mean
 * getBackground(), getForeground() or getFont() will return null. According to {@link
 * java.awt.Component#getBackground()}, {@link java.awt.Component#getForeground()} and {@link
 * java.awt.Component#getFont()}, if the value is null, it will get the value from its parent. In the other words, if
 * you add a null-component to JPanel, you can use JPanel to control the background, foreground and font of this
 * null-component. The feature is very helpful if you want to make sure all components in a JPanel has the same
 * background, foreground or font.
 * <p/>
 * Even in null-components, you can still change the foreground, background or font value if you do want. However, you'll
 * have to use a font which is not an instance of FontUIResource or a color which is not an instance of ColorUIResource.
 * <p/>
 * We creates a few null-components. It doesn't cover all components. You can always create your own. All you need to do
 * is this
 * <pre><code>
 * public class NullXxxComponent extends XxxComponent {
 *     // invoke clearAttribute() in all the constructors
 * <p/>
 * public void setFont(Font font) {
 *     if (font instanceof FontUIResource) {
 *         return;
 *     }
 *     super.setFont(font);
 * }
 * <p/>
 * public void setBackground(Color bg) {
 *     if (bg instanceof ColorUIResource) {
 *         return;
 *     }
 *     super.setBackground(bg);
 * }
 * <p/>
 * public void setForeground(Color fg) {
 *     if (fg instanceof ColorUIResource) {
 *         return;
 *     }
 *     super.setForeground(fg);
 * }
 * <p/>
 *     private void clearAttribute() {
 *         setFont(null);
 *         setBackground(null);
 *         // do not do this for JButton since JButton always paints button
 *         // content background. So it'd better to leave the foreground alone
 *         setForeground(null);
 *     }
 * }
 * </code></pre>
 *
 * @see com.jidesoft.swing.NullButton
 * @see com.jidesoft.swing.NullCheckBox
 * @see com.jidesoft.swing.NullJideButton
 * @see com.jidesoft.swing.NullPanel
 * @see com.jidesoft.swing.NullRadioButton
 * @see com.jidesoft.swing.NullTristateCheckBox
 */
public class NullLabel extends JLabel {
    public NullLabel() {
        clearAttribute();
    }

    public NullLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        clearAttribute();
    }

    public NullLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        clearAttribute();
    }

    public NullLabel(String text) {
        super(text);
        clearAttribute();
    }

    public NullLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        clearAttribute();
    }

    public NullLabel(Icon image) {
        super(image);
        clearAttribute();
    }

    private void clearAttribute() {
        super.setFont(null);
        super.setBackground(null);
        super.setForeground(null);
    }

    @Override
    public void setFont(Font font) {
        if (font instanceof FontUIResource) {
            return;
        }
        super.setFont(font);
    }

    @Override
    public void setBackground(Color bg) {
        if (bg instanceof ColorUIResource) {
            return;
        }
        super.setBackground(bg);
    }

    @Override
    public void setForeground(Color fg) {
        if (fg instanceof ColorUIResource) {
            return;
        }
        super.setForeground(fg);
    }
}

