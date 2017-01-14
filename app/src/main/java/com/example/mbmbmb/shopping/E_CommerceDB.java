package com.example.mbmbmb.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by mbmbmb on 12/12/2016.
 */
public class E_CommerceDB extends SQLiteOpenHelper {
    private static String NAME="E_COMMERCE";
    private static int VERSION=10;
    private SQLiteDatabase e_commerce_db;
    public E_CommerceDB(Context context) {
        super(context,NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CUSTOMERS (CUST_ID INTEGER PRIMARY KEY AUTOINCREMENT ,CUST_NAME TEXT NOT NULL,USER_NAME TEXT NOT NULL,EMAIL TEXT NOT NULL,PASSWORD TEXT  NOT NULL,GENDER TEXT NOT NULL,BIRTHDATE TEXT NOT NULL,JOB TEXT NOT NULL)");
        db.execSQL("CREATE TABLE ORDERS (ORD_ID INTEGER PRIMARY KEY AUTOINCREMENT,ORD_DATE TEXT NOT NULL,CUST_ID INTEGER NOT NULL,ADDRESS TEXT NOT NULL,FOREIGN KEY(CUST_ID) REFERENCES CUSTOMERS(CUST_ID))");
        db.execSQL("CREATE TABLE CATEGORIES (CAT_ID INTEGER PRIMARY KEY AUTOINCREMENT,CAT_NAME TEXT NOT NULL)");
        db.execSQL("CREATE TABLE PRODUCTS (PROD_ID INTEGER PRIMARY KEY AUTOINCREMENT,PROD_NAME TEXT NOT NULL,PRICE FLOAT NOT NULL,QUANTITY INTEGER NOT NULL,CAT_ID INTEGER NOT NULL,FOREIGN KEY(CAT_ID) REFERENCES CATEGORIES(CAT_ID))");
        db.execSQL("CREATE TABLE ORDER_DETAILS (ORD_ID INTEGER NOT NULL,PROD_ID INTEGER NOT NULL,QUANTITY INTEGER NOT NULL,PRIMARY KEY(ORD_ID,PROD_ID),FOREIGN KEY(ORD_ID) REFERENCES ORDERS(ORD_ID),FOREIGN KEY(PROD_ID) REFERENCES PRODUCTS(PROD_ID))");
//
        db.execSQL("CREATE TABLE SHOPPING_LIST (SHPL_LIST_ID INTEGER PRIMARY KEY AUTOINCREMENT ,USER_NAME TEXT NOT NULL,NAME TEXT NOT NULL,PRICE FLOAT NOT NULL,QUANTITY INTEGER NOT NULL) ");

        ContentValues category=new ContentValues();
        category.put("CAT_NAME","Perfumes");
        db.insert("CATEGORIES", null, category);

        category=new ContentValues();
        category.put("CAT_NAME", "JEWELRY");
        db.insert("CATEGORIES", null, category);
        category=new ContentValues();
        category.put("CAT_NAME", "BAGS");
        db.insert("CATEGORIES", null, category);
//
        category.put("CAT_NAME","Dresses");
        db.insert("CATEGORIES", null, category);
//
        category.put("CAT_NAME","shoes");
        db.insert("CATEGORIES", null, category);
//
        category.put("CAT_NAME","wallets");
        db.insert("CATEGORIES", null, category);
//
//
        ContentValues product=new ContentValues();

        product.put("PROD_NAME","MyGold");
        product.put("PRICE",849);
        product.put("QUANTITY",100);
        product.put("CAT_ID",1);
        db.insert("PRODUCTS", null, product);

        product=new ContentValues();
        product.put("PROD_NAME","Aigner Starlight");
        product.put("PRICE",299);
        product.put("QUANTITY",100);
        product.put("CAT_ID",1);
        db.insert("PRODUCTS", null, product);

        product=new ContentValues();
        product.put("PROD_NAME","Guess Seductive");
        product.put("PRICE",400);
        product.put("QUANTITY",100);
        product.put("CAT_ID",1);
        db.insert("PRODUCTS", null, product);

        product=new ContentValues();
        product.put("PROD_NAME","Pleated Set");
        product.put("PRICE",299);
        product.put("QUANTITY",100);
        product.put("CAT_ID",2);
        db.insert("PRODUCTS", null, product);
        product=new ContentValues();
        product.put("PROD_NAME","Earrings");
        product.put("PRICE",28);
        product.put("QUANTITY",100);
        product.put("CAT_ID",2);
        db.insert("PRODUCTS", null, product);
        product=new ContentValues();
        product.put("PROD_NAME","Necklace");
        product.put("PRICE",98);
        product.put("QUANTITY",100);
        product.put("CAT_ID",2);
        db.insert("PRODUCTS", null, product);
        product=new ContentValues();
        product.put("PROD_NAME","ZISKA Leather");
        product.put("PRICE",299);
        product.put("QUANTITY",100);
        product.put("CAT_ID",2);
        db.insert("PRODUCTS",null,product);


        product=new ContentValues();
        product.put("PROD_NAME","yellow bag");
        product.put("PRICE",28);
        product.put("QUANTITY",16);
        product.put("CAT_ID",3);
        db.insert("PRODUCTS",null,product);
        product=new ContentValues();
        product.put("PROD_NAME","Pink bag");
        product.put("PRICE",23);
        product.put("QUANTITY",16);
        product.put("CAT_ID",3);
        db.insert("PRODUCTS",null,product);

        product=new ContentValues();
        product.put("PROD_NAME","yellow dress");
        product.put("PRICE",789);
        product.put("QUANTITY",16);
        product.put("CAT_ID",4);
        db.insert("PRODUCTS",null,product);
        product=new ContentValues();
        product.put("PROD_NAME","bink jaket");
        product.put("PRICE",278);
        product.put("QUANTITY",16);
        product.put("CAT_ID",4);
        db.insert("PRODUCTS",null,product);
        product=new ContentValues();
        product.put("PROD_NAME","red dress");
        product.put("PRICE",200);
        product.put("QUANTITY",16);
        product.put("CAT_ID",4);
        db.insert("PRODUCTS",null,product);

        product=new ContentValues();
        product.put("PROD_NAME","black shose");
        product.put("PRICE",52);
        product.put("QUANTITY",16);
        product.put("CAT_ID",5);
        db.insert("PRODUCTS",null,product);

        product=new ContentValues();
        product.put("PROD_NAME","green shose");
        product.put("PRICE",70);
        product.put("QUANTITY",16);
        product.put("CAT_ID",5);
        db.insert("PRODUCTS",null,product);

        product=new ContentValues();
        product.put("PROD_NAME","brwon shose");
        product.put("PRICE",70);
        product.put("QUANTITY",16);
        product.put("CAT_ID",5);
        db.insert("PRODUCTS",null,product);
        product=new ContentValues();
        product.put("PROD_NAME","red shose");
        product.put("PRICE",70);
        product.put("QUANTITY",16);
        product.put("CAT_ID",5);
        db.insert("PRODUCTS",null,product);


        product=new ContentValues();
        product.put("PROD_NAME","green wallet");
        product.put("PRICE",30);
        product.put("QUANTITY",16);
        product.put("CAT_ID",6);
        db.insert("PRODUCTS",null,product);
        product=new ContentValues();
        product.put("PROD_NAME","blue wallet");
        product.put("PRICE",55);
        product.put("QUANTITY",16);
        product.put("CAT_ID",6);
        db.insert("PRODUCTS",null,product);
        product=new ContentValues();
        product.put("PROD_NAME","bink wallet");
        product.put("PRICE",70);
        product.put("QUANTITY",16);
        product.put("CAT_ID",6);
        db.insert("PRODUCTS",null,product);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ORDER_DETAILS ");
        db.execSQL("DROP TABLE IF EXISTS ORDERS ");
        db.execSQL("DROP TABLE IF EXISTS CUSTOMERS ");
        db.execSQL("DROP TABLE IF EXISTS PRODUCTS");
        db.execSQL("DROP TABLE IF EXISTS CATEGORIES ");
        db.execSQL("DROP TABLE IF EXISTS SHOPPING_LIST ");
        onCreate(db);

    }

    public boolean signUp(String customer_name,String user_name,String email,String password,String gender,String birthdate,String job){
        e_commerce_db=getWritableDatabase();
        ContentValues new_user=new ContentValues();
        new_user.put("CUST_NAME",customer_name);
        new_user.put("USER_NAME",user_name);
        new_user.put("EMAIL", email);
        new_user.put("PASSWORD", password);
        new_user.put("GENDER", gender);
        new_user.put("BIRTHDATE", birthdate);
        new_user.put("JOB", job);
        long user_id=e_commerce_db.insert("CUSTOMERS", null, new_user);
        return user_id != -1;
    }
/////logindatabase
    public boolean logIn(String user_name,String password){
        e_commerce_db=getReadableDatabase();
        String[] login_parameters={user_name,password};
        Cursor user=e_commerce_db.rawQuery("SELECT CUST_ID FROM CUSTOMERS WHERE USER_NAME=? AND PASSWORD=?", login_parameters);
        return user.getCount() != 0;
    }

    public Cursor searchProducts(String name) {
        e_commerce_db = getReadableDatabase();
        String[] search_paramates = {"%" + name + "%"};
        Cursor products = e_commerce_db.rawQuery("SELECT PROD_NAME,PRICE,QUANTITY FROM PRODUCTS WHERE PROD_NAME LIKE ?", search_paramates);
        products.moveToFirst();
        if (products.getCount() > 0) {
            return products;
        }
        return null;
    }

    public Cursor getCategories(){
        e_commerce_db=getReadableDatabase();
        Cursor categories=e_commerce_db.rawQuery("SELECT CAT_NAME FROM CATEGORIES",null);
        categories.moveToFirst();
        if(categories.getCount()>0){
            return categories;
        }
        return null;
    }


    public Cursor ProductsByCategory(String name) {
        e_commerce_db = getReadableDatabase();
        String[] search_parameters={name};
        Cursor products = e_commerce_db.rawQuery("SELECT PROD_NAME ,PRICE,QUANTITY FROM PRODUCTS WHERE CAT_ID= (SELECT CAT_ID FROM CATEGORIES WHERE CAT_NAME=?)",search_parameters);
        products.moveToFirst();
        if (products.getCount() > 0) {
            return products;
        }
        return null;
    }

    public Cursor getUserDetails(String user_name){
        e_commerce_db=getReadableDatabase();
        String[] search_parameters={user_name};
        Cursor user=e_commerce_db.rawQuery("SELECT * FROM CUSTOMERS WHERE USER_NAME=?",search_parameters);
        user.moveToFirst();
        if(user.getCount()==1){
            return user;
        }
        return null;
    }
    public boolean editQuantity(String user_name,ShoppingListItem item){
        e_commerce_db=getWritableDatabase();
        Cursor price=e_commerce_db.rawQuery("SELECT PRICE FROM PRODUCTS WHERE PROD_NAME=?", new String[]{item.getName()});
        price.moveToFirst();
        ContentValues row=new ContentValues();
        row.put("PRICE",item.getQuantity()*price.getFloat(0));
        row.put("QUANTITY", item.getQuantity());
        int rows=e_commerce_db.update("SHOPPING_LIST",row,"USER_NAME=? AND NAME=?",new String[]{user_name,item.getName()});
        if(rows>0){
            return  true;
        }
        return false;
    }

    public boolean update(int cust_id,String customer_name, String user_name,String email, String password, String gender, String birth_date, String job) {
        e_commerce_db=getWritableDatabase();
        String[] update_parameters={String.valueOf(cust_id)};
        ContentValues user=new ContentValues();
        user.put("CUST_NAME", customer_name);
        user.put("USER_NAME",user_name);
        user.put("EMAIL",email);
        user.put("PASSWORD", password);
        user.put("GENDER",gender);
        user.put("BIRTHDATE",birth_date);
        user.put("JOB",job);
        int result= e_commerce_db.update("CUSTOMERS",user,"CUST_ID=?",update_parameters);
        return result==1;
    }
//forgeting password
    public String getUserPassword(String email){
        e_commerce_db=getReadableDatabase();
        String[] search_parameters={email};
        Cursor password=e_commerce_db.rawQuery("SELECT PASSWORD FROM CUSTOMERS WHERE EMAIL=?",search_parameters);
        password.moveToFirst();
        if(password.getCount()==1){
            return password.getString(0);
        }
        return null;
    }

    public boolean addOrder(String username, String order_date, String address, List<ShoppingListItem> items){
        e_commerce_db=getWritableDatabase();
        boolean inserted=true;
        Cursor user=e_commerce_db.rawQuery("SELECT CUST_ID FROM CUSTOMERS WHERE USER_NAME=?", new String[]{username});
        if(user.getCount()==1){
            user.moveToFirst();
            int cust_id=user.getInt(0);
            ContentValues order=new ContentValues();
            order.put("ORD_DATE",order_date);
            order.put("CUST_ID",cust_id);
            order.put("ADDRESS",address);
            long order_id=e_commerce_db.insert("ORDERS",null,order);
            if(order_id!=-1){
                for (ShoppingListItem item:items){
                    Cursor product=e_commerce_db.rawQuery("SELECT PROD_ID,QUANTITY FROM PRODUCTS WHERE PROD_NAME=?",new String[]{item.getName()});
                    if(product.getCount()==1){
                        product.moveToFirst();
                        int prod_id=product.getInt(0);
                        ContentValues order_detail=new ContentValues();
                        order_detail.put("ORD_ID",order_id);
                        order_detail.put("PROD_ID",prod_id);
                        order_detail.put("QUANTITY",item.getQuantity());
                        if(e_commerce_db.insert("ORDER_DETAILS",null,order_detail)==-1){
                            inserted=false;
                        }else {
                            ContentValues data=new ContentValues();
                            data.put("QUANTITY",product.getInt(1)-item.getQuantity());
                            e_commerce_db.update("PRODUCTS",data,"PROD_ID=?",new String[]{String.valueOf(prod_id)});
                        }
                    }
                }
            }
        }
        if(inserted){
            e_commerce_db.delete("SHOPPING_LIST","USER_NAME=?",new String[]{username});
        }
        return inserted;
    }

    public boolean addToShoppingList(String user_name,ShoppingListItem item) {
        e_commerce_db=getWritableDatabase();
        ContentValues shopping_lsit_item=new ContentValues();
        shopping_lsit_item.put("USER_NAME",user_name);
        shopping_lsit_item.put("NAME",item.getName());
        shopping_lsit_item.put("PRICE",item.getPrice());
        shopping_lsit_item.put("QUANTITY",item.getQuantity());
        long id=e_commerce_db.insert("SHOPPING_LIST", null, shopping_lsit_item);
        if (id!=-1){
            return true;
        }
        return false;
    }

    public boolean removeFromShoppingList(String user_name,ShoppingListItem item){
        e_commerce_db=getWritableDatabase();
        int rows=e_commerce_db.delete("SHOPPING_LIST", "USER_NAME=? AND NAME=?", new String[]{user_name, item.getName()});
        if (rows>0){
            return true;
        }
        return false;
    }

    public Cursor getAllItems(String user_name){
        e_commerce_db=getReadableDatabase();
        Cursor items=e_commerce_db.rawQuery("SELECT NAME,PRICE,QUANTITY FROM SHOPPING_LIST WHERE USER_NAME=?", new String[]{user_name});
        if(items.getCount()>0) {
            items.moveToFirst();
            return items;
        }
        return null;
    }


}
