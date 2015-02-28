package com.gmail.lrchfox3.utilitarios.encriptar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * **********************************************************************************
 *************************************************************************************
 * An Encrption/Decryption Application using Simplified DES ( S-DES ) ** ** **
 * Developed by: Yanal H.Shawash ** Student ID: 0002931 ** Course: Information
 * Sytems Security ** Instructor: Dr.Haitham Nasser ** Section: 2 - 3 ** Year:
 * 2002 - 2003 ** Semester: Second ** **
************************************************************************************
 */
public class DES_Encryption_and_Decryption extends JFrame {

    private JLabel EInputTextLabel, DInputTextLabel;

    private JTextArea EInputText, EOutputText, DInputText, DOutputText;
    private JButton EButton, DButton;                          /* GUI components used */

    private JPasswordField EPasswordField, DPasswordField;

    private JPopupMenu popupMenu1, popupMenu2, popupMenu3,
            popupMenu4;                             /* four popup menus used,
     one for each JTextField */

    private JMenuItem pop1a, pop1b, pop1c, pop1d, pop1e,
            pop2a, pop2b, pop2c, pop2d, pop2e,
            pop3a, pop3b, pop3c, pop3d, pop3e,
            pop4a, pop4b, pop4c, pop4d, pop4e;      /* menu items of each of
     the popup menus */

    public String currentPassword;
    private UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
    private JTabbedPane tabbed;

    private char key[] = new char[10];
    private char subkey1[] = new char[8];
    private char subkey2[] = new char[8];

    public DES_Encryption_and_Decryption() {
        super("Encryption/Decryption using S-DES (Simplified DES algorithm)");

        // setting up GUI components for the Encryption portion ...
        EInputTextLabel = new JLabel("Enter text to Encrypt: ");
        EInputText = new JTextArea(10, 30);
        EInputText.setLineWrap(true);
        EInputText.setWrapStyleWord(true);
        EInputText.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    private void checkForTriggerEvent(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            popupMenu1.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
                );

        EOutputText = new JTextArea(10, 30);
        EOutputText.setLineWrap(true);
        EOutputText.setWrapStyleWord(true);
        EOutputText.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    private void checkForTriggerEvent(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            popupMenu2.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
                );

        EButton = new JButton("Encrypt...");
        ButtonHandler handler = new ButtonHandler();
        EButton.addActionListener(handler);

        Box EPanel = Box.createVerticalBox();  /* this panel will contain the GUI 
         of the encryption portion */

        // setting up GUI components for the Decryption portion

        DInputTextLabel = new JLabel("Enter text to Decrypt: ");
        DInputText = new JTextArea(10, 30);
        DInputText.setLineWrap(true);
        DInputText.setWrapStyleWord(true);
        DInputText.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    public void mouseReleased(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    private void checkForTriggerEvent(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            popupMenu3.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
                );

        DOutputText = new JTextArea(10, 30);
        DOutputText.setLineWrap(true);
        DOutputText.setWrapStyleWord(true);
        DOutputText.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    public void mouseReleased(MouseEvent e) {
                        checkForTriggerEvent(e);
                    }

                    private void checkForTriggerEvent(MouseEvent e) {
                        if (e.isPopupTrigger()) {
                            popupMenu4.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
                );

        DButton = new JButton("Decrypt...");
        DButton.addActionListener(handler);

        Box DPanel = Box.createVerticalBox();  /* this panel will contain the GUI 
         of the decryption portion */

        // setting up menus

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        JMenuItem openFileItem = new JMenuItem("Open text File...");
        openFileItem.setMnemonic('O');
        openFileItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (tabbed.getSelectedIndex() == 0) {
                            openFile(EInputText);
                        } else {
                            openFile(DInputText);
                        }
                    }
                }
                );
        fileMenu.add(openFileItem);

        JMenuItem saveFileItem = new JMenuItem("Save to file...");
        saveFileItem.setMnemonic('S');
        saveFileItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (tabbed.getSelectedIndex() == 0) {
                            EOutputText.selectAll();
                            saveFile(EOutputText);
                        } else if (tabbed.getSelectedIndex() == 1) {
                            DOutputText.selectAll();
                            saveFile(DOutputText);
                        }

                    }
                }
                );
        fileMenu.add(saveFileItem);

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('X');
        exitItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.print("             _______  ______  _________ _______  _______ \n"
                        + "            (  ___  )(  __  \\ \\__   __/(  ____ \\(  ___  )\n"
                        + "            | (   ) || (  \\  )   ) (   | (    \\/| (   ) |\n"
                        + "            | (___) || |   ) |   | |   | |      | (___) |\n"
                        + "            |  ___  || |   | |   | |   | | ____ |  ___  |\n"
                        + "            | (   ) || |   ) |   | |   | | \\_  )| (   ) |\n"
                        + "            | )   ( || (__/  )___) (___| (___) || )   ( |\n"
                        + "            |/     \\|(______/ \\_______/(_______)|/     \\|\n\n\n\n");
                        System.out.println("Thank you for using this application..\nHave a nice day ;)\n");
                        System.exit(0);
                    }
                }
                );

        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic('A');
        aboutItem.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        JOptionPane.showMessageDialog(DES_Encryption_and_Decryption.this,
                        "This application was developed by:"
                        + "\nYanal H.Shawash\n"
                        + "Student ID: 0002931\n"
                        + "Student email: yanal502@yahoo.com\n"
                        + "Course: Information Sytems Security"
                        + "\nInstructor: Dr.Haitham Nasser\n"
                        + "Section: 2 - 3\n"
                        + "Year: 2002 - 2003\n"
                        + "Semester: Second\n\n"
                        + "Information Technology Faculty\n"
                        + "The University of Jordan",
                        "About this application...",
                        JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                );

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // setting up the panels used to organize the GUI components
        Box EInputPanel = Box.createVerticalBox();
        EInputPanel.add(EInputTextLabel);
        EInputPanel.add(EInputText);
        EInputPanel.add(new JScrollPane(EInputText));

        Box EOutputPanel = Box.createVerticalBox();
        EOutputPanel.add(new JLabel("Encrypted Text:"));
        EOutputPanel.add(EOutputText);
        EOutputPanel.add(new JScrollPane(EOutputText));

        Box EPanel1 = Box.createHorizontalBox();
        EPanel1.add(Box.createHorizontalStrut(5));
        EPanel1.add(EInputPanel);
        EPanel1.add(Box.createHorizontalStrut(25));
        EPanel1.add(EOutputPanel);
        EPanel1.add(Box.createHorizontalStrut(5));

        EPasswordField = new JPasswordField(15);
        EPasswordField.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        encrypt();
                    }
                }
                );

        Box EButtomPanel = Box.createHorizontalBox();
        EButtomPanel.add(Box.createHorizontalStrut(5));
        EButtomPanel.add(new JLabel("Enter password: "));
        EButtomPanel.add(Box.createHorizontalStrut(5));
        EButtomPanel.add(EPasswordField);
        EButtomPanel.add(Box.createHorizontalStrut(15));
        EButtomPanel.add(EButton);

        JPanel EPanel3 = new JPanel();
        EPanel3.setLayout(new BorderLayout());
        EPanel3.add(EButtomPanel, BorderLayout.WEST);

        EPanel.add(EPanel1);
        EPanel.add(Box.createVerticalStrut(10));

        EPanel.add(EPanel3);
        EPanel.add(Box.createVerticalStrut(5));

        Box DInputPanel = Box.createVerticalBox();
        DInputPanel.add(DInputTextLabel);
        DInputPanel.add(DInputText);
        DInputPanel.add(new JScrollPane(DInputText));

        Box DOutputPanel = Box.createVerticalBox();
        DOutputPanel.add(new JLabel("Decrypted Text:"));
        DOutputPanel.add(DOutputText);
        DOutputPanel.add(new JScrollPane(DOutputText));

        Box DPanel1 = Box.createHorizontalBox();
        DPanel1.add(Box.createHorizontalStrut(5));
        DPanel1.add(DInputPanel);
        DPanel1.add(Box.createHorizontalStrut(25));
        DPanel1.add(DOutputPanel);
        DPanel1.add(Box.createHorizontalStrut(5));

        DPasswordField = new JPasswordField(15);
        DPasswordField.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        decrypt();
                    }
                }
                );

        Box DButtomPanel = Box.createHorizontalBox();
        DButtomPanel.add(Box.createHorizontalStrut(5));
        DButtomPanel.add(new JLabel("Enter password: "));
        DButtomPanel.add(Box.createHorizontalStrut(5));
        DButtomPanel.add(DPasswordField);
        DButtomPanel.add(Box.createHorizontalStrut(15));
        DButtomPanel.add(DButton);

        JPanel DPanel3 = new JPanel();
        DPanel3.setLayout(new BorderLayout());
        DPanel3.add(DButtomPanel, BorderLayout.WEST);

        DPanel.add(DPanel1);
        DPanel.add(Box.createVerticalStrut(10));

        DPanel.add(DPanel3);
        DPanel.add(Box.createVerticalStrut(5));

        tabbed = new JTabbedPane();
        tabbed.addTab("Encrypt", null, EPanel, "This section is for encrypting text!");
        tabbed.addTab("Decrypt", null, DPanel, "This section is for decrypting text!");
        tabbed.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        if (tabbed.getSelectedIndex() == 0) {
                            EInputText.grabFocus();
                        } else if (tabbed.getSelectedIndex() == 1) {
                            DInputText.grabFocus();
                        }
                    }

                    
                }
                );

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(tabbed);

        // setting up the four popup menus
        popupMenu1 = new JPopupMenu();
        PopupMenu_1_Handler popup1 = new PopupMenu_1_Handler();

        pop1a = new JMenuItem("Cut");
        pop1a.addActionListener(popup1);
        pop1b = new JMenuItem("Copy");
        pop1b.addActionListener(popup1);
        pop1c = new JMenuItem("Paste");
        pop1c.addActionListener(popup1);
        pop1d = new JMenuItem("Delete");
        pop1d.addActionListener(popup1);
        pop1e = new JMenuItem("Select all");
        pop1e.addActionListener(popup1);

        popupMenu1.add(pop1a);
        popupMenu1.add(pop1b);
        popupMenu1.add(pop1c);
        popupMenu1.add(pop1d);
        popupMenu1.addSeparator();
        popupMenu1.add(pop1e);

        popupMenu2 = new JPopupMenu();
        PopupMenu_2_Handler popup2 = new PopupMenu_2_Handler();

        pop2a = new JMenuItem("Cut");
        pop2a.addActionListener(popup2);
        pop2b = new JMenuItem("Copy");
        pop2b.addActionListener(popup2);
        pop2c = new JMenuItem("Paste");
        pop2c.addActionListener(popup2);
        pop2d = new JMenuItem("Delete");
        pop2d.addActionListener(popup2);
        pop2e = new JMenuItem("Select all");
        pop2e.addActionListener(popup2);

        popupMenu2.add(pop2a);
        popupMenu2.add(pop2b);
        popupMenu2.add(pop2c);
        popupMenu2.add(pop2d);
        popupMenu2.addSeparator();
        popupMenu2.add(pop2e);

        popupMenu3 = new JPopupMenu();
        PopupMenu_3_Handler popup3 = new PopupMenu_3_Handler();

        pop3a = new JMenuItem("Cut");
        pop3a.addActionListener(popup3);
        pop3b = new JMenuItem("Copy");
        pop3b.addActionListener(popup3);
        pop3c = new JMenuItem("Paste");
        pop3c.addActionListener(popup3);
        pop3d = new JMenuItem("Delete");
        pop3d.addActionListener(popup3);
        pop3e = new JMenuItem("Select all");
        pop3e.addActionListener(popup3);

        popupMenu3.add(pop3a);
        popupMenu3.add(pop3b);
        popupMenu3.add(pop3c);
        popupMenu3.add(pop3d);
        popupMenu3.addSeparator();
        popupMenu3.add(pop3e);

        popupMenu4 = new JPopupMenu();
        PopupMenu_4_Handler popup4 = new PopupMenu_4_Handler();

        pop4a = new JMenuItem("Cut");
        pop4a.addActionListener(popup4);
        pop4b = new JMenuItem("Copy");
        pop4b.addActionListener(popup4);
        pop4c = new JMenuItem("Paste");
        pop4c.addActionListener(popup4);
        pop4d = new JMenuItem("Delete");
        pop4d.addActionListener(popup4);
        pop4e = new JMenuItem("Select all");
        pop4e.addActionListener(popup4);

        popupMenu4.add(pop4a);
        popupMenu4.add(pop4b);
        popupMenu4.add(pop4c);
        popupMenu4.add(pop4d);
        popupMenu4.addSeparator();
        popupMenu4.add(pop4e);

        try {
            UIManager.setLookAndFeel(looks[0].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error while loading the Look-And-Feel",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        setSize(720, 315);       // size of the frame
        setLocation(100, 100);
        setResizable(false);

        

    }

    /* inner class used to handle ActionEvents 
     generated by the two command buttons */
    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == EButton) // if the encrypt button was pressed
            {
                encrypt();

            } else if (e.getSource() == DButton) // if the decrypt button was pressed
            {
                decrypt();

            }

        }
    }

    /* inner class used to handle ActionEvents 
     generated by the first popup menu
     which appears over EInputText */
    private class PopupMenu_1_Handler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pop1a) {
                EInputText.cut();
            } else if (e.getSource() == pop1b) {
                EInputText.copy();
            } else if (e.getSource() == pop1c) {
                EInputText.paste();
            } else if (e.getSource() == pop1d) {
                doDelete(EInputText);
            } else if (e.getSource() == pop1e) {
                EInputText.selectAll();
            }

        }

    }

    /* inner class used to handle ActionEvents 
     generated by the second popup menu
     which appears over EOutputText */
    private class PopupMenu_2_Handler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pop2a) {
                EOutputText.cut();
            } else if (e.getSource() == pop2b) {
                EOutputText.copy();
            } else if (e.getSource() == pop2c) {
                EOutputText.paste();
            } else if (e.getSource() == pop2d) {
                doDelete(EOutputText);
            } else if (e.getSource() == pop2e) {
                EOutputText.selectAll();
            }

        }

    }

    /* inner class used to handle ActionEvents 
     generated by the third popup menu
     which appears over DInputText */
    private class PopupMenu_3_Handler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pop3a) {
                DInputText.cut();
            } else if (e.getSource() == pop3b) {
                DInputText.copy();
            } else if (e.getSource() == pop3c) {
                DInputText.paste();
            } else if (e.getSource() == pop3d) {
                doDelete(DInputText);
            } else if (e.getSource() == pop3e) {
                DInputText.selectAll();
            }

        }

    }

    /* inner class used to handle ActionEvents 
     generated by the forth popup menu
     which appears over DOutputText */
    private class PopupMenu_4_Handler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == pop4a) {
                DOutputText.cut();
            } else if (e.getSource() == pop4b) {
                DOutputText.copy();
            } else if (e.getSource() == pop4c) {
                DOutputText.paste();
            } else if (e.getSource() == pop4d) {
                doDelete(DOutputText);
            } else if (e.getSource() == pop4e) {
                DOutputText.selectAll();
            }

        }

    }

    public void doDelete(JTextArea area) {
        String selection = area.getSelectedText();
        area.replaceRange("", area.getSelectionStart(),
                area.getSelectionEnd());
    }

    public void openFile(JTextArea area) {
        JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode(JFileChooser.FILES_ONLY);
        String txtmsg = "";

        int result = fch.showOpenDialog(this);

        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        } else {
            try {
                FileInputStream fis = new FileInputStream(fch.getSelectedFile());
                try (BufferedInputStream bis = new BufferedInputStream(fis)) {
                    int c = bis.read();
                    while (c != -1) {
                        txtmsg = txtmsg + (char) c;
                        c = bis.read();
                    }
                    
                    area.setText(txtmsg);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error while loading file!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveFile(JTextArea area) {
        JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fch.showSaveDialog(this);

        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        } else {
            try {
                try (FileOutputStream fos = new FileOutputStream(fch.getSelectedFile()); BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                    bos.write(area.getText().getBytes());
                    bos.flush();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error while saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // S-DES encryption algorithm
    public void encrypt() {
        JTextField passField = (JTextField) EPasswordField;
        currentPassword = passField.getText();

        if (currentPassword.length() < 10) {
            JOptionPane.showMessageDialog(this, "Your password must not be\nless than 10 characters...",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        generateKeys();

        String textToEncrypt = EInputText.getText();
        String EncryptedText = "";

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToEncrypt.length(); i++) {

            char theChar = textToEncrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }

            // chars to manipulate are now ready
            S_DES s = new S_DES();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey1);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey2);
            char array5[] = new char[8];
            array5 = s.IP_inverse(array4);

            String tmp = "";
            for (int k = 0; k < array5.length; k++) {
                tmp += String.valueOf(array5[k]);
            }

            EncryptedText += (char) (Integer.parseInt(tmp, 2));

        }

        EOutputText.setText(EncryptedText);

    }

    // S-DES decryption algorithm 
    public void decrypt() {
        JTextField passField = (JTextField) DPasswordField;
        currentPassword = passField.getText();

        if (currentPassword.length() < 10) {
            JOptionPane.showMessageDialog(this, "Your password must not be\nless than 10 characters...",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        generateKeys();

        String textToDecrypt = DInputText.getText();
        String DecryptedText = "";

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToDecrypt.length(); i++) {

            char theChar = textToDecrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }
            // chars to manipulate are now ready

            S_DES s = new S_DES();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey2);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey1);
            char array5[] = new char[8];
            array5 = s.IP_inverse(array4);

            String tmp = "";
            for (int k = 0; k < array5.length; k++) {
                tmp += String.valueOf(array5[k]);
            }

            DecryptedText += (char) (Integer.parseInt(tmp, 2));

        }

        DOutputText.setText(DecryptedText);
    }

    private void generateKeys() {

        key = (new YKey(currentPassword)).generateKey();

        // P10
        char p10[] = new char[10];
        p10[0] = key[2];
        p10[1] = key[4];
        p10[2] = key[1];
        p10[3] = key[6];
        p10[4] = key[3];
        p10[5] = key[9];
        p10[6] = key[0];
        p10[7] = key[8];
        p10[8] = key[7];
        p10[9] = key[5];

        // Split the 10 bits into 5's
        YBitSet LeftFiveBits = new YBitSet(5);
        YBitSet RightFiveBits = new YBitSet(5);

        for (int i = 0; i < p10.length; i++) {

            if (i < 5) {
                if (p10[i] == '1') {
                    LeftFiveBits.set(i);
                } else if (p10[i] == '0') {
                    LeftFiveBits.clear(i);
                }
            } else {
                if (p10[i] == '1') {
                    RightFiveBits.set(i);
                } else if (p10[i] == '0') {
                    RightFiveBits.clear(i);
                }
            }
        }

        // Apply LS_1 on each one
        YBitSet LS_1LeftBits = LeftFiveBits.LS_1(5);
        YBitSet LS_1RightBits = RightFiveBits.LS_1(5);

        // Apply P8 to produce the first subkey
        char theKeyAfterLS_1[] = new char[10];

        char left1[] = LS_1LeftBits.bitSetToCharArray(5);
        char right1[] = LS_1RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_1[i] = left1[i];
            } else {
                theKeyAfterLS_1[i] = right1[i - 5];
            }
        }

        subkey1[0] = theKeyAfterLS_1[5];
        subkey1[1] = theKeyAfterLS_1[2];
        subkey1[2] = theKeyAfterLS_1[6];
        subkey1[3] = theKeyAfterLS_1[3];
        subkey1[4] = theKeyAfterLS_1[7];
        subkey1[5] = theKeyAfterLS_1[4];
        subkey1[6] = theKeyAfterLS_1[9];
        subkey1[7] = theKeyAfterLS_1[8];

        // Apply LS_2 
        YBitSet LS_2LeftBits = LS_1LeftBits.LS_2(5);
        YBitSet LS_2RightBits = LS_1RightBits.LS_2(5);

        // Apply P8
        char theKeyAfterLS_2[] = new char[10];

        char left2[] = LS_2LeftBits.bitSetToCharArray(5);
        char right2[] = LS_2RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_2[i] = left2[i];
            } else {
                theKeyAfterLS_2[i] = right2[i - 5];
            }
        }

        subkey2[0] = theKeyAfterLS_2[5];
        subkey2[1] = theKeyAfterLS_2[2];
        subkey2[2] = theKeyAfterLS_2[6];
        subkey2[3] = theKeyAfterLS_2[3];
        subkey2[4] = theKeyAfterLS_2[7];
        subkey2[5] = theKeyAfterLS_2[4];
        subkey2[6] = theKeyAfterLS_2[9];
        subkey2[7] = theKeyAfterLS_2[8];

    }

    public static void main(String args[]) {
        DES_Encryption_and_Decryption app = new DES_Encryption_and_Decryption();
        app.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.print("             _______  ______  _________ _______  _______ \n"
                        + "            (  ___  )(  __  \\ \\__   __/(  ____ \\(  ___  )\n"
                        + "            | (   ) || (  \\  )   ) (   | (    \\/| (   ) |\n"
                        + "            | (___) || |   ) |   | |   | |      | (___) |\n"
                        + "            |  ___  || |   | |   | |   | | ____ |  ___  |\n"
                        + "            | (   ) || |   ) |   | |   | | \\_  )| (   ) |\n"
                        + "            | )   ( || (__/  )___) (___| (___) || )   ( |\n"
                        + "            |/     \\|(______/ \\_______/(_______)|/     \\|\n\n\n\n");
                        System.out.println("Thank you for using this application..\nHave a nice day ;)\n");
                        System.exit(0);
                    }
                }
                );
    }
}
