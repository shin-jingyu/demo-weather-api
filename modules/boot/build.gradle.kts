plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example.weather"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modules:application:sync-application"))
    implementation(project(":modules:application:inquire-application"))
    implementation(project(":modules:domain:weather-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}
