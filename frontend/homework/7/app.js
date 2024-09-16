const express = require('express'); 
const http = require('http'); 
const cors = require('cors'); 
const socketIo = require('socket.io'); // Import the Socket.io library

const app = express(); 
const server = http.createServer(app); // Create a new HTTP server using the Express app
const io = new socketIo.Server(server, { // Create a new instance of Socket.io and attach it to the HTTP server
    cors: {
        origin: "http://127.0.0.1:5500" // Allow requests from this origin
    }
});

app.use(cors()); // Use CORS middleware to allow cross-origin requests
app.use(express.json()); // Use JSON middleware to parse JSON request bodies

io.on("connection", (socket) => { // Event listener for new socket connections
    socket.on("message", (payload) => { // Event listener for incoming "message" events
        io.except(socket.id).emit('new-message', payload); // Emit a "new-message" event to all clients except the sender
    });
});

server.listen(3000, () => { // Start the server and listen for incoming connections on port 3000
    console.log("App Started on port 3000"); 
});


