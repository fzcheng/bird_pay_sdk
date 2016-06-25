/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/a/Documents/bird_pay_sdk/client/Android/LeyoMegicBirdPaySDK/src/com/legame/paysdk/download/IDownloadFileCallback.aidl
 */
package com.legame.paysdk.download;
public interface IDownloadFileCallback extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.legame.paysdk.download.IDownloadFileCallback
{
private static final java.lang.String DESCRIPTOR = "com.legame.paysdk.download.IDownloadFileCallback";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.legame.paysdk.download.IDownloadFileCallback interface,
 * generating a proxy if needed.
 */
public static com.legame.paysdk.download.IDownloadFileCallback asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.legame.paysdk.download.IDownloadFileCallback))) {
return ((com.legame.paysdk.download.IDownloadFileCallback)iin);
}
return new com.legame.paysdk.download.IDownloadFileCallback.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onError:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.onError(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_OnGetContentLength:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
long _arg1;
_arg1 = data.readLong();
this.OnGetContentLength(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_onDownloadFinish:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.onDownloadFinish(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_OnUserCanceled:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.OnUserCanceled(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_OnBufferUpdate:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
long _arg1;
_arg1 = data.readLong();
this.OnBufferUpdate(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.legame.paysdk.download.IDownloadFileCallback
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
	 * 回调函数，在http下载过程中发生错误后会回调该函数
	 * 
	 * @param indentifier
	 *            下载任务标识
	 * 
	 * @param msg
	 *            错误原因
	 * */
@Override public void onError(java.lang.String indentifier, int msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(indentifier);
_data.writeInt(msg);
mRemote.transact(Stub.TRANSACTION_onError, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 回调函数，通知下载的文件总大小
	 * 
	 * @param size
	 *            文件大小，单位bytes
	 * */
@Override public void OnGetContentLength(java.lang.String indentifier, long size) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(indentifier);
_data.writeLong(size);
mRemote.transact(Stub.TRANSACTION_OnGetContentLength, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 回调函数，通知下载已经完成
	 * */
@Override public void onDownloadFinish(java.lang.String indentifier) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(indentifier);
mRemote.transact(Stub.TRANSACTION_onDownloadFinish, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 回调函数，通知用户的取消操作已经完成
	 * */
@Override public void OnUserCanceled(java.lang.String indentifier) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(indentifier);
mRemote.transact(Stub.TRANSACTION_OnUserCanceled, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 回调函数，通知用户当前缓冲的情况
	 * */
@Override public void OnBufferUpdate(java.lang.String indentifier, long downloadedSize) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(indentifier);
_data.writeLong(downloadedSize);
mRemote.transact(Stub.TRANSACTION_OnBufferUpdate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onError = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_OnGetContentLength = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_onDownloadFinish = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_OnUserCanceled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_OnBufferUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
/**
	 * 回调函数，在http下载过程中发生错误后会回调该函数
	 * 
	 * @param indentifier
	 *            下载任务标识
	 * 
	 * @param msg
	 *            错误原因
	 * */
public void onError(java.lang.String indentifier, int msg) throws android.os.RemoteException;
/**
	 * 回调函数，通知下载的文件总大小
	 * 
	 * @param size
	 *            文件大小，单位bytes
	 * */
public void OnGetContentLength(java.lang.String indentifier, long size) throws android.os.RemoteException;
/**
	 * 回调函数，通知下载已经完成
	 * */
public void onDownloadFinish(java.lang.String indentifier) throws android.os.RemoteException;
/**
	 * 回调函数，通知用户的取消操作已经完成
	 * */
public void OnUserCanceled(java.lang.String indentifier) throws android.os.RemoteException;
/**
	 * 回调函数，通知用户当前缓冲的情况
	 * */
public void OnBufferUpdate(java.lang.String indentifier, long downloadedSize) throws android.os.RemoteException;
}
