/*
 * Create by SangKwon on 2019. 9. 17.
 */

package md.ysk5898.kotlin.retrofit.model


data class ktModel(var list:List<data>){
    data class data(var id : Int, var name : String, var age :Int)
}

