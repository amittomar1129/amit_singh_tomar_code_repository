package distributedsystem;

//  API Gateway + BFF(Backend for Frontend)

//  API Gateway: An API Gateway is a single entry point for all clients (web, mobile, backend services).
//  It sits in front of microservices and handles: Core Responsibilities.

//  Routing to downstream services
//  Request/response transformation
//  Request validation
//  Authentication / Authorization
//  Caching
//  Observability (metrics, logs, tracing)
//  Circuit breaking
//  Retries / timeouts
//  Rate limiting and throttling
//  SSL termination
//  Canary releases, blue/green deployments
//  Multitenancy rules
//  Think of it as the "global gatekeeper" for all clients.

//  BFF (Backend for Frontend): A Backend for Frontend is a custom backend service created specifically for one client type.
//  eg. Web BFF, Mobile BFF , Wearable / IoT BFF, Partner API BFF, Admin dashboard BFF.
//  Core Responsibilities.

//  Aggregating multiple microservices into one response
//  Adding UI-specific logic (sort, search, format)
//  Filtering fields, send only what UI needs
//  Handling pagination differently for web/mobile
//  Optimizing payload size for mobile
//  Managing session / feature flags
//  Providing backward compatibility for older apps
//  Think of BFF as the "translator between frontend and microservices".

//  When NOT to use BFF?
//  Avoid BFF if:
//  the system is simple
//  frontends are homogeneous (similar in technology, structure, and behavior.)
//  the team cannot manage multiple backend services
//  GraphQL already covers aggregation needs


public class ApiGatewayBFF {

}
