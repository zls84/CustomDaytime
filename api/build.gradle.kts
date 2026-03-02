plugins {
    id("java")
}

group = "xyz.mayahive.customdaytime"
version = "2.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javaTarget = 21 // Sponge targets a minimum of Java 21
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaTarget))
}