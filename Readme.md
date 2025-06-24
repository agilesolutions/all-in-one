# Spring Boot all-in-one backend reference application
- Spring Framework
  - Spring data JPA
  - Spring data MongoDB
  - Spring Web
    - controller
    - controller advice
    - problem+json
    - declarative RestClient
  - Spring kafka
  - Spring security
  - Spring Scheduler
  - Spring Retry
- Helm
- Kustomize
- DevOps pipeline
- Terraform AKS

## run


```
gradle generateAvro
gradle bootRun

http://localhost:8080/jpa/shares
http://localhost:8080/mongo/shares
```