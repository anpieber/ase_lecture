package ase.consumer.internal;

import javax.swing.*;

import ase.api.StringHeaderExtensions;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

    private MainFrame mainFrame;
    private ServiceTracker<StringHeaderExtensions, JComponent> serviceTracker;

    @Override
    public void start(BundleContext context) throws Exception {
        mainFrame = new MainFrame();
        serviceTracker = new StringHeaderExtensionsTracker(context, mainFrame);
        serviceTracker.open();
        mainFrame.setVisible(true);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        serviceTracker.close();
        mainFrame.setVisible(false);
    }

}
