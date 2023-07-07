package com.example.xmlretrofit

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "Tv",strict = false)
data class TvGuide(
    @field:Attribute(name = "date")
    var date: String = "",

    @field:Attribute(name = "source-info-name")
    var sourceInfoName: String = "",

    @field:Attribute(name = "generator-info-name")
    var generatorInfoName: String = "",

    @field:Attribute(name = "generator-info-url")
    var generatorInfoUrl: String = ""
)

@Root(name = "channel")
data class Channel @JvmOverloads constructor(
    @field:Element(name = "display-name")
    var displayName: String = "",

    @field:Element
    var url: String = "",

    @field:Attribute(name = "id", required = false)
    var id: String = "",

    @field:Attribute(name = "date", required = false)
var date: String? = null,

    @field:Attribute(name = "source-info-name", required = false)
    var sourceinfoname: String? = null,

@field:Attribute(name = "generator-info-name", required = false)
    var generatorinfoname: String? = null,
@field:Attribute(name = "generator-info-url", required = false)
    var generatorinfourl: String? = null,

//
    )
//
//@Root(name = "programme",strict = false)
//data class Programme @JvmOverloads constructor(
//    @field:Attribute(name = "start")
//    var start: String = "",
//
//    @field:Attribute(name = "stop")
//    var stop: String = "",
//
//    @field:Attribute(name = "channel")
//    var channel: String = "",
//
//    @field:Element(name = "orig-language", required = false)
//    var origlanguage: String = "",
//
//
//    @field:Element
//    var title: String = "",
//
//    @field:Element(name = "sub-title")
//    var subTitle: String = "",
//
//    @field:Element(required = false)
//    var icon: String? = null,
//
//
//    @field:Element
//    var desc: String = "",
//
//
//
//    @field:Element
//    var language: String = "",
//
//    @field:Element
//    var length: Length = Length(),
//
//    @field:ElementList(entry = "credits", inline = true, required = false)
//    var credits: List<String> = emptyList(),
//
//    @field:Element(required = false)
//    var video: String? = null,
//
//    @field:Element(required = false)
//    var audio: String? = null,
//
//    @field:Element(name = "previously-shown", required = false)
//    var previouslyShown: String? = null
//)
//
//@Root(name = "length")
//data class Length @JvmOverloads constructor(
//    @field:Text(required = false)
//    var value: Int? = null,
//
//    @field:Attribute(required = false)
//    var units: String? = null,
//)