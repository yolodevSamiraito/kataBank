This project is developed with Spring Boot.



To Build and Run the Project use the command bellow:
```
> mvn spring-boot:run
```

To Test the Project use the command bellow:
```
> mvn test
```

The App uses the port 8181 so to access it, use the link bellow:
```
http://localhost:8181/
```

This project is configured with H2 database, so, there is to need to do any configuration.


The main functions are tested using PostMan and are listed bellow 

To check:
```
Get
http://localhost:8181//api/account/{id}/check
```
To Deposit
```
Post
http://localhost:8181//api/account/{id}/deposit

the body contains a value.
```
To Withdraw
```
Post
http://localhost:8181//api/account/{id}/withdraw

the body contains a value.
```

To Print
```
Get
http://localhost:8181//api/account/{id}/print
```

The dataBase contains 2 accounts, with Ids 1 and 2, which can be used for manual tests.
