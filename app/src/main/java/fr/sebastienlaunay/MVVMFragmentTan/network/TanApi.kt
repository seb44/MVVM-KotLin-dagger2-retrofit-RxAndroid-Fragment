package fr.sebastienlaunay.MVVMFragmentTan.network

import fr.sebastienlaunay.MVVMFragmentTan.model.TanArret
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface TanApi {

    @GET("/arrets")
    fun getTanStops(): Observable<List<TanArret>>

    @GET("/arrets")
    fun getTanStops2(): Single<List<TanArret>>
}