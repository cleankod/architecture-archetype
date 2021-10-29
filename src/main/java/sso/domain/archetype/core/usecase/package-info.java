/**
 * The base package for the use case implementations. These should be the base classes for
 * aggregating business logic and coordinate calling proper components.
 *
 * Examples:
 * - AuthenticationUseCase
 * -- AuthenticationByFacebookUseCase
 * -- AuthenticationByGoogleUseCase
 * -- AuthenticationByUsernameUseCase
 * - FindAuthenticationSettingsUseCase
 * -- FindAuthenticationSettingsByFacebookUseCase
 * -- FindAuthenticationSettingsByGoogleUseCase
 * -- FindLastAuthenticationAttemptsUseCase
 * - ReauthenticateSessionUseCase
 */
package sso.domain.archetype.core.usecase;