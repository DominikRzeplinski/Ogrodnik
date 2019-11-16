package rodzajeRoslin.model;
import java.beans.*;
import dataBase.table.ITable;
import dataBase.connection.*;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import viewHelper.*;
import slowniki.model.*;


public class RodzajeRoslinModel extends SlownikModel
{
	public String GetTableName(){return "RodzajeRoslin";} 
	public RodzajeRoslinModel(){
		super();
	}
}