plugins {
    java
}
repositories {
    mavenCentral() // Where to look for jars
}
dependencies {
    implementation("commons-io:commons-io:2.4")
    implementation("com.omertron:thetvdbapi:1.7")
    testImplementation("junit:junit:4.12")
}