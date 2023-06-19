# Android-MVVM-Hilt-RX-Room-Usecase

This repository serves as a demo showcasing the integration of Android Architecture components such as MVVM, Hilt, RX, Room, and Usecase. It provides a maintainable and testable codebase example for building an Android app using these components.

## Clean Architecture Implementation

The project follows the Clean Architecture approach, dividing the codebase into three layers:

- UI: Handles the user interface and interaction.
- Domain: Contains the business logic and use cases.
- Data: Manages the data sources and repositories.


![Project Structure](![Screenshot 2023-06-19 at 17 07 19](https://github.com/bishal559/-Android-Architecture-MVVM-Hilt-RX-Room-Usecase/assets/65449966/2784c5ec-74de-4bb7-8f19-7350305cfab0))

## Communication between Layers

1. The UI layer calls methods from the ViewModel.
2. The ViewModel executes the corresponding Use case.
3. The Use case combines data from the Album and Photo Repositories.
4. Each Repository retrieves data from a Data Source (Cached or Remote).
5. The information flows back to the UI layer, where the list of posts is displayed.

## Scenario

We utilized the public API [jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com/) to generate fake data for testing purposes.

## App Features:

- Display a list of Albums.
- Show the name of each Album in its respective item.
- When a user taps on an Album, a new page is shown with a list of photos.
- When a user taps on a photo, the image is displayed in a larger size using transition effects.
- Tests were written to cover Exceptions/Expectations comprehensively.
- Additional Features:
  - Supported orientation changes.
  - Supported offline mode.

Feel free to explore and adapt this example to build your own Android app based on the Android Architecture components mentioned.
