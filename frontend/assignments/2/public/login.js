async function login() {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  const data = {
      username: username,
      password: password
  };

  const response = await fetch("http://localhost:3000/api/login", {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
  });
  
  const res = await response.json();

  if (res.success) {
      window.location.href = "http://localhost:3000/index.html";
  } else {
      alert("Invalid username or password. Please try again.");
  }
}
