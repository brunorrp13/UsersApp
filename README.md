# UsersApp

UsersApp was made in Kotlin (Android) and it is a two-screens app: The first screen has a textbox and a Next button. The user enters the server address (there are two options: https://6185073a23a2fe0017fff312.mockapi.io/ and https://6185257423a2fe0017fff3d5.mockapi.io/). The user has to enter something on the textBox, otherwise a Toast message is displayed requesting the server address to be entered. After the server address is inserted, the second screen is displayed with a loading screen. If the server address typed was valid and there is no problem with the connection, the user list is displayed with the avatar image, first name and second name of the user. If there was any problem with the connection of with the API call, a toast message is displayed informing the user. 

## Architectural pattern - MVVM
The architectural pattern chosen for the project was MVVM due to the clear separation of responsibilities. There are 3 layers to the project: The Data Layer (with the API mapping, user model and repository implementtion), the Domain Layer (with the repository interface and the get users use case) and the Presentation Layer (with the views). 

## Dependency injection
The Google library Dagger Hilt was used for dependency injection because of its flexibility and practical way of building the dependencies as Singletons and having a readable structure with modules.
