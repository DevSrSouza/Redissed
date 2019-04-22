import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.30"
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

group = "br.com.devsrsouza"
version = "0.1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("redis.clients:jedis:3.0.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = project.name.toLowerCase()

            from(components["java"])
            artifact(sources.get())

            pom {
                name.set("Redissed")
                description.set("Redis Kotlin wrapper using Jedis inspired on Exposed")
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
