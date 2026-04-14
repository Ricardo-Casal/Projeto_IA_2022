# Mummy Maze — Artificial Intelligence Project (2022)

An academic project that implements an AI agent to solve the **Mummy Maze** puzzle — a maze game where a hero must reach the exit without being caught by mummies and scorpions.

## The Game

The hero (`H`) navigates a 13x13 maze grid and must reach the exit (`S`) while avoiding enemies that move automatically after each hero action.

### Maze Elements

| Symbol | Description |
|--------|-------------|
| `H` | Hero (explorer) |
| `S` | Exit (goal) |
| `M` | White mummy |
| `V` | Red mummy |
| `E` | Scorpion |
| `C` | Key |
| `A` | Trap |
| `=` | Door (requires key) |
| `\|` | Vertical wall |
| `-` | Horizontal wall |

### Hero Actions
The hero can move in 4 directions (up, down, left, right) or **stay still**. After each hero move, all enemies also move.

## Search Algorithms Implemented

### Uninformed Search
- **BFS** — Breadth-First Search
- **DFS** — Depth-First Search
- **Depth-Limited Search**
- **Iterative Deepening Search**
- **Uniform Cost Search**

### Informed Search (heuristic-based)
- **Greedy Best-First Search**
- **A\*** — A-Star (f = g + h)
- **IDA\*** — Iterative Deepening A-Star
- **Beam Search**

## Heuristics

1. **Hero distance to exit** — Manhattan distance between the hero and the exit
2. **Enemy distance to hero** — Manhattan distance from the nearest enemy to the hero

## Project Structure

```
src/
├── agent/          # Base classes: Agent, State, Action, Problem, Heuristic, Solution
├── eightpuzzle/    # Mummy Maze logic: state, problem, agent, enemies, heuristics
├── searchmethods/  # Search algorithm implementations
├── gui/            # Graphical interface (Java Swing)
├── showSolution/   # Solution animation
├── utils/          # Helper structures (lists, priority queues)
└── sprites/        # Game images

Niveis/             # 22+ levels as .txt files
dist/
└── Search.jar      # Executable JAR
```

## How to Run

### Requirements
- Java 11 or higher

**macOS (with Homebrew):**
```bash
brew install openjdk
echo 'export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

### Run the JAR directly
```bash
java -jar dist/Search.jar
```

### Build and run in IntelliJ IDEA
1. Open the project (`File` → `Open` → project folder)
2. `File` → `Project Structure` → `SDKs` → add the installed JDK
3. Set `src/gui/Main.java` as the main class
4. Run with `Shift+F10`

## GUI Usage

1. Click **Open** to load a level from the `Niveis/` folder
2. Select a search algorithm from the dropdown
3. Select a heuristic (for informed search algorithms)
4. Click **Solve** to let the agent find a solution
5. Click **Show Solution** to watch the step-by-step animation

## Tech Stack

- **Java** with Swing (GUI)
- **IntelliJ IDEA** (development environment)
