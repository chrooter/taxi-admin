/*
	Copyright 2011 Open Code Ltd.
    http://www.o-code.ru
 
    $Id$
*/
package ru.dreamjteam.db;

import ru.dreamjteam.db.DbAccessException;

/**
 * @author abolmasov (18.10.2011 10:54:36)
 * @version $Revision$
 */
public class EntityNotFoundException extends DbAccessException {
	public EntityNotFoundException(Throwable cause) {
		super(cause);
	}
}
