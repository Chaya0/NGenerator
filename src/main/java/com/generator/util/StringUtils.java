package com.generator.util;

public class StringUtils {
   /**
    * Uppercase-uje prvo slovo u tekstu
    * @param text
    * @return
    */
   public static String uppercaseFirst (String text) {
      if ( text==null ) return null;
      if ( text.length()==0 ) return text;
      
      String start = text.substring(0, 1);
      String end = text.substring(1);
      return start.toUpperCase() + end;
   }

   /**
    * Lowercase-uje prvo slovo u tekstu
    * @param text
    * @return
    */
   public static String lowercaseFirst (String text) {
      if ( text==null ) return null;
      if ( text.length()==0 ) return text;
      
      String start = text.substring(0, 1);
      String end = text.substring(1);
      return start.toLowerCase() + end;
   }
   
   /**
    * Razdvaja rec napisanu "grbavom notacijom" u pojedinacne reci
    * @param text
    * @return
    */
   public static String camelNotationToText (String text) {
      if ( text==null ) return null;

      String s = "";
      String uppercase = text.toUpperCase();
      for ( int i=0 ; i<text.length() ; i++ ) {
         if ( i>0 && (text.charAt(i) == uppercase.charAt(i)) ) {
            s += " ";
         }
         s += text.charAt(i);
      }
      return s;
   }
}
