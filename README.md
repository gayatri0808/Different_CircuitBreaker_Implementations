In Micro Services architecture, when one micro service synchronously calls another micro service, there is a possibility that the other service is unavailable or is exhibiting high latency. This might lead to resource exhaustion, which would make the calling service unable to handle other requests which in-turn results in a cascading failure across the enterprise. Service client retry logic will cause catastrophic failure for the service and can bring down the server. To avoid such failure conditions and increase the resilience of the services, Circuit breaker pattern can be considered as one of the potential solution. Using the Circuit Breaker implementation, most of the scenarios leading to cascading failures can be handled. Among the numerous implementations for the Circuit Breaker pattern in multiple technologies, this article, compares two major implementations related to spring cloud API.
Circuit breaker pattern functions like electrical switch to protect an electrical circuit from damage     caused by excess electric current.Circuit Breaker Object is a wrapper around a function call which will be monitored for the failures 

     Different States of the Circuit Breaker
The circuit breaker has three distinct states: Closed, Open, and Half-Open:
	Closed – When the failures are below the threshold limit.
	Open – When the number of failures exceeds a threshold the breaker trips
	Half-Open – After timeout period, the circuit switches to a half-open state to verify if the problem persists. If a single call fails in half-open state, the breaker again tripped. If it succeeds, the circuit breaker resets back to the normal, closed state circuit. 


 
Each service that’s wrapped by a circuit breaker implements a fallback using one of the following three approaches:
1.	Custom fallback — Certain service client libraries provides fallback method which can be invoked, or we can use locally available data on an API server (e.g., a cookie or local JVM cache) to generate a fallback response
2.	Fail silent -The Fallback method simply returns a null value, which is useful if the data provided by the service being invoked is optional for the response that will be sent back to the requesting client
3.	Fail fast —It is used where the data is required or there’s no good fallback and results 5xx response. This can negatively affect the end user, which is not ideal, but it keeps API servers healthy and allows the system to recover quickly when the failing service becomes available again.

        Different Implementations for Circuit Breaker
Spring Cloud Circuit breaker provides an abstraction across different circuit breaker implementations. It provides a consistent API to use in your applications allowing you the developer to choose the circuit breaker implementation that best fits your needs for your app.
Supported Implementations
1.  Netflix Hystrix
2.  Resilience4J
3. 	Sentinel
4.	Spring Retry

Netflix Hystrix

Hystrix has been popular over the last several years. Now that it is in maintenance mode, many people are seeking alternatives. So, in this article, Hystrix is not been prioritized.

Spring Retry

Spring Retry provides an ability to automatically re-invoke a failed operation. This is helpful where the errors may be transient in nature (like a momentary network glitch). Spring Retry provides declarative control of the process and policy-based behavior that is easy to extend and customize.
To make processing more robust and less prone to failure, sometimes it helps to automatically retry a failed operation in case it might succeed on a subsequent attempt. Let’s take an example of external API integration where web service call may fail because of a network failure or network glitch. Retry provides the ability to automatically re-invoke a failed operation. 
Even though Spring Retry can also be considered as one of the implementations for Circuit Breaker, it is not good alternative to Hystrix or to any other circuit breaker implementations. It can be wrapped with APIs for performing the retry logic.

Resilience4j

Resilience4j is a lightweight fault tolerant library inspired by Netflix Hystrix, but designed for Java 8 and functional programming
The library is lightweight, because it only uses Vavr (formerly known as Javaslang) and has no other external library dependencies
Salient Features
1.	For Java 8 and functional programming, it provides a functional and responsive API	
2.	It adds two modules, Rate Limiting and Automatic Retrying
3.	 Rate Limiting introduces a simple implementation of rate control, which complements the function of flow control.
4.	 Automatic Retrying encapsulates the logic of automatic retry, which simplifies the process of exception recovery.
5.	The resilience4j repository consists of several implementations patterns, including a circuit breaker, time limiter, rate limiter, retry and cache

Sentinel

IT is a lightweight and highly available flow control component for distributed service architectures, 
Sentinel mainly takes the flow as the breakthrough point to help users improve the stability of services from multiple dimensions such as flow control, fault tolerance and system load protection.
Sentinel focuses on various scenarios such as flow shaping, system protection and fault tolerance, and on specialized scenarios such as spikes in pulse flow, the continuous flow peaks at midnight on Double Eleven, the automatic detection and control of popular commodities, peak load shifting, the cluster flow limiting for uneven distribution of clusters, cold start and the adaptive system protection based on capacity and flow.

Proof of Concept

In this article , I compared Resilience4j with Sentinel by using same application which consists of three micro services which are interacts among themselves.

Resilience4j

Implementation

Create three interdependent micro services.
1.  Configure rate limiter, bulkhead and circuit breaker to the micro service which will be called by the end user.
2. 	Configure Spring actuator to the application which gives the application metrics.
3.	Configure Prometheus to the application 
4.	Configure Prometheus as the data source for Grafana.
5.  Use JMeter to test the various configurations of Resilience4j  .


Sentinel
 
Implementation

1.  Create three interdependent microservices.
2.  Configure circuit breaker to the microservice which will be called by the end user.
3.  Configure the sentinel dashboard to the application.
4.	Configure the rules
5.	Use JMeter to test the various features of Sentinel Dashboard.



Real-time Monitoring:

Using Sentinel Dashboard "Monitoring" will only record metrics in 5 minutes. 
Resource metrics log is generated in the directory: ${home}\logs\csp\${appName}-${pid}-metrics.log.${date}.xx
Some of the useful URLS of the Real time monitoring which gives you the details of the clusters,nodes,tree,invocation tree respectively   are :
1. http://localhost:8719/clusterNode   
2. http://localhost:8719/cnode?id=xxxx
3. http://localhost:8719/origin?id=xxxx
4. http://localhost:8719/tree


Resilience4j VS Sentinel

Resilience4j

1.	With resilience4J , the required features can be configured independently. For example, if we need RateLimiter,only that can be configured .If multiple features are needed they can also be configured.
2.	The disadvantage with resilience4J is there is no Graphical interface or dashboard to show the application metrics. Application is  to be configured with either Grafana or other popular dashboard implementation. And any changes in the CB conditions are to be implemented in the API level.
 
 Sentinel
 
1.	The configuration with Sentinel is simple as it has an inbuilt dashboard and even the rules can be changed in the production itself.
2. The dashboard enhancements are not supporting internationalization. The current version of dashboard is 1.8 but the version that supports English Language(en-locale) is still 1.3 only.

 

