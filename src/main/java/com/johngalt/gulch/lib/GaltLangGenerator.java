package com.johngalt.gulch.lib;

import com.johngalt.gulch.GulchMod;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/18/2014.
 */
public class GaltLangGenerator
{
    private static List<GaltLangEntry> _fileEntries = new ArrayList<GaltLangEntry>();

    public static GaltLangGenerator instance = new GaltLangGenerator();

    private GaltLangGenerator()
    {
        if (GulchMod.generateLangFile)
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
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void GenerateLangFile()
    {
        // /D:/Work/SampleModding/Forge/out/production/Forge/com/johngalt/gulch/GulchMod.class
        String path = GulchMod.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String runPath = path.replace("/com/johngalt/gulch/GulchMod.class", "/assets/gulch/lang/en_US.lang");
        String sourcePath = path.replace("/Forge/out/production/Forge/com/johngalt/gulch/GulchMod.class", "/Gulch/src/main/resources/assets/gulch/lang/en_US.lang");

        createLangFileAt(runPath);
        createLangFileAt(sourcePath);
    }

    private static void createLangFileAt(String Path)
    {
        BufferedWriter writer = null;

        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Path), "utf-8"));

            for (GaltLangEntry entry : _fileEntries)
            {
                writer.write(entry.CreateLangFileLine());
                writer.newLine();
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (writer != null)
                {
                    writer.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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
