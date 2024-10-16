# WeatherForecastApp

WeatherForecastApp is application developed in android native with mvvm architectural pattern.

## Feature
This application provides weather forecast for 5 days with useful information.
## Approach to development
This application has been designed with MVVM architectural pattern.
* `Model` - Model class will be holding data in our case `WeatherResponseModel` is the model which will be holding response of API
* `ViewModel` - Heart of MVVM architecture. `WeatherViewModel` is our viewmodel that is being subscribed by Views. Currently We are fetching API calls to decide of flow of applicaiton.
* `View` - View is just for rendering the UI. It accepts user input and render the UI as decided by business logic. `MainActivity` is our view class.
Since there is a few no. of classes hence MVVM alone is sufficient for this if there is need to include more features, then We can go with clean architecture with MVVM design pattern.

## Building the App
First, clone the repo:
```
git clone https://github.com/sampicks/WeatherForecastApp.git
```
Next you need to register in [www.weatherapi.com](https://www.weatherapi.com/)
to get your API keys (`mandatory`). 
Copy API keys and paste it into file 
 `app/src/main/java/com/example/weatherforecastapp/data/network/ApiConfig.kt`

Building the sample then depends on your build tools.

### Android Studio (Recommended)

(These instructions were tested with Android Studio Giraffe | 2022.3.1 Patch 4)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Technologies

_Name the libraries used in the project._ 
* [Dagger - Hilt](https://dagger.dev/hilt/) - Dependency Injection.
* [Retorfit](https://square.github.io/retrofit/) - Type-safe http client for network.
* [Glide](https://github.com/bumptech/glide) - Image Loader.

## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'


## Using the Sample App

Each time you press the `Search` button after providing input a valid `city name`. `WeatherForecast API` provides data if available for asked location. If request is successful, it displays current weather and next 5 days weather forecast also. In case of no data found app shows error.

## Screenshot
![Screenshot_20241016_163954](https://github.com/user-attachments/assets/0074bff4-5c30-4ddf-b688-3f917740091d)
![Screenshot_20241016_164035](https://github.com/user-attachments/assets/e783435a-986d-4a3a-b02f-054b1e4865c8)
![Screenshot_20241016_164127](https://github.com/user-attachments/assets/1c5cf3cc-1860-4fe5-9e00-e66fdc9a7252)

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.


### License


```
Copyright 2022 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
