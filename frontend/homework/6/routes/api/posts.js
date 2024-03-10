const express = require("express");
const posts = require("../../data/Posts");
const uuid = require("uuid");
const router = express.Router();
//api call to get all the posts
router.get("/", (req, res) => {
  res.json(posts);
});
//api call to get particular post
router.get("/:id", (req, res) => {
  const found = posts.some((post) => post.id === parseInt(req.params.id));
  if (found) {
    res.json(posts.filter((post) => post.id === parseInt(req.params.id)));
  } else {
    res.status(400).json({ msg: `Member not found with id ${req.params.id}` });
  }
});
//api call to post a new post
router.post("/", (req, res) => {
  const newPost = {
    id: uuid.v4(),
    name: req.body.name,
    email: req.body.email,
    content: req.body.content,
  };
  if (!newPost.name || !newPost.email) {
    return res.status(400).json({ msg: `Please include a name and email` });
  }
  posts.push(newPost);
  res.json(posts);
});
//exporting
module.exports = router;
