“In production-grade FAANG systems, we avoid manual singletons.
Instead, we use DI frameworks (like Spring) that manage singletons as beans, allowing lazy loading,
testing, and controlled lifecycles.
However, for low-level utilities (e.g., constants or loggers), Enum Singleton is the cleanest, most robust option.”