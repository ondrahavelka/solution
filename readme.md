# Solution Sample Application

Application is written by design provided in exam interview.

Maybe you will ask - why Spring Boot? 

There is more task to develop this app with DB output and web page with results.

Note: If I can have a request - please provide feedback on code even, well, especially if you are not satisfied with whatever is 
written here, it will be welcomed and most appreciated. 
# Understanding the Solution application 


## Running Solution locally
Solution is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). 
Add at least one parameter with path to file with transactions, more files are optional.
If no parameter is presented, exception is thrown and program is terminated. 
You can build a jar file and run it from the command line:


```
cd solution/
./mvn package
java -jar target/solution-0.0.1-SNAPSHOT.jar solution/input.txt solution/input2.txt 
```


Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```
## Invalid states
I did not take this task really seriously since it is just interview check for coding - so for invalid inputs there are exceptions:

Codes are inherited from https://discworld.fandom.com/wiki/Hex

InitialLoadException - <b>"+++ Divide By Cucumber Error. Please Reinstall Universe And Reboot. +++"</b>
Provided arguments are wrong.
1) Wrapper error parsing a line 
2) Args are invalid (probably will be thrown as IllegalStateException from your favourite SpringBoot)

InvalidInputException - <b>"+++Whoops! Here comes the cheese! +++"</b> and - <b>"Error at Address: 14, Treacle Mine Road, Ankh-Morpork"<b>
Provided data are wrong: 
1) Address error - Input line with transaction (i.e subscription) does not match given limitation - is too long or contains illegal characters
1) Cheese error - Input line with product (i.e Netfix) does not match given limitation - is too long or contains illegal characters

## Note to unit testing
I have just wrote several unit test for model creation (exceptional states and valid states) and basic app run and one simple integration check.
I did not want to waste time on unit test to check output, since a lot of app logic just prints output.
Since app is not complex, most of functional logic was tested by running the app during the development.
## Working with Solution in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 11 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)

### Steps:

1) On the command line
```
git clone https://github.com/ondrahavelka/solution.git
```
2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```

3) Inside IntelliJ IDEA

In the main menu, choose `File -> Open` and select the solution's [pom.xml](pom.xml). Click on the `Open` button.

A run configuration named `SolutionApplication` should have been created for you if you're using a recent Ultimate
version. Otherwise, run the application by right clicking on the `SolutionApplication` main class and choosing
`Run 'SolutionApplication'`. But don't forget to add parameters.

4) Enjoy application within your favorite command line