# GrpcKotlin-simple
This repository contains a basic example that shows using Gradle-KotlinDsl, 
Kotlin, and reactor-grpc.  This code is a work in progress, but it may be 
helpful to others...

The web service is implemented with the following key technologies:

- Language: Kotlin
- Build: Gradle, Kotlin DSL 
- Remote Interface: grpc and reactor-grpc
- Deployment: Executable Jar
- Persistence: None

<h1>Reminders for myself...</h1>
<h2>How to update the Gradle Wrapper</h2>
 ./gradlew wrapper --gradle-version 5.2.1 --distribution-type all

<h2>How to create a Gradle-Kotlin project</h2>
gradle init --dsl kotlin
