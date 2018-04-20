# Student-Information-System
This project is build using java API for the fulfillment of  M.C.A degree 4th semester minor project.
***********************************Student Information System**************************************************************

Disclaimer:  Just simply run it with the help of command line  
    
 ********************************BUT Before running it; There are list of things to be done.******************************************

1. first of all install JDK1.5 after downloading it from this link:- http://download.oracle.com/otn/java/jdk/1.5.0_22/jdk-1_5_0_22-windows-i586-p.exe    [for 32-bit]
   http://download.oracle.com/otn/java/jdk/1.5.0_22/jdk-1_5_0_22-windows-amd64.exe[for 64-bit]
   or you can be just google it if links will not work.

2. After successfull installation, set the environment variable in system path settings.
    Detailed steps can be followed through this link :- https://www.java.com/en/download/help/path.xml

3. Open cmd and check for "java -version" and "javac -version" by typing these two commands respectively.

4. Hoping you have installed MS-OFFICE ; Because this JAVA APPLICATION runs on Type-1 ODBC-JDBC    drivers. Which requires setting of DNS     name for database connection. steps for setting it up are followed by 
    this link:-  https://stackoverflow.com/questions/20755393/how-to-connect-ms-access-through-java-and-how-to-configure-odbc-driver-in-window
    and steps are as follows as well:
    JDBC connection to Microsoft Access in Windows 7

    Introduction - In this article we make a connection using JDBC to a Microsoft Access database. This connection is made with the help of a JdbcOdbc     driver. You need to use the following steps for making the connection to the data base.

    Creating a Database

          Step 1 : Open Microsoft Access and select Blank data base option and give the data base name as File name option

         Step 2 : Create a table and insert your data into the table

        Step 3 : Save the table with the desired name; in this article we save the following records with the table name student.

        Now Creating DSN of your data base
 
        Step 4 : Open your Control Panel and than select Administrative Tools.

        Step 5 : Click on Data Source(ODBC)-->System DSN.

        Step 6 : Now click on add option for making a new DSN.select Microsoft Access Driver (*.mdb. *.accdb) and than click on Finish

        Step 7 : Make your desired Data Source Name and then click on the Select option, for example in this article article we us the name mydsn

       Step 8 : Now you select your data source file for storing it and then click ok and then click on Create and Finish.

5. The DSN name i used in this application is:student (if you want to change the DSN name so you have to change throughout the program also..).

6. And at last ,Now COMPILE your application and run it successfully.

***********************************THANKS! Happy Coding!! :)***********************************************************
