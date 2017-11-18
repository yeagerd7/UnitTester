#ifndef _LEDSupport_H
#define _LEDSupport_H


#include <cstddef>

class LED {

    public:
	static const size_t COLOR_STR_LEN = 15;

	// Constructor
	LED(int pin, const char *col);

	// Method to retrieve the color setting of an RGB LED
        const char *getColor(void) const;

	// Method to set the color of an RGB LED
	void setColor(const char *col);

    private: 

	unsigned outputPin;
	char color[COLOR_STR_LEN];

}; // end class LED

// You should use a comment on the end of the #endif line showing to what #define this #endif belongs
#endif // _LEDSupport_H
