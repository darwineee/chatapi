plugins {
    java
    id("org.graalvm.buildtools.native") version "0.10.2"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.graalvm.buildtools.native")

    group = "com.darwin.dev"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    graalvmNative {
        toolchainDetection = false
        binaries.all {
            resources.autodetect()
        }
        binaries {
            named("main") {
                javaLauncher = javaToolchains.launcherFor {
                    languageVersion.set(JavaLanguageVersion.of(17))
                    vendor.set(JvmVendorSpec.matching("Oracle Corporation"))
                }
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

val uploadJars by tasks.registering{
    dependsOn(
        ":chat-service:uploadJar",
        ":crm-service:uploadJar",
        ":gateway:uploadJar",
    )
}