package com.simpleminds.popbell;

import android.content.Context;

public class AndroidContext
{

private static Context s_oContext;
/**
* Sets the Android context used for file access. Should be set
* at application startup, before the log is used.
* @param oContext Application context
*/
public static synchronized void setContext( Context oContext )
{
s_oContext = oContext;
}

/**
* Sets the Android context used for file access. Should be set
* at application startup, before the log is used.
* @param oContext Application context
*/
public static synchronized Context getContext()
{
return s_oContext;
}
}