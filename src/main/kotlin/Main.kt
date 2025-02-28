import kotlin.math.max

const val ERROR_TYPE = -1
const val ERROR_LIMIT = -2

fun main() {
    println(comission("Mastercard", 150_000, 0))
    println(comission("Visa", 50_000, 100_000))
    println(comission(amount = 150_001, previousAmount = 100_000))
    println(comission("Проверка", 0))
}

fun comission(typeCard: String = "Мир", amount: Int, previousAmount: Int = 0): Int {
    if (amount > 150_000 || amount + previousAmount > 600_000) return ERROR_LIMIT
    return when (typeCard) {
        "Mastercard" -> if (amount + previousAmount <= 75_000) 0
        else if (previousAmount > 75_000) (0.006 * amount).toInt() + 20
        else (0.006 * (amount + previousAmount - 75_000)).toInt() + 20

        "Visa" -> max(35, (0.0075 * amount).toInt())
        "Мир" -> 0
        else -> ERROR_TYPE
    }
}