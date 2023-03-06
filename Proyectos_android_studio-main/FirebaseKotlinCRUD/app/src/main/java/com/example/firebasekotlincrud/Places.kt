package com.example.firebasekotlincrud

class Places {


    var id: String = ""
    var name : String=""
    var location: String=""
    var descripcion: String=""
    var type: String=""
    var url: String=""
    var status: String=""

    //Constructor vacío (requerido por Firebase)
    constructor()

    //Constructor con parámetros.
    constructor(

        id: String,
        name: String,
        location: String,
        descripcion: String,
        type: String,
        url: String,
        status:String
    ) {

        this.id = id
        this.name = name
        this.location = location
        this.descripcion = descripcion
        this.type = type
        this.url = url
        this.status = status
    }




}