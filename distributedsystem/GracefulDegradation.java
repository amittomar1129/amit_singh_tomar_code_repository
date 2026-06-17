package distributedsystem;


//  Graceful degradation:
//  Graceful Degradation is a resilience pattern where a system continues to function with reduced features or
//  quality instead of failing completely when part of the system is under stress or unavailable.
//  Graceful degradation means designing systems to deliver partial functionality when full functionality is not possible.
//  Fail soft, not fail hard

//  Example: E-commerce Website
//  If: Recommendation service is down
//  Instead of:
//      ? Page load fails
//  Do:
//      ? Show product page without recommendations
//      ? Use cached or default recommendations
//  This is graceful degradation.

//  What Can Be Degraded?
//  Features: Disable non-critical features
//  Quality: Lower resolution images
//  Freshness: Serve cached/stale data
//  Accuracy: Approximate results
//  Performance: Slower but functional responses


//  Common Graceful Degradation Techniques:
//  1. Feature Fallbacks: If Service A fails ? use default response.
//  Example: Payment analytics fails ? allow payment but skip analytics.
//
//  2. Cache Fallback: DB slow ? serve cached data.
//  Used heavily in FAANG systems.
//
//  3. Read-Only Mode: When writes fail: Allow reads. Block writes temporarily.
//  Example: Banking app during maintenance.
//
//  4. Load Shedding: When overloaded, Reject low-priority requests. Serve high-priority users.
//  Example: Free users throttled, paid users served.

//  5. Partial Responses:
//  Example:
//      {
//      "user": {...},
//      "orders": [...],
//      "recommendations": "temporarily unavailable"
//      }

//  Pitfalls:
//  Complex fallback logic.
//  Difficult testing.
//  Silent data inconsistency.
//  Serving stale data too long.
//  Hidden errors.
//  Business metrics distortion.


public class
GracefulDegradation {

}
