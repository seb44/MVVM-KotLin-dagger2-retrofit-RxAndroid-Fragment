package fr.sebastienlaunay.kotlinfragment.network

import fr.sebastienlaunay.kotlinfragment.model.TanArret
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface TanApi {

    @GET("/arrets")
    fun getTanStops(): Observable<List<TanArret>>

    @GET("/arrets")
    fun getTanStops2(): Single<List<TanArret>>
}