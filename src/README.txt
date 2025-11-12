Use of Lambda Expressions as Command Objects

In this lab, I used lambda expressions as command objects to control what action the visitor applies to each node.
Instead of writing a new class every time I wanted a different operation, I could pass a small lambda straight into the visitor.
The visitor then used that lambda to update each element. 
This made the behaviour easy to swap at runtime and kept the overall design cleaner and more flexible.


Use of Generics for Reusing Data Structures

I also made use of generics to reuse the same data structures with different types. 
By writing classes like Node<T> and Visitor<T>, the code could handle integers, colours, or any other value without needing separate versions of each class. 
Generics helped maintain type safety while still giving me a reusable structure that works in many situations, which reduced duplication and made the design easier to maintain. 
Generics let me write one reusable data structure that works with any type, so I didn’t have to create separate versions of the same class for different kinds of values.