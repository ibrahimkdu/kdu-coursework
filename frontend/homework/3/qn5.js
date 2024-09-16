function displayKeysAndValues(obj) {
  console.log("Keys:");
  for (const key in obj) {
    console.log(key);
    if (typeof obj[key] === "object") {
      displayKeysAndValues(obj[key]);
    }
  }

  console.log("\nValues:");
  for (const key in obj) {
    if (typeof obj[key] !== "object") {
      console.log(obj[key]);
    } else {
      displayKeysAndValues(obj[key]);
    }
  }
}

let player = {
  firstName: "Leo",
  lastName: "Messi",
  address: {
    country: "Spain",
    city: "Barcelona",
  },
  careerInfo: {
    fcBarcelona: {
      appearances: 780,
      goals: {
        premierLeagueGoals: 590,
        championsLeagueGoals: 50,
      },
    },
  },
};

displayKeysAndValues(player);
