/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.helper;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author onurkuran
 */
public class ViewHelper {

    public static boolean confirm(String message) {
        String title = "Are you sure?";
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION) == 0;
    }
}
