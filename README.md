# Amadeus_Travel_To_Future_Project

### Swagger API Documentation Screen:

![swagger1](https://github.com/Rahman2001/amadeus_project_case_study/blob/master/src/main/resources/swagger_doc/swagger1.JPG)
![swagger2](https://github.com/Rahman2001/amadeus_project_case_study/blob/master/src/main/resources/swagger_doc/swagger2.JPG)


### High-Level Architecture Diagram:
![uml_diagram](https://github.com/Rahman2001/amadeus_project_case_study/blob/master/src/main/resources/high_level_arch/project_uml_diagram.jpg)


## Assumptions
There are some assumptions made in this application which defines business constraints.
Assumptions are as follows:

1. There is only one airline company (for example, Turkish Airlines)
2. Only one plane can fly to certain destination and at certain time. (for example, at 23.12.2023 7:30 A.M. from Ankara to Istanbul
there is only a plane A that will fly at that time and place).
3. Those flights that do not have return time are one way flights.

All of these assumptions are made to decrease the possibility of duplicate data in database. Thus, application is developed accordingly.
