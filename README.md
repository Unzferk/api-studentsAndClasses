## Pre Requirements ##

To Run the API for Students and Classes Administration Project, you need to install some tools and programs.

Download and Install Docker Desktop in your local Machine.

- Windows 10 Pro/Enterprise: https://docs.docker.com/docker-for-windows/
- Windows 10 Home: https://docs.docker.com/docker-for-windows/install-windows-home/ (You will have to do some extra steps on your machine before install Docker Desktop).
- Mac (Version 10.13 or newer): https://docs.docker.com/docker-for-mac/install/

 Download IntelliJ IDEA 2020.2.1 Community Edition (Recommended) https://blog.jetbrains.com/idea/2021/08/intellij-idea-2021-2-1/


Download Maven (Not necessary if you will use IntelliJ):

- Download maven archive: https://maven.apache.org/download.cgi (You will need to download the Binary zip archive)

Install Maven (Not necessary if you will use IntelliJ):

- For windows: https://howtodoinjava.com/maven/how-to-install-maven-on-windows/
- For Linux: https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html
- For OSX (if it isn't already installed): https://mkyong.com/maven/install-maven-on-mac-osx/

Verify that Java is installing with (This project is running with Java 17):
```sh
$ java -version
```
Confirm that mvn is correctly installed with:
```sh
$ mvn -v
```
Download and Install MySQL database management system, we recommend using MySQL Workbench but it's on you.
- You can download MySQL Workbench from: https://downloads.mysql.com/archives/workbench/

## Cloning the API PROJECT

To clone the API repository, use the next git command:

```
https://github.com/Unzferk/api-studentsAndClasses.git
```


## IDE Requirements for Lombok dependency ##

On IntelliJ IDEA 2020.2.1 (Community Edition)

```
Go Settings > Plugins > Marketplace

Search for Lombok Plugin

Install
```

On VisualStudioCode 1.16.1 or later

```
Go to Extensions (Ctrl + Shift + X)

Search for Lombok Plugin

Install
```

## Setup Application.properties ##

As you know, most of the environment variables are sited here, if you want to change the ports you would come to this file to change the defaults.

Default configurations:

```properties
spring.datasource.url=jdbc:mysql://localhost:3333/administration
```

```properties
server.servlet.context-path =/api/v1
server.port=8030
```
- Ports allowed to use the API (Actually default UI port)
```properties
allowed.origin=http://localhost:3000
```

- **! IMPORTANT** : The next lines are enabling the auto population database every time the system starts running, so, if you are testing the
app it's ok. But if you want to use it in production, you should remove this lines from the file.

```properties
spring.jpa.hibernate.ddl-auto=none
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
spring.datasource.data=data.sql
```

## Run the Project ##

To run the project, first execute docker-compose file (this is in the root folder of the project), with the following command:
```sh
$ docker-compose up
```

This will be created mysql database in specific ports to avoid installing these programs on our machine 
(verify that the ports in docker-compose file matches with application.properties ports).

Finally, to run the API execute this command:

```sh
$ mvn spring-boot:run
```

Or if you have IntelliJ run the `AdministrationApplication.java` file.

## Create Tables ##
Actually as we mentioned in the 'Set up application.properties' part, the system will run 2 scripts automatically to create tables and populate them,
this script files are in 'resources' folder.

In case you deactivate the automatic function you can use that script files to run them manually.

## Get connection with the database locally ##
To create the connection with the database, you should have your docker instance running and the next configurations in your MySQL Workbench.

```
Name = administration
Host Address = localhost
Username = administration
Password = administration
Port = 3333
```
## Validate the system is running locally ##

To validate if the system is running, you can open a browser and look at swagger documentation of the API, 
if you are running the API on the **port 8030**, the URL will look something like this:

```
http://localhost:8030/api/v1/swagger-ui/#
```

## ENJOY IT! ##
- With these steps you probably are running the project. That's it, see you in UI part.
