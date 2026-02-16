plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":api"))
    implementation("org.spongepowered:configurate-hocon:4.2.0")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}