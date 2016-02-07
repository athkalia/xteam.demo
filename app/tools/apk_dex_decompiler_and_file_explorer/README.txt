This tool can be used to view inside and APK, and specifically inside the classes.dex file that is contained in the APK. It has a graphical user inteface that
can be used to explore all files stored in the APK. For more details please visit https://github.com/skylot/jadx
Mind you that an obfuscated .jar file with the application contents is generated anyway by proguard under the target folder, but it only cotains .class files.

To use:

1) Copy the built APK file into folder application/tools/apk_dex_decompiler_and_file_explorer/jadx-0.6.1-dev/bin
2) Run the command "jadx-gui.bat <name_of_apk_file>.apk
