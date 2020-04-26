package com.szm.im.db

import com.szm.im.extensions.toVarargArray
import org.jetbrains.anko.db.insert

class IMDatabase {
    companion object{
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    fun  saveContact(contact: Contact){
        databaseHelper.use {
            //SQLiteDataBase扩展方法
            insert(ContactTable.NAME,*contact.map.toVarargArray())
        }
    }
}