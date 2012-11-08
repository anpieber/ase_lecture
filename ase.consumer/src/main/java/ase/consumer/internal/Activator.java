package ase.consumer.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import ase.api.StringHeaderExtensions;

import javax.swing.*;
import java.awt.*;

public class Activator implements BundleActivator {

    private ServiceTracker<StringHeaderExtensions, JComponent> serviceTracker;
    private MainFrame mainFrame;

    @Override
    public void start(BundleContext context) throws Exception {
        mainFrame = new MainFrame();
        serviceTracker = new ServiceTracker<StringHeaderExtensions, JComponent>(
                context, StringHeaderExtensions.class, null) {
            @Override
            public JComponent addingService(ServiceReference<StringHeaderExtensions> reference) {
                StringHeaderExtensions service = context.getService(reference);
                JComponent component = service.addNewStringHeaderExtension();
                mainFrame.addMyComponent(component);
                return component;
            }

            @Override
            public void removedService(ServiceReference<StringHeaderExtensions> reference, JComponent service) {
                mainFrame.removeMyComponent(service);
            }
        };
        serviceTracker.open();
        mainFrame.setVisible(true);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceTracker.close();
        mainFrame.setVisible(false);
    }

    private class MainFrame extends JFrame {
        private MainFrame() throws HeadlessException {
            setLayout(new BoxLayout(getContentPane(), 1));
        }

        private void addMyComponent(final JComponent component) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getContentPane().add(component);
                    getContentPane().revalidate();
                }
            });

        }

        private void removeMyComponent(final JComponent component) {
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
}
