plugins {
	id("org.jetbrains.kotlin.jvm") version "1.3.71"
	id ("org.gretty") version "3.0.2"
    war
}

repositories {
    mavenCentral()
    jcenter()
}

val staging by configurations.creating

dependencies {
    staging("com.heroku:webapp-runner-main:9.0.31.0")
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
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








