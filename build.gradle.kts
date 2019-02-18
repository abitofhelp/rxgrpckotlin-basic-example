import com.google.protobuf.gradle.*

plugins {
    // Apply the application plugin to add support for building a CLI application.
    application

    //java

    // Generate IntelliJ IDEA's .idea & .iml project files.
    // protobuf-gradle-plugin automatically registers *.proto and the gen output files
    // to IntelliJ as sources.
    // For best results, install the Protobuf and Kotlin plugins for IntelliJ.
    idea

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.20")


    id("com.google.protobuf") version "0.8.8"
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

//    implementation( "com.google.api.grpc:proto-google-common-protos:1.14.0")
    implementation("io.grpc:grpc-protobuf:1.15.1")

    // FixMe: Netty-Shaded wouldn't work...
    // Exception in thread "main" java.lang.NoSuchMethodError:
    // io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder.getTransportTracerFactory()Lio/grpc/internal/TransportTracer$Factory;
    implementation("io.grpc:grpc-netty:1.18.0")
    implementation("io.grpc:grpc-stub:1.15.1")

    if (JavaVersion.current().isJava9Compatible) {
        // Workaround for @javax.annotation.Generated
        // see: https://github.com/grpc/grpc-java/issues/3633
        implementation("javax.annotation:javax.annotation-api:1.3.1")
    }

    implementation("com.salesforce.servicelibs:rxgrpc-stub:0.9.0")
    implementation("com.salesforce.servicelibs:reactor-grpc-stub:0.9.0")

    // Use the Kotlin test library.
    //testImplementation("org.jetbrains.kotlin:kotlin-test")

    testImplementation("io.grpc:grpc-testing:1.18.0") // gRCP testing utilities
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:1.9.5")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")


    // implementation("com.google.protobuf:protobuf-java:3.6.1")


}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
        }

        id("reactor") {
            artifact = "com.salesforce.servicelibs:reactor-grpc:0.9.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")

                id("reactor")
            }
        }
    }
}

sourceSets {
    getByName("main").java.srcDirs("generated/source/proto/main/grpc")
    getByName("main").java.srcDirs("generated/source/proto/main/reactor")
    getByName("main").java.srcDirs("generated/source/proto/main/java")
    getByName("main").java.srcDirs("generated/source/proto/main/kotlin")
}

idea {
    module {
        generatedSourceDirs.plusAssign(file("build/generated/source/proto/main/grpc"))
        generatedSourceDirs.plusAssign(file("build/generated/source/proto/main/reactor"))
        generatedSourceDirs.plusAssign(file("build/generated/source/proto/main/java"))
        generatedSourceDirs.plusAssign(file("build/generated/source/proto/main/kotlin"))
    }
}

application {
    // Define the main class for the application.
    mainClassName = "grpckotlin.simple.AppKt"
}
