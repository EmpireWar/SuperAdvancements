import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val versions = listOf(
    "1_20_R3"
)

dependencies {
    api(project(":superadvancements"))
    api(project(":superadvancements-abstract"))

    versions.forEach {
        api(project(":superadvancements-$it"))
    }

    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT") {
        version {
            strictly("1.20.4-R0.1-SNAPSHOT")
        }
    }
}

description = "Bukkit & Spigot (String / BaseComponent) Implementation of the SuperAdvancements API"

java {
    withJavadocJar()
}

tasks {
    compileJava {
        versions.subList(versions.indexOf("1_20_R3"), versions.size)
            .forEach { dependsOn(project(":superadvancements-$it").tasks["assemble"]) }
    }

    javadoc {
        sourceSets["main"].allJava.srcDir("src/main/javadoc")

        exclude("**/abstract/**", "**/nms/**")
    }

    register("sourcesJar", Jar::class.java) {
        archiveClassifier.set("sources")

        val sources = listOf(
            sourceSets["main"].allSource,
            project(":superadvancements").sourceSets["main"].allSource
        )

        from(sources)
    }

    withType<ShadowJar> {
        dependsOn("sourcesJar", "javadocJar")
    }
}

publishing {
    publications {
        getByName<MavenPublication>("maven") {
            artifact(tasks["sourcesJar"])
        }
    }
}