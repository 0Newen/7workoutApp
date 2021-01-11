package com.example.a7workoutapp

class Constants {
    companion object {
        fun defaultExcerciseLis(): ArrayList<ExcerciseModel> {
            val excerciseList = ArrayList<ExcerciseModel>()
            val excercise_one =
                ExcerciseModel(
                    1,
                    "Jumpin Jacks",
                    R.drawable.excercise_one,
                    false,
                    false
                )
            val excercise_two =
                ExcerciseModel(
                    2,
                    "Wall Sit",
                    R.drawable.excercise_two,
                    false,
                    false
                )

            val excercise_three =
                ExcerciseModel(
                    3,
                    "Push Up",
                    R.drawable.excercise_three,
                    false,
                    false
                )

            val excercise_four =
                ExcerciseModel(
                    4,
                    "Abdominal Crunch",
                    R.drawable.excercise_four,
                    false,
                    false
                )

            val excercise_five =
                ExcerciseModel(
                    5,
                    "Step Up onto Chair",
                    R.drawable.excercise_five,
                    false,
                    false
                )

            excerciseList.add(excercise_one)
            excerciseList.add(excercise_two)
            excerciseList.add(excercise_three)
            excerciseList.add(excercise_four)
            excerciseList.add(excercise_five)

            return excerciseList
        }
    }
}