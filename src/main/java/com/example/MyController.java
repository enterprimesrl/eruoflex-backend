package com.example;

import com.tccc.kos.commons.core.context.annotations.Autowired;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiController;
import com.tccc.kos.commons.core.dispatcher.annotations.ApiEndpoint;

@ApiController(base = "/mycontroller", title = "This is a KOS controller")
public class MyController {
    @Autowired
    private ThemeService themeService;

    @ApiEndpoint(GET = "/foo", desc = "This is an endpoint")
    public String foo() {
        return themeService.foo();
    }

    @ApiEndpoint(GET = "/bar", desc = "This is an endpoint")
    public String bar() {
        return themeService.bar();
    }
}
