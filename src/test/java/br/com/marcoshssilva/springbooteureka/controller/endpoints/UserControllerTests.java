package br.com.marcoshssilva.springbooteureka.controller.endpoints;

import br.com.marcoshssilva.springbooteureka.configs.WebSecurityConfiguration;
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
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import java.util.Collections;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(WebSecurityConfiguration.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DataSource dataSource;

    @MockBean
    private UserManagementService userManagementService;

    @MockBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testChangePassword() throws Exception {
        User user = new User("tester", "pass", true, new HashSet<>());
        UserChangePasswordRequestBodyDto body = new UserChangePasswordRequestBodyDto("oldPass", "newPass");
        
        doNothing().when(userManagementService).changePasswordFromUsername(anyString(), anyString(), anyString());

        mockMvc.perform(post("/api/user/change-password")
                .with(csrf())
                .with(user(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }
}
