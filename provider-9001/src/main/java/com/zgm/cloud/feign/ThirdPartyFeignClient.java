package com.zgm.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author octopus
 */
@FeignClient(value = "third-party")
public interface ThirdPartyFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/oss/policy")
    Map<String, String> policy();
}
