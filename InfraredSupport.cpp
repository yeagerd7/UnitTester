#include <iostream>
#include "InfraredSupport.h"

// Use of an initialization list. 
PIR::PIR(unsigned sensorPin, unsigned LEDPin, char *LEDColor) : PowerIndicator(LEDPin, LEDColor) { 
    
	std::cout << "In first line of InfraredSupport constructor\n";
        this->array = new char(LED::COLOR_STR_LEN);
        this->inputPin = sensorPin;
	this->delay = 0;
	this->motionDetected = false;
}

// Destructor - must be included here to delete dynamically allocated memory.
PIR::~PIR() {

    std::cout << "In PIR Destructor, about to delete array\n";
    delete [] array;
}

void PIR::setMotionDetected(bool motion) {
	motionDetected = motion; 
}

bool PIR::getMotionDetected() const {
	return motionDetected;
}

