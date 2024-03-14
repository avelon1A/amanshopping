package com.example.amanshopping.data

data class Products(
   val id:String,
    val category:String,
    val description:String,
    val image:String,
   val price:String,
   val thumbnail:String,
   val title:String

){
    constructor():this("0","","","","","","")

}