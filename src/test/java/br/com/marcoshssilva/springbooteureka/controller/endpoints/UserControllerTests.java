package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.requests.UserChangePasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitSuperUserIfNotExistsTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @Autowired
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @BeforeEach
    void setup() throws Exception {
        // Ensure default users exist
        initSuperUserIfNotExistsTask.executeTask().get();
        initMetricsUserIfNotExistsTask.executeTask().get();
        
        // Remove tester if exists to have a clean state for each test
        try {
            userManagementService.deleteUser("tester");
        } catch (Exception ignored) {
        }
    }

    @Test
    void testChangePassword() throws Exception {
        // Create user with known password
        userManagementService.createUser("tester", "oldPass", true, new String[]{"CLIENT"});

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
        // Create user with known password
        userManagementService.createUser("tester", "oldPass", true, new String[]{"CLIENT"});

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
