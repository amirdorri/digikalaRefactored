// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false  //1.8.10  1.9.0
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id ("com.google.devtools.ksp") version "2.0.21-1.0.25" apply false //1.9.0-1.0.12
// id ("org.jetbrains.kotlin.plugin.serialization") version "2.0.21" apply false
}