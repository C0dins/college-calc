package me.codins.gui;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.iconset.AllIcons;
import com.github.weisj.darklaf.theme.OneDarkTheme;
import me.codins.utils.HelperFunctions;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private static final int INITIAL_WIDTH = 350;
    private static final int INITIAL_HEIGHT = 450;
    private HelperFunctions functions;

    public void loadGUI() {
        functions = new HelperFunctions();
        LafManager.install(new OneDarkTheme());

        JFrame frame = new JFrame("Weighted Final Grade Calculator");
        initFrame(frame);
        functions.initIcon(frame);

        JPanel panel = initPanel();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void initFrame(JFrame frame) {
        // Set initial size and center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - INITIAL_WIDTH) / 2;
        int y = (screenSize.height - INITIAL_HEIGHT) / 2;

        frame.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JPanel initPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Grade Wanted Section
        double gradeWanted = initLabelAndTextField(panel, gbc, "Grade Wanted (%)");
        gbc.gridy++; // Move to the next row

        // Left Section: Components for Current Grades
        double currentExamGrade = initLabelAndTextField(panel, gbc, "Exam Grade (%)");
        gbc.gridy++; // Move to the next row
        double currentAssignmentGrade = initLabelAndTextField(panel, gbc, "Assignment Grade (%)");

        // Add a custom separator under the current grades
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns
        panel.add(new CustomSeparator(JSeparator.HORIZONTAL, 195, 2), gbc);

        // Reset grid width for the next components
        gbc.gridwidth = 1;

        // Right Section: Components for Weights
        gbc.gridy++; // Move to the next row
        double assignmentsWeight = initLabelAndTextField(panel, gbc, "Assignments Weight");

        gbc.gridy++; // Move to the next row
        double examsWeight = initLabelAndTextField(panel, gbc, "Exams Weight");

        gbc.gridy++; // Move to the next row
        double finalExamWeight = initLabelAndTextField(panel, gbc, "Final Exam Weight");

        // Calculate Button
        gbc.gridx = 0; // Move to the first column
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 2; // Span two columns
        initCalculateButton(panel, gbc);

        return panel;
    }


    // Modify the initLabelAndTextField method to directly return the parsed values
    private double initLabelAndTextField(JPanel panel, GridBagConstraints gbc, String label) {
        JLabel gradeLabel = new JLabel(label);
        JTextField gradeTextField = new JTextField(5);

        panel.add(gradeLabel, gbc);
        gbc.gridx++; // Move to the next column
        panel.add(gradeTextField, gbc);

        // Increment only if not in the last row
        if (gbc.gridy < 11) {
            gbc.gridx = 0; // Reset to the first column for the next row
            gbc.gridy++; // Move to the next row
        }

        // Return the parsed value
        return functions.validateAndParse(gradeTextField, label);
    }

    private void initCalculateButton(JPanel panel, GridBagConstraints gbc) {
        JButton calculateButton = new JButton("Calculate!");
        calculateButton.addActionListener(e -> calculateAndShowResult(panel));
        panel.add(calculateButton, gbc);
    }

    private void calculateAndShowResult(JPanel panel) {
        // Validate and retrieve values from text fields
        double currentExamGrade = functions.validateAndParse(functions.getTextField(panel, 1), "Exam Grade");
        double currentAssignmentGrade = functions.validateAndParse(functions.getTextField(panel, 3), "Assignment Grade");

        // Validate and retrieve weights from text fields
        double assignmentsWeight = functions.validateAndParse(functions.getTextField(panel, 5), "Assignments Weight");
        double examsWeight = functions.validateAndParse(functions.getTextField(panel, 7), "Exams Weight");
        double finalExamWeight = functions.validateAndParse(functions.getTextField(panel, 9), "Final Exam Weight");

        // Check if validation failed
        if (Double.isNaN(currentExamGrade) || Double.isNaN(currentAssignmentGrade) ||
                Double.isNaN(assignmentsWeight) || Double.isNaN(examsWeight) || Double.isNaN(finalExamWeight)) {
            return;
        }

        // Check if weights add up to 100
        double totalWeight = assignmentsWeight + examsWeight + finalExamWeight;
        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Assignments Weight: " + assignmentsWeight);
        System.out.println("Exams Weight: " + examsWeight);
        System.out.println("Final Exam Weight: " + finalExamWeight);
        if (totalWeight != 100.00) {
            handleInvalidWeights(panel);
            return;
        }

        // TODO: Add your algorithm calculation here

        // Display the result (you can modify this part based on your UI requirements)
        JOptionPane.showMessageDialog(panel, "Calculation Result");
    }

    private void handleInvalidWeights(JPanel panel) {
            JOptionPane.showMessageDialog(panel, "Weights must add up to 100.");
    }
}
