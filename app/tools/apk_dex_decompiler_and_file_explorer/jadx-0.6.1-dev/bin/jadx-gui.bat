@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  jadx-gui startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and JADX_GUI_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find javaw.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=javaw.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/javaw.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\jadx-gui-0.6.1-dev.jar;%APP_HOME%\lib\android-5.1.jar;%APP_HOME%\lib\dx-1.10.jar;%APP_HOME%\lib\jfontchooser-1.0.5.jar;%APP_HOME%\lib\slf4j-api-1.7.10.jar;%APP_HOME%\lib\jadx-core-0.6.1-dev.jar;%APP_HOME%\lib\jadx-cli-0.6.1-dev.jar;%APP_HOME%\lib\rsyntaxtextarea-2.5.6.jar;%APP_HOME%\lib\gson-2.3.1.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\asm-5.0.3.jar;%APP_HOME%\lib\annotations-12.0.jar;%APP_HOME%\lib\jcommander-1.47.jar;%APP_HOME%\lib\logback-classic-1.1.2.jar;%APP_HOME%\lib\logback-core-1.1.2.jar

@rem Execute jadx-gui
start "jadx-gui" /B "%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %JADX_GUI_OPTS%  -classpath "%CLASSPATH%" jadx.gui.JadxGUI %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable JADX_GUI_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%JADX_GUI_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
