module Service {
	exports pt.rumos.service;
	exports pt.rumos.contract_serv;
	
	requires Repository;
	requires transitive Model;
	requires Exceptions;
}