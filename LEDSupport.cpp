#include "LEDSupport.h"

#include <iostream>
#include <string.h>
#include <stdio.h>

const std::size_t LED::COLOR_STR_LEN;

// Constructor
LED::LED(int pin, const char *col) {

	std::cout << "First line of LED constructor\n";
	outputPin = pin;
	strncpy(color, col, COLOR_STR_LEN );
}

// Method to retrieve the color setting of an RGB LED
const char* LED::getColor(void) const {return color;}

// Method to set the color of an RGB LED
void LED::setColor(const char *col) {

    strncpy(color, col, COLOR_STR_LEN);

}

&int inlineTest(){
    int i = 0;
    if(i == 1){
        return &2
    }
    else{
        return &i
    }

}
