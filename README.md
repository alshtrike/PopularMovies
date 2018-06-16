# PopularMoviesStage1
Stage 1 of the Udacity Popular Movies project

## Project Description
This app will:

* Present the user with a grid arrangement of movie posters upon launch.
* Allow your user to change sort order via a setting
* The sort order can be by most popular or by highest-rated
* Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
  * original title
  * movie poster image thumbnail
  * A plot synopsis (called overview in the api)
  * user rating (called vote_average in the api)
  * release date

## API KEYS
This project uses responses from https://www.themoviedb.org/. In order to use their API, you must provide an API key.
This application will read the API key from movie_db_apikey.txt. This file is not included in this repo.
You must create a resources folder under app/src/main/. In that folder put the movie_db_apikey.txt and paste your API key in there. This is needed in order to be able to successfully run this repo.
If you do not have the API key, you must create an account with themoviedb.org and request API keys from the account settings.
