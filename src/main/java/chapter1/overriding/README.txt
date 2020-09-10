1. The method in the child class must have the same signature as the method in the parent
class.
2. The method in the child class must be at least as accessible or more accessible than the
method in the parent class.
3. The method in the child class may not throw a checked exception that is new or
broader than the class of any exception thrown in the parent class method.
4. If the method returns a value, it must be the same or a subclass of the method in the
parent class, known as covariant return types.
