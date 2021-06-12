package senchenko.interview;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@SpringBootTest
class ConferenceUiApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIndexPage() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("rooms"))
                .andExpect(model().attributeExists("schedules"));
    }

}
