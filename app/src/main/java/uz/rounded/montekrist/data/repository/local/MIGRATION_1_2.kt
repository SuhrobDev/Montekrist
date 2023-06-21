package uz.rounded.montekrist.data.repository.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Perform the necessary migration steps here
        // For example, you can rename a column using SQL query:
        database.execSQL("ALTER TABLE starship RENAME COLUMN id TO id")
    }
}