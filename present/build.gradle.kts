plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
}

android {
    namespace = "present.compose"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "present.compose"
                artifactId = "present"
                version = "1.0.0"
                from(components["release"])
            }
        }
    }
}

dependencies {
    api("androidx.compose.runtime:runtime:1.7.6")
    api("androidx.compose.foundation:foundation:1.7.6")
    api("androidx.compose.material3:material3:1.3.1")
    api("androidx.compose.ui:ui:1.7.6")

    testImplementation("junit:junit:4.13.2")
}
