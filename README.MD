# pgp4Java

pgp4Java is an open source PGP manager tool built on Java.

This means it requires Java 14 to run! [Download](https://download.oracle.com/otn-pub/java/jdk/14.0.2+12/205943a0976c4ed48cb16f1043c5c647/jdk-14.0.2_windows-x64_bin.exe)

## Features:

pgp4Java can:
- Decrypt 
- Encrypt
- Save your main key
- Save your PGP password

pgp4Java has:
- Logs
- A decent looking GUI
- Lots of debug features

pgp4Java WILL have:
- [ ] Support for multiple keys / passwords
- [ ] Support for downloading keys
- [x] A better looking GUI
- [ ] Support for encrypting / decrypting files
- [x] User requested features

## Debugging:

In order to debug pgp4Java, you must open the jar in command line.

Open a command prompt in the folder the .jar is located in and run:

```java -jar pgp4Java.jar```

This will open pgp4Java but leave a command prompt open.
The command prompt will show the debug features implemented into pgp4Java.

Contact Runabox#0001 on discord for further questions

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Other:

pgp4Java's resources (keys, passwords, etc.) are stored in your AppData folder.

Navigate to AppData, Roaming, .pgp4Java, and then everything else should be easy to figure out.
