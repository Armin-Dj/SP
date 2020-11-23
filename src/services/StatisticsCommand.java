#include"SB_Main.h"
#include "System/Os/io_os.h"
#include "System/Scheduler/io_sch_cnf.h"

#include "MKL26Z4.h"
#include "Platform_Types/std_types.h"
#include "Platform_Types/powersar_addon_types.h"
#include "Platform_Types/platform_types.h"
#include <stdio.h>

#include "Other_functions/io_func.h"

#include "MCU_drivers/Adc/io_adc_cnf.h"
#include "MCU_drivers/Adc/io_adc.h"
#include "MCU_drivers/Asy/io_asy.h"
#include "MCU_drivers/Dio/io_dio.h"
#include "MCU_drivers/I2c/io_i2c_cnf.h"
#include "MCU_drivers/Int/io_int_cnf.h"
#include "MCU_drivers/Pcs/io_pcs_cnf.h"
#include "MCU_drivers/Timer/io_tim.h"
#include "MCU_drivers/Tpm/io_tpm_cnf.h"
#include "MCU_drivers/Tpm/io_tpm.h"
#include "MCU_drivers/Wdt/io_wdt.h"
#include "MCU_drivers/Lcd/io_lcd.h"
#include "HW_drivers/Sensors/Accel_Magnet/FX0S8700.h"
#include "SB_Main.h"


	uint8 adc_percent;
	char extended_time = 0;
	char button_flag = 0;
	char motor_dir_flag = 0;
	uint32 button_start_time = 0;
	uint32 button_current_time = 0;
	uint16 mV_voltage = 0;
	uint16 analog_value = 0;
	char toggle_motor_direction = 0;
	char direction_change = 0;
	uint16 LED_array[10];
	uint8 led_counter;
	uint16 pwm_period = 2000;

void Init_LEDarray()
{
	LED_array[0] = LED1;
	LED_array[1] = LED2;
	LED_array[2] = LED3;
	LED_array[3] = LED4;
	LED_array[4] = LED5;
	LED_array[5] = LED6;
	LED_array[6] = LED7;
	LED_array[7] = LED8;
	LED_array[8] = LED9;
	LED_array[9] = LED10;
}

void Main_Init()
{
	Init_LEDarray();
	Turn_ON_Motor();
}

uint16 SB_ReadAnalog(char channel)
{
	uint16 adc_value;
	adc_value = Io_Adc_GetResult(channel);
	return adc_value;
}

uint16 SB_ReadAnalog_mV(char channel)
{
	uint16 result;
	result = (Io_Adc_GetResult(channel) * VREF_mV) / MAX_VALUE_15BITS;
	return result;
}

uint8 SB_ReadAnalog_Percent(char channel)
{
	uint16 adc_value;
	uint8 adc_percent;
	adc_value = SB_ReadAnalog(channel);
	adc_percent = (100 * adc_value) / MAX_VALUE_15BITS;
	return adc_percent;
}

void SB_PWMSetPercentDuty_cycle(uint32 channel,uint8 dutycyclePercentage)
{
	uint16 dutycycle;
	dutycycle = (DUTYCYCLE_MAX_VALUE * dutycyclePercentage) / 100;
	Io_Tpm_PwmChangeDutycycle(channel,dutycycle);
}

void Read_Button()
{
	if(Io_Dio_GetPinLevel(BUTTON_PIN) == HIGH)
	{
		if(button_flag == 0)
				{
					// 0->1 transition
					button_start_time = get_millis(); //gets the time from the moment the user presses the button
					button_flag = 1; //stays ON for as long as BUTTON_PIN is on HIGH
					extended_time = 1; //clears after motor_dir_flag is set
				}
				else if(button_flag == 1 && extended_time == 1)
				{
					button_current_time = get_millis(); //get current time stamp
					//compare it with the time when the pin was first on HIGH
					if(button_current_time - button_start_time > TIME_ON_THRESH)
					{
					//if the button is pressed for a defined amount of time (TIME_ON_THRESH)
					//then set flag for motor direction
						motor_dir_flag = 1;
						extended_time = 0; //clear flag in order to assure that the code inside this if statement is executed only ONCE
						for ( int i = 0; i < 4; i++  )
						{
							Io_Dio_SetPinLevel(RED_LED,LED_OFF); //set RED LED ON to signal that the pin is on HIGH and that the motor_dir_flag was set
							wait(3);
							Io_Dio_SetPinLevel(SET_LED_GREEN,LED_OFF);
							wait(3);
							Io_Dio_SetPinLevel(SET_LED_BLUE,LED_OFF);
						}
					}
				}
	}
	else if(Io_Dio_GetPinLevel(BUTTON_PIN) == LOW)
	{
				//clear every flag
		     	button_flag = 0;
				button_start_time = 0;
				button_current_time = 0;
				motor_dir_flag = 0;
				//turn off RED_LED
				Io_Dio_SetPinLevel(RED_LED,LED_ON);
	}
	if(motor_dir_flag == 1)
	{
		//Code for toggling the digital pin responsible for choosing the direction of the motor
		if(toggle_motor_direction == 0)
		{
			toggle_motor_direction = 1;
			Io_Dio_SetPinLevel(PIN_MOTOR_DIR,LOW);
		}
		else if(toggle_motor_direction == 1)
		{
			toggle_motor_direction = 0;
			Io_Dio_SetPinLevel(PIN_MOTOR_DIR,HIGH);
		}
		motor_dir_flag = 0;
	}
}

void Read_Potentiometer()
{
	adc_percent = SB_ReadAnalog_Percent(ADC_PIN); //
}

void LED_Logic()
{
	adc_percent = adc_percent - (adc_percent % 10); //make sure adc_percent divides by 10
	adc_percent /= 10; //adc_percent possible values: 0,1,2,3,4,5,6,7,8,9,10
	for(led_counter = 0; led_counter < 10; led_counter++)
	{
		if(led_counter < adc_percent)
		{
			Io_Dio_SetPinLevel(LED_array[led_counter],LED_ON);
		}
		else
		{
			Io_Dio_SetPinLevel(LED_array[led_counter],LED_OFF);
		}
	}
}

void Turn_ON_Motor()
{
	SB_PWMSetPercentDuty_cycle(PWM_PIN,MOTOR_ON);
}

void Motor_Speed()
{
	/*
	 * The frequency of the PWM controlling the motor ranges from 1kHz to 500Hz
	 */
	uint16 var = 0; //holds the value of PWM's period in microseconds
	var = 2000 - (adc_percent * 10); //var possible values: 2000 - 1000
	pwm_period = IO_TPM_PERIOD_TO_TICKS(var,IO_TPM_PRESCALER16);
	Io_Tpm_PwmChangePeriod(PWM_PIN,pwm_period);
}

void SB_RunningTask1000ms()
{

}

void SB_RunningTask100ms()
{
	Read_Potentiometer();
	LED_Logic();
	Motor_Speed();
}

void SB_RunningTask10ms()
{
	Io_Adc_Autoscan();
	Read_Button();
}
