# Cafe Simulator

A JavaFX cafe management game written in Java. Serve customers, manage ingredient inventory, and match drink recipes before their patience runs out!

## Tech Stack
- Java
- JavaFX
- Maven
- Object-Oriented Programming (Inheritance, Encapsulation)
- Java Collections

## Key Features
- JavaFX GUI with event-driven gameplay mechanics
- Inventory management system tracking ingredient usage and refills
- Order validation logic using Java collections to match recipes
- Multiple customer behavior types implemented using inheritance
- Separation of game logic, UI, and data models for modular design

## Gameplay

Customers walk in and place drink orders. Click ingredients in the correct combination to build their drink and serve it before their patience runs out. Mess up the recipe or take too long and they leave angry.

- 4 drink types: Latte, Coffee, Matcha Latte, Cold Brew
- 3 customer types: Regular, Angry (patience drains faster), Extra Sugar (requests 1–2 extra sugars randomly)
- Ingredients deplete with use and must be refilled
- Each customer has a patience bar that ticks down every second

## Play Here
https://anjuweecs.itch.io/beary-cozy-cafe


## Build from Source

**Prerequisites:** Java 17+, JavaFX 21, Maven
```bash
git clone https://github.com/anjunthomas/cafe-simulator
cd cafe-simulator
mvn javafx:run
```

## License

MIT
