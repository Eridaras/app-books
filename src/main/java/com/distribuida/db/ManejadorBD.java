package com.distribuida.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.sql.DataSource;

@ApplicationScoped
public class ManejadorBD {

    @Produces
    @ApplicationScoped
    public DbClient db() {
        Config config = Config.create();
        Config dbconfig = config.get("db");
        DbClient dbclient = DbClient.builder(dbconfig)
                .build();
        return dbclient;
    }
}

