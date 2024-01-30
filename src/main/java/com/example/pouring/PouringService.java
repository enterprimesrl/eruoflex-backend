package com.example.pouring;

import com.example.beverage.Beverage;
import com.example.beverage.BeverageService;
import com.example.cup.Cup;
import com.example.cup.CupService;
import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.exceptions.NotFoundException;
import com.tccc.kos.commons.core.service.AbstractConfigurableService;
import com.tccc.kos.commons.util.concurrent.future.FutureWork;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PouringService extends AbstractConfigurableService<PouringConfig> {
    FutureWork currentFw;
    PouringSession session;

    @Autowired
    BeverageService beverageService;
    @Autowired
    CupService cupService;

    public FutureWork startPouring() throws InterruptedException {
        if (session == null || !session.canPour()) {
            throw new NotFoundException("session not created or not ready to pour yet");
        }
        log.info("Pouring started for a maximum of " + session.getPourTime());

        PouringConfig pouringConfig = getConfig();

        currentFw = new FutureWork("startPour", (f) -> {
            do {
                session.setVolumePoured(session.getVolumePoured() + pouringConfig.getPouringRate());
                log.info("Current Pouring: " + session.getVolumePoured() + " of " + session.getCup().getVolume());
                Thread.sleep(1000);
            } while (session.getVolumePoured() < session.getCup().getVolume());
            f.success();
        }, session.getPourTime());

        currentFw.setInterruptable(true);
        currentFw.getClientData().setData(session);
        return currentFw;
    }

    public void stopPouring() {
        currentFw.cancel("stopPouring");
        log.info("Stop Pouring: " + session.getVolumePoured() + " of " + session.getCup().getVolume());
    }

    public PouringSession setCup(String cupKey) {
        Cup selectedCup = cupService.getCup(cupKey);
        if (selectedCup == null) {
            log.error("cup not found. key: " + cupKey);
            throw new NotFoundException("cup not found");
        }

        session.setCup(selectedCup);

        return session;
    }

    public void setBeverage(String beverageKey) {
        Beverage selectedBeverage = beverageService.getBeverage(beverageKey);
        if (selectedBeverage == null) {
            log.error("beverage not found. key: " + beverageKey);
            throw new NotFoundException("beverage not found");
        }

        if (session == null) {
            session = new PouringSession(getConfig().getPouringRate());
        }

        session.setBeverage(selectedBeverage);
    }
}
