/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package org.puremvc.java.interfaces;

/**
 * The interface definition for a PureMVC Facade.
 * 
 * <P>
 * The Facade Pattern suggests providing a single class to act as a central
 * point of communication for a subsystem.
 * </P>
 * 
 * <P>
 * In PureMVC, the Facade acts as an interface between the core MVC actors
 * (Model, View, Controller) and the rest of your application.
 * </P>
 * 
 * @see org.puremvc.java.interfaces.IModel IModel
 * @see org.puremvc.java.interfaces.IView IView
 * @see org.puremvc.java.interfaces.IController IController
 * @see org.puremvc.java.interfaces.ICommand ICommand
 * @see org.puremvc.java.interfaces.INotification INotification
 */
public interface IFacade extends INotifier
{
	public void notifyObservers( INotification note );
	public void registerProxy( IProxy proxy );
	public IProxy retrieveProxy( String proxyName );
	public IProxy removeProxy( String proxyName );
	public boolean hasProxy( String proxyName );
	public void registerCommand( String noteName, ICommand commandClassRef );
	public void removeCommand( String notificationName);
	public boolean hasCommand( String notificationName );
	public void registerMediator( IMediator mediator );
	public IMediator retrieveMediator( String mediatorName );
	public boolean hasMediator( String mediatorName );
	public IMediator removeMediator( String mediatorName );
}
