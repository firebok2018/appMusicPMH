package com.firebok.music;

import java.util.ArrayList;

import android.Manifest;
import android.R.string;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.*;

public class MainActivity extends Activity {
	private static final int MY_PERMISSION_REQUEST=1;
	ArrayList<String> arrayList;
	ListView listview;
	ArrayAdapter<String> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (ContextCompat.chechContextPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			
			if (ActivityCompat.showidShowRequestPermisionRationals(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
				ActivityCompat.requestPermission(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
			}else{
				ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
			}
			
		}else {
			doStuff();
		}*/
		
		
	}
	
	public void getMusic(){
		ContentResolver contentResolver = getContentResolver();
		Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		Cursor songCursor = ContentResolver(songUri, null , null, null, null);
		
		if (songCursor != null && songCursor.moveToFirst()) {
			int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
			int songArtista = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
			
			do {
				String currentTitle = songCursor.getString(songTitle);
				String currentArtista = songCursor.getString(songArtista);
				arrayList.add(currentTitle + "\n" + currentArtista);
			} while (songCursor.moveToNext());
		}
	}
	public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int [] grantResults){
		
		super.onRequestPermissionsResult(requestCode,permissions,grantResults);
	}
	
}
