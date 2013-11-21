package ase.consumer.internal;

import javax.swing.*;

import ase.api.StringHeaderExtensions;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class StringHeaderExtensionsTracker extends ServiceTracker<StringHeaderExtensions, JComponent> {

    private final MainFrame mainFrame;

    public StringHeaderExtensionsTracker(BundleContext context, MainFrame mainFrame) {
        super(context, StringHeaderExtensions.class, null);
        this.mainFrame = mainFrame;
    }

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

}

