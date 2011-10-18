/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id$
*/
package ru.dreamjteam.db;

/**
 * @author abolmasov (18.10.2011 11:02:31)
 * @version $Revision$
 */
public class DuplicateEntityException extends DbAccessException {
	public DuplicateEntityException(Throwable cause) {
		super(cause);
	}
}
