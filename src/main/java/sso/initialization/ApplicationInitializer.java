package sso.initialization;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import spark.servlet.SparkFilter;

public class ApplicationInitializer {
  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    ServletContextHandler contextHandler = new ServletContextHandler();
    contextHandler.setContextPath("/");

    FilterHolder holder = contextHandler.addFilter(
        SparkFilter.class,
        "/*",
        EnumSet.of(DispatcherType.REQUEST)
    );
    holder.setInitParameter(SparkFilter.APPLICATION_CLASS_PARAM, Application.class.getName());
    server.setHandler(contextHandler);
    server.start();
  }
}
