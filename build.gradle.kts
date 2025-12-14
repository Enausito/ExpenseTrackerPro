//config importante profesor, por favor abra build.gradle.kts (Module :app) y agrega esto dentro del bloque android { ... } para activar el ViewBinding. Esto nos evita usar findViewById repetidamente.
android {
    // ... otras configuraciones ...
    buildFeatures {
        viewBinding = true
    }
}
