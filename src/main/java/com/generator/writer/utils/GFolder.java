package com.generator.writer.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class GFolder {
   private final String basePath;
   public String getPath() {
      return basePath;
   }
   
   public GFolder(String path) {
      basePath = path;
   }
   
   public static ArrayList<String> getSubfolders(String path) {
       ArrayList<String> list = new ArrayList<>();
       File pathFile = new File(path);

       if (pathFile.isDirectory()) {
           File[] files = pathFile.listFiles();
           if (files != null) {
               for (File file : files) {
                   if (file.isDirectory()) {
                       list.add(file.getName());
                   }
               }
           }
       }
       return list;
   }
   
   public List<String> getSubfolders() {
      return getSubfolders(basePath);
   }
   
   public boolean create() {
      File folder = new File(basePath);
      if ( folder.exists() ) return true;
      return folder.mkdirs();
   }
   
   public String[] getFiles(String filter) {
      File path = new File(basePath);
      return path.list(new CTFilenameFilter("." + filter));
   }
   
   private static class CTFilenameFilter implements FilenameFilter {
      private final String mFilter;

      public CTFilenameFilter(String pFilter) {
         mFilter = pFilter;
      }

      @Override
      public boolean accept(File dir, String name) {
         return name.endsWith(mFilter);
      }
   }
}
