package com.farda.contoller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.farda.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@TestPropertySource( locations = "classpath:application-test.properties" )
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsers() throws Exception {
        System.out.println("Launching get test :");
        mockMvc.perform(get("/user/getUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username", is("Guillaume")));
    }

//    @Test
//    public void testPostUser() throws Exception {
//        // Données de test
//        String requestBody = "{\"username\": \"Bob\", \"email\": \"b@b.fr\", \"password\": \"secret\",\"" +
//                "articleList\": [{\"title\": \"testtitle\", \"content\": \"testcontent\"}]";
//
//        // Exécution de la requête POST
//        mockMvc.perform(post("/user/newUser")
//                        .content(requestBody)
//                        .contentType("application/json"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("Bob"));
//
//    }

    @Test
    public void testPutUser() throws Exception {
        // Données de test
        String requestBody = "{\"username\": \"Billy\", \"email\": \"a@a.fr\", \"password\": \"newpassword\"}";

        // Exécution de la requête PUT
        mockMvc.perform(put("/user/updateUser/{id}", 2)
                        .content(requestBody)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Billy"))
                .andExpect(jsonPath("$.password").value("newpassword"));

    }

    @Test
    public void deleteUser() throws Exception {

        mockMvc.perform(delete("/user/removeUser/{id}", 2))
                .andExpect(status().isOk());
    }
}
