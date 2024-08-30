plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")

    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("org.apache.commons:commons-collections4:4.5.0-M2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
    implementation("commons-io:commons-io:2.16.1")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jacocoTestReport {
    reports { xml.required.set(true) }
    dependsOn(tasks.test)
}

application {
    mainClass.set("hexlet.code.App")
}

checkstyle {
    configFile = file("config/checkstyle.xml")
}