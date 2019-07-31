import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

group = "br.com.devsrsouza"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    //clients
    compileClasspath("redis.clients:jedis:3.0.1")
    compileClasspath("io.lettuce:lettuce-core:5.1.6.RELEASE")

    // test
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("io.mockk:mockk:1.9")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

bintray {
    val settings = file("publish.json").takeIf { it.exists() }?.let {
        groovy.json.JsonSlurper().parseText(it.readText())
    } as Map<*,*>

    user = settings["bintray_user"] as String
    key = settings["bintray_key"] as String
    setPublications("maven")
    with(pkg) {
        repo = "maven"
        name = "Redissed"
        websiteUrl = "https://github.com/DevSrSouza/Redissed"
        vcsUrl = "https://github.com/DevSrSouza/Redissed.git"
        setLicenses("Apache-2.0")

        with(version) {
            name = project.version.toString()

            with(gpg) {
                sign = settings["gpg_sign"] as Boolean
                passphrase = settings["gpg_passphrase"] as String
            }

            with(mavenCentralSync) {
                sync = settings["maven_sync"] as Boolean
                user = settings["maven_user"] as String
                password = settings["maven_password"] as String
            }
        }
    }
}

val sources by tasks.registering(Jar::class) {
    baseName = project.name
    classifier = "sources"
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    baseName = project.name
    classifier = "javadoc"
    from(tasks.javadoc)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = project.name.toLowerCase()

            from(components["java"])
            artifact(sources.get())
            artifact(javadocJar.get())

            pom {
                name.set("Redissed")
                description.set("Redis Kotlin wrapper inspired on Exposed")
                url.set("https://github.com/DevSrSouza/Redissed")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("DevSrSouza")
                        name.set("Gabriel Souza")
                        email.set("devsrsouza@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/DevSrSouza/Redissed/tree/master/")
                }
            }
        }
    }
}
