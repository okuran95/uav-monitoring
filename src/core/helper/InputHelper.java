/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.helper;

import java.awt.event.KeyEvent;

/**
 *
 * @author onurkuran
 */
public class InputHelper {

    public static void isDouble(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isAlphabetic(c)) {
            evt.consume();
        }
    }

}
