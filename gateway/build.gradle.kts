import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

version = "0.0.1-SNAPSHOT"

extra["springCloudVersion"] = "2023.0.3"

dependencies {
    implementation(projects.distributedCore)
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

val uploadJar by tasks.registering(Exec::class) {
    dependsOn(tasks.named("bootJar"))
    val remoteHost = "ubuntu@3.104.79.169:/home/ubuntu/chatapi"
    val pvkPath = "/home/darwin/Documents/macos-aws.pem"
    val jarFile = tasks.getByName<BootJar>("bootJar").archiveFile.get().asFile
    commandLine("scp", "-i", pvkPath, jarFile.absolutePath, remoteHost)
}
