## _To run the example_

First you should configure your twitter api account settings in ```/src/main/resources/twitter4j.properties```
After done, run as a spring-boot application:

```sh
mvnw spring-boot:run
```

# _Exposed services_
_http://localhost:8080/tags/rank_
Lists the most used hashtags.

*GET* _http://localhost:8080/tags/rank_
Lists the most used hashtags.

*GET* _http://localhost:8080/status/_
Lists all the status entries stored. Pagination is not done yet.


*GET* _http://localhost:8080/status/1234_
Shows the status entry 1234

*GET* _http://localhost:8080/status/?userName=Bia_
Lists the entries of a given user named Bia_

*GET* _http://localhost:8080/status/?userName=Bia&validated=true_
Lists the entries of a given user named Bia_ which were previously validated.

*GET* _http://localhost:8080/status/?userName=Bia&validated=true_
Lists the entries of a given user named Bia_ which were NOT validated.

*PUT* _http://localhost:8080/status/validate/1234_
Sets the validation flag to the status entry 1234

*PUT* _http://localhost:8080/status/invalidate/1234_
unsets the validation flag to the status entry 1234



