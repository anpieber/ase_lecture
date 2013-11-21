package ase.consumer.internal;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    public void addMyComponent(final JComponent component) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getContentPane().add(component);
                getContentPane().revalidate();
            }
        });

    }

    public void removeMyComponent(final JComponent component) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getContentPane().remove(component);
                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });
    }

}
