package sql;

public class SqlShoppingData {
	public static StringBuilder InsertShoppingData(){
		StringBuilder sb = new StringBuilder();

		sb.append("insert ");
		sb.append("into shopping( ");
		sb.append("  users_id");
		sb.append("  , amount");
		sb.append("  , shopping_at");
		sb.append("  , create_at");
		sb.append("  , update_at");
		sb.append(") ");
		sb.append("values ( ");
		sb.append("  ?");
		sb.append("  , ?");
		sb.append("  , ?");
		sb.append("  , CURRENT_TIMESTAMP");
		sb.append("  , CURRENT_TIMESTAMP");
		sb.append(") returning id;") ;

		return sb;
	}

	public static StringBuilder SelectMonthlyShoppingData(){
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("  id ");
		sb.append("  , amount ");
		sb.append("  , shopping_at  ");
		sb.append("from ");
		sb.append("  shopping  ");
		sb.append("where ");
		sb.append("  users_id = ?  ");
		sb.append("  and to_char(shopping_at, 'YYYY/MM') = to_char(DATE(?), 'YYYY/MM') ");
		sb.append("  order by shopping_at desc, id desc; ");

		return sb;
	}

}
