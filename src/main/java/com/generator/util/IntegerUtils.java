package com.generator.util;

public class IntegerUtils {
   public static Integer getInteger(String text) {
      if ( text==null ) return 0;
      try {
         return Integer.valueOf(text);
      } catch (Exception e) {
         return 0;
      }
   }
}
