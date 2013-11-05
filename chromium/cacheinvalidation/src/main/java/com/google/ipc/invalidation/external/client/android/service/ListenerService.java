/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: src/java/com/google/ipc/invalidation/external/client/android/service/ListenerService.aidl
 */
package com.google.ipc.invalidation.external.client.android.service;
/**
 * Defines the bound service interface for an Invalidation listener service.  The service 
 * exposes an intent-like model with a single {@link #handleEvent} entry point that packages
 * the event action and its parameters into a {@link Bundle} but uses a synchronous calling
 * model where a response bundle is also returned to the service containing status and/or
 * <p>
 * Having a single entry point (as compared to a interface method per action with explicit
 * parameters) will make it easier to evolve the interface over time.   New event types or
 * additional optional parameters can be added in subsequent versions without changing the
 * service interface in ways that would be incompatible with existing clients.  This is
 * important because the listener services will be packaged (and updated) independently from
 * the invalidation service.
 * <p>
 * The synchronous nature of the interface (having a response object that can indicate success
 * or failure of event handling) is important to support reliable events.  If the service
 * sends a registration request, it's important to know that it has been successfully received
 * by the local invalidation service.
 *
 * The invalidation service will bind to the invalidation listener using an intent that
 * contains the {@link Event.LISTENER} action along with the explicit listener class name
 * that was provided to {@code AndroidClientFactory.create()}.
 */
public interface ListenerService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.google.ipc.invalidation.external.client.android.service.ListenerService
{
private static final java.lang.String DESCRIPTOR = "com.google.ipc.invalidation.external.client.android.service.ListenerService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.google.ipc.invalidation.external.client.android.service.ListenerService interface,
 * generating a proxy if needed.
 */
public static com.google.ipc.invalidation.external.client.android.service.ListenerService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.google.ipc.invalidation.external.client.android.service.ListenerService))) {
return ((com.google.ipc.invalidation.external.client.android.service.ListenerService)iin);
}
return new com.google.ipc.invalidation.external.client.android.service.ListenerService.Stub.Proxy(obj);
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
case TRANSACTION_handleEvent:
{
data.enforceInterface(DESCRIPTOR);
android.os.Bundle _arg0;
if ((0!=data.readInt())) {
_arg0 = android.os.Bundle.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
android.os.Bundle _arg1;
_arg1 = new android.os.Bundle();
this.handleEvent(_arg0, _arg1);
reply.writeNoException();
if ((_arg1!=null)) {
reply.writeInt(1);
_arg1.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.google.ipc.invalidation.external.client.android.service.ListenerService
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
   * Sends a request to the invalidation service and retrieves the response containing any
   * return data or status/error information.   The {@code action} parameter in the request
   * bundle will indicate the type of request to be executed and the request parameters will
   * also be stored in the bundle.   The service will acknowledge successful processing of
   * the request by returning a response bundle that contains a {@code status} parameter
   * indicating the success or failure of the request.  If successful, any other output
   * parameters will be included as values in the response bundle.  On failure, additional
   * error or debug information will be included in the response bundle.
   *
   * @see Event
   * @see Response
   */
@Override public void handleEvent(android.os.Bundle event, android.os.Bundle response) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((event!=null)) {
_data.writeInt(1);
event.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_handleEvent, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
response.readFromParcel(_reply);
}
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_handleEvent = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
   * Sends a request to the invalidation service and retrieves the response containing any
   * return data or status/error information.   The {@code action} parameter in the request
   * bundle will indicate the type of request to be executed and the request parameters will
   * also be stored in the bundle.   The service will acknowledge successful processing of
   * the request by returning a response bundle that contains a {@code status} parameter
   * indicating the success or failure of the request.  If successful, any other output
   * parameters will be included as values in the response bundle.  On failure, additional
   * error or debug information will be included in the response bundle.
   *
   * @see Event
   * @see Response
   */
public void handleEvent(android.os.Bundle event, android.os.Bundle response) throws android.os.RemoteException;
}
