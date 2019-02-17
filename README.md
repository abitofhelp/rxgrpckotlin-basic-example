# com.ingios.dashboard.services.gismapping
This repository contains a microservice that provides GIS mapping services.

The web service is implemented with the following key technologies:

- Language: Kotlin
- Software Architecture:
  [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)
- Deployment: Linux Docker Image
- Persistence: None


<h2>How to update the Gradle Wrapper</h2>
 ./gradlew wrapper --gradle-version 5.2.1 --distribution-type all

<h2>How to create a Gradle-Kotlin project</h2>
gradle init --dsl kotlin


<h2> How to update dependency versions?</h2>
We are using the "jmfayard.github.io.gradle-kotlin-dsl-libs" plugin, which automates
dependency and version information.  This information is saved in two Kotlin class 
files: Versions.kt and Libs.kt in the following folder:                                                          
                                                           
buildSrc/src/main/kotlin folder.  

Since these files are in the buildSrc hierarchy of directories, the values in these 
classes are available where ever Gradle is being used in the application.

To add a new dependency, insert it into build.gradle.kts with its version information, 
and execute the following command in a terminal in the project-root folder:

$ ./gradlew syncLibs  

It will update the Versions and Libs classes.  Open the Libs.kt file, locate
the new dependency, and copy its variable name.  Replace the string in 
build.gradle.kts with the variable name for the new dependency.

Please visit the following website to learn more about this fantastic plugin:

https://github.com/jmfayard/gradle-kotlin-dsl-libs
