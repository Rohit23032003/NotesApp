<h1>NotesApp</h1>
This NotesApp allows users to create, read, update, and delete notes. It utilizes its own API for data handling and stores notes in a MongoDB Atlas database. It also uses shared preferences to store the user's login information, so they don't need to log in again and again.

<h1>Features</h1>
<b>User authentication:</b> <br></br>The app allows users to log in and securely stores their login information using shared preferences.<br></br>
<b>Create Notes:</b> Users can create new notes with a title and content.<br></br>
<b>Read Notes:</b> All saved notes are displayed on the home screen.<br></br>
<b>Update Notes:</b> Users can edit the title and content of their existing notes.<br></br>
<b>Delete Notes:</b> Users can delete notes they no longer need.<br></br>
<h1>Screens</h1>
<b>Login Screen:</b> The user can log in with their credentials or create a new account.
<b>Home Screen:</b> Displays a list of all saved notes.
<b>Note Details Screen:</b> Shows the details of a selected note with options to edit or delete.
<b>Create Note Screen:</b> Allows the user to create a new note.
<h1>API Endpoints</h1>
The API will be built using Node.js and Express.js and will interact with the MongoDB Atlas database. The following endpoints will be implemented:
<br></br>
<b>POST /api/auth/login:</b> Used for user login. The user provides their credentials, and if they are correct, a token is generated and sent back for authentication purposes.
<b>POST /api/auth/register:</b> Used for user registration. The user provides their desired credentials, and a new account is created in the database.
<b>GET /api/notes:</b> Fetches all notes for the authenticated user.<br></br>
<b>POST /api/notes:</b> Creates a new note for the authenticated user.<br></br>
<b>GET /api/notes/:id:</b> Fetches a specific note by its ID for the authenticated user.<br></br>
<b>PUT /api/notes/:id:</b> Updates a specific note by its ID for the authenticated user.<br></br>
<b>DELETE /api/notes/:id:</b> Deletes a specific note by its ID for the authenticated user.<br></br>
<h1>Technologies Used</h1>
<b>Frontend:</b> Android (Kotlin)<br></br>
<b>Backend:</b> Node.js, Express.js<br></br>
<b>Database:</b> MongoDB Atlas<br></br>
<b>Authentication:</b> Shared Preferences<br></br>


https://github.com/Rohit23032003/NotesApp/assets/99860290/0b90917e-2e71-4db7-8ab7-0d3b85af0a09
