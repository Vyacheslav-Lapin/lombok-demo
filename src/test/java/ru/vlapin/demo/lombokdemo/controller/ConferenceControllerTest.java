package ru.vlapin.demo.lombokdemo.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.vlapin.demo.lombokdemo.model.ConferenceUser;
import ru.vlapin.demo.lombokdemo.service.ConferenceUserService.ConferenceUserDetails;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ConferenceControllerTest.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ConferenceControllerTest {

  MockMvc mockMvc;

  @Test
  @SneakyThrows
  @DisplayName("About returns conference info")
  void aboutReturnsConferenceInfoTest() {
    mockMvc.perform(get("/about"))
        .andExpect(status().isOk())
        .andExpect(content().string("Join us online September 1-2!"));
  }

  @Test
  @SneakyThrows
  @DisplayName("Greetings returns hallo and username")
  void greetingsReturnsHalloAndUsernameTest() {
    mockMvc.perform(get("/greetings")
            .with(user("Ria")))
        .andExpect(status().isOk())
        .andExpect(content().string("Hello, Ria!"));
  }

  @Test
  @SneakyThrows
  @DisplayName("Greetings when unauthenticated user then returns 401")
  void greetingsWhenUnauthenticatedUserThenReturns401Test() {
    mockMvc.perform(get("/greetings"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @SneakyThrows
  @DisplayName("Submissions when user is speaker returns list of submissions")
  void submissionsWhenUserIsSpeakerReturnsListOfSubmissionsTest() {
    // given
    val joe = new ConferenceUser()
        .setUsername("Joe")
        .setSubmissions(List.of("Getting Started with Spring Authorization Server"))
        .setSpeaker(true);
    // when
    mockMvc.perform(get("/submissions")
            .with(user(new ConferenceUserDetails(joe))))
        // then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0]", is("Getting Started with Spring Authorization Server")));
  }

  @Test
  @SneakyThrows
  @DisplayName("Submissions when user is not speaker returns 403")
  void submissionsWhenUserIsNotSpeakerReturns403Test() {
    mockMvc.perform(get("/submissions")
            .with(user("user").roles("ATTENDEE")))
        .andExpect((status().isForbidden()));
  }

  @Test
  @SneakyThrows
  @DisplayName("Submissions when unauthenticated user return 401")
  void submissionsWhenUnauthenticatedUserReturn401Test() {
    mockMvc.perform(get("/submissions"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  @SneakyThrows
  @DisplayName("Post about when user is admin then updates conference info")
  void postAboutWhenUserIsAdminThenUpdatesConferenceInfoTest() {
    //noinspection JsonStandardCompliance
    val content = "Join us online September 11-12!";
    mockMvc.perform(post("/about")
            .content(content)
            .with(user("admin").roles("ADMIN"))
            .with(csrf()))
        .andExpect(status().isNoContent());

    mockMvc.perform(get("/about"))
        .andExpect(status().isOk())
        .andExpect(content().string(content));
  }

  @Test
  @SneakyThrows
  @DisplayName("Post about no csrf token then 403")
  void postAboutNoCsrfTokenThen403Test() {
    //noinspection JsonStandardCompliance
    mockMvc.perform(post("/about")
            .content("Lorem ipsum dolor sit amet")
            .with(user("admin").roles("ADMIN")))
        .andExpect(status().isForbidden());
  }

  @Test
  @SneakyThrows
  @DisplayName("Post about when user is not admin then returns 403")
  void postAboutWhenUserIsNotAdminThenReturns403Test() {
    //noinspection JsonStandardCompliance
    mockMvc.perform(post("/about")
            .content("Lorem ipsum dolor sit amet")
            .with(csrf())
            .with(user("speaker").roles("SPEAKER")))
        .andExpect(status().isForbidden());
  }

  @Test
  @SneakyThrows
  @DisplayName("Post about when unauthenticated user returns 401")
  void postAboutWhenUnauthenticatedUserReturns401Test() {
    //noinspection JsonStandardCompliance
    mockMvc.perform(post("/about")
            .content("Lorem ipsum dolor sit amet")
            .with(csrf()))
        .andExpect(status().isUnauthorized());

  }
}
