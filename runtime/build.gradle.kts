val infoModelVersion: String by project
val jacksonVersion: String by project
val jerseyVersion: String by project

plugins {
    `java-library`
    id("application")
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":extensions:protocol:web"))
    implementation(project(":extensions:control-http"))

    implementation(project(":extensions:metadata:metadata-memory"))

    implementation(project(":extensions:transfer:transfer-core"))
    implementation(project(":extensions:transfer:transfer-nifi"))

    implementation(project(":extensions:ids:ids-core"))
    implementation(project(":extensions:ids:ids-api-catalog"))
    implementation(project(":extensions:ids:ids-api-transfer"))

    implementation(project(":extensions:demo:demo-nifi"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")

}

// workaround for this issue: https://github.com/johnrengelman/shadow/issues/609
application {
    @Suppress("DEPRECATION")
    mainClassName = "com.microsoft.dagx.runtime.DagxRuntime"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    exclude("**/pom.properties", "**/pom.xm")
    mergeServiceFiles()
    archiveFileName.set("dagx-runtime.jar")
}


