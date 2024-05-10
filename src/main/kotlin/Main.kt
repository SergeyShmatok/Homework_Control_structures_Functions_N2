package ru.netology

fun main() {

    calculateTransfer("Mastercard", 0, 150_000)

}

fun calculateTransfer(cardType: String = "Mir", amountPreviousTransfers: Int = 0, amountTransfer: Int) {

    // лимиты на суммы перевода за сутки и за месяц
    val transferLimitDay = 150_000
    val transferLimitMonth = 600_000

    val solution = when (cardType) {
        "Mastercard" -> if (amountPreviousTransfers == 0 && amountTransfer > 75_000) // если переводов ещё не было
            (amountTransfer - 75_000) * 0.006 + 20 // с первых 75 000 комиссия не взимается
        else if (amountPreviousTransfers > 75_000) // месячный лимит был превышен ранее
            amountTransfer * 0.006 + 20 // комиссия идёт в полном объёме с переводимых средств
        else if (amountPreviousTransfers + amountTransfer > 75_000) // если лимит ещё не превышен, но переводы ранее были
            (amountPreviousTransfers + amountTransfer - 75_000) * 0.006 + 20
        else 0.0

        "Visa" -> if (amountTransfer * 0.0075 > 35) amountTransfer * 0.0075 else 35
        else -> 0.0
    }

    if (amountTransfer > transferLimitDay ||
        amountTransfer + amountPreviousTransfers > transferLimitMonth
    ) println("\nОшибка: превышен лимит перевода средств за день/месяц.")
    else println("\nКомиссия составит: ${solution.toInt()} руб.")

}
