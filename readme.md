# GridImageSearch (Google Image Search)
Search for images with multiple filters. Powered by [Google Search API (Deprecated)](https://developers.google.com/image-search/).

## Features
- User can enter a search query that will display a grid of image results from the Google Image API

- User can click on "settings" which allows selection of advanced search options to filter results

- User can configure advanced search filters such as:
  - Size (small, medium, large, extra-large)
  - Color filter (black, blue, brown, gray, green, etc...)
  - Type (faces, photo, clip art, line art)
  - Site (espn.com)

- ~~Subsequent searches will have any filters applied to the search results~~ Filters are applied instantly

- User can tap on any image in results to see the image full-screen

- User can scroll down “infinitely” to continue loading more image results (up to 8 pages)

- Handle error cases from network failures or API Errors

- Use the ActionBar SearchView or custom layout as the query box instead of an EditText

- Replace Filter Settings Activity with a lightweight modal overlay

## Development Tools
- Android Studio
- Development time: 12 hours

## Demo

![image](screenshot.gif)

## Acknowledgement
- [CodePath](http://codepath.com)
- Android CodePath Observer Group Feb 2015
- [android-async-http](http://loopj.com/android-async-http/)
- [Picasso](http://square.github.io/picasso/)