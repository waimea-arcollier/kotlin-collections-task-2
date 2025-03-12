 /**
 * Kotlin Collections Task 2 - Monkeys in Cages
 *
 *       OOOOO  OOO   OOO
 *          O  O   O O   O
 *         O   O   O O   O
 *       OOOOO  OOO   OOO
 * +-------------+-------------+
 * |    __v__    |             |
 * |   ( o o )   |    __v__    |
 * |    (---)    |   ( o o )   |
 * |    __|__    |    (---)    |
 * |   /|. .|\   |      |      |
 * +-------------+-------------+
 *
 * The monkeys have been rounded up and transported
 * to the Zoo. The zookeepers need some help placing
 * the monkeys into cages. Can you help them?
 */


/**
 * Constant vales used to define some key values
 * which can then be used throughout the code...
 */
const val NUMCAGES = 8      // The total number of cages
const val EMPTY = "---"     // Represents an empty cage


/**
 * Program entry point
 */
fun main() {
    //-------------------------------------------------
    println("Setting up the cages...")

    val cages = setupCages()

    listAllCages(cages)
    println()

    //-------------------------------------------------
    println("Placing monkeys into cages...")

    placeMonkeyInCage(cages, 1, "Kevin")
    placeMonkeyInCage(cages, 8, "Sally")
    placeMonkeyInCage(cages, 4, "Pam")
    placeMonkeyInCage(cages, 3, "Samson")
    placeMonkeyInCage(cages, 5, "Frank")
    placeMonkeyInCage(cages, 6, "Jim")

    println()

    listAllCages(cages)
    println()

    listAllMonkeys(cages)
    println("Monkeys: ${monkeyCount(cages)}")
    println()

    listEmptyCages(cages)
    println("Empty: ${emptyCount(cages)}")
    println()

    listAllMonkeysAndCages(cages)
    println()

    showMonkeyCages(cages)
    println()

    check(monkeyCount(cages) == 6)
    check(emptyCount(cages) == 2)

    //-------------------------------------------------
    println("Clearing out some monkeys...")

    clearCage(cages, 5)
    println()

    showMonkeyCages(cages)
    println()

    check(monkeyCount(cages) == 5)
    check(emptyCount(cages) == 3)

    //-------------------------------------------------
    println("Moving some monkeys around...")

    swapCages(cages, 1, 8)
    println()

    showMonkeyCages(cages)
    println()

    //--------------------

    swapCages(cages, 5, 8)
    println()

    showMonkeyCages(cages)
    println()

    check(monkeyCount(cages) == 5)
    check(emptyCount(cages) == 3)

}

/**
 * Creates and returns a Mutable List, size NUMCAGES,
 * populated with strings representing empty cages
 */
fun setupCages(): MutableList<String> {
    val cageList = mutableListOf<String>()
    for (i in 1..NUMCAGES) cageList.add(EMPTY)
    return cageList
}


/**
 * Put a given monkey into the specified cage number (1...MAX)
 */
fun placeMonkeyInCage(cageList: MutableList<String>, cageNum: Int, name: String) {
    println("+++ Putting $name into cage $cageNum")
    cageList[cageNum - 1] = name
}


/**
 * Display a list of all cages in the given list in the format:
 *
 * CAGES
 * - Cage 1: Kevin
 * - Cage 2: ---
 * - Cage 3: Samson
 * - Cage 4: Pam
 * - Etc.
 */
fun listAllCages(cageList: List<String>) {
    println("CAGES")
    for ((i, cage) in cageList.withIndex()) {
        println("${i + 1}: $cage")
    }
}


/**
 * Display a list of all monkeys found in the given cage list:
 *
 * MONKEYS
 * - Kevin
 * - Samson
 * - Pam
 * - Etc.
 */
fun listAllMonkeys(cageList: List<String>) {
    println("MONKEYS")
    for (monkeys in cageList) if (monkeys == EMPTY){
        print("")
    }
    for (monkeys in cageList) if (monkeys != EMPTY){
        println(monkeys)
    }
}


/**
 * Display a list of all empty cages in the given cage list:
 *
 * EMPTY CAGES
 * - Cage 2
 * - Cage 7
 * - Etc.
 */
fun listEmptyCages(cageList: List<String>) {
    println("EMPTY CAGES")
    for ((i, cage) in cageList.withIndex()) if (cage == EMPTY){
        println("Cage ${i+1}")
    }
}


/**
 * Display a full list of all monkeys and the cages they are in.
 * The names and cage numbers should line up in neat columns:
 *
 * MONKEYS & CAGES
 * - Kevin  (Cage 1)
 * - Samson (Cage 3)
 * - Pam    (Cage 4)
 * - Etc.
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun listAllMonkeysAndCages(cageList: List<String>) {
    println("MONKEYS & CAGES")
    // Loop getting index and values starting at one
    for ((i, name) in cageList.withIndex()) if (cageList[i] != EMPTY){
        println("- ${name.padEnd(lengthOfLongestName(cageList))} (Cage ${i+1})")
    }

}


/**
 * Returns the number of monkeys found in the given cage list
 */
fun monkeyCount(cageList: List<String>): Int {
    var monkeys: Int = 0
    for ((i, name) in cageList.withIndex()) if (name != EMPTY){
        monkeys++
    }
    return monkeys
}


/**
 * Returns the number of cages that are empty in the given cage list
 */
fun emptyCount(cageList: List<String>): Int {
    var cages: Int = 0
    for ((i, name) in cageList.withIndex()) if (name == EMPTY){
        cages++
    }
    return cages
}

 /**
  * Returns longest monkey names number of characters
  */
 fun lengthOfLongestName(cageList: List<String>): Int {
     // Loop through the list and find the longest name
     var longestName = ""
     var longestNameLength = 0
     for (name in cageList) {
         if (name.length > longestName.length) {
             longestName = name
         }
         longestNameLength = longestName.length
     }
     return longestNameLength
 }

/**
 * Show all cages from the given list, formatted as a horizontal table:
 *
 * +--------+--------+--------+--------+----
 * | Cage 1 | Cage 2 | Cage 3 | Cage 4 | Etc.
 * +--------+--------+--------+--------+----
 * | Kevin  | ---    | Samson | Pam    | Etc.
 * +--------+--------+--------+--------+----
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun showMonkeyCages(cageList: List<String>) {
    var cageAsciiLength = 4 + lengthOfLongestName(cageList)
    var cageNum = cageList.size
    for ((i) in cageList.withIndex()) {
        while (cageNum > 0) {
            print("+")
            print("-".repeat(cageAsciiLength))
            cageNum --
        }
    }
    print("+")
    println("")
    for ((i) in cageList.withIndex()) {
        print("| Cage ${(i+1).toString().padEnd(lengthOfLongestName(cageList)-3)} ")
    }
    print("|")
    println("")
    var cageNum2 = cageList.size
    for ((i) in cageList.withIndex()) {
        while (cageNum2 > 0) {
            print("+")
            print("-".repeat(cageAsciiLength))
            cageNum2--
        }
    }
    print("+")
    println("")
    for (name in cageList) {
        print("| ${name.padEnd(lengthOfLongestName(cageList)+3)}")
    }
    print("|")
    println("")
    var cageNum3 = cageList.size
    for ((i) in cageList.withIndex()) {
        while (cageNum3 > 0) {
            print("+")
            print("-".repeat(cageAsciiLength))
            cageNum3 --
        }
    }
}


/**
 * Make a given cage empty (if a monkey was in it, it's gone now!)
 */
fun clearCage(cageList: MutableList<String>, cageNum: Int) {
    println("--- Clearing cage $cageNum")
    println("--- ∙∙∙")
    println("--- Cage $cageNum cleared")
    cageList[cageNum - 1] = EMPTY
}


/**
 * Swap the contents of two given cages.
 *
 * If one was full and the other empty, then the monkey just swaps
 * into the empty cage.
 */
fun swapCages(cageList: MutableList<String>, cageNum1: Int, cageNum2: Int) {
    println("<-> Swapping cages $cageNum1 and $cageNum2")
    var firstMonkey =cageList[cageNum1 - 1]
    var secondMonkey = cageList[cageNum2 - 1]
    cageList[cageNum2 - 1] = firstMonkey
    cageList[cageNum1 - 1] = secondMonkey

}



