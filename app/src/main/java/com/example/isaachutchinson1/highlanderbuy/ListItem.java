package com.example.isaachutchinson1.highlanderbuy;

import android.widget.ImageView;

/**
 * Created by isaachutchinson1 on 10/1/17.
 * <p>
 * Source: https://www.youtube.com/watch?v=5T144CbTwjc&index=2&list=PLk7v1Z2rk4hjHrGKo9GqOtLs1e2bglHHA
 */

public class ListItem {

    private String head;
    private String desc;
    private ImageView image;

    public ListItem(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
