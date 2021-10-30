package com.yoti.android.cryptocurrencychallenge.utils

object ErrorHandler {

//    fun <T> parseResponse(errorResponse: Response<T>): T {
//        val responseBody: ResponseBody = errorResponse.errorBody()!!
//        val gson = Gson()
//        val type: Type = object : TypeToken<BaseResponse<*>?>() {}.type
//        return gson.fromJson(responseBody.charStream(), type)
//    }
//
//}
//
//fun Fragment.handleApiError(
//    throwable: Throwable,
//    retry: (() -> Unit)? = null
//) {
//
//    when(throwable) {
//        is HttpException -> {
//            val response = throwable.response()
//            var message = ""
//            response?.let {
//                val baseResponse = ErrorHandler.parseResponse(response) as BaseResponse<*>
//                message = baseResponse.error
//            }
//        }
//        is UnknownHostException -> showSnackBar("Please check your internet settings", Snackbar.LENGTH_INDEFINITE, "OK")
//        is SocketTimeoutException -> showSnackBar("Time Out. Please consider retrying", Snackbar.LENGTH_INDEFINITE,"OK")
//        else -> showSnackBar(throwable.message ?: throwable.localizedMessage, Snackbar.LENGTH_INDEFINITE)
//    }
}
