package com.johngalt.gulch.items;

/**
 * Created by horvay_000 on 10/8/2014.
 */
public class ItemKnife extends GaltCommonItem
{
    public ItemKnife()
    {
        super();
        this.setMaxStackSize(1);

        this.RegisterWithOreDictionary("toolKnife");
    }


}
