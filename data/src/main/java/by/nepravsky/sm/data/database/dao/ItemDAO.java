package by.nepravsky.sm.data.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import by.nepravsky.sm.data.database.entity.ItemDBE;
import io.reactivex.Single;

@Dao
public interface ItemDAO {

    String TABLE_NAME = "items";

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE id IN (:idList)")
    Single<List<ItemDBE>> getItemList(List<Integer> idList);


}
