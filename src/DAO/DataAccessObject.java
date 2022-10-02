package DAO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.logging.Logger;



//import org.aparche.log4jLoggeer;

public abstract class DataAccessObject {

    protected static Connection getConnection(){

        return ConnectionManager.getConnection();
    }

    protected void fillPreParedStatement(PreparedStatement stmt, Object[] params)
        throws SQLException{
        for(int i = 0; i < params.length; i++){
            fillPreParedStatement(stmt,i + 1 , params[i]);
        }
    }


    protected void fillPreParedStatement(
      PreparedStatement stmt, int index, Object param) throws SQLException{

        if(param != null){
            stmt.setObject(index,param);
        }else{
            stmt.setNull(index, Types.OTHER);
        }

    }

    //検索用SQL
    protected String createSQLForFindByPK(){
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT ");
        sb.append(join(getColumns() , ","));
        sb.append(" FROM ");
        sb.append(getTableName());
        sb.append(" WHERE ");
        sb.append(join(getColumns() , "=?  AND "));
        sb.append(" =? ");


        return sb.toString();


    }

    //更新用SQL
    protected String createSQLForUpdate(){
        StringBuilder sb = new StringBuilder();

        String[] columns = getColumns();

        sb.append(" UPDATE ");

        sb.append(getTableName());
        sb.append(" SET ");
        sb.append(join(getColumns(), " " ));
        //sb.append(join(Collections.nCopies(columns.length," ? ").toArray(new String[columns.length]), " , "));
        sb.append(" WHERE ");
        sb.append(getUpColumn());
        //sb.append(join(getColumns(), " =?  AND "));
        //sb.append(" =? ");

        System.out.println(sb);
        return sb.toString();
    }

    //登録用SQL
    protected String createSQLForCreate(){

        String[] columns = getInsert();

        StringBuilder sb = new StringBuilder();

        sb.append(" INSERT INTO ");
        sb.append(getTableName());
        sb.append(" ( ");
        sb.append(join(columns, " , "));
        sb.append(") VALUES (");
        sb.append(join(Collections.nCopies(columns.length," ? ").toArray(new String[columns.length]), " , "));
        sb.append(" ) ");


        return sb.toString();

    }

    //削除用SQL
    protected String createSQLForDelete(){

        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(getTableName());
        sb.append(" WHERE ");
        sb.append(join(getPkColumns() , "=? AND"));
        sb.append(" =? ");

        return sb.toString();

    }

    //重複チャック
    protected String createSQLforPkDuplicateCheck(String pkColumns){

        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT COUNT(*) FROM ");
        sb.append(getTableName());
        sb.append(" WHERE ");
        sb.append(getPkColumns());
        sb.append(" =? ");
        return sb.toString();

    }

    //String配列の各要素に指定の文字列を指定区切り文字
    String[] add(String  string , String delim , String[] arrayofString ){

        String[] addedString = new String[arrayofString.length];
        for(int i = 0; i < arrayofString.length; i++){

            StringBuffer sb = new StringBuffer();
            sb.append(string);
            sb.append(delim);
            sb.append(arrayofString[i]);
            addedString[i] = sb.toString();
        }
        return addedString;


    }

    //String配列の各要素を指定区切り文字で連結します
    String join (String[] arrayofString, String delim){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arrayofString.length; i++){

            sb.append(arrayofString[i]);
            if(i < arrayofString.length -1){
                sb.append(delim);
            }

        }

        return sb.toString();

    }

    //検索処理を実行
    protected Object query(String sql, Object[] params,  Class<?>  clazz){
        Object result = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{

            stmt = getConnection().prepareStatement(sql);
            fillPreParedStatement(stmt,params);
            rs = stmt.executeQuery();
            result = bindBean(rs,clazz);
            return result;
        }catch(SQLException e){

            throw new RuntimeException(e);
        }finally{
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }

            if(rs != null){

                try{
                    rs.close();

                }catch(SQLException e){
                    throw new RuntimeException(e);
                }

                }
            }
        }



        protected int update(String sql, Object[] parms){

            PreparedStatement stmt = null;
            Object result = null;
            ResultSet rs = null;

            try{

                stmt = getConnection().prepareStatement(sql);
                fillPreParedStatement(stmt,parms);
                return stmt.executeUpdate();
            }catch(SQLException e){

                throw new RuntimeException(e);
            }finally{
                if(stmt != null){
                    try{
                        stmt.close();
                    }catch(SQLException e){
                        throw new RuntimeException(e);
                    }
                }

                if(rs != null){

                    try{
                        rs.close();

                    }catch(SQLException e){
                        Logger.getLogger(e.getMessage());
                        ConnectionManager.rollback();
                        throw new RuntimeException(e);
                    }

                    }
                }
            }

        public Object bindBean(ResultSet rs , Class<?> clazz){

            Exception failureCause = null;

            Object result = null;

            try{

                Method[] methodArray = clazz.getMethods();
                ResultSetMetaData rsmd = rs.getMetaData();

                if(rs.next()){
                    result = clazz.newInstance();
                    for(int i = 1; i <= rsmd.getColumnCount() ; i++){
                        for(int j  = 0; j < methodArray.length; j++){
                            if(methodArray[j].getName().equalsIgnoreCase(rsmd.getColumnName(i))){
                                //これで行けるかわかんねえ（笑）
                                if(methodArray[i].getParameterTypes()[0] == Integer.class){
                                    methodArray[j].invoke(result, rs.getInt(i));
                                }else{
                                    methodArray[j].invoke(result, rs.getObject(i));
                                }
                            }
                        }
                    }

                }
                }catch(SQLException e){
                    failureCause = e;
                }catch(IllegalArgumentException e){
                    failureCause = e;
                }catch(IllegalAccessException e){
                    failureCause = e;
                }catch(InvocationTargetException e){
                    failureCause = e;
                } catch (InstantiationException e) {
                    // TODO 自動生成された catch ブロック
                    e.printStackTrace();
                }finally{
                    if(failureCause != null){
                        throw new RuntimeException(failureCause);
                    }
                }
                return result;

        }

        protected boolean PkDuplicateCheck(String[] params){

            int countAll = 0;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try{
                String sql;
                String[] PKColumns = getPkColumns();

                for(int i = 0; i < PKColumns.length; i++){
                    sql =  createSQLforPkDuplicateCheck(PKColumns[i]);
                    stmt = getConnection().prepareStatement(sql);
                    stmt.setString(1, params[i]);
                    rs = stmt.executeQuery();
                    rs.next();
                    countAll += rs.getInt(i);
                }
                //一件でも重複があればTrue
                if(countAll == 0){
                    return false;
                }else{
                    return false;
                }
            }catch(SQLException e){
                throw new RuntimeException();
            }finally{
                if(stmt != null){
                    try{
                        stmt.close();
                    }catch(SQLException e){
                        throw new RuntimeException(e);
                    }
                }
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
            }
        }

        //主キー　カラム名
        public abstract String[] getPkColumns();

        public abstract String[] getColumns();

        public abstract String getUpColumn();

        public abstract String getTableName();

		public abstract String[] getInsert();



}
