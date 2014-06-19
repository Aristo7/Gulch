package com.johngalt.gulch.lib;

import com.johngalt.gulch.blocks.GaltCommonBlock;
import com.johngalt.gulch.blocks.GaltCommonContainer;
import com.johngalt.gulch.items.GaltCommonItem;
import org.apache.commons.lang3.StringUtils;

/**
 * Created on 6/18/2014.
 */
public class GaltLangEntry
{
    private String _UnlocalizedName;
    private String _Name;

    public GaltLangEntry(String unlocalName, String name)
    {
        _UnlocalizedName = unlocalName;
        _Name = name;
    }

    public GaltLangEntry(IGaltObject item)
    {
        this(item.getUnlocalizedName(), getNiceName(item.getUnwrappedUnlocalizedName()));
    }

    private static String getNiceName(String unwrappedUnlocalizedName)
    {
        if (!unwrappedUnlocalizedName.endsWith("Tab")) // don't remove parts of tab names
        {
            if (unwrappedUnlocalizedName.startsWith("Galt")) unwrappedUnlocalizedName = unwrappedUnlocalizedName.replaceFirst("Galt", "");

            if (unwrappedUnlocalizedName.startsWith("Item")) unwrappedUnlocalizedName = unwrappedUnlocalizedName.replaceFirst("Item", "");

            if (unwrappedUnlocalizedName.startsWith("Block")) unwrappedUnlocalizedName = unwrappedUnlocalizedName.replaceFirst("Block", "");
        }

        unwrappedUnlocalizedName = unwrappedUnlocalizedName.replaceAll("([A-Z])", " $1").replaceAll("([0-9]+)", " $1").replace("_", " "); //Cammel case and underscore to title case
        unwrappedUnlocalizedName = StringUtils.capitalize(unwrappedUnlocalizedName);

        if (unwrappedUnlocalizedName.startsWith(" "))
            unwrappedUnlocalizedName = unwrappedUnlocalizedName.substring(1);

        return unwrappedUnlocalizedName;
    }

    public boolean Contains(IGaltObject item)
    {
        if (item.getUnlocalizedName().equals(_UnlocalizedName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String CreateLangFileLine()
    {
        return _UnlocalizedName + ".name=" + _Name;

    }
}
