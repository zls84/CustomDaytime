plugins {
    id("java")
    id("java-library")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":api"))

    implementation(libs.configurate.hocon)
    implementation(libs.gson)
}

val javaTarget = 21 // Sponge targets a minimum of Java 21
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaTarget))
}