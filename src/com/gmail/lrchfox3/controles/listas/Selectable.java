/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.controles.listas;


/**
 * Selectable is an interface indicating something is selectable.
 */
public interface Selectable {
    /**
     * Sets it as selected.
     *
     * @param selected
     */
    void setSelected(boolean selected);

    /**
     * Gets the selected status.
     *
     * @return true if it is selected. Otherwise, false.
     */
    boolean isSelected();

    /**
     * Inverts the selection status.
     */
    void invertSelected();

    /**
     * Enabled selection change. Enabled false doesn't mean selected is false. If it is selected before,
     * setEnable(false) won't make selected become false. In the other word, setEnabled won't change the the value of
     * isSelected().
     *
     * @param enabled
     */
    void setEnabled(boolean enabled);

    /**
     * Checks if selection change is allowed.
     *
     * @return true if selection change is allowed.
     */
    boolean isEnabled();
}

