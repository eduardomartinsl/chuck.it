<img src="https://user-images.githubusercontent.com/19932086/114314984-fafeb580-9aca-11eb-898f-2168ee405b4a.png" height=200>

# Chuck it
[![Build Status](https://github.com/eduardomartinsl/chuck.it/actions/workflows/android_build.yml/badge.svg?branch=develop)](https://github.com/eduardomartinsl/chuck.it/tree/develop)

## what is chuck it?
Wanna know more about Chuck Norris? Chuck it can help you! The application can show you some Chuck Norris Facts based on the [chuck norris API][api.chucknorris.io]! **Try it out!**

## Project Setup

Clone the repo, open the project in Android Studio, Sync Gradle and wait for it, hit "Run". **Done!**

## Architecture Overview

- Dependency Injection: **Dagger2**
- Architectural pattern: **MVVM**

## Dependencies

- OkHttp3
- ViewModel
- LiveData
- Saved state module for ViewModel
- Gson
- retrofit
- dagger
- fragment
- Navigation
- GSON Converter
- room
- Kotlin Extensions and Coroutines support for Room
- [Loading Dots][loading-dots]
 
## Version Control

The branches are divided in:
- **Main**: Stable version of the app, ready to build.
- **Develop**: Used to implement new features or general fixes

Every new implementation should come from the **develop** branch, and any merge on the **main** branch should be made with pull requests. Also, the commit messages should begin with the following prefixes:

- imp: Implementation (new features, for example)
- fix: General Fixes (Bugs, for example)
- test: test implementations

***Note: this is not a rule. the prefix objetive is to help on the issue identification***

## Testing

- **Gradlew test** for Unity Tests
- **Gradlew connectedAndroidTest** For integrated tests

[loading-dots]: <https://github.com/EyalBira/loading-dots>
[api.chucknorris.io]: <https://api.chucknorris.io>
