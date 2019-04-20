import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.30"
    `maven-publish`
}

group = "br.com.devsrsouza"
version = "0.1.0"

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
                        name.set("MIT License")
                        url.set("https://github.com/DevSrSouza/Redissed/blob/master/LICENSE")
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
