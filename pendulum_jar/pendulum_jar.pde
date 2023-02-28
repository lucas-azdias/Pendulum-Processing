int ws = 600;
float r = 300;
float m = 50;
float ang = PI/2;
float angVel = 0.0;
float angAcl = 0.0;
float g = 9.80665;

void setup() {
  size(600,600);
}

void draw() {
  background(255);
  stroke(0);
  strokeWeight(2);
  fill(0);
  translate(300,0);
  float x = r*sin(ang);
  float y = r*cos(ang);
  ellipse(x, y, m, m);
  line(0,0,x,y);
  angAcl = -0.01*sin(ang);
  ang += angVel;
  angVel += angAcl;
  angVel *= g/10;
}
