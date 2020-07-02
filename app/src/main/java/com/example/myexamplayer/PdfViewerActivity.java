package com.example.myexamplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.example.myexamplayer.activity.CryptoCenter.CryptoCenter;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class PdfViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        String key = "1234567890123456";
        String encryptedFile = "/documents/2003 - Eyrolles - Halte Aux Hackeurs.pdf.myexam.enc";
        CryptoCenter cryptoCenter = new CryptoCenter();
        try {
            byte[] FileToDecrypte = cryptoCenter.readFile(encryptedFile);
            byte[] decryptedFile  = cryptoCenter.decrypt(FileToDecrypte,cryptoCenter.getKeyBytes(key),cryptoCenter.getKeyBytes(key));
            //cryptoCenter.decodeFileContent(decryptedFile);
            openPdfFromByte(decryptedFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //getFileFromStorageToByteArray();


    }


    /*public byte[] getFileFromStorageToByteArray()
    {
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath() + "/documents/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File document = new File(dir, doc_name);

        if (document.exists()) {
            document.delete();
        }

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/documents/");
        File document = new File(dir,"2003 - Eyrolles - Halte Aux Hackeurs.pdf.myexam.enc" );
        System.out.println("chemin " +dir.getPath());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(document.exists() + "!!");
        //InputStream in = ressource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //System.out.println("chemin " +bos.write(););
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
            System.out.println("taille final " +buf.length);
        } catch (IOException ex) {
            System.out.println("message fichier ---"+ex.getMessage());
        }
        return buf;
    }*/

    public void openPdfFromByte(byte[] pdfArray)
    {
       try {
          // File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/documents/");
           //File document = new File(dir,"Rapport Final.pdf" );
           //PDF View
           PDFView pdfView = findViewById(R.id.pdfView);
           //pdfView.fromBytes(pdfArray)
           pdfView.fromBytes(pdfArray)
                   .enableSwipe(true) // allows to block changing pages using swipe
                   .swipeHorizontal(true)
                   .enableDoubletap(true)
                   .defaultPage(0)
                   .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                   .password(null)
                   .scrollHandle(null)
                   .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                   // spacing between pages in dp. To define spacing color, set view background
                   .spacing(0)
                   .pageFitPolicy(FitPolicy.WIDTH)
                   .load();
       }catch (Exception e)
       {System.out.println("exep " +e.getMessage());}
    }
}
