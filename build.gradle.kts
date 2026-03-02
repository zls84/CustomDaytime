subprojects {

    group = "xyz.mayahive.customdaytime"
    version = "2.0.0"

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
    }

    if (name != "api") {
        plugins.withId("java") {
            dependencies {
                add("compileOnly", libs.lombok)
                add("annotationProcessor", libs.lombok)
            }
        }
    }
}