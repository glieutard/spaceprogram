/**
 * 
 */
/**
 * 
 */
package com.spaceprogram.tool.config;

import java.sql.Types;

import org.hibernate.dialect.SQLServer2012Dialect;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author GLieutard
 *
 */
public class SqlServer2012Dialect extends SQLServer2012Dialect {
	
	public SqlServer2012Dialect () {
		registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());
	}

}
