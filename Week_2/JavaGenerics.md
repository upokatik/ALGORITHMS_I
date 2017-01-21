# Explain why Java prohibits generic array creation.

Generic types don't have associated type information at runtime because of **type erasure**.
At the same time, Java's arrays contain and need information about its component type at the runtime. Therefore, you have to know the actual type when you create an array. And as you don't know what the actual type it is at runtime, you are not allowed to create generic array.
