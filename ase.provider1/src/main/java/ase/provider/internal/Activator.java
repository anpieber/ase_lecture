package ase.provider.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import ase.api.StringHeaderExtensions;

import javax.swing.*;

public class Activator implements BundleActivator {

    private ServiceRegistration<StringHeaderExtensions> component1;

    @Override
    public void start(BundleContext context) throws Exception {
        component1 = context.registerService(StringHeaderExtensions.class,
                new StringHeaderExtensions() {
                    @Override
                    public JComponent addNewStringHeaderExtension() {
                        return new JLabel("component1");
                    }
                }, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        component1.unregister();
    }
}
