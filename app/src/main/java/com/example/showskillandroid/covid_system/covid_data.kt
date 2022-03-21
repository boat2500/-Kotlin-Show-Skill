package com.example.showskillandroid.covid_system

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CovidData {
    @SerializedName("txn_date")
    @Expose
    var txnDate: String? = null

    @SerializedName("new_case")
    @Expose
    var newCase: Int? = null

    @SerializedName("total_case")
    @Expose
    var totalCase: Int? = null

    @SerializedName("new_case_excludeabroad")
    @Expose
    var newCaseExcludeabroad: Int? = null

    @SerializedName("total_case_excludeabroad")
    @Expose
    var totalCaseExcludeabroad: Int? = null

    @SerializedName("new_death")
    @Expose
    var newDeath: Int? = null

    @SerializedName("total_death")
    @Expose
    var totalDeath: Int? = null

    @SerializedName("new_recovered")
    @Expose
    var newRecovered: Int? = null

    @SerializedName("total_recovered")
    @Expose
    var totalRecovered: Int? = null

    @SerializedName("update_date")
    @Expose
    var updateDate: String? = null
}