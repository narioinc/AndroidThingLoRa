# AndroidThingLoRa
An Android things project to provide user driver for the RAK811 lora node

This project aims at providing a user driver for the RAK 811 lora node (868 mhz). I will be expanding the libray for other Wisnode devices.
Newerr Wisnode devices can be created by extending the abstract Wisnode class

# Usage
Usage is quite simple
1) create a object of type WisnodeLora
   ```java
   WisnodeLora mWisnodeLora = new WisnodeLora("USB1-1.4:1.0", 115200);
   ```
   The name of the UART you would like to use can be gathered from the PeripheralManagerService().getUartDeviceList()
   
2) create Request object using the UARTRequest Class and attach a UARTResponseListener
   ```java
   try {
          request.execute(WisnodeLora.VERSION_REQUEST, "at+version\r\n", this);
      }catch (IOException exp){
          Log.e(TAG, "Error while executing request.");
      }
    ```
    
# Revisions    

## rev 1.0
- Added initial support for Wisnode Lora device
- Uart Request class for encapsulating all outgoing request from Android things
- For now the library only supports external usb UART devices (3.3v compatible) connected via USB. 
  If you would like touse UART0 then kindly disable the system debug console on ttyS0 as explained here
  https://stackoverflow.com/questions/41127018/uart-peripherals-on-android-things-for-raspberry-pi-3
