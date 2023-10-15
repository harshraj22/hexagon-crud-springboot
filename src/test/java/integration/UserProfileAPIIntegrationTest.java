package integration;

import com.example.Profile.adaptor.inbound.controller.apiModels.AuthCredsApiModel;
import com.example.Profile.adaptor.inbound.controller.apiModels.UserProfileApiModel;
import com.example.Profile.adaptor.outbound.repository.AuthCredsRepository;
import com.example.Profile.adaptor.outbound.repository.UserProfileRepository;
import com.example.Profile.domain.dto.UserProfileDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static integration.AuthCredsAPIIntegrationTest.getAuthCredsApiModel;
import static org.assertj.core.api.Assertions.assertThat;

public class UserProfileAPIIntegrationTest extends BaseIntegrationTest{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AuthCredsRepository authCredsRepository;

    private void setupMocksUnderIntegrationProfile(Runnable runnable) {
        runnable.run();
    }

    private String userProfileApiUrl() {
        return getBaseUrl() + "/v1/userprofile";
    }

    @AfterEach
    void cleanUp() {
        userProfileRepository.deleteAll();
        authCredsRepository.deleteAll();
    }

    public static UserProfileApiModel getUserProfileApiModel() {
        AuthCredsApiModel authCredsApiModel = getAuthCredsApiModel();
        UserProfileApiModel userProfileApiModel = UserProfileApiModel.builder()
                .username(authCredsApiModel.getUsername())
                .age(23)
                .name("test-name")
                .email("test-email.com")
                .build();
        return userProfileApiModel;
    }

    @Test
    @SneakyThrows
    void givenValidAuthCreds_whenPostUserProfile_thenUserProfileInserted() {
        UserProfileApiModel userProfileApiModel = getUserProfileApiModel();
        AuthCredsApiModel authCredsApiModel = getAuthCredsApiModel();

        HttpEntity<AuthCredsApiModel> authCredsPostHttpEntity = new HttpEntity<>(authCredsApiModel);
        String url = getBaseUrl() + "/v1/authcreds";
        restTemplate.exchange(url, HttpMethod.POST, authCredsPostHttpEntity, Boolean.class);

        HttpEntity<UserProfileApiModel> userProfilePostHttpEntity = new HttpEntity<>(userProfileApiModel);
        String userProfilePostUrl = userProfileApiUrl();
        restTemplate.exchange(userProfilePostUrl, HttpMethod.POST, userProfilePostHttpEntity, Boolean.class);

        ResponseEntity<UserProfileDTO> getResponseEntity = restTemplate.getForEntity(
                String.format(userProfilePostUrl + "/%s", userProfileApiModel.getUsername()),
                UserProfileDTO.class
        );

        assertThat(userProfileApiModel.getAge()).isEqualTo(getResponseEntity.getBody().getAge());
        assertThat(userProfileApiModel.getName()).isEqualTo(getResponseEntity.getBody().getName());
        assertThat(userProfileApiModel.getUsername()).isEqualTo(getResponseEntity.getBody().getUsername());
        assertThat(authCredsApiModel.getUsername()).isEqualTo(getResponseEntity.getBody().getUsername());

    }
}
