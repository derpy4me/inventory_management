import kotlin.random.Random

data class Dimensions(val width: Int, val height: Int)

val colors = listOf<String>("Orange", "Red", "Green", "Yellow", "Purple")

open class PurchasableItem() {
    val dimensions: Dimensions = Dimensions(Random.nextInt(2, 10), Random.nextInt(2, 5))
    val price: Int = Random.nextInt(20, 250)
    open val name: String = ""
}

class Table() : PurchasableItem() {
    private val color: String = colors[Random.nextInt(colors.size)]
    override val name: String = "$color Table"
    val area: Int get() = dimensions.width * dimensions.height
}

class Dresser() : PurchasableItem() {
    val numDrawers: Int = Random.nextInt(1, 8)
    private val color: String = colors[Random.nextInt(colors.size)]
    override val name: String = "$color Dresser"
    val area: Int get() = dimensions.width * dimensions.height

}

class User(private val _name: String, private val _age: Int) {
    var name: String = _name
    var age: Int = _age
    var money: Int = 500
    var purchasedItems = mutableListOf<PurchasableItem>()

    fun buyItem(item: PurchasableItem) {
        if (item.price > money) {
            println("Not enough money")
            println("Need: ${item.price - this.money}")
            return
        }
        purchasedItems.add(item)
        this.money -= item.price
        println("Purchased ${item.name} for ${item.price}")
    }

    fun sellItem(value: Int) {
        if (purchasedItems.isEmpty()) {
            println("No items to sell")
            return
        }
        val item = purchasedItems[value]
        this.money += item.price
        purchasedItems.removeAt(value)
        println("${item.name} sold for ${item.price}")
    }

    fun showInventory() {
        if (purchasedItems.isEmpty()) {
            println("Money: ${this.money}")
            println("Nothing to show")
            return
        }
        println("Inventory:")
        println("Money: ${this.money}")
        purchasedItems.forEachIndexed { index, item -> println("$index: ${item.name}") }
        println("-------")
    }

}