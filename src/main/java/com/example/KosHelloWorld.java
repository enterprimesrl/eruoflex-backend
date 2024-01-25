package com.example;

import com.tccc.kos.core.service.app.BaseAppConfig;
import com.tccc.kos.core.service.app.SystemApplication;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class KosHelloWorld extends SystemApplication<BaseAppConfig> {
    @Override
    public void load() {
        log.info("Load");
        getCtx().add(new MyController());
        getCtx().add( new ThemeService());
    }

    @Override
    public void start(){
        log.info("Start");
    }
}
