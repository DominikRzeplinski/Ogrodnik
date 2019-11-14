package PoraSadzenia.model;
import java.beans.*;
import DataBase.Table.ITable;
import DataBase.Connection.*;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import ViewHelper.*;
import Slowniki.model.*;


public class PoraSadzeniaModel extends SlownikModel
{
	public String GetTableName(){return "PoraSadzenia";} 
	public PoraSadzeniaModel(){
		super();
	}
}