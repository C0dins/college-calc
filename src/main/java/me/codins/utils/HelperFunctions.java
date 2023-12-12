package me.codins.utils;

import javax.swing.*;
import java.awt.*;

public class HelperFunctions {

    public void initIcon(JFrame frame) {
        try {
            Image icon = new ImageIcon(getClass().getResource("/calc.png")).getImage();
            Image scaledIcon = icon.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            frame.setIconImage(scaledIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JTextField getTextField(JPanel panel, int index) {
        Component component = panel.getComponent(index);

        if (component instanceof JTextField) {
            return (JTextField) component;
        } else if (component instanceof JLabel) {
            // Assuming the associated JTextField is the next component
            int textFieldIndex = index + 1;
            if (textFieldIndex < panel.getComponentCount()) {
                Component textFieldComponent = panel.getComponent(textFieldIndex);
                if (textFieldComponent instanceof JTextField) {
                    return (JTextField) textFieldComponent;
                }
            }
        }

        throw new ClassCastException("Component at index " + index + " is not a JTextField");
    }

    public double validateAndParse(JTextField textField, String fieldName) {
        try {
            double value = Double.parseDouble(textField.getText());
            if (value < 0 || value > 100) {
                throw new NumberFormatException();
            }
            return value;
        } catch (NumberFormatException e) {
            textField.setText(""); // Clear the field
            JOptionPane.showMessageDialog(null, "Invalid input for " + fieldName + ". Please enter a number between 0 and 100.");
            return Double.NaN; // Indicate validation failure
        }
    }
}
