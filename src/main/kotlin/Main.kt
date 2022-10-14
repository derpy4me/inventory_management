import java.lang.Exception
import kotlin.random.Random
import kotlin.system.exitProcess

fun main() {
    // Initiate the user
    val user = getUser()
    // Enter the main program loop to begin accepting input
    while (true) {
        showMenu()
        print(":: ")
        val input: Int
        try {
            input = readln().toInt()
        } catch (e: Exception) {
            println("Please Enter a Number")
            continue
        }
        when (input) {
            1 -> handlePurchase(user)
            2 -> handleSale(user)
            3 -> user.showInventory()
            4 -> {
                println("Thank you ${user.name} for using the app!")
                exitProcess(0)
            }

            else -> println("Please enter a valid menu item.")
        }
    }
}


fun getUser(): User {
    print("Enter name: ")
    val name = readln()
    print("Enter age: ")
    val age: Int
    try {
        age = readln().toInt()
    } catch (e: Exception) {
        println("Please enter a valid age")
        exitProcess(1)
    }

    return User(name, age)
}

fun showMenu() {
    println("1: Buy Item")
    println("2: Sell Item")
    println("3: Show Inventory")
    println("4: Exit")
}

fun showItemDetails(items: MutableList<PurchasableItem>) {
    print("Enter item value: ")
    var input: Int
    while (true) {
        try {
            input = readln().toInt()
        } catch (e: Exception) {
            println("Please enter a valid item value")
            continue
        }
        if (input > items.size) {
            println("Please enter a valid item value")
            continue
        }
        break
    }
    val item = items[input]
    println("Name: ${item.name}")
    println("Dimensions: ${item.dimensions}")
    println("Price: ${item.price}")
    print("Press enter to continue...")
    readln()
}

fun handlePurchase(user: User) {
    val purchasableItems = mutableListOf<PurchasableItem>()
    for (num in 1..5) {
        val randomNum = Random.nextInt(1, 3)
        if (randomNum == 1) {
            purchasableItems.add(Table())
        } else {
            purchasableItems.add(Dresser())
        }
    }


    var input: Int
    while (true) {
        purchasableItems.forEachIndexed { index, item -> println("$index: ${item.name}") }
        println("${purchasableItems.size}: Show Item Details")
        print(":: ")
        try {
            input = readln().toInt()
            if (input > purchasableItems.size) {
                println("Please enter a valid item")
                continue
            }
        } catch (e: Exception) {
            println("Please Enter a Number")
            continue
        }
        if (input == purchasableItems.size) {
            showItemDetails(purchasableItems)
            continue
        }
        break
    }
    user.buyItem(purchasableItems[input])
}

fun handleSale(user: User) {
    println("Sales:")
    user.purchasedItems.forEachIndexed { index, item -> println("$index: ${item.name}") }

    var input: Int
    while (true) {
        print(":: ")
        try {
            input = readln().toInt()
        } catch (e: Exception) {
            println("Please enter a valid item value")
            continue
        }
        if (input > user.purchasedItems.size) {
            println("Please enter a valid item value")
            continue
        }
        break
    }
    user.sellItem(input)
}