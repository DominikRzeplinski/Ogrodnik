package RodzajeRoslin.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import ViewHelper.*;
import Slowniki.*;

public class RodzajeRoslinModel extends Slowniki
{
	private String String GetTableName(){return "RodzajeRoslin";} 
	public RodzajeRoslinModel(){
		super();
		
	}
}