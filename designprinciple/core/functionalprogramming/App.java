package designprinciple.core.functionalprogramming;

//  Functional Programming Principle:

//  Functional programming is not about using a “functional language.”
//  It’s about applying mathematical function properties to improve reliability, predictability, testability, and scalability.

//  FR Concepts:

//  Immutable Data: State is never changed — instead, new copies are created.
//  Eliminates race conditions
//  Makes concurrency predictable
//  Enables lock-free parallelism
//  Makes debugging dramatically easier
//  Enables safe caching & memoization

//  Pure Functions:  A function that: given same input ? always returns same output.
//  Pure functions scale horizontally without coordination, Very easy to test, Enables streaming processing (Kafka, Flink).

//  First-Class & Higher-Order Functions:
//  Functions can be passed as data.
//  Enables: map/filter/reduce on distributed data, strategy pattern without classes
//  cleaner business logic, parallel processing pipelines

//  Function Composition:
//  Small pure functions ? composed into bigger ones.
//  This allows: modular pipelines, reusable transformations, predictable behavior

//  Declarative Instead of Imperative
//  Declarative: describe what you want
//  Imperative: write steps manually
//  Declarative code says:
//      “Filter numbers > 10”
//  Imperative code says:
//      “Loop from i = 0 … if > 10 … push”
//  Declarative ? easier to parallelize ? easier to optimize ? less bug-prone.

public class App {

}
