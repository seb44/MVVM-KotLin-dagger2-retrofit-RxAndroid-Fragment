package fr.sebastienlaunay.kotlinfragment.model

data class TanArret (
    var codeLieu: String,
    var libelle: String? = null,
    var distance: String? = null,
    var ligne: List<Ligne>? = null
    )