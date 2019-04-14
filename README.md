# Getting Started

This is a SpringBoot application and the Maven project can be imported directly using the POM.xml. It will execute any csv file. Just make sure it is added in your src/main/resources.

* You can execute this from command line using (check the jar file name):
"java -jar validity-0.0.1-SNAPSHOT.jar" (or) 
"java -jar validity-0.0.1-SNAPSHOT.jar normal" (or) 
"java -jar validity-0.0.1-SNAPSHOT.jar advanced"

Just make sure you append file name (without.csv) in the command line if you want to execute a specific file. It takes normal.csv by default if no file name is provided. The application will print the output to stdout.

* I also created a "GET" endpoint "/duplicates/{name}". You invoke the method using a rest call. {eg: "/duplicates/normal", "/duplicates/advanced"}. There is another endpoint "/duplicates". This takes "normal.csv" by default.

