plugins {
    application
}

val STARTER_APP = "Application"
val APP_NAME = System.getenv("APP_NAME") ?: "QuickStart"

application {
    mainClass.set("src/main/java/${STARTER_APP}.java")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

dependencies {
    implementation(libs.picocli)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

distributions {
    create("scripts") {
        contents {
            from("src/main/java") {
                include("**/*.java")
                rename(STARTER_APP, APP_NAME)
                into("src")
            }
            from(configurations.runtimeClasspath) {
                into("lib")
            }
            from("bin") {
                include("*.sh")
                rename(STARTER_APP, APP_NAME)
                into("bin")
            }
        }
    }
}

tasks.named<Zip>("scriptsDistZip") {
    archiveFileName.set("${APP_NAME}.zip")
}

tasks.compileJava {
    options.compilerArgs.addAll(listOf("--enable-preview"))
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("--enable-preview")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}
