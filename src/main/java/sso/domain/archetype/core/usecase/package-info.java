/**
 * The base package for the use case implementations. These should be the base classes for
 * aggregating business logic and coordinate calling proper components.
 *
 * <p>
 * Examples:
 * <li>AuthenticationUseCase</li>
 * <ul>
 *  <li>AuthenticationByFacebookUseCase</li>
 *  <li>AuthenticationByGoogleUseCase</li>
 *  <li>AuthenticationByUsernameUseCase</li>
 * </ul>
 * <li>FindAuthenticationSettingsUseCase</li>
 * <ul>
 *  <li>FindAuthenticationSettingsByFacebookUseCase</li>
 *  <li>FindAuthenticationSettingsByGoogleUseCase</li>
 *  <li>FindLastAuthenticationAttemptsUseCase</li>
 * </ul>
 * <li>ReauthenticateSessionUseCase</li>
 * </p>
 */
package sso.domain.archetype.core.usecase;