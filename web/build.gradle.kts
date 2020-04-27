plugins {
	id("org.jetbrains.kotlin.jvm") version "1.3.71"
	id ("org.gretty") version "3.0.2"
    war
}

repositories {
    mavenCentral()
    jcenter()
}

val staging: Configuration by configurations.creating


dependencies {
    staging("com.heroku:webapp-runner-main:9.0.31.0")
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-api:2.1")
    implementation("org.apache.logging.log4j:log4j-core:2.1")
}

tasks {
    val copyToLib by registering(Copy::class) {
        into("$buildDir/server")
        from(staging) {
            include("webapp-runner*")
        }
    }

    register("stage") {
        dependsOn(war, copyToLib)
    }
}








