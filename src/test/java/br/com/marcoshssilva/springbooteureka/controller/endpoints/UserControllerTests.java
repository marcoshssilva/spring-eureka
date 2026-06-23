package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.controller.data.requests.UserChangePasswordRequestBodyDto;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTests {

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
    @WithMockUser(username = "tester")
    void testChangePassword() throws Exception {
        UserChangePasswordRequestBodyDto body = new UserChangePasswordRequestBodyDto("oldPass", "newPass");
        
        doNothing().when(userManagementService).changePasswordFromUsername(anyString(), anyString(), anyString());

        mockMvc.perform(post("/api/user/change-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }
}
