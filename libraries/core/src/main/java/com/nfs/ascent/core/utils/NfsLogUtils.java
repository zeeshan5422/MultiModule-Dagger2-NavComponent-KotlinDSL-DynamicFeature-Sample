package com.nfs.ascent.core.utils;

import android.util.Log;

/* ---  Created by akhtarz on 2/20/2020. ---*/
public class NfsLogUtils {

    public static String TAG = NfsLogUtils.class.getSimpleName();

    public static int STACK_INDEX = 4;
    public static boolean isDebug = true;

    private static boolean isLoggable(){
        return isDebug;
    }

    public static void v(){
        if(NfsLogUtils.isLoggable()){
            Log.v(TAG, getMetaInfo());
        }
    }

    public static void v(String message){
        if(NfsLogUtils.isLoggable()){
            Log.v(TAG, getMetaInfo() + null2str(message));
        }
    }

    public static void v(String tag, String message){
        if(NfsLogUtils.isLoggable()){
            Log.v(tag, getMetaInfo() + null2str(message));
        }
    }

    public static void d(){
        if(NfsLogUtils.isLoggable()){
            Log.d(TAG, getMetaInfo());
        }
    }

    public static void d(String message){
        if(NfsLogUtils.isLoggable()){
            Log.d(TAG, getMetaInfo() + null2str(message));
        }
    }

    public static void d(String tag, String message){
        if(NfsLogUtils.isLoggable()){
            Log.d(tag, getMetaInfo() + null2str(message));
        }
    }

    public static void i(){
        if(NfsLogUtils.isLoggable()){
            Log.i(TAG, getMetaInfo());
        }
    }

    public static void i(String message){
        if(NfsLogUtils.isLoggable()){
            Log.i(TAG, getMetaInfo() + null2str(message));
        }
    }

    public static void i(String tag, String message){
        if(NfsLogUtils.isLoggable()){
            Log.i(tag, getMetaInfo() + null2str(message));
        }
    }

    public static void w(String message){
        if(NfsLogUtils.isLoggable()){
            Log.w(TAG, getMetaInfo() + null2str(message));
        }
    }

    public static void w(String tag, String message){
        if(NfsLogUtils.isLoggable()){
            Log.w(tag, getMetaInfo() + null2str(message));
        }
    }

    public static void w(String message, Throwable e){
        if(NfsLogUtils.isLoggable()){
            Log.w(TAG, getMetaInfo() + null2str(message), e);
            logThrowable(e);
            if(e.getCause() != null){
                logThrowable(e.getCause());
            }
        }
    }

    public static void e(String message){
        if(NfsLogUtils.isLoggable()){
            Log.e(TAG, getMetaInfo() + null2str(message));
        }
    }

    public static void e(String tag, String message){
        if(NfsLogUtils.isLoggable()){
            Log.e(tag, getMetaInfo() + null2str(message));
        }
    }

    public static void e(String message, Throwable e){
        if(NfsLogUtils.isLoggable()){
            Log.e(TAG, getMetaInfo() + null2str(message), e);
            logThrowable(e);
            if(e.getCause() != null){
                logThrowable(e.getCause());
            }
        }
    }

    public static void e(Throwable e){
        if(NfsLogUtils.isLoggable()){
            logThrowable(e);
            if(e.getCause() != null){
                logThrowable(e.getCause());
            }
        }
    }

    private static String null2str(String string){
        if(string == null){
            return "(null)";
        }
        return string;
    }

    /**
     * Output exception stack trace to log
     *
     * @param e
     */
    private static void logThrowable(Throwable e){
        Log.e(TAG, e.getClass().getName() + ": " + e.getMessage());
        for(StackTraceElement element : e.getStackTrace()){
            Log.e(TAG, "  at " + NfsLogUtils.getMetaInfo(element));
        }
    }

    /**
     * Get meta information of log caller
     *
     * @return [className#methodName:line]
     */
    private static String getMetaInfo(){
        // Get information from stack trace // 0: VM, 1: Thread, 2: LogUtil#getMetaInfo, 3: LogUtil#d Such, 4: Caller
        final StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_INDEX];
        return NfsLogUtils.getMetaInfo(element);
    }

    /**
     * Get class name, method name, and number of lines from stack trace
     *
     * @return [className#methodName:line]
     */
    public static String getMetaInfo(StackTraceElement element){
        // Get class name, method name, line count
        final String fullClassName = element.getClassName();
        final String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        final String methodName = element.getMethodName();
        final int lineNumber = element.getLineNumber();
        //
        final String metaInfo = "[" + (NfsLogUtils.isLoggable() ? simpleClassName : fullClassName) + "#" + methodName + ":" + lineNumber + "]";
        return metaInfo;
    }
}
