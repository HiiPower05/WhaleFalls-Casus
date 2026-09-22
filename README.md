Had to use the versions below. agp above 9.1.0 requires Gradle 9.3.1.
to update: gradle/wrapper/gradle-wrapper.properties -> Find distributionUrl -> change version number 9.3.1
gradle/libs.versions.toml: 
[versions]
agp = "8.8.0"
compileSdk = 35
coreKtx = "1.15.0"
activityCompose = "1.10.0"


changed firebase version instead of gradle.
implementation(platform("com.google.firebase:firebase-bom:34.19.0")) --> implementation(platform("com.google.firebase:firebase-bom:33.1.0")) 
