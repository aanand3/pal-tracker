package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EnvController {
    private String port;
    private String memLimit;
    private String index;
    private String addr;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memLimit,
                         @Value("${cf.instance.index:NOT SET}") String index,
                         @Value("${cf.instance.addr:NOT SET}") String addr)
    {
        this.port = port;
        this.memLimit = memLimit;
        this.index = index;
        this.addr = addr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv()
    {
        return Map.of("PORT", port, "MEMORY_LIMIT", memLimit,
                      "CF_INSTANCE_INDEX", index, "CF_INSTANCE_ADDR", addr);
    }
}
