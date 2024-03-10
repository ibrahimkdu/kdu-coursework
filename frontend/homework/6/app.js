const express = require("express"); // Importing the Express.js framework
const path = require("path"); // Importing the path module to work with file paths

// Creating an Express application
const app = express();

// Importing custom middleware for logging
const logger = require("./logger");

// Adding middleware to log requests
app.use(logger);

// Adding middleware to parse incoming JSON data
app.use(express.json());

// Adding routes for handling posts API
app.use("/api/posts", require("./routes/api/posts"));

// Setting up the port for the server to listen on
const PORT = process.env.PORT || 5000;

// Starting the server
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));
