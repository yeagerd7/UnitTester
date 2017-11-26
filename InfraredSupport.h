// The following preprocessor directives check if a variable is defined. If not then it is defined on the line
// following the check. If however it is already defined and processed, then none of the remaining lines of the file 
// are processed again by the preprocessor or the compiler.
// By convention, use the file's name with underscores. This ensures the variable is not accidently used twice.

#ifndef _InfraredSupport_H
#define _InfraredSupport_H

#include "LEDSupport.h"

class PIR {

public:

    PIR(unsigned sensorPin, unsigned LEDPin, char *LEDColor);

    ~PIR();

    void setMotionDetected(bool motion);

    bool getMotionDetected() const;

    inline void test();

private:
    unsigned inputPin;
    float    delay;
    bool     motionDetected;
    char     *array;
    LED      PowerIndicator;
};

#endif // _InfraredSupport_H
