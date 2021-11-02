package sso.domain.user.config;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import sso.domain.user.core.gateway.UserRepository;
import sso.domain.user.provider.UserInMemoryRepository;

public class UserDomainConfiguration {
  @Provides
  @Singleton
  public UserRepository userRepository() {
    return new UserInMemoryRepository();
  }
}
