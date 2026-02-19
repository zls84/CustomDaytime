plugins {
    id("java")
    id("java-library")
}

group = "xyz.mayahive.customdaytime"
version = "2.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":api"))
    implementation("org.spongepowered:configurate-hocon:4.2.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()
}