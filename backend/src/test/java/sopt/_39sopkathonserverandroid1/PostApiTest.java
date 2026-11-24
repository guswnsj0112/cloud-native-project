package sopt._39sopkathonserverandroid1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = {"spring.datasource.url=jdbc:h2:mem:boardtest;DB_CLOSE_DELAY=-1", "spring.jpa.hibernate.ddl-auto=create-drop"})
@AutoConfigureMockMvc
class PostApiTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;
    @Test void crudAndValidation() throws Exception {
        String result = mvc.perform(post("/v1/posts").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"첫 글\",\"content\":\"내용\"}"))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.data.createdAt").exists()).andReturn().getResponse().getContentAsString();
        long id = mapper.readTree(result).path("data").path("id").asLong();
        mvc.perform(get("/v1/posts")).andExpect(status().isOk()).andExpect(jsonPath("$.data[0].title").value("첫 글"));
        mvc.perform(put("/v1/posts/{id}", id).contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"수정\",\"content\":\"내용\"}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data.title").value("수정"));
        mvc.perform(delete("/v1/posts/{id}", id)).andExpect(status().isOk());
        mvc.perform(get("/v1/posts/{id}", id)).andExpect(status().isNotFound()).andExpect(jsonPath("$.success").value(false));
        mvc.perform(post("/v1/posts").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\" \" ,\"content\":\"내용\"}"))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.data.title").exists());
    }
}
