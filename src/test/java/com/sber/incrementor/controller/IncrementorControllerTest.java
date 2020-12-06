package com.sber.incrementor.controller;

import com.sber.incrementor.service.IncrementorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = IncrementorController.class)
public class IncrementorControllerTest {

    @MockBean
    private IncrementorService incrementorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetNumber() throws Exception {
        when(incrementorService.getNumber()).thenReturn(42);

        mockMvc.perform(get("/incrementor/number"))
                .andExpect(content().string("42"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldIncrementNumber() throws Exception {
        mockMvc.perform(put("/incrementor/increment"))
                .andExpect(status().isOk());

        verify(incrementorService, times(1)).incrementNumber();
    }

    @Test
    void shouldSetMaximumValue() throws Exception {
        mockMvc.perform(put("/incrementor/maximum?newMaxValue=3"))
                .andExpect(status().isOk());

        verify(incrementorService, times(1)).setMaximumValue(3);
    }
}
