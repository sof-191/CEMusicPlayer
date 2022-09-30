long brillo;
              // resistencia fija del divisor de tension 
int R = 0; //potenciometro 10k
int ledfav = 2;
int ledcont = 3;
int led1 = 4;
int led2 = 5;
int led3 = 6;
int led4 = 12;
int led5 = 13;
int pinon=7;
int estaon = HIGH;
int estaoff = HIGH;
int botonfav = 7;
int botoncont= 8;
int botonbefore= 9;
int botonplay = 10;
int botonnext = 11;
int cont =0;
bool ban = false; //boolean para pausar y reproducir
//bool cont = false; // boolean para el loop
bool fav = false; // boolean para ver si es favorita
//Boton 

void setup() {
  
  pinMode(led1, OUTPUT);  
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(led4, OUTPUT);
  pinMode(led5, OUTPUT);
  pinMode(ledfav, OUTPUT);
  pinMode(ledcont, OUTPUT);
  pinMode(R, OUTPUT);
  // CONFIGURAR PINES COMO ENTRADAS
  pinMode(pinon, INPUT);
  //pinMode(pinoff, INPUT);
  pinMode(botoncont, INPUT);
  pinMode(botonfav, INPUT);
  pinMode(botonplay, INPUT);
  pinMode(botonnext, INPUT); 
  pinMode(botonbefore, INPUT); 
  // CONFIGURAR PIN DE LED COMO SALIDA
  Serial.begin(9600);
}

void loop() {
  if(Serial.available()>0){
    int entrada = Serial.read();
    switch(entrada)
    {
      case 'C':
        digitalWrite(led2,HIGH);
        break;

      case 'D':
        digitalWrite(led2,LOW);
        break;
      
      case 'F':
        digitalWrite(led1,HIGH);
        break;

      case 'N':
        digitalWrite(led1,LOW);
        break;
    }
    }
  if (digitalRead(9) == HIGH) {    
    Serial.println("anterior");
    delay(300);

  }


  if (digitalRead(10) == HIGH) { 
    if (ban == true){  
      Serial.println("play");
      delay(300);
      ban = false;
    } else if (ban == false){
      Serial.println("pause");  
      ban = true;
      delay(300); 
    }     
  }
  
  if (digitalRead(11) == HIGH) {    
    Serial.println("siguiente");
    delay(300);

  }

  if (digitalRead(7) == HIGH) {
    if(digitalRead(ledfav)==LOW){
      Serial.println("fav");
      digitalWrite(ledfav,HIGH);
      delay(300);  
    } else if (digitalRead(ledfav)==HIGH){
      Serial.println("nofav");
      digitalWrite(ledfav,LOW);
      delay(300);        
    }  
    // conectar con funcion de java para reproduccion continua
 }
  if (digitalRead(8) == HIGH) {
    if(digitalRead(ledcont)==LOW){
      Serial.println("cont");
      digitalWrite(ledcont,HIGH);
      delay(300);
    } else if (digitalRead(ledcont)==HIGH){
      Serial.println("nocont");
      digitalWrite(ledcont,LOW);
      delay(300);
    }    
 }
    

  brillo = analogRead(R); //potenciometro 10k
  if (brillo >= 0 && brillo <= 100){
    digitalWrite(led1, LOW);
    digitalWrite(led2, LOW);
    digitalWrite(led3, LOW);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println("0");
 } 
  if (brillo >= 100 && brillo <= 200){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, LOW);
    digitalWrite(led3, LOW);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println("1");
 }
 if (brillo >= 200 && brillo <= 300){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, LOW);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println("2");
 }
 if (brillo >= 300 && brillo <= 500){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    digitalWrite(led4, LOW);
    digitalWrite(led5, LOW);
    Serial.println("3");
 }
 if (brillo >= 500 && brillo <= 700){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    digitalWrite(led4, HIGH);
    digitalWrite(led5, LOW);
    Serial.println("4");
 }
 if (brillo >= 700){
    digitalWrite(led1, HIGH);
    digitalWrite(led2, HIGH);
    digitalWrite(led3, HIGH);
    digitalWrite(led4, HIGH);
    digitalWrite(led5, HIGH);
    Serial.println("5");    
  }
}
