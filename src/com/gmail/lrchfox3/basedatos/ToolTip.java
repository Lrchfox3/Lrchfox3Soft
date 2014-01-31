/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.basedatos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import net.java.balloontip.styles.IsometricBalloonStyle;
import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.styles.ModernBalloonStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.styles.TexturedBalloonStyle;
import net.java.balloontip.styles.ToolTipBalloonStyle;

/**
 *
 * @author lchinchilla
 */
public class ToolTip {

    
    private final BalloonTip balloonTip=null;
    
    private List mensajes = new ArrayList();

    /*public ToolTip() {
        
    }*/

    public void addMensaje(String _mensaje) {
        mensajes.add(_mensaje);
    }

    public void limpiar() {
        mensajes.clear();
    }

    public void construir(){
        
    }

    
    
    /*private void setBalloonTipStyle(int opcion) {
		switch (opcion) {
		case 0:
			balloonTip.setStyle(new EdgedBalloonStyle(fillColor, borderColor));
			break;
		case 1:
			balloonTip.setStyle(new IsometricBalloonStyle(fillColor, borderColor, 5));
			break;
		case 2:
			Color transparentFill = new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 180);
			balloonTip.setStyle(new MinimalBalloonStyle(transparentFill, 8));
			break;
		case 3:
			ModernBalloonStyle style = new ModernBalloonStyle(10, 10, fillColor, new Color(230,230,230), borderColor);
			style.setBorderThickness(3);
			style.enableAntiAliasing(true);
			balloonTip.setStyle(style);
			break;
		case 4:
			balloonTip.setStyle(new RoundedBalloonStyle(5, 5, fillColor, borderColor));
			break;
		case 5:
			try {
				balloonTip.setStyle(new TexturedBalloonStyle(5, 5, CompleteExample.class.getResource("/net/java/balloontip/images/bgPattern.png"), borderColor));
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 6:
			balloonTip.setStyle(new ToolTipBalloonStyle(fillColor, borderColor));
			break;
		}
	}*/
}
