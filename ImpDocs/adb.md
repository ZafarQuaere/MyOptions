# How adb works #

Android Debug Bridge (adb) is a versatile command line tool that lets you communicate with an emulator instance or connected Android device. It facilitates a variety of device actions, such as installing and debugging apps, and it provides access a Unix shell that you can use to run a variety of commands on an emulator or connected device. It is a client-server program that includes three components:

A client, which sends commands. The client runs on your development machine. You can invoke a client from a command-line terminal by issuing an adb command.
A daemon, which runs commands on a device. The daemon runs as a background process on each emulator or device instance.
A server, which manages communication between the client and the daemon. The server runs as a background process on your development machine.


When you start an adb client, the client first checks whether there is an adb server process already running. If there isn't, it starts the server process. When the server starts, it binds to local TCP port 5037 and listens for commands sent from adb clients�all adb clients use port 5037 to communicate with the adb server.

The server then sets up connections to all running emulator/device instances. It locates emulator/device instances by scanning odd-numbered ports in the range 5555 to 5585, the range used by emulators/devices. Where the server finds an adb daemon, it sets up a connection to that port



Here is the solutions steps (for Windows),

* Close your IDE (Android Studio or Eclipse)
* Close your Device (Emulator or Phone)
* Start task manager and make sure adb.exe isn�t running. If it running, end task.
* Open command prompt (write cmd in windows search box to open)
* Go folder that contain adb.exe (usually C:\Users\{your pc name}\AppData\Local\Android\sdk\platform-tools) you can use �cd� command to go folder. (cd AppData/Local/Android/sdk/platform-tools)
* Run �adb kill-server� command.
* After step 6 run �adb start-server� command.
* Restart your IDE.

