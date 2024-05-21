struct LED {
  const char* name;
  int pin;
};

// 사용할 LED와 핀 번호 초기화
LED leds[] = {
  {"LRCE", 41},
  {"LYCE", 42},
  {"LGLE", 43},
  {"LGCE", 44},
  {"LRRE", 45},
  {"LYRE", 46},
  {"LGRE", 47},
  {"LRWE", 48},
  {"LGWE", 49}
};

static int numLeds = sizeof(leds) / sizeof(leds[0]);
const int bufferSize = 32;
char buffer[bufferSize]; 

void setup() {
  Serial.begin(115200UL);
  
  for (int i = 0; i < numLeds; i++) {
    pinMode(leds[i].pin, OUTPUT);
  }
}

void loop() {
  if (Serial.available()) {
    size_t len = Serial.readBytesUntil('\n', buffer, bufferSize - 1);
    buffer[len] = '\0';
    controlLED(buffer);
    buffer[0]='\0';
  }
}

void controlLED(char *data) {
  char *comma = strchr(data, ',');
  if (!comma) return;
  *comma = '\0';

  char *ledName = data;
  char *ledState = comma + 1;

  for (int i = 0; i < numLeds; i++) {
    if (strcmp(leds[i].name, ledName) == 0) {
      int state = (strcmp(ledState, "HIGH") == 0) ? HIGH : LOW;
      digitalWrite(leds[i].pin, state);
      return;
    }
  }
}