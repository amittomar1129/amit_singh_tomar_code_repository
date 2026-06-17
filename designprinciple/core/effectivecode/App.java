package designprinciple.core.effectivecode;

//  Effective Java / Effective Code Practices:

//  Object Creation & Initialization:
//  ? Prefer static factory methods over constructors
//      Better names, caching, flexible return types.
//  ? Use builders for objects with many parameters
//  Avoid telescoping constructors and null hell.
//  ? Avoid unnecessary object creation
//  Reuse immutable objects, avoid boxing/unboxing.
//  ? Prefer immutability
//  Immutable objects ? thread-safe ? cacheable ? easier reasoning.


//  Classes & Interfaces
//  ? Minimize mutability
//  Make fields final, classes final when possible.
//      ? Favor composition over inheritance
//  Prevents fragile base-class problems.
//      ? Prefer interfaces over abstract classes
//  Allows multiple behavior combinations, testability.
//      ? Design small, cohesive interfaces
//  Avoid "fat interfaces". Align with ISP.

//  Methods & APIs
//  ? Keep methods small & single-purpose
//      Limit branching, reduce cognitive load.
//      ? Validate arguments early (fail fast)
//  Throw meaningful exceptions.
//  ? Avoid returning null
//  Return empty collections or Optional.
//  ? API surface should be minimal
//  Expose only what clients require (encapsulation + boundary control).


//  Generics & Collections:
//  ? Prefer generics to raw types
//  Eliminates ClassCastException.
//      ? Favor List over arrays when possible
//  More type-safe.
//  ? Use appropriate collections
//  HashMap vs LinkedHashMap vs ConcurrentHashMap vs TreeMap.
//  ? Prefer EnumSet & EnumMap
//  High performance & readability.


//  Concurrency & Multithreading:
//  ? Prefer immutability for shared data
//  Minimizes locking.
//      ? Use concurrent collections over synchronized ones
//  ConcurrentHashMap, CopyOnWriteArrayList.
//  ? Avoid using threads directly
//      Use ExecutorService, thread pools.
//  ? Never use double-checked locking incorrectly
//  Use volatile, or better: use lazy holders.
//  ? Avoid premature parallelism
//  Only parallelize when CPU-bound AND measurable benefit exists.


//  Exceptions & Error Handling:
//  ? Use exceptions only for exceptional conditions
//  Not for control flow.
//      ? Document thrown exceptions
//  Force predictability.
//      ? Prefer unchecked exceptions for programming errors
//  Checked exceptions often lead to noisy code.
//  ? Include context-rich messages
//  So debugging production issues is easier.


//  Performance & Memory:
//  ? Measure before optimizing
//  Micro-optimizations often backfire.
//  ? Prefer simple algorithms with good complexity
//  O(n log n) beats everything.
//      ? Reuse objects where safe (e.g., in hot loops)
//  Avoid GC pressure.
//  ? Avoid reflection unless necessary
//  Slow & breaks compile-time safety.
//      ? Use caching intentionally & sparingly
//  Cache invalidation is one of the hardest problems.

//  Testing & Reliability
//  ? Design for testability
//      Inject dependencies, use mocks, avoid globals.
//      ? Make tests deterministic
//  Inject clock/random.
//  ? Write idempotent logic
//  Ensures correct retries.
//  ? Prefer pure functions where possible
//  Side effects complicate testing.
//      ? Use contract tests for critical API boundaries
//  Ensures stability across versions.

public class App {

}
