<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>벽돌깨기 게임</title>
  <style>
    canvas {
        border-radius: 12px;
      border: 1px solid #000;
      float: bottom;
    }
    
  </style>
</head>
<body>
  <canvas id="gameCanvas" width="800" height="600"></canvas>
  <script src="game.js"></script>
</body>
</html>

<script>
const canvas = document.getElementById("gameCanvas");
const ctx = canvas.getContext("2d");

// 게임 객체 변수 설정
const ballRadius = 10;
let ballX = canvas.width / 2;
let ballY = canvas.height - 30;
let ballDX = 2;
let ballDY = -2;

const paddleHeight = 15;
const paddleWidth =100;
let paddleX = (canvas.width - paddleWidth) / 2;

let rightPressed = false;
let leftPressed = false;

// 벽돌 객체 설정
const brickRowCount = 4;
const brickColumnCount = 9;
const brickWidth = 65;
const brickHeight = 30;
const brickPadding = 10;
const brickOffsetTop = 30;
const brickOffsetLeft = 30;
let bricks = [];

// 벽돌 개수 계산 및 새로운 벽돌 생성
const newBrickRowCount = Math.floor(canvas.height / (brickHeight + brickPadding));
const newBrickColumnCount = Math.floor(canvas.width / (brickWidth + brickPadding));

for (let c = 0; c < newBrickColumnCount; c++) {
  bricks[c] = [];
  for (let r = 0; r < newBrickRowCount; r++) {
    bricks[c][r] = { x: 0, y: 0, status: 1 };
  }
}


for (let c = 0; c < brickColumnCount; c++) {
  bricks[c] = [];
  for (let r = 0; r < brickRowCount; r++) {
    bricks[c][r] = { x: 0, y: 0, status: 1 };
  }
}

// 벽돌 그리기 함수
function drawBricks() {
  for (let c = 0; c < brickColumnCount; c++) {
    for (let r = 0; r < brickRowCount; r++) {
      if (bricks[c][r].status == 1) {
        let brickX = (c * (brickWidth + brickPadding)) + brickOffsetLeft;
        let brickY = (r * (brickHeight + brickPadding)) + brickOffsetTop;
        bricks[c][r].x = brickX;
        bricks[c][r].y = brickY;
        ctx.beginPath();
        ctx.rect(brickX, brickY, brickWidth, brickHeight);
        ctx.fillStyle = "#0095DD";
        ctx.fill();
        ctx.closePath();
      }
    }
  }
}

// 패들 그리기 함수
function drawPaddle() {
  ctx.beginPath();
  ctx.rect(paddleX, canvas.height - paddleHeight, paddleWidth, paddleHeight);
  ctx.fillStyle = "#0095DD";
  ctx.fill();
  ctx.closePath();
}

// 공 그리기 함수
function drawBall() {
  ctx.beginPath();
  ctx.arc(ballX, ballY, ballRadius, 0, Math.PI*2);
  ctx.fillStyle = "#0095DD";
  ctx.fill();
  ctx.closePath();
}

// 충돌 감지 함수
function collisionDetection() {
  for (let c = 0; c < brickColumnCount; c++) {
    for (let r = 0; r < brickRowCount; r++) {
      let b = bricks[c][r];
      if (b.status == 1) {
        if (ballX > b.x && ballX < b.x + brickWidth && ballY > b.y && ballY < b.y + brickHeight) {
          ballDY = -ballDY;
          b.status = 0;
        }
      }
    }
  }
}

// 게임 실행 함수
function game() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  // 벽돌 그리기
  drawBricks();

  // 패들 그리기
  drawPaddle();

  // 공 그리기
  drawBall();

  // 충돌 감지
  collisionDetection();

  // 패들 이동 제어
  if (rightPressed) {
    paddleX += 7;
    if (paddleX + paddleWidth > canvas.width) {
      paddleX = canvas.width - paddleWidth;
    }
  } else if (leftPressed) {
    paddleX -= 7;
    if (paddleX < 0) {
      paddleX = 0;
    }
  }

  // 벽과 공의 충돌 감지
  if (ballX + ballDX > canvas.width - ballRadius || ballX + ballDX < ballRadius) {
    ballDX = -ballDX;
  }
  if (ballY + ballDY < ballRadius) {
    ballDY = -ballDY;
  } else if (ballY + ballDY > canvas.height - ballRadius - paddleHeight) {
    if (ballX > paddleX && ballX < paddleX + paddleWidth) {
      ballDY = -ballDY;
    } else {
      alert("GAME OVER");
      document.location.reload();
      clearInterval(interval);
    }
  }

  ballX += ballDX;
  ballY += ballDY;
}

// 키 제어 함수
function keyDownHandler(event) {
  if (event.keyCode == 39) {
    rightPressed = true;
  } else if (event.keyCode == 37) {
    leftPressed = true;
  }
}

function keyUpHandler(event) {
  if (event.keyCode == 39) {
    rightPressed = false;
  } else if (event.keyCode == 37) {
    leftPressed = false;
  }
}

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);

const interval = setInterval(game, 10);
</script>