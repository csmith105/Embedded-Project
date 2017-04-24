package com.embedded.project;

import java.util.concurrent.TimeUnit;

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.*;

public class Main {
	
	// Host / login:		raspberrypi.local / pi : raspberry
	// Pi4J:				http://pi4j.com/usage.html
	
	/*
	 * Robot Pinout (Raspberry Pi)
	 * http://pi4j.com/pins/model-zero-rev1.html
	 * 
	 * 				(use me)
	 * Pin	GPIO		WiPi		Function				Wire Color
	 * ---	----		----		--------				----	
	 * 11	17		0		Direction Left		Blue	
	 * 13	27		2		Direction Right		Blue	
	 * 15	22		3		Step Left			Green
	 * 16	23		4		Step Right			Green
	 * 12	18		1		Enable Both			Yellow
	 */

	public static void main(String[] args) throws InterruptedException {
		
		// Create gpio controller instance
		final GpioController gpio = GpioFactory.getInstance();
		
		GpioPinDigitalOutput dirLeft = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Direction Left", PinState.LOW);
		GpioPinDigitalOutput dirRight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Direction Right", PinState.LOW);
		
		GpioPinDigitalOutput stepLeft = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Step Left", PinState.LOW);
		GpioPinDigitalOutput stepRight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "Step Right", PinState.LOW);
		stepLeft.setShutdownOptions(true, PinState.LOW);
		stepRight.setShutdownOptions(true, PinState.LOW);
		
		GpioPinDigitalOutput enable = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Enable", PinState.LOW);
		enable.setShutdownOptions(true, PinState.HIGH);
		
		System.out.println("Beep Boop");
		
		enable.low();
		dirRight.high();
		
		while(true) {
			
			// High
			stepLeft.setState(true);
			stepRight.setState(true);
			
			// Low
			stepLeft.setState(false);
			stepRight.setState(false);
			
			TimeUnit.MICROSECONDS.sleep(1);
			
		}
		
	}

}
