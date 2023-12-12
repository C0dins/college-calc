package me.codins.gui;

import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.OneDarkTheme;

import javax.swing.*;
import java.awt.*;
import javax.swing.WindowConstants;

public class GUI {

    private Double assignmentGradesPercentage;
    private Double examGradesPercentage;
    private JFrame frame;
    public GridBagLayout gbl;
    public GridBagConstraints gbc;

    public void loadGUI(){
        //Theme
        LafManager.install(new OneDarkTheme());

        gbl =  new GridBagLayout();
        gbc =  new GridBagConstraints();

        //Initiate Frame
        frame = new JFrame("Weighted Final Grade Calculator");
        frame.setLayout(gbl);
        JPanel panel = getPanel();

        //Scale Favicon
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/calc.png"));
        Image scaledIcon = icon.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(scaledIcon).getImage();  // transform it back

        //Add panel to frame
        frame.setIconImage(scaledIcon);
        frame.add(panel);
        frame.setSize(500, 625);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private JPanel getPanel() {
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        JLabel assignmentWeightLabel = new JLabel("What is your assignments weighted at? (Example .30)");
        JLabel examWeightLabel = new JLabel("What is your exams weighted at? (Example .40)");
        JLabel finalWeightLabel = new JLabel("What is your final exam weighted at? (Example .30)");
        
        JTextField assignmentWeightField = new JTextField(6);
        JTextField examWeightField = new JTextField(6);
        JTextField finalWeightField = new JTextField(6);

        //Add Fields to Labels
        examWeightLabel.add(examWeightField);
        assignmentWeightLabel.add(assignmentWeightField);
        finalWeightLabel.add(finalWeightField);
        
        //Buttons
        JButton calculateButton = new JButton("Calculate!");
        calculateButton.setBorderPainted(false);
        calculateButton.setContentAreaFilled(false);
        calculateButton.setOpaque(true);
        
        JPanel panel = new JPanel();

        //Add Components to panel
        panel.add(finalWeightLabel);
        panel.add(examWeightLabel);
        panel.add(assignmentWeightLabel);

        panel.add(finalWeightField);
        panel.add(examWeightField);
        panel.add(assignmentWeightField);

        panel.add(calculateButton);
        return panel;
    }


}
