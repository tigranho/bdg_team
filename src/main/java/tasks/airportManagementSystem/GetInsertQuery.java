package tasks.airportManagementSystem;

/**
 * @author Tatevik Mirzoyan
 * Created on 18-Sep-20
 */
public class GetInsertQuery {
    public static String getInsertQueryFromStringArray(String[] paramArr, String tableName) {
        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("INSERT INTO ");
        insertQuery.append(tableName);
        insertQuery.append(" VALUES (");
        for (int i = 0; i < paramArr.length; i++) {
            insertQuery.append("'").append(paramArr[i]).append("'");
            if (i == paramArr.length - 1) {
                insertQuery.append(")");
            } else {
                insertQuery.append(",");
            }
        }
        return insertQuery.toString();
    }
}
