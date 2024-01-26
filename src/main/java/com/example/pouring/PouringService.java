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

import java.util.Optional;

@Slf4j
public class PouringService extends AbstractConfigurableService<PouringConfig> {
    FutureWork currentFw;
    PouringSession session = new PouringSession();

    @Autowired
    BeverageService beverageService;
    @Autowired
    CupService cupService;

    public FutureWork startPouring() throws InterruptedException {
        log.info("Pouring started for a maximum of " + getPourTime());

        currentFw = new FutureWork("startPour", (f) -> {
            do {
                session.setVolumePoured(session.getVolumePoured() + session.getCup().getDefaultRate());
                log.info("Current Pouring: " + session.getVolumePoured() + " of " + session.getCup().getVolume());
                Thread.sleep(1000);
            } while (session.getVolumePoured() < session.getCup().getVolume());
            f.success();
        }, getPourTime());

        currentFw.setInterruptable(true);
        currentFw.getClientData().setData(session);
        return currentFw;
    }

    public void stopPouring() {
        currentFw.cancel("stopPouring");
        log.info("Stop Pouring: " + session.getVolumePoured() + " of " + session.getCup().getVolume());
    }

    public PouringSession setSize(String s) {
        log.info("PouringService.setSize: " + s);
        if (session.getCup() != null && !session.getCup().getKey().equals(s)) {
            Beverage selectedBev = session.getBeverage();
            session = new PouringSession();
            session.setBeverage(selectedBev);
        }

        Optional<Cup> selectedCup = cupService
                .getCups()
                .stream()
                .filter((cup) -> {
                    return cup.getKey().equals(s);
                })
                .findFirst();

        selectedCup.ifPresentOrElse(
                (cup) -> {
                    log.info("PouringService.selectedCup: " + cup.getVolume());
                    session.setCup(cup);
                },
                () -> {
                    log.error("PouringService.selectedCup: not found");
                    throw new NotFoundException("Cup not found");
                });

        return session;
    }

    public void setSelectedBeverage(String b) {
        if (session.getBeverage() != null &&
                !session.getBeverage().getId().equals(b)) {
            session = new PouringSession();
        }

        Optional<Beverage> selectedBeverage = beverageService
                .getBeverages()
                .stream()
                .filter((beverage) -> {
                    return beverage.getId().equals(b);
                })
                .findFirst();

        selectedBeverage.ifPresentOrElse(
                (cup) -> session.setBeverage(cup),
                () -> {
                    throw new NotFoundException("Beverage not found");
                });
    }
    
    private int getPourTime() {
        double volumePoured = session.getVolumePoured();
        int totalVolumeToPour = session.getCup().getVolume();
        if (volumePoured >= totalVolumeToPour) {
            return 0;
        }

        double volumeToPour = totalVolumeToPour - volumePoured;
        return (int) (volumeToPour / session.getCup().getDefaultRate()) * 1000;
    }
}
