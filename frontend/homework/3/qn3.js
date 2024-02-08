let shoes = [
  { type: "sneakers", color: "blue", size: 7, price: 1000 },
  { type: "sandal", color: "black", size: 8, price: 500 },
];
let shirts = [
  { type: "polo", color: "blue", size: "Medium", price: 600 },
  { type: "neck", color: "black", size: "Large", price: 700 },
];

let wareHouse = [];
wareHouse.push(...shoes, ...shirts);
let totalWorth = wareHouse.reduce((acc, curr) => acc + curr.price, 0);

wareHouse.sort((a, b) => b.price - a.price);

let blueProducts = wareHouse.filter((product) => product.color === "blue");

console.log("Warehouse:", wareHouse);
console.log("Total worth of products stored in the warehouse:", totalWorth);
console.log("Warehouse products which are blue in color:", blueProducts);
