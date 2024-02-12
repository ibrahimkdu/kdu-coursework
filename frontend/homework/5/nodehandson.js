const os = require("os");
const fs = require("fs");
const http = require("http");
const path = require("path");

// Function to create system information JSON
function createSystemInfo() {
  return {
    HostName: os.hostname(),
    OperatingSystem: os.platform(),
    Architecture: os.arch(),
    OSRelease: os.release(),
    Uptime: os.uptime(),
    NumberOfCPUCores: os.cpus().length,
    TotalMemory: os.totalmem(),
    FreeMemory: os.freemem(),
    CurrentWorkingDirectory: process.cwd(),
  };
}

// Write system information JSON to a local file
function writeSystemInfoToFile() {
  const systemInfo = createSystemInfo();
  const filePath = path.join(__dirname, "system-info.json");
  fs.writeFileSync(filePath, JSON.stringify(systemInfo, null, 2));
  console.log("System information has been written to system-info.json");
}

// Call the function to create the system-info.json file
writeSystemInfoToFile();

// Create HTTP server to serve system information JSON
const server = http.createServer((req, res) => {
  if (req.url === "/") {
    const filePath = path.join(__dirname, "system-info.json");
    fs.readFile(filePath, (err, data) => {
      if (err) {
        console.error(err); // Log the error for debugging
        res.writeHead(500, { "Content-Type": "text/plain" });
        res.end("Internal Server Error");
      } else {
        res.writeHead(200, { "Content-Type": "application/json" });
        res.end(data);
      }
    });
  } else {
    res.writeHead(404, { "Content-Type": "text/plain" });
    res.end("Not Found");
  }
});

server.on("error", (err) => {
  console.error("Server error:", err.message);
});

const PORT = 3000;
server.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});

// Function to extract file information
function extractFileInfo(filePath) {
  return {
    extension: path.extname(filePath),
    baseName: path.basename(filePath),
    directory: path.dirname(filePath),
  };
}

// Function to process file paths
function processFilePaths(filePaths) {
  return filePaths.map((filePath) => extractFileInfo(filePath));
}

// Test data
const filePaths = [
  "dir1/dir2/file1.txt",
  "dir1/dir3/file2.txt",
  "dir1/dir3/file3.md",
  "dir4/file4.jpg",
  "dir4/file5.pdf",
];

// Output result of calling processFilePaths function
console.log(processFilePaths(filePaths));
