package me.haile.nationalparks.data

data class Contacts(
    val phoneNumbers: List<PhoneNumber>,
    val emailAddresses: List<EmailAddress>
)

data class PhoneNumber(
    val phoneNumber: String,
    val description: String,
    val extension: String,
    val type: String
)

data class EmailAddress(
    val description: String,
    val emailAddress: String
)

data class OperatingHour(
    val exceptions: List<Exception>,
    val description: String,
    val standardHours: StandardHours,
    val name: String
)

data class Exception(
    val exceptionHours: Map<String, String>, // Empty object, replace with proper type if needed
    val startDate: String,
    val name: String,
    val endDate: String
)

data class StandardHours(
    val wednesday: String,
    val monday: String,
    val thursday: String,
    val sunday: String,
    val tuesday: String,
    val friday: String,
    val saturday: String
)

data class Address(
    val postalCode: String,
    val city: String,
    val stateCode: String,
    val countryCode: String,
    val provinceTerritoryCode: String,
    val line1: String,
    val type: String,
    val line3: String,
    val line2: String
)

data class Image(
    val credit: String,
    val title: String,
    val altText: String,
    val caption: String,
    val url: String
)