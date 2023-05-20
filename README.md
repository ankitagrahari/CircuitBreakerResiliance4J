# CircuitBreakerResiliance4J

Calls the Joke API to return a random Joke.
Uses the circuit breaker feature of Resilience4J to handle any unforeseen situation where the API hosted site is down, or internet is down. A fallback method is added to handle these scenarios.
