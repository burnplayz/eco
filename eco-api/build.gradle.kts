repositories{
    mavenLocal()
    mavenCentral()
    maven("https://repo.nexomc.com/releases")
    maven("https://jitpack.io")
    maven("https://repo.nexomc.com/releases")
    maven("https://repo.byteflux.net/repository/maven-releases/")  // Libby's official repo
    maven("https://repo.byteflux.net/repository/maven-snapshots/")
}
dependencies {
    // Adventure
    compileOnly("net.kyori:adventure-platform-bukkit:4.1.0")

    // Other
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly("commons-lang:commons-lang:2.6")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.6.1-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.8.8")

    compileOnly("com.nexomc:nexo:1.0.0") {
        exclude(group = "net.byteflux", module = "libby")
        exclude(group = "net.byteflux", module = "libby-bukkit")
    }
}

group = "com.willfp"
version = rootProject.version

java {
    withJavadocJar()
}

tasks {
    build {
        dependsOn(publishToMavenLocal)
    }
}

publishing {
    publications {
        create<MavenPublication>("shadow") {
            from(components["java"])
            artifactId = "eco"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Auxilor/eco")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publishing {
        repositories {
            maven {
                name = "Auxilor"
                url = uri("https://repo.auxilor.io/repository/maven-releases/")
                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }
}
