package com.divya.rx.datalayer.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class BaseResponse<T> : Serializable {

    @SerializedName("_meta")
    internal var metaInfo: MetaInfo? = null

    @SerializedName("result")
    internal var result: T? = null


}

internal class MetaInfo {

    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("code")
    var code: Int = 0

    @SerializedName("totalCount")
    var totalCount: Int = 0

    @SerializedName("pageCount")
    var pageCount: Int = 0

    @SerializedName("currentPage")
    var currentPage: Int = 0

    @SerializedName("perPage")
    var perPage: Int = 0

}