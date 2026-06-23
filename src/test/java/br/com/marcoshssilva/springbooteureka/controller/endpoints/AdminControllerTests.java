package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminCreateUserRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminResetPasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.controller.data.requests.AdminUpdateUserRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitSuperUserIfNotExistsTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminController.class)
@WithMockUser(roles = "ADMIN")
class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserManagementService userManagementService;

    @MockBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testResetPassword() throws Exception {
        AdminResetPasswordRequestBodyDto body = new AdminResetPasswordRequestBodyDto("tester", "newPass");

        mockMvc.perform(post("/api/admin/reset-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testCreateUser() throws Exception {
        AdminCreateUserRequestBodyDto body = new AdminCreateUserRequestBodyDto("tester", "password", true, new UserRoles[]{UserRoles.CLIENT});

        mockMvc.perform(post("/api/admin/create-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testUpdateUser() throws Exception {
        AdminUpdateUserRequestBodyDto body = new AdminUpdateUserRequestBodyDto("tester", new UserRoles[]{UserRoles.CLIENT});

        mockMvc.perform(post("/api/admin/update-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/admin/delete-user/tester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testEnableUser() throws Exception {
        mockMvc.perform(put("/api/admin/enable-user/tester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    void testDisableUser() throws Exception {
        mockMvc.perform(put("/api/admin/disable-user/tester"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }
}
