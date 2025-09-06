# MyAnimeApp
A simple Android app that uses the Jikan API to fetch and display a list of popular anime series, allowing users to view details and trailers. 
The app implements MVVM architecture with Retrofit, Glide, Room, and LiveData for reactive and offline-capable UX.

## Features
Fetches and displays top anime from the Jikan API, including:
-  Title
-  Number of Episodes
-  MyAnimeList Rating
-  Poster Image
-Detailed anime page shows:
-  Video player for the trailer (or poster if unavailable)
-  Title, synopsis, genres, main cast, episodes, rating
- Local caching using Room database for offline availability
- Data synchronization when online
- Error handling for API, database, and network issues
- Reactive UI updates via LiveData
- Handles legal constraints by adapting UI if images/displays are restricted

## Architecture & Libraries
- MVVM (Model-View-ViewModel) design pattern for clean, testable code
- Retrofit for network API calls
- Gson for JSON parsing with @SerializedName annotations
- Room for local data persistence
- Glide for image loading and caching
- LiveData for reactive UI updates



## Assumptions
- API responses follow the Jikan API v4 spec.
- User device runs Android 6.0 (API 23) or later.
- Profile images are allowed unless otherwise restricted.

## Known Limitations
- No pagination or search functionality implemented.
- Trailer plays externally via URLs instead of embedded player.
- No retry logic for failed network requests.
- UI designed for functionality over polish.

## Future Improvements
- Add search and pagination features.
- Implement an embedded video player for trailers.
- Improve offline sync strategy and caching.
- Enhance UI/UX with animations and error states.
