plugins {
	id("org.jetbrains.kotlin.jvm") version "1.3.71"
	id ("org.gretty") version "3.0.2"
    id("com.github.johnrengelman.shadow") version "5.1.0"
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

    implementation("org.eclipse.jetty:jetty-server:9.4.25.v20191220")
    implementation("org.eclipse.jetty:jetty-servlet:9.4.25.v20191220")

    implementation("org.eclipse.jetty:jetty-webapp:9.4.25.v20191220")
    implementation("org.eclipse.jetty:jetty-annotations:9.4.25.v20191220")
    implementation("org.eclipse.jetty:apache-jsp:9.4.25.v20191220")

    implementation("com.google.inject:guice:4.2.3")
    implementation("com.google.inject.extensions:guice-servlet:4.2.3")

    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("org.apache.logging.log4j:log4j-api:2.1")
    implementation("org.apache.logging.log4j:log4j-core:2.1")
}

tasks {
    val copyToLib by registering(Copy::class) {
        into("${rootProject.buildDir}/libs")
        from("$buildDir/libs")
    }

    copyToLib {
        dependsOn(shadowJar)
    }

    register("stage") {
        dependsOn(clean, shadowJar)
    }
}








