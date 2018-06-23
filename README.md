# PopularMoviesStage1
Stage 1 of the Udacity Popular Movies project

## Project Description
This app will:

* Present the user with a grid arrangement of movie posters upon launch.
* Allow your user to change sort order via a setting
* The sort order can be by most popular or by highest-rated
* Allow the user to tap on a movie poster and transition to a details screen with additional information such as:
  * original mTitle
  * movie poster image thumbnail
  * A plot synopsis (called overview in the api)
  * user rating (called vote_average in the api)
  * release mDate

## API KEYS
This project uses responses from https://www.themoviedb.org/. In order to use their API, you must provide an API key.
This application will read the API key from your global gradle.properties. This should be located under
Users/YourUsername/.gradle/gradle.properties. If your .gradle folder does not have this file, please make one.
Once you've found your global gradle.properies please put your api key there like so: **movie_db_api_key**="_your_api_key_goes_here_"