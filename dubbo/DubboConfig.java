package com.blt.dubboprovider.config;

import com.alibaba.dubbo.config.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pk
 * @date 2018/6/6
 */
@Configuration
public class DubboConfig {

    @Value("${dubbo.application.name}")
    private String applicationName;
    @Value("${dubbo.application.logger}")
    private String logger;
    @Value("${dubbo.application.dumpDirectory}")
    private String dumpDirectory;
    @Value("${dubbo.application.owner}")
    private String owner;


    @Value("${dubbo.registry}")
    private String registryAddress;
    @Value("${dubbo.protocol.port}")
    private int dubboPort;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger(logger);
        applicationConfig.setName(applicationName);
        applicationConfig.setOwner(owner);
        applicationConfig.setDumpDirectory(dumpDirectory);

        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddress);
        registryConfig.setRegister(true);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", dubboPort);
        // 默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
        protocolConfig.setSerialization("java");
        return protocolConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(10000);
        providerConfig.setThreadpool("fixed");
        providerConfig.setThreads(100);
        providerConfig.setAccepts(1000);
        return providerConfig;
    }
}
