package com.example.demo.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceConfigRepository extends JpaRepository<DataSourceConfig, Long> {

    DataSourceConfig findByStageIdAndServiceId(String stageId, String serviceId);
}