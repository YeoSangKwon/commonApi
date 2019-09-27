/*
 * Create by SangKwon on 2019. 9. 27.
 */

package md.ysk5898.kotlin.retrofit.model

data class dataModelList<T>(var list:List<dataModel>){
    data class dataModel(var id: Int = 0, var name: String = "NULL", var resId: Int?)
}

