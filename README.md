# DailyThought 💭

A simple and intuitive Kotlin application to store and manage your daily thoughts with full CRUD (Create, Read, Update, Delete) operations.

## 📖 Description

DailyThought is a personal journaling application built with pure Kotlin that allows users to capture, organize, and reflect on their daily thoughts and experiences. Whether you want to jot down quick notes, write detailed reflections, or track your mood and ideas over time, DailyThought provides a clean and user-friendly interface for all your journaling needs.

## ✨ Features

- **Create Thoughts**: Add new daily thoughts with timestamps
- **View Thoughts**: Browse through your collection of thoughts
- **Edit Thoughts**: Update and modify existing entries
- **Delete Thoughts**: Remove thoughts you no longer need
- **Search & Filter**: Find specific thoughts quickly
- **Date Organization**: Thoughts organized by date for easy navigation
- **Local Storage**: All data stored locally for privacy
- **Clean Architecture**: Built with modern Kotlin practices

## 🚀 Getting Started

### Prerequisites

Before running this application, make sure you have the following installed:
- [JDK 8 or higher](https://www.oracle.com/java/technologies/downloads/)
- [Kotlin](https://kotlinlang.org/docs/command-line.html) (if running from command line)
- An IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Android Studio](https://developer.android.com/studio) (recommended)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/aayushmak/DailyThought.git
   cd DailyThought
   ```

2. **Open in your IDE**
    - Open the project in IntelliJ IDEA or Android Studio
    - Wait for the project to sync and dependencies to download

3. **Build the project**
   ```bash
   # Using Gradle wrapper (if available)
   ./gradlew build
   
   # Or compile directly with kotlinc
   kotlinc src/**/*.kt -include-runtime -d DailyThought.jar
   ```

4. **Run the application**
   ```bash
   # If using JAR
   java -jar DailyThought.jar
   
   # Or run directly from IDE
   # Right-click on main function and select "Run"
   ```

## 🛠️ Built With

- **Language**: Kotlin (Pure Kotlin, no Android dependencies)
- **Build Tool**: Gradle
- **Data Storage**: Local file storage / SQLite
- **Architecture**: Clean Architecture principles
- **Design Patterns**: Repository pattern, Observer pattern

## 📱 Usage

### Adding a New Thought
```kotlin
// Example usage
val thought = Thought(
    content = "Today was a great day for learning Kotlin!",
    timestamp = LocalDateTime.now(),
    tags = listOf("learning", "kotlin")
)
thoughtRepository.createThought(thought)
```

### Viewing Your Thoughts
- All thoughts are displayed in chronological order
- Use search functionality to find specific thoughts
- Filter by date range or tags

### Editing a Thought
```kotlin
// Update existing thought
val updatedThought = existingThought.copy(
    content = "Updated content",
    lastModified = LocalDateTime.now()
)
thoughtRepository.updateThought(updatedThought)
```

### Deleting a Thought
```kotlin
// Remove thought by ID
thoughtRepository.deleteThought(thoughtId)
```

## 🏗️ Project Structure

```
DailyThought/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── model/
│   │   │   │   └── Thought.kt
│   │   │   ├── repository/
│   │   │   │   ├── ThoughtRepository.kt
│   │   │   │   └── LocalThoughtRepository.kt
│   │   │   ├── service/
│   │   │   │   └── ThoughtService.kt
│   │   │   ├── ui/
│   │   │   │   ├── ThoughtManager.kt
│   │   │   │   └── ConsoleUI.kt
│   │   │   └── Main.kt
│   │   └── resources/
│   └── test/
│       └── kotlin/
│           └── ThoughtRepositoryTest.kt
├── build.gradle.kts
├── gradle.properties
└── README.md
```

## 📊 Data Model

```kotlin
data class Thought(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val lastModified: LocalDateTime = LocalDateTime.now(),
    val tags: List<String> = emptyList(),
    val mood: String? = null
)
```

## 🧪 Testing

Run the test suite with:
```bash
# Using Gradle
./gradlew test

# Or from IDE
# Right-click on test directory and select "Run All Tests"
```

## 🔧 Build Configuration

### Gradle Build Script (build.gradle.kts)
```kotlin
plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
}

application {
    mainClass.set("MainKt")
}
```

## 🎯 Core CRUD Operations

| Operation | Method | Description |
|-----------|--------|-------------|
| **Create** | `createThought(thought: Thought)` | Add a new thought |
| **Read** | `getAllThoughts()` | Get all thoughts |
| **Read** | `getThoughtById(id: String)` | Get specific thought |
| **Update** | `updateThought(thought: Thought)` | Update existing thought |
| **Delete** | `deleteThought(id: String)` | Remove thought |

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

### Steps to Contribute:
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Write tests for your changes
4. Ensure all tests pass (`./gradlew test`)
5. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
6. Push to the branch (`git push origin feature/AmazingFeature`)
7. Open a Pull Request

### Coding Standards
- Follow Kotlin coding conventions
- Write unit tests for new features
- Use meaningful variable and function names
- Add KDoc comments for public APIs

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Aayushma Kafle**
- GitHub: [@aayushmak](https://github.com/aayushmak)

## 🙏 Acknowledgments

- Thanks to all contributors who helped improve this project
- Inspired by the need for simple, effective personal journaling tools
- Built with ❤️ using Kotlin

## 📞 Support

If you have any questions or need help with setup, please feel free to:
- Open an issue on GitHub
- Contact the maintainer

## 🔮 Future Enhancements

- [ ] Export thoughts to different formats (JSON, CSV, PDF)
- [ ] Theme customization
- [ ] Backup and restore functionality
- [ ] Advanced search with regex support
- [ ] Mood tracking analytics
- [ ] Reminder notifications

---

**Happy Journaling with Kotlin! 📝✨**