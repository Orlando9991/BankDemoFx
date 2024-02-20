package pt.rumos.repository.database;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import pt.rumos.contract.IDebitCardRepository;
import pt.rumos.database.Database;
import pt.rumos.model.DebitCard;
import pt.rumos.repository.database.commands.DbCommands;
import pt.rumos.repository.database.convert.DbConvert;

public class DebitCardRepository implements IDebitCardRepository {

	private Database database = Database.getInstance();
	
	@Override
	public Optional<DebitCard> save(DebitCard debitCard) {
		if(debitCard.getId()==null) {
			return saveObj(debitCard);
		}
		else {
			return updateObj(debitCard);
		}
	}

	@Override
	public Optional<DebitCard> saveObj(DebitCard debitCard) {
		String command = DbCommands.insertDebitCardCommand(debitCard);
		
		if(database.executeCommand(command)){
			return Optional.of(debitCard);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<DebitCard> updateObj(DebitCard debitCard) {
		String command = DbCommands.updateDebitCardCommand(debitCard);
		
		if(database.executeCommand(command)){
			return Optional.of(debitCard);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<DebitCard> findById(Long id) {
		String query = DbCommands.findDebitCardQuery(id);
		HashMap<String,Object> rs = database.executeQuerySingleRow(query);
		
		return DbConvert.extractDebitCardObject(rs);
	}

	@Override
	public void deleteById(Long id) {
		String query = DbCommands.deleteDebitCardCommand(id);
		database.executeCommand(query);
	}

	@Override
	public List<DebitCard> findAll() {
		String query = DbCommands.findAllDebitCardQuery();
		List<HashMap<String,Object>> rs = database.executeQueryMultiRow(query);
		
		return DbConvert.extractDebitCardObjectList(rs);
	}

}
