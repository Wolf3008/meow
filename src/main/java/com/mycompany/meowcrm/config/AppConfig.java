package com.mycompany.meowcrm.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class AppConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(DbConfig.class, SecurityConfig.class, ServiceConf.class/*, SchedulerConfig.class*/);

        rootContext.setServletContext(sc);//new

        // Manage the lifecycle of the root application context
        sc.addListener(new ContextLoaderListener(rootContext));

        sc.addFilter("openSessionInViewFilter",
                new OpenSessionInViewFilter())
                .addMappingForUrlPatterns(null, true, "/*");

        sc.addFilter("securityFilter",
                new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");

//        sc.addFilter("sessionDeniedFilter", new SessionDeniedFilter())
//                .addMappingForUrlPatterns(null, false, "/*");
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(MvcConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = sc.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
