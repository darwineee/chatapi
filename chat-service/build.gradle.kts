import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(projects.distributedCore)
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val uploadJar by tasks.registering(Exec::class) {
    dependsOn(tasks.named("bootJar"))
    val remote = "vps:/home/debian/chatapi"
    val jarFile = tasks.getByName<BootJar>("bootJar").archiveFile.get().asFile
    commandLine("scp", jarFile.absolutePath, remote)
}
