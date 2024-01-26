package com.example.pouring;

import com.example.beverage.Beverage;
import com.example.beverage.BeverageService;
import com.example.cup.Cup;
import com.example.cup.CupService;
import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.service.AbstractConfigurableService;
import com.tccc.kos.commons.util.concurrent.future.FutureWork;

import java.util.Optional;

public class PouringService extends AbstractConfigurableService<PouringConfig> {
    FutureWork currentFw;
    PouringSession session = new PouringSession();

    @Autowired
    BeverageService beverageService;
    CupService cupService;

    public FutureWork startPouring() {
        currentFw = new FutureWork("start-pour", (f) -> {

            session = new PouringSession();
            f.success();
        }, 3000);

        return currentFw;
    }

    public void stopPouring() {
        currentFw.cancel("stop pouring");
    }

    public void setSize(String s) {
        if (!session.getCup().getKey().equals(s)) {
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

        selectedCup.ifPresent((cup) -> session.setCup(cup));
    }

    public void setSelectedBeverage(String b) {
        if (!session.getBeverage().getId().equals(b)) {
            session = new PouringSession();
        }

        Optional<Beverage> selectedBeverage = beverageService
                .getBeverages()
                .stream()
                .filter((beverage) -> {
                    return beverage.getId().equals(b);
                })
                .findFirst();

        selectedBeverage.ifPresent((bev) -> session.setBeverage(bev));
    }
}
