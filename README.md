# About
This is an example project that proposes a certain application architecture.

Each package contains a `package-info.java` file, explaining what the package is for.

There are also two modules, which implement an oversimplified use case of authenticating with Username&Password and Facebook (without the user federation use case though).

# Assumptions and design decisions
## Black-box testing
Black-box testing is mostly used in order to favor refactoring. It is much simpler to completely change the underlying encapsulation of an use case without changing the tests. Moreover, the application is tested end-to-end, also with the exposed contract (API).

## Framework-less tests
Only the [`BaseRestSpec`](src/test/groovy/sso/BaseRestSpec.groovy) contains library-specific code. This approach eases the migration to other potential framework or toolset. The whole specification for the project stays the same.

## Framework-less modules' core
Wherever possible, no Framework-specific or library-specific stuff was used inside of actual modules' core. This also eases potential framework change or upgrade. The framework upgrade could also be more seamless for all of those changes that are not backwards compatible because framework specific stuff is kept in one place and the business logic is not polluted.

## Package-scoped classes
Package scoped access is used whenever possible to encapsulate the internal classes of a module.

## Value-objects
There is no simple value passed around in the project. Every business value is encapsulated within a value-object. It increases readability, enables nice methods override (instead of: `findUserById(String id)`, `findUserByUsername(String username)`, you can use: `find(UserId userId)`, `find(Username username)`), encapsulates internal data type (see `sso.domain.authentication.core.domain.PlainPassword` as an example).

Also, value-objects are responsible for a little more than just plain data holding. See [`sso/domain/authentication/core/domain/PlainPassword.java`](src/main/java/sso/domain/authentication/core/domain/PlainPassword.java) or [`sso/domain/authentication/core/domain/Token.java`](src/main/java/sso/domain/authentication/core/domain/Token.java) as examples. It is still debatable what to do if a certain logic needs non-static dependency. 

## Value objects validation
Bean Validation seamed an overkill here, hence the syntactic validation is done in the constructors of the value-objects. Beside keeping it simple, another advantage is that there is no way that a syntactically invalid value object would exist in runtime.

## Result either instead of exceptions
Each use case returns a [`Result<Success, Failure>`](src/main/java/sso/util/domain/Result.java). This enables error handling in a declarative and functional manner rather than using Exception, since  generally, the use of exceptions for flow-control is an anti-pattern.

## Commit-less release
By using Gradle's [axion-release-plugin](https://github.com/allegro/axion-release-plugin) creating a release does not require a commit, since the version of the project is not kept inside of any file. It is rather computed, based on the current tag, as an ancestor to the current commit. See the [plugin's docs](https://axion-release-plugin.readthedocs.io/en/latest/) for more information on how this works.

## Built with
* [SparkJava](https://sparkjava.com/) - A web micro-framework
* [Gradle](https://gradle.org/) - Dependency Management and build tool
* [Spock Framework](http://spockframework.org/) - Testing and specification framework
* [Project Lombok](https://projectlombok.org/) - Less boilerplate code
