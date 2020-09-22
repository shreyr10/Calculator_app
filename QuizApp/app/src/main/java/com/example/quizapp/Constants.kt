package com.example.quizapp

object Constants {

    const val USER_NAME: String = "name"
    const val CORRECT_ANS: String = "aaa"
    const val TOTAL_QUES: String = "cdc"

    fun getQuestions() : ArrayList<Questions> {

        val list = ArrayList<Questions>()

        val que1 = Questions(1,"What Country does this flag belong to?",R.drawable.ic_flag_of_argentina,"Argentina","Australia","Armenia","Austria",1)
        list.add(que1)

        val que2 = Questions(2, "What country does this flag belong to?", R.drawable.ic_flag_of_australia, "Angola", "Austria", "Australia", "Armenia", 3)
        list.add(que2)

        val que3 = Questions(3, "What country does this flag belong to?", R.drawable.ic_flag_of_brazil, "Belarus", "Belize", "Brunei", "Brazil", 4)
        list.add(que3)

        val que4 = Questions(4, "What country does this flag belong to?", R.drawable.ic_flag_of_belgium, "Bahamas", "Belgium", "Barbados", "Belize", 2)
        list.add(que4)

        val que5 = Questions(5, "What country does this flag belong to?", R.drawable.ic_flag_of_fiji, "Gabon", "France", "Fiji", "Finland", 3)
        list.add(que5)

        val que6 = Questions(6, "What country does this flag belong to?", R.drawable.ic_flag_of_germany, "Germany", "Georgia", "Greece", "none of these", 1)
        list.add(que6)

        val que7 = Questions(7, "What country does this flag belong to?", R.drawable.ic_flag_of_denmark, "Dominica", "Egypt", "Denmark", "Ethiopia", 3)
        list.add(que7)

        val que8 = Questions(8, "What country does this flag belong to?", R.drawable.ic_flag_of_india, "Ireland", "Iran", "Hungary", "India", 4)
        list.add(que8)

        val que9 = Questions(9, "What country does this flag belong to?", R.drawable.ic_flag_of_new_zealand, "Australia", "New Zealand", "Tuvalu", "United States of America", 2)
        list.add(que9)

        val que10 = Questions(10, "What country does this flag belong to?", R.drawable.ic_flag_of_kuwait, "Kuwait", "Jordan", "Sudan", "Palestine", 1)
        list.add(que10)

        return list
    }
}