package com.mybank.mscuentamovimiento.infrastrcuture.feign;

import com.mybank.mscuentamovimiento.application.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-ms", url = "${client.ms.url}")
public interface ClientFeignClient {
    @GetMapping("/clients/{id}")
    ClientDto getClient(@PathVariable String id);
}
