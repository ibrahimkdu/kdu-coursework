// q1
function tipCalculator(bill) {
  let tip = 0;
  if (bill < 50) {
    tip = 0.2 * bill;
  } else if (bill < 200) {
    tip = 0.15 * bill;
  } else {
    tip = 0.1 * bill;
  }
  return tip;
}
const tips = [];
const bill = [140, 45, 280];
console.log(bill);
for (i = 0; i < bill.length; i++) {
  tips.push(tipCalculator(bill[i]));
}
console.log(tips);
const totalBill = [];
for (i = 0; i < bill.length; i++) {
  totalBill.push(tips[i] + bill[i]);
}
console.log(totalBill);

// q2
