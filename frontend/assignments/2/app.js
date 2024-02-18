const express = require("express");
const path = require("path");
const cors = require("cors");
const fs = require("fs");

const session = require("express-session");

const app = express();

const Members = require("./data/Members");
let Posts = require("./data/Posts");

// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, "public")));

// Parse JSON-encoded bodies
app.use(express.json());
app.use(cors());

// Initialize express-session middleware
app.use(
  session({
    secret: "your-secret-key",
    resave: false,
    saveUninitialized: false,
  })
);

// API endpoint for handling login requests
app.post("/api/login", (req, res) => {
  const { username, password } = req.body;

  // Check if username and password are provided
  if (!username || !password) {
    return res
      .status(400)
      .json({ error: "Please provide both username and password" });
  }

  // Check if the entered credentials match any user data
  const user = Members.find(
    (member) => member.user_name === username && member.password === password
  );

  if (user) {
    // Store the username in session
    req.session.username = username;
    return res.json({ success: true, username });
  } else {
    return res
      .status(401)
      .json({ success: false, error: "Invalid username or password" });
  }
});

// API endpoint for getting the logged-in user's username
app.get("/api/loggedInUser", (req, res) => {
  const username = req.session.username;
  if (username) {
    res.json({ username });
  } else {
    res.status(401).json({ error: "User not logged in" });
  }
});
app.post("/api/posts", (req, res) => {
  const { username, content } = req.body;

  if (!username || !content) {
    return res
      .status(400)
      .json({ error: "Please provide both username and content" });
  }
  console.log(username);
  console.log(content);
  const newPost = { username, content };
  Posts.push(newPost);

  // Write the updated Posts array to the posts.js file
  fs.writeFile(
    path.join(__dirname, "data", "Posts.js"),
    `module.exports = ${JSON.stringify(Posts)}`,
    (err) => {
      if (err) {
        console.error("Error writing to Posts.js:", err);
        return res
          .status(500)
          .json({ error: "Failed to create post", details: err.message });
      }
      console.log("Post created successfully");
      // Return the newly created post
      res.status(201).json(newPost);
    }
  );
});

// API endpoint for getting all posts
app.get("/api/posts", (req, res) => {
  // Read the posts from the Posts array
  res.json(Posts);
});

// Starting the server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => console.log(`Server started on port ${PORT}`));
