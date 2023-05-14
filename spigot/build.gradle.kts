import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    api(project(":superadvancements"))
    api(project(":superadvancements-abstract"))

    listOf(
        "1_12_R1",
        "1_13_R1",
        "1_13_R2",
        "1_14_R1",
        "1_15_R1",
        "1_16_R1",
        "1_16_R2",
        "1_16_R3",
        "1_17_R1",
        "1_18_R1",
        "1_18_R2",
        "1_19_R1",
        "1_19_R2",
        "1_19_R3"
    ).forEach {
        api(project(":superadvancements-$it"))
    }

    compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT") {
        version {
            strictly("1.12.2-R0.1-SNAPSHOT")
        }
    }
}

description = "Bukkit & Spigot (String / BaseComponent) Implementation of the SuperAdvancements API"

java {
    withJavadocJar()
    withSourcesJar()
}

tasks {
    javadoc {
        sourceSets["main"].allJava.srcDir("src/main/javadoc")

        exclude("**/abstract/**", "**/nms/**")
    }

    withType<ShadowJar> {
        dependsOn("sourcesJar", "javadocJar")
    }
}
