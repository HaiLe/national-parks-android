package me.haile.nationalparks.utils

import me.haile.nationalparks.data.OperatingHour
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

    fun displayOperationHours (operatingHours: List<OperatingHour>) : String {
        if (operatingHours.isEmpty()) {
            return "No hours available. Contact the park for more information."
        }
        val sb = StringBuilder()
        val hours = operatingHours[0]

        sb.append(hours.description + "\n\n")
        sb.append("Monday: ${hours.standardHours.monday}\n")
        sb.append("Tuesday: ${hours.standardHours.tuesday}\n")
        sb.append("Wednesday: ${hours.standardHours.wednesday}\n")
        sb.append("Thursday: ${hours.standardHours.thursday}\n")
        sb.append("Friday: ${hours.standardHours.friday}\n")
        sb.append("Saturday: ${hours.standardHours.saturday}\n")
        sb.append("Sunday: ${hours.standardHours.sunday}\n")
        
        return sb.toString()
    }
}