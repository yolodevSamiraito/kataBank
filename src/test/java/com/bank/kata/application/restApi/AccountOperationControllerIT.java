package com.bank.kata.application.restApi;

import com.bank.kata.application.KataBankAccountApplication;
import com.bank.kata.application.dao.AccountRepository;
import com.bank.kata.application.entities.Account;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.AccountService;
import com.bank.kata.application.service.dto.AccountDTO;
import com.bank.kata.application.service.dto.HistoryDTO;
import com.bank.kata.application.service.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = KataBankAccountApplication.class)
@Transactional
public class AccountOperationControllerIT {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MockMvc restAccountMockMvc;

    @Test
    public void testCheck() throws Exception {
        restAccountMockMvc.perform(get("/api/account/1/check")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("3000"));
    }

    @Test
    public void testCheck_error() throws Exception {
        restAccountMockMvc.perform(get("/api/account/99999/check")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(content().string("Account not Found"));
    }

    @Test
    public void testDeposit_success() throws Exception {
        restAccountMockMvc.perform(post("/api/account/1/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("5000"))
                .andExpect(status().isOk())
                .andExpect(content().string("8000"));
    }

    @Test
    public void testDeposit_error() throws Exception {
        restAccountMockMvc.perform(post("/api/account/99999/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("5000"))
                .andExpect(status().is(404))
                .andExpect(content().string("Account not Found"));
    }

    @Test
    public void testDepositNegative_error() throws Exception {
        restAccountMockMvc.perform(post("/api/account/99999/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("-5000"))
                .andExpect(status().is(400))
                .andExpect(content().string("Balance can't be negative"));
    }

    @Test
    public void testWithdraw() throws Exception {
        restAccountMockMvc.perform(post("/api/account/1/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2000"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }

    @Test
    public void testWithdraw_error() throws Exception {
        restAccountMockMvc.perform(post("/api/account/99999/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("2000"))
                .andExpect(status().is(404))
                .andExpect(content().string("Account not Found"));
    }

    @Test
    public void testWithdrawNegative_error() throws Exception {
        restAccountMockMvc.perform(post("/api/account/1/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("-2000"))
                .andExpect(status().is(400))
                .andExpect(content().string("Balance can't be negative"));
    }

    @Test
    public void testWithdrawInsufficientBalance() throws Exception {
        restAccountMockMvc.perform(post("/api/account/1/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("4000"))
                .andExpect(status().is(400))
                .andExpect(content().string("Insufficient Balance to process this operation"));
    }

    @Test
    public void testPrint() throws Exception {
        List<HistoryDTO> historyDTOS = new ArrayList<>();
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setAmount(5000L);
        historyDTO.setNewBalance(6000L);
        historyDTO.setOldBalance(1000L);
        historyDTO.setOperationType(History.OperationType.Deposit);
        historyDTO.setDateCreated(new Date());
        historyDTOS.add(historyDTO);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setBalance(6000L);
        accountDTO.setFirstName("AccountTestFirstName");
        accountDTO.setLastName("AccountTestLastName");
        accountDTO.setHistories(historyDTOS);
        Account account = accountMapper.accountDTOToAccount(accountDTO);
        accountRepository.saveAndFlush(account);
        int id = accountRepository.findByLastName("AccountTestLastName").getId();
        restAccountMockMvc.perform(get("/api/account/"+id+"/print")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].oldBalance").value(1000L));
    }

    @Test
    public void testPrint_error() throws Exception {

        restAccountMockMvc.perform(get("/api/account/99999/print")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404))
                .andExpect(content().string("Account not Found"));
    }
}
