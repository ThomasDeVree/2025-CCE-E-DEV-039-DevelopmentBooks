This is a webservice that calculate the basket of a bookstore, depending on the basket discount can be applied.


To run it locally use the command: mvn spring-boot:run

To test it via postman locally:
base_url= http://localhost:8080/api
to access to the service add "/price" 
and don't forget the body, an example is available int the resources folder "body_example.json" 

I added some more checks in case of encoding errors and make it more general in case if the price is different (example: damaged book, previous discount)