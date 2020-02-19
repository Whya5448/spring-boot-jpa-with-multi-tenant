package com.example.demo.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

@Component
public class TenantDataSource implements Serializable {

    private HashMap<String, DataSource> dataSources = new HashMap<>();

    private final DataSourceConfigRepository configRepo;

    public TenantDataSource(DataSourceConfigRepository configRepo) {
        this.configRepo = configRepo;
    }

    public DataSource getDataSource(String stageId, String serviceId) {
        if (dataSources.get(stageId + ":" + serviceId) != null) {
            return dataSources.get(stageId + ":" + serviceId);
        }
        DataSource dataSource = createDataSource(stageId, serviceId);
        if (dataSource != null) {
            dataSources.put(stageId + ":" + serviceId, dataSource);
        }
        return dataSource;
    }

    @PostConstruct
    public Map<String, DataSource> getAll() {
        List<DataSourceConfig> configList = configRepo.findAll();
        Map<String, DataSource> result = new HashMap<>();
        for (DataSourceConfig config : configList) {
            DataSource dataSource = getDataSource(config.getStageId(), config.getServiceId());
            result.put(config.getStageId() + ":" + config.getServiceId(), dataSource);
        }
        return result;
    }

    private DataSource createDataSource(String stageId, String serviceId) {
        DataSourceConfig config = configRepo.findByStageIdAndServiceId(stageId, serviceId);
        if (config != null) {
            DataSourceBuilder factory = DataSourceBuilder
                .create().driverClassName(config.getDriverClassName())
                .username(config.getUsername())
                .password(config.getPassword())
                .url(config.getJdbcUrl());
            return factory.build();
        }
        return null;
    }

}