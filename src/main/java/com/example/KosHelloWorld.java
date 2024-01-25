package com.example;

import com.example.pouring.PouringController;
import com.example.pouring.PouringService;
import com.tccc.kos.core.service.app.BaseAppConfig;
import com.tccc.kos.core.service.app.SystemApplication;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class KosHelloWorld extends SystemApplication<BaseAppConfig> {
    @Override
    public void load() {
        log.info("Load");
        getCtx().add(new PouringController());
        getCtx().add(new PouringService());
    }

    @Override
    public void start(){
        log.info("Start");
    }

    @Override
    public void started() {
        log.info("started");
    }

    @Override
    public void stop() throws Exception {
        log.info("stop");
        super.stop();
    }

    @Override
    public void unload() throws Exception {
        log.info("unload");
        super.unload();
    }
}
