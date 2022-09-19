## Kraze-App-Application
Take Home Assignment for Kraze Application


## Project Description

1) Have Created a SpringBoot Application in which I have written a PUT API that takes the location file as an input
2) The file will be read and a call will be made to a third-party API which is Positionstack in our case.
3) We can change the ApiKey in the application.properties file and also the API call that was made in our service class to accomodate any other
Third-party API
4) I have created custom exceptions according to the need
5) Have also added in-memory caching and to simulate it have added a delay of 5 seconds usinf threads when a new location is encountered, if that location
is already encountered then the delay will not be there and coordinates will be fetched from the cache
6) Have also created test-case using Mockito by executing a mock-request
7) After completion a file called output.txt will be generated in : ``` /target/classes/DataFolder/output.txt ```


## Running The Project:

# Requirements:
1) Java 15
2) Maven

# How To Run:
1) Download/clone the project
2) Running on CLI:<br>
  a) go the project directory<>br
  b) Run the command:<br>
    ```mvn clean install spring-boot:run```<br>
3) Running on eclipse IDE:<br>
  a) Import the project into the workspace<br>
  b) ``` Right Click on Project name -> Maven -> Update Project ```<br>
  c) ``` Right Click on Project name -> Run As -> Junit ``` -> for running thr unit test-cases<br>
  d) ``` Right Click on Project name -> Run As -> SpringBoot Application ```<br>
4) The application will start on default port of 8080<br>
5) Go to Postman:<br>
  a) Run a PUT request : http://localhost:8080/api/getCodes<br>
  b) Go to Body -> form-data<br>
  c) In the Key select file and add key as ``` DataFile ```<br>
  d) In the value upload the file containing locations<br>
 
 
 ## Assumptions:
 
 1) Since the API was returning multiple coordinates for a particular location so selected only the first of them to be diplayed in the ouput
 
 
## Additional Info:

1) All the working screenshots have been added in the scrrenshots folder
