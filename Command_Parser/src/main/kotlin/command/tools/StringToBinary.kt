package command.tools

class StringToBinary {
    // utility function
    fun strToBinary(s: String): String {
        val n = s.length
        var output = ""
        for (i in 0 until n) {
            // convert each char to
            // ASCII value
            var value = Integer.valueOf(s[i].code)

            // Convert an ASCII value to binary
            var bin = ""
            while (value > 0) {
                bin += if (value % 2 == 1) {
                    '1'
                } else '0'
                value /= 2
            }
            bin = reverse(bin)
            output += "$bin "
        }
        return output
    }

    private fun reverse(input: String): String {
        val a = input.toCharArray()
        var r: Int = a.size - 1
        var l = 0
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
