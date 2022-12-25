package com.epam.examinationsystem.core.listener;

import com.epam.di.DependencyInjectionApplication;
import com.epam.di.annotation.PleaseComponentScan;
import com.epam.di.annotation.PleaseService;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.examinationsystem.core.util.ObjectFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
@PleaseService
@PleaseComponentScan("com.epam.examinationsystem.core")
public class ApplicationStartupListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartupListener.class);
    private ConnectionPoolManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DependencyInjectionApplication.run(ApplicationStartupListener.class);
        DependencyInjectionContext configuredContext = DependencyInjectionApplication.getConfiguredContext();
        ObjectFactory.setContext(configuredContext);
        connectionManager = ObjectFactory.getInstance(ConnectionPoolManager.class);
        LOG.debug("Application has started ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connectionManager.destroyPool();
        LOG.debug("Termination of the application ...");
    }
}
