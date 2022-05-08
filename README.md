# cocktail-db <img src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png?raw=true" width="48" height="48">
Every day is Kampai Day! Discover, Learn and be a Barista on your own!
## Screenshots
<img src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195707_Cocktail%20DB.png?raw=true" width="150" height="280"> <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195711_Cocktail%20DB.png?raw=true" width="150" height="280"> <img
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195735_Cocktail%20DB.png?raw=true" width="150" height="280"> <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195744_Cocktail%20DB.png?raw=true" width="150" height="280"> <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195749_Cocktail%20DB.png?raw=true" width="150" height="280"> <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195820_Cocktail%20DB.png?raw=true" width="150" height="280"> <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/Screenshot_20220508-195901_Cocktail%20DB.png?raw=true" width="150" height="280">


## Download APK
[Click me!](https://github.com/Fangzsx/cocktail-db/raw/master/app/release/app-release.apk)


## Inspiration
Aside from theMealDB API that I used in my-fud-app, the same team have an api called theCocktailDB which inspires me to create an app with it.
One problem that I encountered with theMealDB API is the video url being null and I want to solve it. The solution that I have in mind is to use YoutubeAPI
and search a video related to it.

## Features
1. Instructive- Each cocktail is provided by a list of ingredients and a video preparation guide
2. Recommendation- Generates a random cocktail
3. Popular Cocktail- Since I can't pay for the API, I only generate list of cocktail based on certain category (in this project, alcoholic)
4. Find cocktail by Ingredient- User can sort cocktail based on ingredient
5. Save a cocktail as Favorite- Cocktails can be saved locally, powered by ROOM database.
6. Search Cocktail- User can search cocktail by name.
7. Night Mode and Light Mode Supported
8. Supports Landscape and Portrait Orientation


## Project Experience, Problem and Solution
For this projects, I used theCocktailDB API, same team that created theMealDB which I used on my previous app. 
I no longer face difficulty implementing MVVM and Room Database.
The problem that I faced is some videoURL/ID coming from theCocktailDB API are null. To solve this, I used YoutubeAPI.
I created a custom search query where I format as "$cocktail_name preparation guide" and select the first relevant video.
With this, I will always have a video for each of cocktail.

## Tech Stack
<img src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/android-studio.png?raw=true" width="48" height="48" title ="Android Studio">  <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/kotlin.png?raw=true" width="48" height="48" title ="Kotlin">  <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/database.png?raw=true" width="48" height="48" title ="ROOM Database">  <img 
src="https://github.com/Fangzsx/cocktail-db/blob/master/app/src/main/assets/api.png?raw=true" width="48" height="48" title ="REST-API">

## Run Locally
1. Open Android Studio
2. Click File -> New -> Project from Version Control 
3. In the URL field, paste this: https://github.com/Fangzsx/cocktail-db.git
4. Set directory of the project
5. Click Clone

## Libraries
I'd like to express my thanks to the following authors:

Spikeysanju for Motion Toast 🤩🔥

PierfrancescoSoffritti for Android Youtube Player 💗💗

bitvale for LightProgress 😻💞

wajahatkarim3 for EasyFlipView 🥰❣️

coil-kt for Image Loading 💟💓


