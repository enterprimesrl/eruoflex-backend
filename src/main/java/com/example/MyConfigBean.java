package com.example;

import com.tccc.kos.commons.core.service.config.ConfigBean;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyConfigBean extends ConfigBean {
    private boolean isDarkMode;
    private String themeName;

    public MyConfigBean() {
        isDarkMode = false;
        themeName = "Default";
    }
}
