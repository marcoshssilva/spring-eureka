package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminCreateUserRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminResetPasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminUpdateUserRequestBodyDto;
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
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(username = "admin", roles = "ADMIN")
@Transactional
class AdminControllerTests {

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
    void testResetPassword() throws Exception {
        // Create user first
        userManagementService.createUser("tester", "password", true, new String[]{"CLIENT"});

        AdminResetPasswordRequestBodyDto body = new AdminResetPasswordRequestBodyDto("tester", "newPass");

        mockMvc.perform(post("/api/admin/reset-password")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testCreateUser() throws Exception {
        AdminCreateUserRequestBodyDto body = new AdminCreateUserRequestBodyDto("tester", "password", true, new UserRoles[]{UserRoles.CLIENT});

        mockMvc.perform(post("/api/admin/create-user")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testUpdateUser() throws Exception {
        // Create user first
        userManagementService.createUser("tester", "password", true, new String[]{"CLIENT"});

        AdminUpdateUserRequestBodyDto body = new AdminUpdateUserRequestBodyDto("tester", new UserRoles[]{UserRoles.CLIENT});

        mockMvc.perform(post("/api/admin/update-user")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testDeleteUser() throws Exception {
        // Create user first
        userManagementService.createUser("tester", "password", true, new String[]{"CLIENT"});

        mockMvc.perform(delete("/api/admin/delete-user/tester")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testEnableUser() throws Exception {
        // Create user first
        userManagementService.createUser("tester", "password", false, new String[]{"CLIENT"});

        mockMvc.perform(put("/api/admin/enable-user/tester")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testDisableUser() throws Exception {
        // Create user first
        userManagementService.createUser("tester", "password", true, new String[]{"CLIENT"});

        mockMvc.perform(put("/api/admin/disable-user/tester")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testCreateUser_InvalidPayload() throws Exception {
        AdminCreateUserRequestBodyDto body = new AdminCreateUserRequestBodyDto("", "", null, null);

        mockMvc.perform(post("/api/admin/create-user")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.message").value("Invalid argument data in payload."));
    }

    @Test
    void testCreateUser_MalformedJson() throws Exception {
        mockMvc.perform(post("/api/admin/create-user")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"invalid\": json }"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.message").value("Invalid or corrupted payload"));
    }

    @Test
    void testCreateUser_Duplicate() throws Exception {
        AdminCreateUserRequestBodyDto body = new AdminCreateUserRequestBodyDto("tester", "password", true, new UserRoles[]{UserRoles.CLIENT});
        
        // Create user once
        mockMvc.perform(post("/api/admin/create-user")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk());

        // Try to create again
        mockMvc.perform(post("/api/admin/create-user")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("ERROR"))
                .andExpect(jsonPath("$.message").value("Username already exists in database."));
    }

    @Test
    void testNonExistentResource() throws Exception {
        mockMvc.perform(get("/api/admin/non-existent")
                .with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testMethodNotSupported() throws Exception {
        mockMvc.perform(get("/api/admin/create-user")
                .with(csrf()))
                .andExpect(status().isNotFound());
    }
}
