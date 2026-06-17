package distributedsystem;



//  Deployment Types: Deployment types describe how new versions of a service are released to production, with goals like:
//  Zero / minimal downtime.
//  Safe rollouts.
//  Fast rollback.
//  Risk reduction.
//  Scalability.

//  1. Recreate Deployment (Big Bang): Shut down old version, Deploy new version. Restart service.
//  Stop v1 ? Deploy v2 ? Start v2
//  Cons: Downtime, High risk, Not acceptable for user-facing FAANG services.

//  2. Rolling Deployment (Rolling Update): Gradually replace old instances with new ones. Service stays available.
//                      Flow:
//                            v1 v1 v1 v1
//                            ?
//                            v2 v1 v1 v1
//                            ?
//                            v2 v2 v1 v1
//                            ?
//                            v2 v2 v2 v2
//  Pros: No downtime, Resource efficient, Default in kubernetes.
//  Cons: Old + new versions coexist, Requires backward compatibility, Hard rollback (slow).
//  FAANG Usage: Very common for stateless services.

//  3. Blue–Green Deployment: Two identical environments:
//  Blue = current production
//  Green = new version
//  Switch traffic instantly.
//              Flow:
//                      Users -> Blue
//                      Deploy v2 -> Green
//                      Switch traffic -> Green
//  Pros: Zero downtime, Instant rollback, Very safe.
//  Cons: Double infrastructure cost, Data migration complexity.
//  FAANG Usage: High-risk releases, Databases, core services.

//  4. Canary Deployment (FAANG Favorite):  Release to small % of users. Monitor metrics. Gradually increase traffic.
//  Flow: 1% ? 5% ? 25% ? 50% ? 100%
//  Pros: Risk isolation, Real user feedback, Fast rollback.
//  Cons: Complex monitoring, Requires traffic routing control.
//  Metrics Watched: Error rate, Latency, CPU / memory, Business KPIs.
//  Extremely common in Google, Netflix, Amazon, Meta.

//  5. A/B Testing Deployment: Two (or more) versions run simultaneously. Compare behavior / business impact.
//  Flow: 50% ? Version A, //50% ? Version B.
//  Purpose: Product experiments, UX changes, Recommendation algorithms.
//  FAANG Usage: Product & growth teams.

//  6. Shadow Deployment (Dark Launch): New version receives real traffic. Responses are not returned to users.
//    Flow:
//          User ? v1 (real response)
//          User ? v2 (shadow, ignored)
//  Pros: Safe testing with real data, No user impact.
//  Cons: Extra infra cost, No user behavior feedback.
//  FAANG Used in High risk logic, ML models, Data pipelines.

//  7. Feature Flag–Driven Deployment: Code deployed everywhere. Behavior controlled via runtime flags. Canary at feature level.
//  FAANG usage: Everywhere combined with canary & A/B.

//  8. Progressive Delivery (Advanced): Automated canary + analysis. Decisions driven by metrics.
//  Often integrated with service mesh.
//  Pros: Fully automated safety, scales well.
//  Cons: High operational complexity.
//  FAANG Usage: Large -scale platforms.

//  9. Immutable Deployment: Never modify running instances. Deploy new version as new instances.
//  Pros: Predictable, Easier rollback.
//  FAANG Usage: Containers, Cloud-native infra.



public class DeploymentStrategies {

}
