package com.okala.repository.utils

data class Resource<out T>(val status: Statuse,val data: T?,val error: Throwable?) {

    companion object{
        fun <T> success(data: T?):Resource<T> {
            return Resource(status = Statuse.SUCCESS,
                data = data,
                error = null)
        }

        fun <T>error(error:Throwable,data: T?):Resource<T> {
            return Resource(status = Statuse.ERROR,
                data = data,
                error = error)
        }

        fun <T>loading(data: T?): Resource<T> {
            return Resource(status = Statuse.LOADING,
                data = data,
                error = null)
        }
    }
}