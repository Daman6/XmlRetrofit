package com.example.xmlretrofit

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import retrofit2.http.Field

@Root(name = "Tv", strict = false)
data class Tv(
    @field:Attribute(name = "date", required = false)
    @param:Attribute(name = "date", required = false)
    var date: String = "",

    @field:Attribute(name = "generator-info-name", required = false)
    @param:Attribute(name = "generator-info-name", required = false)
    var generatorInfoName: String? = null,

    @field:Attribute(name = "generator-info-url", required = false)
    @param:Attribute(name = "generator-info-url", required = false)
    var generatorInfoUrl: String? = null,

    @field:Attribute(name = "source-info-name", required = false)
    @param:Attribute(name = "source-info-name", required = false)
    var sourceInfoName: String? = null,

    @field:Element(name = "channel", required = false)
    @param:Element(name = "channel", required = false)
    var channel: Channel2? = null,


    @field:ElementList(name = "programme", inline = true, required = false)
    @param:ElementList(name = "programme", inline = true, required = false)
    var programme: List<Programme>? = null
)

@Root(strict = false)
data class Channel2(

    @field:Attribute(name = "id", required = false)
    @param:Attribute(name = "id", required = false)
    var id: String = "",

    @field:Element(name = "url", required = false)
    @param:Element(name = "url", required = false)
    var url: String = "",

    @field:Element(name = "display-name", required = false)
    @param:Element(name = "display-name", required = false)
    var displayname: String = "",
)

@Root(name = "programme", strict = false)
data class Programme(
    @field:Attribute(name = "start")
    @param:Attribute(name = "start")
    var start: String = "",

    @field:Attribute(name = "stop")
    @param:Attribute(name = "stop")
    var stop: String = "",

    @field:Attribute(name = "channel")
    @param:Attribute(name = "channel")
    var channel: String = "",

    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String = ""
)

