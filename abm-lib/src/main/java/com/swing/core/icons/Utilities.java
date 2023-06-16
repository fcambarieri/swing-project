/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.swing.core.icons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author fernando
 */
public final class Utilities {
    public static final BufferedImage loadImage(String fileName){
        try {
            return ImageIO.read(Utilities.class.getClassLoader().getResource(fileName));
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Error loading image %s", fileName), ex);
        }
    }
    
    public static final ImageIcon OK = new ImageIcon(loadImage("com/fcc/abm/swing/icons/ok-16x16.png"));
    public static final ImageIcon CANCELAR = new ImageIcon(loadImage("com/fcc/abm/swing/icons/cancelar.png"));
    public static final ImageIcon EDITAR = new ImageIcon(loadImage("com/fcc/abm/swing/icons/editar-16x16.png"));
    

   
}
