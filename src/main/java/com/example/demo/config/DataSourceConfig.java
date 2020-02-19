package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "apim_master_info")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DataSourceConfig implements Serializable {

    private static final long serialVersionUID = 5104181924076372196L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "stage_id")
    private String stageId;

    @Column(name = "stage_name")
    private String stageName;

    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "db_user_name")
    private String username;

    @Column(name = "db_password")
    private String password;

    @Column(name = "jdbc_class")
    private String driverClassName;

    @Column(name = "jdbc_url")
    private String jdbcUrl;
}