// 1. press one number - we save that number
// 2. press the operator - we save that operator
// 3. press second number - save second number
// 4. press equal sign - make the calculation

let firstNumber;
let secondNumber;
let step = 0;

let operation;
let result = 0

let numArray = []
let secondNumArray = []

let display = document.getElementById('display');


function getNumber(num) {
  if (step === 0 || step === 1) {
    numArray.push(num);
    step = 1;
    firstNumber = Number(numArray.join('')); //merge into one string, then into number
    display.value = firstNumber;
    
  } else if (step === 2) {
    secondNumArray.push(num);
    secondNumber = Number(secondNumArray.join(''));
    display.value = secondNumber;
  }
}

function getOperator(op) {
  step = 2;
  operation = op;
  
}

function clearDisplay() {
  display.value = 0;
  firstNumber = null;
  secondNumber = null;
  step = 0;
  operation = null;
  result = 0;
  numArray = [];
  secondNumArray = [];
}

const calculateEquals = () => {
  if (operation === '+') {
    result = firstNumber + secondNumber;
    display.value = result;
  } else if (operation === '-') {
    result = firstNumber - secondNumber;
    display.value = result;
  } else if (operation === '*') {
    result = firstNumber * secondNumber;
    display.value = result;
  } else if (operation === '/') {
    result = firstNumber / secondNumber;
    display.value = result;
  }
  
}