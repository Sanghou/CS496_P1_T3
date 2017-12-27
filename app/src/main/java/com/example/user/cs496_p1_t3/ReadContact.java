package com.example.user.cs496_project1;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ReadContact extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readcontact);

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        int ididx = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int nameidx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        StringBuilder result = new StringBuilder();
        while(cursor.moveToNext())
        {
            result.append(cursor.getString(nameidx) + " :");

            String id = cursor.getString(ididx);
            Cursor cursor2 = cr.query(ContactsContract.CommonDataKinds.
            Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                    new String[]{id},null);

            int typeidx = cursor2.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.TYPE);

            int numidx = cursor2.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER);

            while (cursor2.moveToNext()){
                String num = cursor2.getString(numidx);
                switch(cursor2.getInt(typeidx)){
                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                        result.append(" Mobile:"+num);
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                        result.append(" Home:"+num);
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                        result.append(" Work:"+num);
                        break;
                }
            }
            cursor2.close();
            result.append("\n");

        }
        cursor.close();

        TextView txtResult = (TextView)findViewById(R.id.result);
        txtResult.setText(result);


    }

}
