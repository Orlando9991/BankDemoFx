module Repository {
	exports pt.rumos.repository.array;
	exports pt.rumos.repository.database;
	exports pt.rumos.contract;
	
	requires transitive Model;
	requires Exceptions;
	requires Database;
	requires java.sql;
	
}