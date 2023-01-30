package com.distribuida.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

@ApplicationScoped
public class AppEvents {
    @Inject
    DataSource dataSource;

    public void init(@Observes @Initialized(ApplicationScoped.class)Object event) {
        System.out.println("************ Migrando Datos ****************");
        System.out.println(dataSource.toString());
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .load();
        flyway.migrate();
    }
}
