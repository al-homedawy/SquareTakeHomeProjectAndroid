package com.example.squaretakehomeproject.models

import com.google.gson.annotations.SerializedName

data class EmployeeModel(
    @SerializedName("uuid")
    val uuid: String?,

    @SerializedName("full_name")
    val fullName: String?,

    @SerializedName("phone_number")
    val phoneNumber: String?,

    @SerializedName("email_address")
    val emailAddress: String?,

    @SerializedName("biography")
    val biography: String?,

    @SerializedName("photo_url_small")
    val photoUrlSmall: String?,

    @SerializedName("photo_url_large")
    val photoUrlLarge: String?,

    @SerializedName("team")
    val team: EmployeeTeam?,

    @SerializedName("employee_type")
    val type: EmployeeType?,
) {
    companion object {
        fun isValid(employee: EmployeeModel): Boolean = with(employee) {
            uuid != null && fullName != null && emailAddress != null && team != null && type != null
        }
    }
}

enum class EmployeeType(
    val value: String
) {
    @SerializedName("FULL_TIME")
    FULL_TIME("Full Time"),

    @SerializedName("PART_TIME")
    PART_TIME("Part Time"),

    @SerializedName("CONTRACTOR")
    CONTRACTOR("Contractor")
}

enum class EmployeeTeam(
    val value: String
) {
    @SerializedName("Appointments")
    APPOINTMENTS("Appointments Team"),

    @SerializedName("Core")
    CORE("Core Team"),

    @SerializedName("Cash")
    CASH("Cash Team"),

    @SerializedName("Hardware")
    HARDWARE("Hardware Team"),

    @SerializedName("Invoices")
    INVOICES("Invoices Team"),

    @SerializedName("Point of Sale")
    POINT_OF_SALE("Point of Sale Team"),

    @SerializedName("Point of Sale Platform")
    POINT_OF_SALE_PLATFORM("Point of Sale Platform Team"),

    @SerializedName("Retail")
    RETAIL("Retail Team"),

    @SerializedName("Restaurants")
    RESTAURANTS("Restaurants Team"),

    @SerializedName("Public Web & Marketing")
    WEB_AND_MARKETING("Public Web & Marketing Team")
}