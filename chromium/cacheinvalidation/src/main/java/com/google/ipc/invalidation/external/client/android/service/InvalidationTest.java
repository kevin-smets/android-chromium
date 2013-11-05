/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: src/java/com/google/ipc/invalidation/testing/android/InvalidationTest.aidl
 */
package com.google.ipc.invalidation.testing.android;
public interface InvalidationTest extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.google.ipc.invalidation.testing.android.InvalidationTest
{
private static final java.lang.String DESCRIPTOR = "com.google.ipc.invalidation.testing.android.InvalidationTest";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.google.ipc.invalidation.testing.android.InvalidationTest interface,
 * generating a proxy if needed.
 */
public static com.google.ipc.invalidation.testing.android.InvalidationTest asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.google.ipc.invalidation.testing.android.InvalidationTest))) {
return ((com.google.ipc.invalidation.testing.android.InvalidationTest)iin);
}
return new com.google.ipc.invalidation.testing.android.InvalidationTest.Stub.Proxy(obj);
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
case TRANSACTION_setCapture:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
boolean _arg1;
_arg1 = (0!=data.readInt());
this.setCapture(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getRequests:
{
data.enforceInterface(DESCRIPTOR);
android.os.Bundle[] _result = this.getRequests();
reply.writeNoException();
reply.writeTypedArray(_result, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
return true;
}
case TRANSACTION_getEvents:
{
data.enforceInterface(DESCRIPTOR);
android.os.Bundle[] _result = this.getEvents();
reply.writeNoException();
reply.writeTypedArray(_result, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
return true;
}
case TRANSACTION_sendEvent:
{
data.enforceInterface(DESCRIPTOR);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.sendEvent(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_reset:
{
data.enforceInterface(DESCRIPTOR);
this.reset();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.google.ipc.invalidation.testing.android.InvalidationTest
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
   * Used to set whether the invalidation test service should store incoming
   * actions and outgoing events respectively by {@link getActionEvents()}
   * and {@link getEventIntents()}.  If {@code false}, they will be processed
   * and forgotten.
   */
@Override public void setCapture(boolean captureActions, boolean captureEvents) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((captureActions)?(1):(0)));
_data.writeInt(((captureEvents)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_setCapture, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
   * Returns an array of bundle containing the set of invalidation requests
   * received by the test service since the last call to this method.
   */
@Override public android.os.Bundle[] getRequests() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getRequests, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArray(android.os.Bundle.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Returns an array of intents containing the set of invalidation event
   * bundles sent by the test service since the last call to this method.
   */
@Override public android.os.Bundle[] getEvents() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getEvents, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArray(android.os.Bundle.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
   * Instructs the test service to send an event back to the client to support
   * testing of listener functionality.
   */
@Override public void sendEvent(android.os.Bundle eventBundle) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((eventBundle!=null)) {
_data.writeInt(1);
eventBundle.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_sendEvent, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
   * Reset all state for the invalidation test service.  This will clear all
   * current clients and drop and disable any captured action or event bundles.
   */
@Override public void reset() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_reset, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_setCapture = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getRequests = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getEvents = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_sendEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_reset = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
/**
   * Used to set whether the invalidation test service should store incoming
   * actions and outgoing events respectively by {@link getActionEvents()}
   * and {@link getEventIntents()}.  If {@code false}, they will be processed
   * and forgotten.
   */
public void setCapture(boolean captureActions, boolean captureEvents) throws android.os.RemoteException;
/**
   * Returns an array of bundle containing the set of invalidation requests
   * received by the test service since the last call to this method.
   */
public android.os.Bundle[] getRequests() throws android.os.RemoteException;
/**
   * Returns an array of intents containing the set of invalidation event
   * bundles sent by the test service since the last call to this method.
   */
public android.os.Bundle[] getEvents() throws android.os.RemoteException;
/**
   * Instructs the test service to send an event back to the client to support
   * testing of listener functionality.
   */
public void sendEvent(android.os.Bundle eventBundle) throws android.os.RemoteException;
/**
   * Reset all state for the invalidation test service.  This will clear all
   * current clients and drop and disable any captured action or event bundles.
   */
public void reset() throws android.os.RemoteException;
}
