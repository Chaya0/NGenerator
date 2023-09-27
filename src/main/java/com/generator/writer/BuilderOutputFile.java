package com.generator.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Fajl u koji se upisuje sadrzaj tokom rada Builder-a<br>
 * Upis u fajl se vrsi kao upis niza linija (svaka se upisuje pozivom metode write)
 * pri cemu se prosleduju sadrzaj linije, i velicina njenog indent-a
 */
public class BuilderOutputFile implements AutoCloseable {
   /**
    * Velicina "jedinicnog" indent-a
    */
   private static final String indent = "\t";
   
   /**
    * Indikator koji govori da li je fajl vec ranije postojao
    */
   private boolean alreadyExisted = true;
   public boolean hasAlreadyExisted() {
      return alreadyExisted;
   }
   
   /**
    * Indikator koji govori da li je fajl vec ranije imao automatski generisan sadrzaj
    */
   private boolean hasServiceBuilderMarks = false;
   boolean hasServiceBuilderMarks() {
      return hasServiceBuilderMarks;
   }
   
   /**
    * Odgovor da li je dozvoljan upis sadrzaja u tekuci fajl
    * @return 
    * <i>true</i>, ako fajl nije ranije postojao<br>
    * <i>false</i>, u suprotnom
    */
   boolean writePermitted() {
      return !alreadyExisted;
   }
   
   /**
    * Sadrzaj fajla (ako je vec postojao) koji prethodi sistemskim oznakama za pocetak generisanog dela<br>
    * U slucaju da se sadrzaj tekuceg fajla ponovo generise, a da je fajl tipa "insert" onda ovaj 
    * prethodno postojeci sadrzaj treba da se sacuva (i da se ponovo upise u izlazni fajl)
    */
   private String previousContentStart = "";
   
   /**
    * Sadrzaj fajla (ako je vec postojao) koji sledi sistemskim oznakama za pocetak generisanog dela<br>
    * U slucaju da se sadrzaj tekuceg fajla ponovo generise, a da je fajl tipa "insert" onda ovaj 
    * prethodno postojeci sadrzaj treba da se sacuva (i da se ponovo upise u izlazni fajl)
    */
   private String previousContentEnd = "";
   
   /**
    * Fizicki fajl u koji se upisuje sadrzaj
    */
   private final File file;
   
   /**
    * Writer koji sluzi za upis sadrzaja<br>
    * Kreira se u konstruktoru, a oslobadja u metodi close (ili tokom rada garbage collector-a)
    */
   private BufferedWriter writer = null;
   
   BuilderOutputFile (String filename, String charset, boolean overwrite) throws IOException {
      // Otvori fajl (kreiraj ga, ako vec ne postoji)
      file = new File(filename);
      alreadyExisted = true;
      if ( !file.exists() ) {
         alreadyExisted = false;
         file.createNewFile();
      } else {
         if ( !overwrite ) {
            return;
         }
      }
      
      // Kreiraj kanal za upis u fajl
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), charset));
      // Upisi eventualno postojeci sadrzaj koji prethodi kljucnim recima
      conditionalWrite(previousContentStart);
   }
   
   BuilderOutputFile (String filename, String charset) throws IOException {
      this (filename, "UTF-8", true);
   }
   
   BuilderOutputFile (String filename) throws IOException {
      this (filename, "UTF-8");
   }
   
   /**
    * Upisi tekst, ako postoji (nije null, i nije prazan string)
    * @param s
    * @throws IOException
    */
   private void conditionalWrite (String s)  throws IOException {
      if ( writer==null ) return;
      if ( s==null ) return;
      if ( s.length()==0 ) return;

      __write(0, s, "");
   }
   
   private void __write (int indentLevel, String s, String suffix) throws IOException {
      String blank = "";
      for ( int i=0 ; i<indentLevel ; i++ ) blank += indent;
      
      writer.write(blank + s + suffix);
   }
   
   private void _write (int indentLevel, String line, String suffix) throws IOException {
      if ( writer==null ) return;
      if ( line==null ) return;
      __write(indentLevel, line, suffix);
   }
   
   public void writeln (int indentLevel, String line) throws IOException {
      _write(indentLevel, line, "\r\n");
   }
   
   void write (int indentLevel, String lines) throws IOException {
      _write(indentLevel, lines, "");
   }
   
   @Override
   public void close() throws IOException {
      if ( writer==null ) return;
      conditionalWrite(previousContentEnd);
      writer.close();
      writer = null;
   }
   
   @Override
   public String toString() {
		return file.getAbsolutePath();
	}
}
