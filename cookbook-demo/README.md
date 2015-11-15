Cookbook Demo (Spring / Wicket / Hibernate)
===========================================

This project builds and runs a simple Cooking Recipe Administration Web application built on Spring, Wicket and Hibernate.

##Requirements

* MySQL Database Server (you can easily switch to embedded database by changing the datasource configuration in application.yml)
* Gradle (no wrapper files included)

##Things not implemented (due to lack of time, sorry...)
* upload of photos and other media
* extensive unit tests
* proper form validation and notifications
* 100% Clean Code
* a tremendously good looking GUI ;)

##Notable classes

###WicketWebApplication

The Wicket Web Application class is a spring bean triggering the spring boot configuration and start up by using the @EnableAutoConfiguration annotation and providing the main class.

###WebInitializer

This class replaces the web.xml by using spring boot's ServletContextInitializer that is found and executed automatically on startup.

###WarInitializer

This class is used when being deployed on an application server. In this context it has the functionality of the application's main method. 

## Running / Deployment

Building the project provides two war archives. One is a fully featured spring boot runnable war, the other having the suffix original is a standard war archive and can be deployed directly on an application server.
