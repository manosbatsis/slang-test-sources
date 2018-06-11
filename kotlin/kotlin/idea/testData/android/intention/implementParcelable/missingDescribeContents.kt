// INTENTION_CLASS: org.jetbrains.kotlin.android.intention.ImplementParcelableAction
import android.os.Parcel
import android.os.Parcelable


class <caret>Simple(parcel: Parcel) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    companion object CREATOR : Parcelable.Creator<Simple> {
        override fun createFromParcel(parcel: Parcel): Simple {
            return Simple(parcel)
        }

        override fun newArray(size: Int): Array<Simple?> {
            return arrayOfNulls(size)
        }
    }
}