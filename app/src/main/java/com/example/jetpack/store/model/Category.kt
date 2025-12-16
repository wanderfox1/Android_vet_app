package com.example.jetpack.store.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
  val name: String,
  val imageByteArray: ByteArray? = null
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Category

    if (id != other.id) return false
    if (name != other.name) return false
    if (imageByteArray != null) {
      if (other.imageByteArray == null) return false
      if (!imageByteArray.contentEquals(other.imageByteArray)) return false
    } else if (other.imageByteArray != null) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + name.hashCode()
    result = 31 * result + (imageByteArray?.contentHashCode() ?: 0)
    return result
  }
}