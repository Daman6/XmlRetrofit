package com.example.xmlretrofit

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "programme")
data class XMLModel @JvmOverloads constructor(

    @field:Element(name = "title")
//    @param:Element(name = "title")
    var title: String? = null,

 @field:Element(name = "language")
//    @param:Element(name = "language")
    var language: String? = null,

)