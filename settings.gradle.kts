pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "WineNote"

include(":app")
include(":core:data")
include(":core:designsystem")
include(":core:resource")
include(":core:ui")
include(":core:domain")
include(":feature:main")
include(":feature:wine-list")
include(":feature:wine-write")
include(":feature:wine-detail")
include(":feature:setting")
include(":feature:wine-bin")
include(":feature:splash")
include(":core:model")
