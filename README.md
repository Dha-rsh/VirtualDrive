1. Project Folder:   C:\apache-tomcat-10.1.43\webapps\VirtualDrive
2. list of all Java files : dir /s /b *.java > sources.txt
   
3. Compile the Java files

javac -cp "C:\apache-tomcat-10.1.43\webapps\VirtualDrive\WEB-INF\classes*;C:\apache-tomcat-10.1.43\webapps\VirtualDrive\WEB-INF\lib*;C:\apache-tomcat-10.1.43\lib*" -d "C:\apache-tomcat-10.1.43\webapps\VirtualDrive\WEB-INF\classes" @sources.txt
This command compiles all Java files and puts the .class files in WEB-INF/classes.

4. Start the server using "C:\apache-tomcat-10.1.43\bin" then run startup.bat
   
5. http://localhost:8080/VirtualDrive

Required Library
WEB-INF/lib/
   -> mysql-connector-j-9.3.0.jar

Storage Folder
C:/VirtualDriveStorage
