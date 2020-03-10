@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\postgresql\postgresql\42.2.5\postgresql-42.2.5.jar;"%REPO%"\org\hibernate\hibernate-core\5.4.10.Final\hibernate-core-5.4.10.Final.jar;"%REPO%"\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;"%REPO%"\javax\persistence\javax.persistence-api\2.2\javax.persistence-api-2.2.jar;"%REPO%"\org\javassist\javassist\3.24.0-GA\javassist-3.24.0-GA.jar;"%REPO%"\net\bytebuddy\byte-buddy\1.10.2\byte-buddy-1.10.2.jar;"%REPO%"\antlr\antlr\2.7.7\antlr-2.7.7.jar;"%REPO%"\org\jboss\spec\javax\transaction\jboss-transaction-api_1.2_spec\1.1.1.Final\jboss-transaction-api_1.2_spec-1.1.1.Final.jar;"%REPO%"\org\jboss\jandex\2.1.1.Final\jandex-2.1.1.Final.jar;"%REPO%"\com\fasterxml\classmate\1.5.1\classmate-1.5.1.jar;"%REPO%"\javax\activation\javax.activation-api\1.2.0\javax.activation-api-1.2.0.jar;"%REPO%"\org\dom4j\dom4j\2.1.1\dom4j-2.1.1.jar;"%REPO%"\org\hibernate\common\hibernate-commons-annotations\5.1.0.Final\hibernate-commons-annotations-5.1.0.Final.jar;"%REPO%"\javax\xml\bind\jaxb-api\2.3.1\jaxb-api-2.3.1.jar;"%REPO%"\org\glassfish\jaxb\jaxb-runtime\2.3.1\jaxb-runtime-2.3.1.jar;"%REPO%"\org\glassfish\jaxb\txw2\2.3.1\txw2-2.3.1.jar;"%REPO%"\com\sun\istack\istack-commons-runtime\3.0.7\istack-commons-runtime-3.0.7.jar;"%REPO%"\org\jvnet\staxex\stax-ex\1.8\stax-ex-1.8.jar;"%REPO%"\com\sun\xml\fastinfoset\FastInfoset\1.2.15\FastInfoset-1.2.15.jar;"%REPO%"\org\primefaces\primefaces\6.2\primefaces-6.2.jar;"%REPO%"\com\sun\faces\jsf-api\2.2.20\jsf-api-2.2.20.jar;"%REPO%"\com\sun\faces\jsf-impl\2.2.20\jsf-impl-2.2.20.jar;"%REPO%"\org\primefaces\themes\bootstrap\1.0.10\bootstrap-1.0.10.jar;"%REPO%"\javax\mail\mail\1.5.0-b01\mail-1.5.0-b01.jar;"%REPO%"\javax\activation\activation\1.1\activation-1.1.jar;"%REPO%"\javax\servlet\jstl\1.2\jstl-1.2.jar;"%REPO%"\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-core\8.5.23\tomcat-embed-core-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-annotations-api\8.5.23\tomcat-annotations-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.23\tomcat-embed-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-el\8.5.23\tomcat-embed-el-8.5.23.jar;"%REPO%"\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper\8.5.23\tomcat-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-servlet-api\8.5.23\tomcat-servlet-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-juli\8.5.23\tomcat-juli-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-el-api\8.5.23\tomcat-el-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-api\8.5.23\tomcat-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-util-scan\8.5.23\tomcat-util-scan-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-util\8.5.23\tomcat-util-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper-el\8.5.23\tomcat-jasper-el-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-jsp-api\8.5.23\tomcat-jsp-api-8.5.23.jar;"%REPO%"\com\aleksieienko\questionnair\portal\questionnair-portal\1.0-SNAPSHOT\questionnair-portal-1.0-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" com.aleksieienko.questionnaire.portal.launch.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
