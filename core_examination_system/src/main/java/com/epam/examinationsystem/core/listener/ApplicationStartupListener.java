package com.epam.examinationsystem.core.listener;

import com.epam.di.DependencyInjectionApplication;
import com.epam.di.annotation.PleaseComponentScan;
import com.epam.di.annotation.PleaseService;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.di.context.DependencyInjectionContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
@PleaseService
@PleaseComponentScan("com.epam.examinationsystem.core")
public class ApplicationStartupListener implements ServletContextListener {

    private ConnectionPoolManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DependencyInjectionApplication.run(ApplicationStartupListener.class);
        DependencyInjectionContext configuredContext = DependencyInjectionApplication.getConfiguredContext();
        connectionManager = configuredContext.getInstance(ConnectionPoolManager.class);
        System.out.println("Starting the application ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connectionManager.destroyPool();
    }
}
