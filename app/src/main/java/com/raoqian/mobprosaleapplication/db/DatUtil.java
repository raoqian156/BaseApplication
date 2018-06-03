package com.raoqian.mobprosaleapplication.db;

import android.content.ContentResolver;
import android.support.annotation.NonNull;
import android.util.Log;

import com.raoqian.mobprosaleapplication.base.BaseApplication;
import com.raoqian.mobprosaleapplication.bean.NetCashInfo;
import com.raoqian.mobprosaleapplication.bean.NetCashInfoDao;
import com.raoqian.mobprosaleapplication.bean.Shop;
import com.raoqian.mobprosaleapplication.bean.ShopDao;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class DatUtil {

    /**
     * 添加数据
     *
     * @param shop
     * @return
     */
    public static void insertCart(Shop shop) {
        BaseApplication.getDaoInstant().getShopDao().save(shop);
    }

    /**
     * 删除数据
     *
     * @param resolver
     * @param id
     */
    public static void deleteCart(ContentResolver resolver, int id) {
//        resolver.delete(MyCartProvider.URI.CODE_CART_DELETE, "id = " + id, null);
    }


    /**
     * 查询数据
     *
     * @return
     */
    public static List<Shop> queryCart() {
        List<Shop> results = BaseApplication.getDaoInstant().getShopDao().queryBuilder().list();
//        List<Shop> list = new ArrayList<>();
//        String[] projection = {"id", "name", "price", "sell_num", "image_url", "address"};
//        Cursor cursor = resolver.query(MyCartProvider.URI.CODE_CART_QUERY, projection, null, null, null);
//        while (cursor.moveToNext()) {
//            Shop shop = new Shop();
//            shop.setId(cursor.getLong(cursor.getColumnIndex("id")));
//            shop.setName(cursor.getString(cursor.getColumnIndex("name")));
//            shop.setPrice(cursor.getString(cursor.getColumnIndex("price")));
//            shop.setSell_num(cursor.getInt(cursor.getColumnIndex("sell_num")));
//            shop.setImage_url(cursor.getString(cursor.getColumnIndex("image_url")));
//            shop.setAddress(cursor.getString(cursor.getColumnIndex("address")));
//            list.add(shop);
//        }
        return results;
    }

    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param shop
     */
    public static void insertLove(Shop shop) {
        BaseApplication.getDaoInstant().getShopDao().insertOrReplace(shop);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deleteLove(long id) {
        BaseApplication.getDaoInstant().getShopDao().deleteByKey(id);
    }

    /**
     * 更新数据
     *
     * @param shop
     */
    public static void updateLove(Shop shop) {
        BaseApplication.getDaoInstant().getShopDao().update(shop);
    }

    /**
     * 查询条件为Type=TYPE_LOVE的数据
     *
     * @return
     */
    public static List<Shop> queryLove() {
        return BaseApplication.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
    }

    /**
     * 查询全部数据
     */
    public static List<Shop> queryAll() {
        return BaseApplication.getDaoInstant().getShopDao().loadAll();
    }

    public final static long DO_NOT_SAVE = -1;

    public static void saveNetInfo(@NonNull NetCashInfo cashInfo) {
        if (DO_NOT_SAVE != cashInfo.getRequestTime()) {
            BaseApplication.getDaoInstant().getNetCashInfoDao().insertOrReplace(cashInfo);
            Log.e("DatUtil", "save = " + cashInfo.getContent());
        }
    }

    public static NetCashInfo getNetInfo(String url) {
        List<NetCashInfo> list = BaseApplication.getDaoInstant().getNetCashInfoDao().queryBuilder()
                .where(NetCashInfoDao.Properties.Url.eq(url)).list();
        if (list != null && list.size() > 0) {
            Log.e("DatUtil.LINE", "120 list.size = " + list.size());
            return list.get(0);
        } else {
            Log.e("DatUtil.LINE", "122 null");
            return null;
        }
    }
}
