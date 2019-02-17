/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonToObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONValue;

/**
 *
 * @author D
 */
public class getJSONFromR {

    public static  String getJSONFromResultSet(ResultSet rs, String keyName) {
        Map json = new HashMap();
        List list = new ArrayList();
        if (rs != null) {

            try {
                ResultSetMetaData metaData = rs.getMetaData();
                while (rs.next()) {
                    Map<String, Object> columnMap = new HashMap<String, Object>();
                    for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                        String val = rs.getString(metaData.getColumnName(columnIndex));
                        String key = metaData.getColumnLabel(columnIndex);
                        if (val == null) {
                            columnMap.put(key, "");
                            
                        } else if (val == "") {
                            columnMap.put(key, "");
                        } else if (val.chars().allMatch(Character::isDigit)) {
                            System.out.println("ovdisam"+val);
                            columnMap.put(key, Integer.parseInt(val));
                        } else {
                            columnMap.put(key, val);
                        }
                    }
                    list.add(columnMap);
                }
            }
                catch (SQLException ex) {
            Logger.getLogger(getJSONFromR.class.getName()).log(Level.SEVERE, null, ex);
        }
            

            json.put(keyName, list);
        }
        return JSONValue.toJSONString(json);
    }
}
