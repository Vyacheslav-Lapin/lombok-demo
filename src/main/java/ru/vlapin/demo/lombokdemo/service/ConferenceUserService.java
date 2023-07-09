package ru.vlapin.demo.lombokdemo.service;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.vlapin.demo.lombokdemo.model.ConferenceUser;

import java.util.Collection;

import static org.springframework.security.core.authority.AuthorityUtils.*;

/**
 * ConferenceUserService.
 *
 * @see <a href="https://youtu.be/TytSz7u1xQ8">"Test-Driven Security" by Eleftheria Stain-Kousathana, SpringOne, 2021</a>
 */
@UtilityClass
public class ConferenceUserService {
  public final class ConferenceUserDetails extends ConferenceUser implements UserDetails {

    public ConferenceUserDetails(ConferenceUser user) {
      super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      val roles = createAuthorityList("ROLE_ATTENDEE");
      if (isSpeaker()) roles.add(new SimpleGrantedAuthority("ROLE_SPEAKER"));
      if (isAdmin()) roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return false;
    }
  }
}
