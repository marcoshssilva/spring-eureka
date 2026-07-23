package com.github.marcoshssilva.eureka.controller.endpoints;

import com.github.marcoshssilva.eureka.controller.data.requests.UserChangePasswordRequestBodyDto;
import com.github.marcoshssilva.eureka.domain.services.UserManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@WithMockUser(username = "admin", roles = "ADMIN")
@Transactional
class UserControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private UserManagementService userManagementService;

    @BeforeEach
    void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
        // Remove tester if exists to have a clean state for each test
        try {
            if (!userDetailsManager.userExists("tester")) {
                // Create user with known password
                userManagementService.createUser("tester", "oldPass", true, new String[]{"CLIENT"});
            }
        } catch (Exception _) {
            // Ignored: user might not exist
        }
    }

    @Test
    void testChangePassword() throws Exception {
        UserDetails userDetails = userDetailsManager.loadUserByUsername("tester");

        UserChangePasswordRequestBodyDto body = new UserChangePasswordRequestBodyDto("newPass", "oldPass");
        
        mockMvc.perform(post("/api/user/change-password")
                .with(csrf())
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testChangePassword_WrongOldPassword() throws Exception {
        UserDetails userDetails = userDetailsManager.loadUserByUsername("tester");

        UserChangePasswordRequestBodyDto body = new UserChangePasswordRequestBodyDto("newPass", "wrongOldPass");
        
        mockMvc.perform(post("/api/user/change-password")
                .with(csrf())
                .with(user(userDetails))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.message").value("Password doesn't match. Check credentials."));
    }
}
