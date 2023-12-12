package me.codins.gui;

import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class CustomSeparator extends JSeparator {

    private int separatorWidth, separatorHeight;

    public CustomSeparator(int orientation, int separatorWidth, int separatorHeight) {
        super(orientation);
        this.separatorWidth = separatorWidth;

        // Set the preferred size explicitly
        if (orientation == JSeparator.HORIZONTAL) {
            setPreferredSize(new Dimension(separatorWidth, separatorHeight));
        } else {
            setPreferredSize(new Dimension(getPreferredSize().width, separatorWidth));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension preferredSize = getPreferredSize();
        int separatorHeight = preferredSize.height;

        if (getOrientation() == JSeparator.HORIZONTAL) {
            g.setColor(Color.GRAY); // Set your desired color
            g.fillRect(0, 0, separatorWidth, separatorHeight);
        } else {
            super.paintComponent(g);
        }
    }
}
