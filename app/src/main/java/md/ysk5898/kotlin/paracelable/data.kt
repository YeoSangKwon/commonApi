/*
 * Create by SangKwon on 2019. 10. 10.
 */

package md.ysk5898.kotlin.paracelable

import android.os.Parcel
import android.os.Parcelable

class data(parcel: Parcel):Parcelable{
    private var name: String? = null
    private var phoneNumber: String? = null

    fun data(name:String, phoneNumber: String){
        this.name = name
        this.phoneNumber = phoneNumber
    }
    fun data(parcel: Parcel){
        name = parcel.readString()
        phoneNumber = parcel.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(phoneNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<data> {
        override fun createFromParcel(parcel: Parcel): data {
            return data(parcel)
        }

        override fun newArray(size: Int): Array<data?> {
            return arrayOfNulls(size)
        }
    }

}