package com.example;

import com.example.beverage.BeverageController;
import com.example.beverage.BeverageService;
import com.example.cup.CupController;
import com.example.cup.CupService;
import com.example.pouring.PouringController;
import com.example.pouring.PouringService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tccc.kos.core.service.app.BaseAppConfig;
import com.tccc.kos.core.service.app.SystemApplication;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class KosHelloWorld extends SystemApplication<BaseAppConfig> {
    @Override
    public void load() throws JsonProcessingException {
        log.info("Load");
        getCtx().add(new MyController());
        getCtx().add(new ThemeService());

        getCtx().add(new PouringController());
        getCtx().add(new PouringService());

        getCtx().add(new BeverageController());
        getCtx().add(new BeverageService());

        getCtx().add(new CupController());
        getCtx().add(new CupService());
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

    public static void main(String[] args) {
    }
}
