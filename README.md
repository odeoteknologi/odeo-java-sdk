# odeo-java-sdk
Odeo Java SDK for interacting with https://api.v2.odeo.co.id/

## Quickstart
This SDK can be obtained by adding it as a maven dependency, cloning the source into your project, or by downloading one of the precompiled JARs from the [releases page on Github](https://github.com/odeoteknologi/odeo-java-sdk/releases).

**IF YOU USE THE JAR, you'll also need to include several dependencies:**
1. [Apache HttpComponents 4.5.10](https://hc.apache.org/httpcomponents-client-4.5.x/index.html)
2. [JSON Simple 1.1.1](https://code.google.com/archive/p/json-simple/)

## Building
The SDK uses Gradle for its build system. Running `gradle build` from the root of the repository will compile the SDK.

## Documentation
Javadocs are generated when `gradle javadoc` is run and can be found in
`build/doc/javadoc`.
