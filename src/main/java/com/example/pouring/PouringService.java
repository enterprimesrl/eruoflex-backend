package com.example.pouring;

import com.tccc.kos.commons.core.service.AbstractConfigurableService;
import com.tccc.kos.commons.util.concurrent.future.FutureWork;

public class PouringService extends AbstractConfigurableService<PouringConfig> {
    FutureWork currentFw;
    String selectedSize;
    Beverage selectedBeverage;

    public FutureWork startPouring() {
        currentFw = new FutureWork("start-pour", (f) -> {
            Thread.sleep(3000);
            f.success();
        }, 3000);

        return currentFw;
    }

    public void stopPouring() {
        currentFw.cancel("stop pouring");
    }

    public void setSize(String s) {
        selectedSize = s;
    }

    public void setSelectedBeverage(Beverage b) {
        selectedBeverage = b;
    }
}
