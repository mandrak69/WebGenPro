/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author D
 */

/**
 * Converts database types to Java class types.
 */
public class SQLTypeMap {
    /**
     * Translates a data type from an integer (java.sql.Types value) to a string
     * that represents the corresponding class.
     * 
     * @param type
     *            The java.sql.Types value to convert to its corresponding class.
     * @return The class that corresponds to the given java.sql.Types
     *         value, or Object.class if the type has no known mapping.
     */
    public static Class<?> toClass(int type) {
        Class<?> result = Object.class;

        switch (type) {
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                result = String.class;
                break;

            case Types.NUMERIC:
            case Types.DECIMAL:
                result = java.math.BigDecimal.class;
                break;

            case Types.BIT:
                result = Boolean.class;
                break;

            case Types.TINYINT:
                result = Byte.class;
                break;

            case Types.SMALLINT:
                result = Short.class;
                break;

            case Types.INTEGER:
                result = Integer.class;
                break;

            case Types.BIGINT:
                result = Long.class;
                break;

            case Types.REAL:
            case Types.FLOAT:
                result = Float.class;
                break;

            case Types.DOUBLE:
                result = Double.class;
                break;

            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                result = Byte[].class;
                break;

            case Types.DATE:
                result = java.sql.Date.class;
                break;

            case Types.TIME:
                result = java.sql.Time.class;
                break;

            case Types.TIMESTAMP:
                result = java.sql.Timestamp.class;
                break;
        }

        return result;
    }
    public Class<?> getJavaType( String schema, String object, String column )
    throws Exception {
        
          DbUtil instanca = DbUtil.getInstance();
        Connection baza = instanca.getConn();
        
        
    String fullName = schema + '.' + object + '.' + column;
    DatabaseMetaData metaData = baza.getMetaData();
    
    ResultSet columnMeta = metaData.getColumns( null, schema, object, column );
    Class<?> javatip = null;

    if( columnMeta.first() ) {
      int dataType = columnMeta.getInt( "DATA_TYPE" );
   javatip = SQLTypeMap.toClass( dataType );
    }
    else {
      throw new Exception( "Unknown database column " + fullName + '.' );
    }

    return javatip;
  }
}
