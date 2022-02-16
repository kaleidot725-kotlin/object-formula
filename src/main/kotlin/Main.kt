import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

data class PersonNameDataHolder(val name: String)

data class PersonAgeDataHolder(val age: Int)

interface Person : Serializable {
    val name: String
    val age: Int
}

private fun writePersonToFile(fileName: String, person: Person) {
    val file = FileOutputStream(fileName)
    ObjectOutputStream(file).use { outputStream ->
        outputStream.writeObject(person)
    }
}

fun main() {
    val personAgeDataHolder = PersonAgeDataHolder(100)
    val personNameDataHolder = PersonNameDataHolder("PERSON_NAME")

    val person = object : Person {
        override val age: Int = personAgeDataHolder.age
        override val name: String = personNameDataHolder.name
    }

    try {
        writePersonToFile(fileName = "person.txt", person = person)
        println("Person Serialization Success")
    } catch (e: Exception) {
        println("Person Serialization Error: $e")
    }
}
