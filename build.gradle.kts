plugins {
//    kotlin("jvm") version "1.4.30"
    id("java")
    id("com.adarshr.test-logger") version "2.1.1"
    id("io.qameta.allure") version "2.11.2"
    id("com.github.ben-manes.versions") version "0.36.0"
}

group = "dev.kotler"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
configurations {
    create("testCompile")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}



dependencies {
    val codeborneSelenideVersion = "7.3.2"
    val fluentleniumVersion = "4.6.1"
    val seleniumJavaVersion = "4.22.0"
    val webdriverManagerVersion = "5.8.0"
    val browsermobVersion = "2.1.5"
    val skrapeitVersion = "1.0.0-alpha8"
    val jUnitVersion = "5.8.1"
    val assertjVersion = "3.19.0"
    val striktVersion = "0.34.1"
    val awaitilityVersion = "4.0.3"
    val rerunnerVersion = "2.1.6"
    val kotlinLoggerVersion = "2.0.4"
    val julToSlf4jVersion = "1.7.28"

    testImplementation(
        group = "com.codeborne",
        name = "selenide",
        version = codeborneSelenideVersion
    )

    testImplementation(
        group = "org.seleniumhq.selenium",
        name = "selenium-java",
        version = seleniumJavaVersion
    )
    testImplementation(
        group = "io.github.bonigarcia",
        name = "webdrivermanager",
        version = webdriverManagerVersion
    )
    testImplementation(
        group = "net.lightbody.bmp",
        name = "browsermob-proxy",
        version = browsermobVersion
    )
    testImplementation(
        group = "it.skrape",
        name = "skrapeit-core",
        version = skrapeitVersion
    )

    testImplementation(
        group = "org.assertj",
        name = "assertj-core",
        version = assertjVersion
    )
    testImplementation(
        group = "org.fluentlenium",
        name = "fluentlenium-junit-jupiter",
        version = fluentleniumVersion
    )
    testImplementation(
        group = "org.fluentlenium",
        name = "fluentlenium-assertj",
        version = fluentleniumVersion
    )
    testImplementation(
        group = "io.strikt",
        name = "strikt-core",
        version = striktVersion
    )
    testImplementation(
        group = "org.junit.jupiter",
        name = "junit-jupiter",
        version = jUnitVersion
    )
    testImplementation(
        group = "io.github.artsok",
        name = "rerunner-jupiter",
        version = rerunnerVersion
    )
    testImplementation(
        group = "org.awaitility",
        name = "awaitility-kotlin",
        version = awaitilityVersion
    )
    testImplementation(
        group = "io.github.microutils",
        name = "kotlin-logging",
        version = kotlinLoggerVersion
    )
    testImplementation(
        group = "org.slf4j",
        name = "jul-to-slf4j",
        version = julToSlf4jVersion
    )
}

configurations {
    all {
        exclude(module = "junit5")
        exclude(module = "htmlunit-driver")
        exclude(module = "slf4j-log4j12")
        exclude(module = "slf4j-simple")
        exclude(module = "log4j")
    }
}

tasks {
    withType<Test> {
        useJUnitPlatform()
        parallelTestExecution()

//        systemProperty("browser", System.getProperty("browser"))
        systemProperty("allure.results.directory", "build/allure-results")

        finalizedBy("allureReport")
    }


}

fun Test.parallelTestExecution() {
    val parallel = "junit.jupiter.execution.parallel"
    if (!project.hasProperty("serial")) {
        systemProperties = mapOf(
            "$parallel.enabled" to true,
            "$parallel.mode.default" to "concurrent",
            "$parallel.config.dynamic.factor" to 4
        )
    }
}

//allure {
//    val allureVersion = "2.27.0"
//    version = allureVersion
////    autoconfigure = true
////    aspectjVersion = "1.9.22.1"
////    useJUnit5 {
////        version = allureVersion
////    }
////    configuration = "testImplementation"
//}


//allure {
//    autoconfigure = false
//    version = "2.5.0"
//    configuration = "implementation"
//    junit5 {
//        version = "2.0-BETA21"
//    }
//}