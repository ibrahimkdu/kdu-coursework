//part 1
function encode1(text) {
  text = text.toUpperCase().substring(0, 3);
  return text;
}

function encodeArray(arr) {
  for (i = 0; i < arr.length; i++) {
    arr[i] = encode1(arr[i]);
  }
  return arr;
}
//part 2
const arr = ["sunday", "monday", "tuesday", "friday", "saturday", "sunday"];
console.log(encodeArray(arr));

function encoder(text) {
  let newString = text
    .trim()
    .replace("a", "5")
    .replace("e", "3")
    .replace("i", "1")
    .replace("o", "0")
    .replace("s", "5");
  return newString;
}

text = "      javascript is cool ";
console.log(encoder(text));
