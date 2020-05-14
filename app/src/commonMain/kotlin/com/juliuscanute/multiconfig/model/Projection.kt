package com.juliuscanute.multiconfig.model

class Projection(
    userMax: Int,
    private val userMin: Int,
    progressMax: Int = 100,
    private val progressMin: Int = 0
) {
    private var slope = ((progressMax - progressMin).toFloat() / (userMax - userMin).toFloat())
    private var estimatedProgress = 0
    private var estimatedUserValue = 0
    var userValue: Int = 0
        set(value) {
            estimatedProgress = (progressMin + slope * (value - userMin)).toInt()
            estimatedUserValue = value
            field = value
        }
        get() = estimatedUserValue
    var progressValue: Int = 0
        set(value) {
            estimatedUserValue = (userMin + (value - progressMin) / slope).toInt()
            estimatedProgress = value
            field = value
        }
        get() = estimatedProgress
}