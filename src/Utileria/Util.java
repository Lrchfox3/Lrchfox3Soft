/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utileria;

import java.util.ResourceBundle;

/**
 *
 * @author chinchillal
 */
public  class Util {
    private static final ResourceBundle cnnConfig = ResourceBundle.getBundle("ViewController/Resources/cnnConfig");

    private String hola = cnnConfig.getString("HOLA");

}
