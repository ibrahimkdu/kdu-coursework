const express = require("express");
const http = require("http");
const cors = require("cors");
const socketIo = require("socket.io");
const path = require("path");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
  cors: {
    origin: "*", // Allow requests from this origin
  },
});

app.use(cors());
app.use(express.json());

function priceUpdate(price) {
  let min = 0;
  let max = 501;
  min = Math.ceil(min);
  max = Math.floor(max);
  price = Math.floor(Math.random() * (max - min + 1)) + min;
  return price;
}

io.on("connection", (socket) => {
  setInterval(() => {
    const newPrice = priceUpdate();
    io.emit("new-price", newPrice); // Emit new price to all clients
  }, 5000);
});

server.listen(3000, () => {
  console.log("App Started on port 3000");
});

app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).send("Something broke!");
});
