package com.mybank.mscuentamovimiento.application.rest;

import com.mybank.mscuentamovimiento.application.dto.AccountDto;
import com.mybank.mscuentamovimiento.application.mapper.AccountMapper;
import com.mybank.mscuentamovimiento.application.service.AccountService;
import com.mybank.mscuentamovimiento.domain.model.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService service;

    @MockitoBean
    private AccountMapper mapper;

    @Test
    void testCreateAccount() throws Exception {
        AccountDto dto = new AccountDto();
        dto.setAccountNumber("Test");

        when(mapper.toDomain(any(AccountDto.class))).thenReturn(new Account());
        when(service.create(any(Account.class))).thenReturn(new Account());
        when(mapper.toDto(any(Account.class))).thenReturn(dto);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\":\"Test\"}"))
                .andExpect(status().isOk());
    }
}