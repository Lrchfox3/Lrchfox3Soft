/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.utilitarios;

import java.io.File;
import javax.swing.filechooser.*;

/* ImageFilter.java is used by FileChooserDemo2.java. */
public class FiltrosImportar extends FileFilter {

    //Accept all directories and all gif, jpg, tiff, or png files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utileria.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utileria.excel)
                    || extension.equals(Utileria.texto)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Ficheros de datos (*.xls|*.txt)";
    }
}

