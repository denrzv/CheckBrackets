package io.github.denrzv.checkbrackets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Валидация корректного текста")
    public void shouldReturnIsCorrectWhenTextIsValid() throws Exception {
        mockMvc.perform(post("/api/checkBrackets")
            .contentType("application/json")
            .content("{\"text\":\"Это корректный (текст)\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isCorrect").value(true));
    }

    @Test
    @DisplayName("Валидация некорректного текста")
    public void shouldReturnNotCorrectWhenTextIsInvalid() throws Exception {
        mockMvc.perform(post("/api/checkBrackets")
                        .contentType("application/json")
                        .content("{\"text\":\"Это некорректный (текст\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isCorrect").value(false));
    }

    @Test
    @DisplayName("Валидация пустого текста")
    public void shouldReturnBadRequestWhenTextIsEmpty() throws Exception {
        mockMvc.perform(post("/api/checkBrackets")
                        .contentType("application/json")
                        .content("{\"text\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}
