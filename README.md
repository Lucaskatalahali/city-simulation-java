# 🏙️ City Simulation (Java)

![Academic Project](https://img.shields.io/badge/Academic_Project-Sakarya_University-blue)

![Course](https://img.shields.io/badge/Course-Principles_of_Programming_Languages-blue)

> 🎓 Part of my **[Computer Engineering Academic Portfolio](https://github.com/Lucaskatalahali/computer-engineering-projects)**.

The project models a hierarchical administrative structure composed of **Cities**, **Districts**, **Neighborhoods**, and **People**, simulating population growth over multiple rounds. As cities grow, they dynamically split into new cities according to predefined rules while preserving their internal hierarchy.

---

## 🛠️ Technologies

- Java
- Eclipse IDE
- Java Faker

---

## 📌 Overview

The simulation starts from a sequence of two-digit numbers entered by the user.

Each number represents the (initial) population of a city.

Before the simulation begins, the input is automatically validated and adjusted to satisfy the simulation rules.

During each round:

- Population grows according to the city's current growth rate.
- New citizens are generated using **Java Faker**.
- Neighborhood, district and city populations are recalculated.
- Cities reaching a population of **1000 or more** are automatically divided into new cities.

After all rounds are completed, the user can inspect any city by entering its row and column coordinates.

---

## ✨ Features

- Hierarchical object-oriented city model.
- Abstract base class shared by administrative units.
- Dynamic population growth simulation.
- Recursive population updates throughout the hierarchy.
- Automatic city splitting based on population threshold.
- Three different city splitting strategies.
- Random generation of Turkish city names, districts, streets and citizens using Java Faker.
- Coordinate-based inspection of any city at the end of the simulation.

---

## 🏛️ Architecture

The project models the administrative hierarchy using composition and inheritance.

```text
Main
 │
 ▼
Game (Oyun)
 │
 ├── List<City>
 │
 ▼
Abstract Place (Yer)
 ├── City (Sehir)
 │     └── List<District>
 │            └── District (Ilce)
 │                   └── List<Neighborhood>
 │                          └── Neighborhood (Mahalle)
 │                                 └── List<Person>
 │                                        └── Person (Kisi)

Utility
└── Duzenleyici
```
---

## 🌆 Dynamic City Splitting

One of the project's core features is the automatic division of cities when they reach four-digit populations.

Depending on the city's structure, one of three strategies is applied:

### 1. District-based splitting

If a city contains multiple districts, half of its districts are transferred to a newly created city.

### 2. Neighborhood-based splitting

If the city contains only one district but multiple neighborhoods, neighborhoods are divided between the original and the new city.

### 3. Population-based splitting

If the city contains only one district and one neighborhood, citizens are divided directly, creating a completely new administrative hierarchy.

All newly created cities receive randomly generated Turkish names using **Java Faker**.

---

## ⚡ Performance Comparison

This project was also implemented in **C** as part of the same university assignment, preserving the same simulation rules and data structures.

The C implementation of this project can be found in **[City Simulation in C](https://github.com/your-username/city-simulation-c)**

Using the same input sequence and the same number of simulation rounds, the C implementation completed the execution significantly faster.

| Implementation | Language | Simulation Rounds | Approximate Execution Time |
|----------------|----------|-------------------|----------------------------|
| This repository | Java | 4 | ~15 seconds |
| C implementation | C | 4 | ~3 seconds |

> **Note**
>
> The comparison was performed using the same input sequence and simulation settings. The C version benefits from lower runtime overhead and manual memory management, resulting in noticeably faster execution.

---

## ▶️ How to Run

### Requirements

- Java JDK
- Eclipse IDE (or another Java IDE)
- Java Faker library

### Running in Eclipse

1. Clone this repository.
2. Open Eclipse.
3. Go to **File → Import → Existing Projects into Workspace**.
4. Select the project folder.
5. Ensure the required Java version and Java Faker dependency are available.
6. Run the `Main` class.

Alternatively, execute the compiled JAR located in the dist folder.

---

---

## 📄 Documentation

The `docs` folder contains:

- Project specification
- Development report

---

## 🎓 Academic Information

- **University:** Sakarya University
- **Department:** Computer Engineering
- **Course:** Principles of Programming Languages
- **Academic Year:** 2025–2026
- **Project Grade:** 100/100

---

## 📌 Notes

This repository preserves the original academic project exactly as it was submitted and evaluated.

For more academic projects, visit my **[Computer Engineering Academic Portfolio](https://github.com/Lucaskatalahali/computer-engineering-projects)**.
