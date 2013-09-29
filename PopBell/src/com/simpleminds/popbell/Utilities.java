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
 
    /*
     * Launch an application
     * @param   c   Context of application
     * @param   pm  the related package manager of the context
     * @param   pkgName Name of the package to run
     */
    public static boolean launchApp(Context c, PackageManager pm, String pkgName) {
    	
    	ApplicationInfo ai;
    	try {
    	    ai = pm.getApplicationInfo(pkgName, 0);
    	} catch (final NameNotFoundException e) {
    	    ai = null;
    	}
    	final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
    	
    	
    	ContentValues values = new ContentValues();
    	values.put(AppBlackListDBhelper.APPNAME, applicationName);
    	values.put(AppBlackListDBhelper.PKGNAME, pkgName);
    	
    	mHelper.getWritableDatabase().insert("appblacklist", AppBlackListDBhelper.APPNAME, values);

Toast.makeText(c, "appname:" + applicationName + "packagename:" + pkgName + "added", Toast.LENGTH_LONG);
        // if intent is available

        // by default, fail to launch
        return false;
    }
}