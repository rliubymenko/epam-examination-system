package com.epam.examinationsystem.core.listener;

import com.epam.di.DependencyInjectionApplication;
import com.epam.di.annotation.PleaseComponentScan;
import com.epam.di.annotation.PleaseService;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.di.context.ObjectProvider;
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
        connectionManager = ObjectProvider.getInstance(ConnectionPoolManager.class);
        LOG.debug("Application has been started ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connectionManager.destroyPool();
        LOG.debug("Application has been terminated ...");
    }
}
