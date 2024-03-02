package com.fernandosini.storeapikotlin.adapters

import org.modelmapper.ModelMapper

class Mapper {

     fun <D> convert(from: Any, to: Class<D>): D {
        val mapper = ModelMapper();
        return mapper.map(from, to)
    }
}