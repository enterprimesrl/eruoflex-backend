package com.example.pouring;

import com.tccc.kos.commons.core.service.config.ConfigBean;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PouringConfig extends ConfigBean {
    private int pouringRate = 75;
}
