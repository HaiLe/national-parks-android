package me.haile.nationalparks.utils

import me.haile.nationalparks.utils.Constants.usStateAbbrev

object CommonUtils {
    fun getListOfStateNames (statesString: String) : String {
        val abbrevList = statesString.split(",")
        var listOfStates : String = ""
        abbrevList.forEach { abbrev ->
            val stateName = usStateAbbrev[abbrev.trim()] ?: ""
            if (stateName.isNotBlank()) {
                if (listOfStates.isNotBlank()) {
                    listOfStates += ", "
                }
                listOfStates += stateName
            }
        }
        return listOfStates
    }
}