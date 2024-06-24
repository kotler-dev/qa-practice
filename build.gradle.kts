plugins {
    id("java")
}

group = "dev.kotler"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val slf4jNopVersion = "2.0.13"

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.2.1")
    testImplementation(
        group = "org.slf4j",
        name = "slf4j-nop",
        version = slf4jNopVersion
    )
}

tasks.test {
    useJUnitPlatform()
}