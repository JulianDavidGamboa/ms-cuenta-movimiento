package com.mybank.mscuentamovimiento.infrastrcuture.feign;

import com.mybank.mscuentamovimiento.application.dto.ClientDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.ServiceUnavailableException;


@FeignClient(name = "client-ms", url = "${client.ms.url}")
@CircuitBreaker(name = "clientValidation", fallbackMethod = "fallbackGetClient")
@Retry(name = "clientValidation")
public interface ClientFeignClient {
    @GetMapping("/clients/{id}")
    ResponseEntity<ClientDto> getClient(@PathVariable String id);

    default ResponseEntity<ClientDto> fallbackGetClient(String clientId, Throwable t) throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Servicio de clientes no disponible temporalmente");
    }
}
