package com.example;

import com.tccc.kos.commons.core.service.AbstractConfigurableService;
import com.tccc.kos.commons.core.service.config.BeanChanges;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThemeService extends AbstractConfigurableService<MyConfigBean> {
    public String bar() {
        log.info("KosService bar");
        MyConfigBean config = getConfig();
        return String.format("is dark mode: %s", config.isDarkMode());
    }

    @Override
    public void onConfigChanged(BeanChanges changes) {
        log.info("ThemeService config changed");
        super.onConfigChanged(changes);
    }
}
