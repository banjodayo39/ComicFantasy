package com.example.comicfantasy.util

data class DataUIModel<T>(
    var isLoading: Boolean = false,
    var data: T? = null,
    var message: String? = "",
    var code: Int = 0,
    var error: String? = ""
){
    private var contentUsed: Boolean = false
    val contentIfNotUsed: DataUIModel<T>?
        get() {
            if(!contentUsed) {
                contentUsed = true
                return DataUIModel(isLoading, data, message, code, error)
            }
            return null
        }
}

data class ListUIModel<T>(
    var isLoading: Boolean = false,
    var list: List<T>? = null,
    var error: String? = ""
) {
    private var contentUsed: Boolean = false
    val contentIfNotUsed: ListUIModel<T>?
        get() {
            if(!contentUsed){
                contentUsed = true
                return ListUIModel(isLoading, list, error)
            }
            return null
        }
}

data class GenericUIModel(
    var isLoading: Boolean = false,
    var message: String? = "",
    var error: String? = "",
    var code: Int = 0
) {
    private var contentUsed: Boolean = false
    val contentIfNotUsed: GenericUIModel?
        get() {
            if(!contentUsed){
                contentUsed = true
                return GenericUIModel(isLoading, message, error, code)
            }
            return null
        }
}
