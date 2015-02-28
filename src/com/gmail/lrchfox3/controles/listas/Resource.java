/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.listas;

import java.util.Locale;
import java.util.ResourceBundle;

class Resource {
    static final String BASENAME = "com.gmail.lrchfox3.controles.listas";

    static final ResourceBundle RB = ResourceBundle.getBundle(BASENAME);

    public static ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(BASENAME, locale);
    }
}