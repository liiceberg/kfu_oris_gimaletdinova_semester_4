import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "ru.kpfu.itis.gimaletdinova"
version = "1.0-SNAPSHOT"

application {
    mainClass = "ru.kpfu.itis.gimaletdinova.Main"
}
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:${properties["springVersion"]}")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:8.0.47")
}


tasks.withType<ShadowJar> {
    archiveFileName.set("hello.jar")
    mergeServiceFiles()
    manifest {
        attributes(mapOf("Main-Class" to "ru.kpfu.itis.gimaletdinova.Main"))
    }
}