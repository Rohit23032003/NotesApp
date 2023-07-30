This NotesApp allows users to create, read, update, and delete notes. It utilizes its own API for data handling and stores notes in a MongoDB Atlas database. It also uses shared preferences to store the user's login information, so they don't need to log in again and again.

Features
User authentication: The app allows users to log in and securely stores their login information using shared preferences.
Create Notes: Users can create new notes with a title and content.
Read Notes: All saved notes are displayed on the home screen.
Update Notes: Users can edit the title and content of their existing notes.
Delete Notes: Users can delete notes they no longer need.
Screens
Login Screen: The user can log in with their credentials or create a new account.
Home Screen: Displays a list of all saved notes.
Note Details Screen: Shows the details of a selected note with options to edit or delete.
Create Note Screen: Allows the user to create a new note.
API Endpoints
The API will be built using Node.js and Express.js and will interact with the MongoDB Atlas database. The following endpoints will be implemented:

POST /api/auth/login: Used for user login. The user provides their credentials, and if they are correct, a token is generated and sent back for authentication purposes.
POST /api/auth/register: Used for user registration. The user provides their desired credentials, and a new account is created in the database.
GET /api/notes: Fetches all notes for the authenticated user.
POST /api/notes: Creates a new note for the authenticated user.
GET /api/notes/:id: Fetches a specific note by its ID for the authenticated user.
PUT /api/notes/:id: Updates a specific note by its ID for the authenticated user.
DELETE /api/notes/:id: Deletes a specific note by its ID for the authenticated user.
Technologies Used
Frontend: Android (Kotlin)
Backend: Node.js, Express.js
Database: MongoDB Atlas
Authentication: Shared Preferences
Setup Instructions
Clone the repository.
Install the required dependencies for both the frontend and backend.
Set up MongoDB Atlas and obtain the connection string.
Configure the backend with the MongoDB Atlas connection string.
Run the backend server.
Deploy the backend server.
Build and run the Android app on your device.
Contribution
Contributions to the NotesApp are welcome! If you find any bugs or have suggestions for improvements, please feel free to open an issue or submit a pull request.
