#include <stdio.h>
#include <string.h>
#include <unistd.h>  // for sleep()

#include "LEDSupport.h"
#include "InfraredSupport.h"
#include "colors.h"

int main(int argc, char *argv[]) {
    
    // Instantiate an RGB LED and set its hardware pin and color.
    LED RGBLed1(13, "BLUE");
    printf("RGBLed1 current color: %s%s%s\n", SETBLUE, RGBLed1.getColor(), RESET);

    RGBLed1.setColor("GREEN");
    // Since we know the LED is GREEN, set the text color using SETGREEN, 
    // print a message, and reset the color to normal
    printf("RGBLed1 current color: %s%s%s\n", SETGREEN, RGBLed1.getColor(), RESET);

    // strncmp(const char *s1, const char *s2, size_t n);
    // The strncmp() function compares not more than n characters.
    // Note COLOR_STR_LEN is a static member of the LED class. Because it is public static
    // it can be directly referenced, and without using an instance of the LED class.
    if (strncmp("GREEN", RGBLed1.getColor(), LED::COLOR_STR_LEN) == 0) {

	    RGBLed1.setColor("Red");
	    printf("RGBLed1 current color: %s%s%s\n", SETRED, RGBLed1.getColor(), RESET);
    }

    // Variables for the LED that are embedded in the Infrared motion sensor
    unsigned IRPin    = 10;
    unsigned IRLEDPin = 1;
    char IRLEDColor[LED::COLOR_STR_LEN]  = "Green";

    // Instantiate the Infrared sensor and initialize it to use a hardware pin.
    // Also provide the inputs used to instantiate the embedded LED within the IR sensor.
    PIR PIR1(IRPin, IRLEDPin, IRLEDColor);

    for (int i=0; i < 5; i++) {

            sleep(1);

            if (PIR1.getMotionDetected() == false) {

		    RGBLed1.setColor("Red");
		    printf("%sNo motion detected. %s%s\n", SETRED, RGBLed1.getColor(), RESET);

                    // Change the state of the boolean to the opposite of what it currently is.
                    PIR1.setMotionDetected( ! PIR1.getMotionDetected() );
	    }
	    else {

		    RGBLed1.setColor("GREEN");
		    printf("%sMotion detected. %s%s\n", SETGREEN, RGBLed1.getColor(), RESET);

                    // Change the state of the boolean to the opposite of what it currently is.
                    PIR1.setMotionDetected( ! PIR1.getMotionDetected() );
	    }
    } // end while

    return 0;

} // end main
