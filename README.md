# AppDevAssignment2
Project using Eclipse, Spring, Thymeleaf, MongoDB, JUnit and RestTemplate.

Use of spring autowiring and value injection can be mainly found in worker.java and controller.java

Use of scalable systems that allow for component reuse can be seen in the dataloader.java and all the document classes where id's are strings 

Custom JPA repository searches and nosql mongo repos code in AddOnRepo.java (and other repo's)

Web App structured using Thymeleaf

Authentication code in SpringSecurityConfig.java, login.html

Front end rest code in worker.java

Extensive use of Internationalization throughout the application
  -Page headers and welcome message (footer.html)
  -Custom error messages during checkout form validation (checkout.html)

Service class/business class was used to seperate controller from the functions of the system. See worker.java for details
