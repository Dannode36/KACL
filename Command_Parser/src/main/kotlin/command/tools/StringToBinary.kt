package command.tools

class StringToBinary {
    // utility function
    fun strToBinary(s: String) {
        val n = s.length
        for (i in 0 until n) {
            // convert each char to
            // ASCII value
            var `val` = Integer.valueOf(s[i].code)

            // Convert ASCII value to binary
            var bin = ""
            while (`val` > 0) {
                bin += if (`val` % 2 == 1) {
                    '1'
                } else '0'
                `val` /= 2
            }
            bin = reverse(bin)
            println("$bin ")
        }
    }

    private fun reverse(input: String): String {
        val a = input.toCharArray()
        var r: Int = a.size - 1
        var l: Int = 0
        while (l < r) {

            // Swap values of l and r
            val temp = a[l]
            a[l] = a[r]
            a[r] = temp
            l++
            r--
        }
        return String(a)
    }
}