package com.szm.im.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.szm.im.app.IMApplication
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context = IMApplication.instance) : ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object{
        const val NAME = "im.db"
        const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(ContactTable.NAME,true,
        ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME,true)
        onCreate(db)
    }


}