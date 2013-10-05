/*
 *PopBell Application for Android
 *Copyright (C) 2013 SimpleMinds Team
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.simpleminds.popbell;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Doc_Contributors extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doclayout);
        
        TextView helloTxt = (TextView)findViewById(R.id.contents);
        helloTxt.setText(readTxt());
    }
    
    private String readTxt(){

     InputStream inputStream = getResources().openRawResource(R.raw.contributors);
     
     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
     
     int i;
  try {
   i = inputStream.read();
   while (i != -1)
      {
       byteArrayOutputStream.write(i);
       i = inputStream.read();
      }
      inputStream.close();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
     return byteArrayOutputStream.toString();
    }
}
