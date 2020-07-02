package com.example.myexamplayer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myexamplayer.R;
import com.example.myexamplayer.activity.CryptoCenter.CryptoCenter;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

import net.alhazmy13.mediapicker.Video.VideoPicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.github.barteksc.pdfviewer.*;

public class PlayerActivity extends AppCompatActivity {

    private ImageView imgPickFile;

    private TextView txtEncryptResultat;
    private EditText txtOriginalData;
    private Button BtnEncryptData;

    private TextView txtDecryptResultat;
    private EditText txtEncryptedData;
    private Button BtnDecryptData;
    private CryptoCenter cryptoCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        cryptoCenter = new CryptoCenter();
        imgPickFile = findViewById(R.id.imgPick);
        /*
        txtEncryptResultat = findViewById(R.id.txtEncryptResult);
        txtOriginalData = findViewById(R.id.txtOriginalData);
        BtnEncryptData = findViewById(R.id.btnEncrypt);

        txtDecryptResultat = findViewById(R.id.txtDecryptResult);
        txtEncryptedData = findViewById(R.id.txtEncryptData);
        BtnDecryptData = findViewById(R.id.btnDecrypt);
        */

        String key = "1234567890123456";
        String textToEncrypt = "evarice";
        String textToDecrypt = null;
        try {
            textToDecrypt = cryptoCenter.encryptSrting(textToEncrypt,key);
            System.out.println("cle => "+key+" data To Encrypt => "+textToEncrypt+" Decryted Data =>"+textToDecrypt);
        } catch (Exception e)
        {
            e.printStackTrace();
        }



        imgPickFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFile(v);
            }
        });
    }

    public void pickFile(View view)
    {
        new VideoPicker.Builder(PlayerActivity.this)
                .mode(VideoPicker.Mode.CAMERA_AND_GALLERY)
                .directory(VideoPicker.Directory.DEFAULT)
                .extension(VideoPicker.Extension.MP4)
                .enableDebuggingMode(true)
                .build();
         //PDFView pdfView = new PDFView(this)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VideoPicker.VIDEO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> mPaths =  data.getStringArrayListExtra(VideoPicker.EXTRA_VIDEO_PATH);
            //Your Code

            MKPlayerActivity.configPlayer(this).play(mPaths.get(0));
        }
    }

    //----------------

}
