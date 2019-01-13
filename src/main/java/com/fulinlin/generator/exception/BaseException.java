package com.fulinlin.generator.exception;

/**
 * @program: database-doc
 * @author: fulin
 * @create: 2019-01-10 10:15
 **/
public class BaseException extends RuntimeException{
	public BaseException(Object obj)
	{
		super(obj.toString());
	}
}
