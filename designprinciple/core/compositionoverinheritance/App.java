package designprinciple.core.compositionoverinheritance;

//  Composition over Inheritance Principle:
//  Prefer composing behavior using smaller components instead of relying on deep class inheritance.
//  In other words: Build objects by combining simple capabilities, Don’t hard-wire behavior using rigid class hierarchies.

//  Why FAANG Engineers prefer Composition over Inheritance:
//  Inheritance creates: Rigid structures, Deep chains, Fragile coupling, Ripple effects
//  Violations of LSP (Liskov Substitution), Hard-to-change behavior in large teams
//  Cross-team conflicts over shared base classes

//  Composition creates: Replaceable components, Behavior injection, Smaller surface area, Better isolation
//  Ability to test parts independently, Swappable implementations, New features without modifying old code.

//  Inheritance (Tight, hard-coded):
//  Penguin ? Bird ? Animal
//  Behavior from base classes automatically flows down, Penguin suddenly inherits fly()

//  Composition (Flexible, behavior assembled):
//  Penguin {
//    Movement movement = new WalkMovement();
//  }
//  Eagle {
//    Movement movement = new FlyMovement();
//  }
//  Behavior chosen, not inherited. Swappable and safe.

//  When Inheritance Is Acceptable:  FAANG engineers only use inheritance when:
//  Is-a relationship is mathematically true.
//  The hierarchy will not change frequently.
//  The base class provides a stable, core abstraction.
//  Behavior is safe to generalize to all children.
//  Examples:
//  List ? ArrayList / LinkedList

public class App {

}
