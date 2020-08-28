plugins {
    kotlin("jvm") version "1.4.0"
}

group = "com.thijsboehme"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.reflections:reflections:0.9.12")
    implementation("org.apache.commons:commons-lang3:3.11")
    implementation("com.google.guava:guava:29.0-jre")
    implementation("junit:junit:4.13")
    implementation("com.google.inject:guice:4.2.3")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")
    implementation("org.springframework.statemachine:spring-statemachine-core:2.2.0.RELEASE")
    implementation("org.springframework:spring-context:5.2.8.RELEASE")
}
