package com.simpleminds.popbell;

import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.widget.Toast;
import com.simpleminds.popbell.AppBlackListDBhelper;
 
public class Utilities {
	private static AppBlackListDBhelper mHelper = null;
	private static Cursor mCursor = null;
    /*
     * Get all installed application on mobile and return a list
     * @param   c   Context of application
     * @return  list of installed applications
     */
    public static List getInstalledApplication(Context c) {
        return c.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
    }
 


}