package com.johngalt.gulch.lib;

import com.johngalt.gulch.GulchMod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/18/2014.
 */
public class GaltLangGenerator
{
    private static List<GaltLangEntry> _fileEntries = new ArrayList<GaltLangEntry>();

    private static GaltLangGenerator instance = new GaltLangGenerator();

    private GaltLangGenerator()
    {
        InputStream instLangFile = GulchMod.class.getResourceAsStream("/assets/gulch/lang/en_US.lang");
        try
        {
            List<String> fileLines = IOUtils.readLines(instLangFile);

            for (String entry : fileLines)
            {
                if (entry.contains("="))
                {
                    String[] parts = entry.split("=");
                    GaltLangGenerator._fileEntries.add(new GaltLangEntry(parts[0].replace(".name", ""), parts[1]));
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void GenerateLangFile()
    {
        List<String> fileLines = new ArrayList<String>();

        for (GaltLangEntry item : _fileEntries)
        {
            fileLines.add(item.CreateLangFileLine());
        }

        String path = GulchMod.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }


    public static void AddEntry(IGaltObject item)
    {
        for (GaltLangEntry entry : _fileEntries)
        {
            if (entry.Contains(item))
            {
                return;
            }
        }

        _fileEntries.add(new GaltLangEntry(item));
    }


}
