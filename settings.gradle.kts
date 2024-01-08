rootProject.name = "SuperAdvancements"

include(":superadvancements")
project(":superadvancements").projectDir = rootDir.resolve("base")

listOf("abstract", "spigot", "paper").forEach {
    include(":superadvancements-$it")
    project(":superadvancements-$it").projectDir = rootDir.resolve(it)
}

listOf(
    "1_20_R3"
).forEach {
    include(":superadvancements-$it")
    project(":superadvancements-$it").projectDir = rootDir.resolve("nms/$it")
}