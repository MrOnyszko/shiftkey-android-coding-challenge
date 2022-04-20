# Shiftkey Android Coding Challenge

## Languages, libraries and tools used

* [Kotlin](https://kotlinlang.org/)
* [Compose](https://developer.android.com/jetpack/compose)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Navigation](https://developer.android.com/jetpack/compose/navigation)

## Requirements

* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android ([API 23 to 30](https://developer.android.com/preview/api-overview.html))
* Latest Android SDK Tools and build tools.

## Task

* Original [README](android-coding-challenge/README.md)

## Architecture

In this application you can see layered architecture. Whe have several layers:

- Presentation in [app module](android-coding-challenge/app).
- Domain in [domain module](android-coding-challenge/domain).
- Remote in [remote module](android-coding-challenge/remote).

### Presentation

The presentation layer is responsible for displaying UI and managing its state. In this layer for UI
we use [Compose](https://developer.android.com/jetpack/compose) and for state
management [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
with [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) from
coroutines.

### Domain

The domain layer is responsible for performing business logic of the application. It manages data
source layers. The domain exposes so
called [DataSourceActions](android-coding-challenge/domain/src/main/java/pl/onyszko/domain/dataSourceAction/GetAvailableShiftsDataSourceAction.kt)
. The DataSourceAction is an interface that imposes communication from data source layers to the
domain. Thanks to that we achieve dependency inversion and better decoupling.

### Remote

The remote layer is a data source layer. It allows us to connect with external services. In this
case we access REST API using Retrofit. In this layer we implement DataSourceActions.