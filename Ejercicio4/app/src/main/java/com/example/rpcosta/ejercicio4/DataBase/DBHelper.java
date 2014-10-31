package com.example.rpcosta.ejercicio4.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.rpcosta.ejercicio4.Dominio.Item;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by rpcosta on 27/10/14.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "items_trackeados.db";
    private static final int DATABASE_VERSION = 1;
    private Item i;
    private Dao<Item, Integer> itemDao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Item.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Item, Integer> getItemDao() throws SQLException {
        if (itemDao == null) {
            itemDao = getDao(Item.class);
        }
        return itemDao;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(db, connectionSource);
    }

    @Override
    public void close() {
        super.close();
        itemDao = null;
    }
}
