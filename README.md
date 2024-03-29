**Project Name - NEWS App with Jetpack Compose**

**Description  -**  A news app developed in android using kotlin language.

**Technologies Used -**  Kotlin, Retrofit, MVVM architecture


**Migrate XML project to jetpack compose**

* Update Android Gradle Plugin and Kotlin Plugin: Using the latest versions of the Android Gradle Plugin and Kotlin Plugin.

* Add Compose Dependencies: build.gradle (module-level)

* Set Up Compose Application: Create a new @Composable function

* Replace XML Layouts with Compose Code: For example, if you had an XML layout with a TextView, replace it with a Compose Text element.

* Adopt Compose Components: Replace XML-based UI components with their Compose equivalents.

* Integrate Compose Navigation: Migrate from XML-based navigation to Compose Navigation.

* Migrate UI Logic: Update UI logic to use Compose's state management.

* Migrate Resources: Migrate string resources, colors, drawable.

* Update Gradle Plugin Versions: Update your Gradle dependencies accordingly.

* Testing: Write tests for your Compose UI using the Compose testing library.

* Documentation and Learning: - Refer to the official Jetpack Compose documentation and samples. Learn about Compose concepts like Composables, state management, and navigation

**Libraries Used -**

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Compose
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3:1.1.2")
    implementation ("io.coil-kt:coil-compose:2.4.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation ("androidx.navigation:navigation-compose:2.7.6")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    implementation ("androidx.compose.foundation:foundation")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Recycler View
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation("androidx.browser:browser:1.6.0")

    //Room
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")
    kapt ("androidx.room:room-compiler:2.6.1")

    //Paging
    implementation ("androidx.paging:paging-compose:3.2.1")

    //WorkManager
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    //Local unit test
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("app.cash.turbine:turbine:0.12.1")

    //UI test
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.navigation:navigation-testing:2.6.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

**How to run the project :**

Clone the project repo in yor local machine and import the project in android studio. After successful gradle build and compilation run the project in the emulator/physical device. 
