This tool extracts data out of an android application backup
(http://android.stackexchange.com/questions/69567/what-all-does-adb-backup-and-how-do-i-restore-part-of-it)

This library is hosted on github: https://github.com/nelenkov/android-backup-extractor

Step A: Perform Backup
1) Connect the phone that you wish to backup on your computer.
2) Type "adb backup <app.package>". Example: "adb backup com.lifesigns". This will open up a confirmation dialog on the mobile phone.
3) On the phone, click on "Back up my data". This will start the backup.
4) The resulting backup file will be generated on your computer, usually under C:/users/<current_user> with a name "backup.ab"

Step B: Extract Backup
1) Copy the "backup.ab" file on the same directory with the extractor tool (i.e. on the same directory with the "abe.jar")
2) Type "java -jar abe.jar unpack backup.ab backup.tar". This will create a tar file on the same location that can be accessed via winrar.

Note: This process should only be used for non-debuggable apk files. If an apk is debuggable then all of it's data can be accessed easily by:
1) Typing "adb shell".
2) Typing "run-as <app.package>. Example "run-as com.lifesigns".
3) Manually go to the data directory and access any data you like.
