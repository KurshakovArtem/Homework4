import org.junit.Test
import kotlin.test.assertEquals

class MainKtTest {

    @Test
    fun comissionTestMastercard() {
        val typeCard = "Mastercard"
        val amount = 150_000
        val amount2 = 70_000
        val previousAmount = 80_000

        val resultExceedingDailyLimit = comission(typeCard, amount)
        val resultExceedingMonthlyLimit = comission(typeCard, amount2, previousAmount)
        val resultBelowLimit = comission(typeCard, amount2)

        assertEquals(470, resultExceedingDailyLimit)
        assertEquals(440, resultExceedingMonthlyLimit)
        assertEquals(0, resultBelowLimit)
    }

    @Test
    fun comissionTestVisa() {
        val typeCard = "Visa"
        val amount1 = 4_000
        val amount2 = 5_000

        val resultBelowMinimum = comission(typeCard, amount1)
        val resultAboveMinimum = comission(typeCard, amount2)

        assertEquals(35, resultBelowMinimum)
        assertEquals(37, resultAboveMinimum)
    }

    @Test
    fun comissionTestMir(){
        val amount = 1_000

        val resultMir = comission(amount = amount)

        assertEquals(0, resultMir)
    }

    @Test
    fun comissionTestErrorLimit(){
        val amount = 150_001
        val previousAmount = 600_001

        val resultErrorDailyLimit = comission(amount = amount)
        val resultErrorMonthlyLimit = comission(amount = amount - 1, previousAmount = previousAmount)

        assertEquals(-1, resultErrorDailyLimit)
        assertEquals(-2, resultErrorMonthlyLimit)
    }

    @Test
    fun comissionTestErrorType(){
        val typeCard = "Test"
        val amount = 10

        val result = comission(typeCard, amount)

        assertEquals(-1, result)
    }
}