# TV Show app using a [TV Maze API](https://www.tvmaze.com/api)
This project aims to help cinema lovers to find their favorite TV Shows and keep track of them, adding them to their favorite list, search for new tv shows, as well as browsing their preferred actors and actresses.

# Technologies
- Android
- Retrofit
- Kotlin
- Mockk
- Room
- Glide
- Android navigation
- Koin
- Coroutines

# Project features
This section is going to present all of the app's features into each section below, providing valuable information about the app's usage.
- [TV show listing](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#tv-show-listing)
- [TV show search](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#tv-show-search)
- [TV show details](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#tv-show-details)
- [Favorite tv shows](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#favorite-tv-shows)
- [People listing(actors and actresses)](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#people-listingactors-and-actresses)

## TV show listing
This page lists TV shows from TVMaze API by using their paging scheme. The user can scroll the page down to see more options and it will load the next pages as needed.

![TV Show listing](https://github.com/MatheusHoudin/tvmaze-challenge/blob/main/screenshots/tv_shows_listing.jpeg)

## TV show search
This page enables the user to search any of the available TV Shows on TVMaze API by typing it's name on the search field.

![TV Show search](https://github.com/MatheusHoudin/tvmaze-challenge/blob/main/screenshots/tv_shows_search.jpeg)

## TV show details
This page shows details about the TV Show, such as:
- Genres
- Episode posting schedule
- Synopsis
- Episodes separated by season

It's also possible to add the TV Show to your favorite list tapping on the :heart: icon. The favorites are going to be listed on the [Favorites section](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#favorite-tv-shows)

![TV Show details](https://github.com/MatheusHoudin/tvmaze-challenge/blob/main/screenshots/tv_shows_details.jpeg)

## Favorite tv shows
This page shows all of your favorite TV Shows ordered alphabetically. The user can tap on the TV Show to open it's details page.

![Favorite tv shows](https://github.com/MatheusHoudin/tvmaze-challenge/blob/main/screenshots/tv_shows_favorites.jpeg)

## People listing(actors and actresses)
This page shows all actors and actresses from TVMaze API by using their paging scheme. The user can scroll the page down to see more options and it will load the next pages as needed.

![People listing](https://github.com/MatheusHoudin/tvmaze-challenge/blob/main/screenshots/people.jpeg)

## Person details(actors and actresses)
This page shows a person details, such as:
- Person picture
- Name
- Cast credits
- Person page on TVMaze

![Person details](https://github.com/MatheusHoudin/tvmaze-challenge/blob/main/screenshots/person_details.jpeg)

# App architecture
This section is an overview of the app's architecture, going through the software development good practices, tools and well known software patterns. 

## Folder structure
This project has two main folders as follows:

### [features](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features)

On this folder is located all of the app's features, separated by a folder each. It helps to easily find a piece of code a developer may need. Inside each of those folders, there is another structure, which is described on the [Google architecture](https://github.com/MatheusHoudin/tvmaze-challenge/edit/main/README.md#google-architecture) section.

The folders are the following ones:
- [favorites](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/favorites)
- [people](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/people)
- [persondetails](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/persondetails)
- [search](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/search)
- [tvshowdetails](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/tvshowdetails)
- [tvshows](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/tvshows)

### [shared](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared)

On this folder is located all of the resources commonly used by other parts of the app, for exemple different features.

The folders are the following ones:
- [adapter](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/adapter)
Recycler view adapters
- [api](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/api)
Components responsible for creating a connection to a remote source
- [base](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/base)
Base classes with shared implementations
- [bindingadapter](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/bindingadapter)
View extensions for XML on Android
- [components](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/components)
Shared XML components
- [dao](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/dao)
Components responsible for communicating with local database(Room)
- [di](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/di)
Dependency injection componentes
- [entity](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/entity)
Components with business data to be presented on UI
- [error](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/error)
Components for declaring errors on the application
- [extensions](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/extensions)
Kotlin extension functions
- [model](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/model)
Classes responsible for defining contract on datasources
- [util](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/shared/util)
General utility classes

## Architecture
In order to develop this app, following [Google's recommended architecture](https://developer.android.com/jetpack/guide) and [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), the project is separated into layers as follows:

- OBS: Each feature has it's own layers, so that they become independent of each other, take a look at this example from [TV Show listing](https://github.com/MatheusHoudin/tvmaze-challenge/tree/main/app/src/main/java/com/matheus/tvmazechallenge/features/tvshows)

## Datasource layer
Responsible for dealing with data fetching, adding, removing and update. This layer can communicate with any source of data, for example TVMaze API, Local database and so on.

## Repository layer
Responsible for communicating with the datasource layer, convert the data so that it fits the UI needs and handle errors.

## UI and ViewModel
Responsible for handling the user actions and delegating it to the repository, so that it can present valueable data to the user.
