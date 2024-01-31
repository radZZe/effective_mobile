pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs{
        create("libs"){
            from(files("libs.toml"))
        }
    }
}

rootProject.name = "effective_mobile"
include(":app")
 