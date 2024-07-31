plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm") version "1.4.20"
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

gradlePlugin {
    val elephant by plugins.creating {
        id = "elephant.ElephantPlugin"
        implementationClass = "elephant.ElephantPlugin"
    }
}
