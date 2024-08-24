enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "chatapi"

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":gateway")
include(":crm-service")
include(":chat-service")
include(":distributed-core")
