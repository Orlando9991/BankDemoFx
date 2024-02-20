package pt.rumos.repository.database;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.ICreditCardRepository;
import pt.rumos.database.Database;
import pt.rumos.model.CreditCard;
import pt.rumos.repository.database.commands.DbCommands;
import pt.rumos.repository.database.convert.DbConvert;

public class CreditCardRepository implements ICreditCardRepository {
	
	private Database database = Database.getInstance();
	
	@Override
	public Optional<CreditCard> save(CreditCard creditCard) {
		if(creditCard.getId()==null) {
			return saveObj(creditCard);
		}
		else {
			return updateObj(creditCard);
		}
	}
	
	@Override
	public Optional<CreditCard> saveObj(CreditCard creditCard) {
		String command = DbCommands.insertCreditCardCommand(creditCard);
		
		if(database.executeCommand(command)){
			return Optional.of(creditCard);
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<CreditCard> updateObj(CreditCard creditCard) {
		String command = DbCommands.updateCreditCardCommand(creditCard);
		
		if(database.executeCommand(command)){
			return Optional.of(creditCard);
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<CreditCard> findById(Long id) {
		String query = DbCommands.findCreditCardQuery(id);
		HashMap<String,Object> rs = database.executeQuerySingleRow(query);
		
		return DbConvert.extractCreditCardObject(rs);
	}
	
	@Override
	public void deleteById(Long id) {
		String query = DbCommands.deleteCreditCardCommand(id);
		database.executeCommand(query);
	}
	
	@Override
	public List<CreditCard> findAll() {
		String query = DbCommands.findAllCreditCardQuery();
		List<HashMap<String,Object>> rs = database.executeQueryMultiRow(query);
		
		return DbConvert.extractCreditCardObjectList(rs);
	}
	
}
