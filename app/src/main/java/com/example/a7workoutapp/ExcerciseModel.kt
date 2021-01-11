package com.example.a7workoutapp

class ExcerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isCompleted: Boolean,
    private var isSelected: Boolean
) {
    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getImage(): Int {
        return image
    }

    fun setIsselected(isSelected: Boolean) {
        this.isSelected = isSelected
    }

    fun setIsCompleted(isCompleted: Boolean){
        this.isCompleted=isCompleted
    }

    fun getIsCompleted(): Boolean {
        return isCompleted
    }

    fun getIsSelected(): Boolean {
        return isSelected
    }

}