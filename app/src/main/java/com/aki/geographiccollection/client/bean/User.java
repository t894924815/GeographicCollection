package com.aki.geographiccollection.client.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by chunr on 2016/9/4.
 */

@Table("User")
public class User {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("id") // 指定列名
    public int id;
    @Column("userName")
    public String userName;
    @Column("passWord")
    public String passWord;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

}
